package generator;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public final class Generator extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable {

    private int maxCount = 200; // maksymalna liczba iteracji
    private int fractal = 0; // aktualnie rysowany fraktal
    private boolean smooth = false; // wygładzanie krawędzi
    private boolean toDrag = false; // mozliwosc przesuwania
    private Color[][] colors; // paleta kolorow
    private int pal = 0; // aktualna paleta kolorow
    // zmienne do rysowania zbioru Juli
    private boolean julia = false;
    private double juliaX =  0.56667, juliaY = -0.5;
    private boolean toDrawAll = true;
    // obecnie widoczne polozenie okna
    private double viewX = 0.0;
    private double viewY = 0.0;
    private double zoom = 1.0;
    private Image image; // zmienna potrzebna do przerysowywania fraktali
    private Graphics graphics;
    private int width, height; // aktualna szerokosc i wysokosc okna
    private Image bufferImage;
    private Graphics bufferGraphics;
    private Thread thread = null;
    private int mouseX, mouseY; // pozycja myszki podczas nacisniecia przycisku
    private int dragX, dragY; // pozycja myszki podczas przeciagania
    private static final int[][][] colpal = { // paleta kolorow, gotowiec
        {{12, 0, 10, 20}, {12, 50, 100, 240}, {12, 20, 3, 26}, {12, 230, 60, 20},
            {12, 25, 10, 9}, {12, 230, 170, 0}, {12, 20, 40, 10}, {12, 0, 100, 0},
            {12, 5, 10, 10}, {12, 210, 70, 30}, {12, 90, 0, 50}, {12, 180, 90, 120},
            {12, 0, 20, 40}, {12, 30, 70, 200}},
        {{10, 70, 0, 20}, {10, 100, 0, 100}, {14, 255, 0, 0}, {10, 255, 200, 0}},
        {{8, 40, 70, 10}, {9, 40, 170, 10}, {6, 100, 255, 70}, {8, 255, 255, 255}},
        {{12, 0, 0, 64}, {12, 0, 0, 255}, {10, 0, 255, 255}, {12, 128, 255, 255}, {14, 64, 128, 255}},
        {{16, 0, 0, 0}, {32, 255, 255, 255}},};
    private static final int[][] rows = {
        {0, 16, 8}, {8, 16, 8}, {4, 16, 4}, {12, 16, 4},
        {2, 16, 2}, {10, 16, 2}, {6, 16, 2}, {14, 16, 2},
        {1, 16, 1}, {9, 16, 1}, {5, 16, 1}, {13, 16, 1},
        {3, 16, 1}, {11, 16, 1}, {7, 16, 1}, {15, 16, 1},};

    //inicjalizacja listenerow i palety kolorow
    @Override
    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        colors = new Color[colpal.length][];
        for (int p = 0; p < colpal.length; p++) {
            int n = 0;
            for (int i = 0; i < colpal[p].length; i++) {
                n += colpal[p][i][0];
            }

            colors[p] = new Color[n];
            n = 0;
            for (int i = 0; i < colpal[p].length; i++) {
                int[] c1 = colpal[p][i];
                int[] c2 = colpal[p][(i + 1) % colpal[p].length];
                for (int j = 0; j < c1[0]; j++) {
                    colors[p][n + j] = new Color(
                            (c1[1] * (c1[0] - 1 - j) + c2[1] * j) / (c1[0] - 1),
                            (c1[2] * (c1[0] - 1 - j) + c2[2] * j) / (c1[0] - 1),
                            (c1[3] * (c1[0] - 1 - j) + c2[3] * j) / (c1[0] - 1));
                }
                n += c1[0];
            }
        }
        thread = null;
    }

    //start appletu = przerysowanie
    @Override
    public void start() {
        redraw();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        while (thread != null) {
            while (draw());
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private void redraw() {
        toDrawAll = true;
        if (thread != null && thread.isAlive()) { //jesli nie jest nullem i zyje
            thread.interrupt(); //przerwanie watku
        } else {
            thread = new Thread(this); //tworzymy nowy watek
            thread.setPriority(Thread.MIN_PRIORITY); //ustawiamy mu priorytet
            thread.start(); //startujemy
        }
    }

    private synchronized Color getColor(int i) {
        int palSize = colors[pal].length;
        if (i + palSize < 0) {
            i = Math.max(0, i + palSize);
        }
        return colors[pal][(i + palSize) % palSize];
    }

    private synchronized boolean toDraw() {
        boolean f = toDrawAll;
        toDrawAll = false;
        return f;
    }

    private boolean draw() {
        if (!toDraw()) {
            return false;
        }
        Dimension size = getSize();

        if (image == null || size.width != width || size.height != height) {
            width = size.width;
            height = size.height;
            image = createImage(width, height);
            graphics = image.getGraphics();
            bufferImage = createImage(width, height);
            bufferGraphics = bufferImage.getGraphics();
        }

        double r = zoom / Math.min(width, height);
        double sx = width > height ? 2.0 * r * (width - height) : 0.0;
        double sy = height > width ? 2.0 * r * (height - width) : 0.0;
        for (int y = 0; y < height + 4; y += 8) {
            if (Thread.interrupted() && toDrawAll) {
                return true;
            }
            for (int x = 0; x < width + 4; x += 8) {
                double dx = 4.0 * (x * r + viewX) - 2.0 - sx;
                double dy = -4.0 * (y * r + viewY) + 2.0 + sy;
                Color color = color(dx, dy);
                graphics.setColor(color);
                graphics.fillRect(x - 4, y - 4, 8, 8);
            }
        }
        repaint();
        for (int row = 0; row < rows.length; row++) {
            for (int y = rows[row][0]; y < height; y += rows[row][1]) {
                if (Thread.interrupted() && toDrawAll) {
                    return true;
                }
                for (int x = 0; x < width; x++) {
                    double dx = 4.0 * (x * r + viewX) - 2.0 - sx;
                    double dy = -4.0 * (y * r + viewY) + 2.0 + sy;
                    Color color = color(dx, dy);
                    graphics.setColor(color);
                    graphics.fillRect(x, y - rows[row][2] / 2, 1, rows[row][2]);
                }
            }
            repaint();
        }
        return toDraw();
    }

    private Color color(double x, double y) {
        int count = julia ? zfun(x, y, juliaX, juliaY) : zfun(0.0, 0.0, x, y);
        Color color = getColor(count / 256);
        if (smooth) {
            Color color2 = getColor(count / 256 - 1);
            int k1 = count % 256;
            int k2 = 255 - k1;
            int red = (k1 * color.getRed() + k2 * color2.getRed()) / 255;
            int green = (k1 * color.getGreen() + k2 * color2.getGreen()) / 255;
            int blue = (k1 * color.getBlue() + k2 * color2.getBlue()) / 255;
            color = new Color(red, green, blue);
        }
        return color;
    }

    //wybor funkcji do rysowania, zastosowany switch w celu latwego dodawania kolejnych funkcji
    private int zfun(double zr, double zi, double cr, double ci) {
        switch (fractal) {
            case 1:
                return phoenix(zr, zi, cr, ci);
            default:
                return mandel(zr, zi, cr, ci);
        }
    }

    // Mandelbrot
    private int mandel(double zr, double zi, double cr, double ci) {
        double pr = zr * zr, pi = zi * zi;
        double zm = 0.0;
        int count = 0;
        while (pr + pi < 4.0 && count < maxCount) {
            zm = pr + pi;
            zi = 2.0 * zr * zi + ci;
            zr = pr - pi + cr;
            pr = zr * zr;
            pi = zi * zi;
            count++;
        }
        if (count == 0 || count == maxCount) {
            return 0;
        }
        zm += 0.000000001;
        return 256 * count + (smooth ? (int) (255.0 * Math.log(4.0 / zm) / Math.log((pr + pi) / zm)) : 0);
    }

    //Phoenix
    private int phoenix(double zr, double zi, double cr, double ci) {
        double pr = zr * zr, pi = zi * zi;
        double sr = 0.0, si = 0.0;
        double zm = 0.0;
        int count = 0;
        while (pr + pi < 4.0 && count < maxCount) {
            zm = pr + pi;
            pr = pr - pi + ci * sr + cr;
            pi = 2.0 * zr * zi + ci * si;
            sr = zr;
            si = zi;
            zr = pr;
            zi = pi;
            pr = zr * zr;
            pi = zi * zi;
            count++;
        }
        if (count == 0 || count == maxCount) {
            return 0;
        }
        zm += 0.000000001;
        return 256 * count + (smooth ? (int) (255.0 * Math.log(4.0 / zm) / Math.log((pr + pi) / zm)) : 0);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (image == null) {
            return;
        }
        Dimension size = getSize();
        if (size.width != width || size.height != height) {
            redraw();
            return;
        }

        bufferGraphics.drawImage(image, 0, 0, null);
        g.drawImage(bufferImage, 0, 0, null);
        if (toDrag) {
            g.setColor(Color.black);
            g.setXORMode(Color.white);
            int x = Math.min(mouseX, dragX);
            int y = Math.min(mouseY, dragY);
            double w = mouseX + dragX - 2 * x;
            double h = mouseY + dragY - 2 * y;
            double r = Math.max(w / width, h / height);
            g.drawRect(x, y, (int) (width * r), (int) (height * r));
        }
    }

    //zmiana licznika (dokladnosci z jaka rysowane sa fraktale)
    public void changeCount(int c) {
        maxCount = c;
        redraw();
    }

    //rysujemy fraktal od nowa ustawiajac zoom na 1. i X i Y
    public void escape() {
        viewX = viewY = 0.0;
        zoom = 1.0;
        redraw();
    }

    //zmiana rysowanego fraktala
    public void space() {
        fractal = (fractal + 1) % 2;
        viewX = viewY = 0.0;
        zoom = 1.0;
        julia = false;
        if (fractal == 0) {
            juliaX = 0.56667;
            juliaY = -0.5;
            julia = true;
        }
        redraw();
    }

    //powiekszenie
    public void zoomIN() {
        viewX += 0.25 * zoom;
        viewY += 0.25 * zoom;
        zoom *= 0.5;
        redraw();
    }

    //pomniejszenie
    public void zoomOUT() {
        viewX -= 0.5 * zoom;
        viewY -= 0.5 * zoom;
        zoom *= 2.0;
        redraw();

    }

    //rysowanie zbioru Julli
    public void setJulia() {
        julia = !julia;
        redraw(); 
    }

    //wygladzanie krawedzi
    public void smoothing() {
        smooth = !smooth;
        redraw(); 
    }

    //nastepna paleta kolorow
    public void nextPalette() {
        pal = (pal + 1) % colors.length;
        redraw();
    }

    //przerysowywanie fraktala przy zaznaczeniu mysza
    public void zoomMouse(int x, int y, double r) {
        int mx = Math.min(x, mouseX);
        int my = Math.min(y, mouseY);
        double sx = width > height ? width - height : 0.0;
        double sy = height > width ? height - width : 0.0;
        viewX += r * (mx - sx / 2.0);
        viewY += r * (my - sy / 2.0);
        double w = x + mouseX - 2 * mx;
        double h = y + mouseY - 2 * my;
        double zoom0 = zoom;
        zoom *= Math.max(w / width, h / height);
        viewX += r / zoom0 * zoom * sx / 2.0;
        viewY += r / zoom0 * zoom * sy / 2.0;
        redraw();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = dragX = e.getX();
        mouseY = dragY = e.getY();
        toDrag = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toDrag = false;
        int x = e.getX();
        int y = e.getY();
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) { // puszczenie lewego przycisku myszy
            double r = zoom / Math.min(width, height);
            if (x != mouseX || y != mouseY) { //powiekszenie obrazu
                zoomMouse(x, y, r);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) { // trzymanie lewego przycisku myszy, czyli rysowanie prostokata
            dragX = e.getX();
            dragY = e.getY();
            repaint(); // tylko przerysowanie
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // nowy
            escape();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // nastepny
            space();
        } else if (e.getKeyCode() == KeyEvent.VK_I) { // zoom in
            zoomIN();
        } else if (e.getKeyCode() == KeyEvent.VK_O) { // zoom out
            zoomOUT();
        } else if (e.getKeyCode() == KeyEvent.VK_J) { // julia
            setJulia();
        } else if (e.getKeyCode() == KeyEvent.VK_P) { // nastepny kolor
            nextPalette();
        } else if (e.getKeyCode() == KeyEvent.VK_S) { // wygladz
            smoothing();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    } //not used

    @Override
    public void keyTyped(KeyEvent e) {
    } // not used

    @Override
    public void mouseClicked(MouseEvent e) {
    } // not used

    @Override
    public void mouseEntered(MouseEvent e) {
    } // not used

    @Override
    public void mouseExited(MouseEvent e) {
    } // not used

    @Override
    public void mouseMoved(MouseEvent e) {
    } //not used
}