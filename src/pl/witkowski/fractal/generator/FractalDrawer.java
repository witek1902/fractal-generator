package pl.witkowski.fractal.generator;

import pl.witkowski.fractal.generator.function.FunctionProvider;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public final class FractalDrawer extends Applet implements MouseListener, MouseMotionListener, Runnable {

    private Thread thread;

    private int maxCount = 200;
    private int actualFractal = 0;
    private boolean shouldSmooth;
    private boolean dragAvailable;
    private Color[][] colors;
    private int actualPalette = 0;

    private boolean isJuliaSelected;
    private double juliaX = 0.56667;
    private double juliaY = -0.5;
    private boolean shouldDrawAll = true;

    private Image image;
    private Graphics graphics;
    private Image bufferImage;
    private Graphics bufferGraphics;

    private double actualWindowPositionX = 0.0;
    private double actualWindowPositionY = 0.0;
    private double zoom = 1.0;
    private int actualWindowWidth, actualWindowHeight;
    private int clickMousePositionX, clickMousePositionY;
    private int dragMousePositionX, dragMousePositionY;
    private static final int[][] rows = {
            {0, 16, 8}, {8, 16, 8}, {4, 16, 4}, {12, 16, 4},
            {2, 16, 2}, {10, 16, 2}, {6, 16, 2}, {14, 16, 2},
            {1, 16, 1}, {9, 16, 1}, {5, 16, 1}, {13, 16, 1},
            {3, 16, 1}, {11, 16, 1}, {7, 16, 1}, {15, 16, 1}
    };


    @Override
    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);

        colors = ColorPaletteInitializer.init();
        thread = null;
    }

    @Override
    public void start() {
        redraw();
    }

    @Override
    public void run() {
        while (thread != null) {
            while (draw()) ;
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void redraw() {
        shouldDrawAll = true;
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        } else {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }
    }

    private synchronized Color getColor(int i) {
        int palSize = colors[actualPalette].length;
        if (i + palSize < 0) {
            i = Math.max(0, i + palSize);
        }
        return colors[actualPalette][(i + palSize) % palSize];
    }

    private synchronized boolean toDraw() {
        boolean f = shouldDrawAll;
        shouldDrawAll = false;
        return f;
    }

    private boolean draw() {
        if (!toDraw()) {
            return false;
        }
        Dimension size = getSize();

        if (image == null || size.width != actualWindowWidth || size.height != actualWindowHeight) {
            actualWindowWidth = size.width;
            actualWindowHeight = size.height;
            image = createImage(actualWindowWidth, actualWindowHeight);
            graphics = image.getGraphics();
            bufferImage = createImage(actualWindowWidth, actualWindowHeight);
            bufferGraphics = bufferImage.getGraphics();
        }

        double r = zoom / Math.min(actualWindowWidth, actualWindowHeight);
        double sx = actualWindowWidth > actualWindowHeight ? 2.0 * r * (actualWindowWidth - actualWindowHeight) : 0.0;
        double sy = actualWindowHeight > actualWindowWidth ? 2.0 * r * (actualWindowHeight - actualWindowWidth) : 0.0;
        for (int y = 0; y < actualWindowHeight + 4; y += 8) {
            if (Thread.interrupted() && shouldDrawAll) {
                return true;
            }
            for (int x = 0; x < actualWindowWidth + 4; x += 8) {
                double dx = 4.0 * (x * r + actualWindowPositionX) - 2.0 - sx;
                double dy = -4.0 * (y * r + actualWindowPositionY) + 2.0 + sy;
                Color color = getColorForPosition(dx, dy);
                graphics.setColor(color);
                graphics.fillRect(x - 4, y - 4, 8, 8);
            }
        }
        repaint();

        for (int row = 0; row < rows.length; row++) {
            for (int y = rows[row][0]; y < actualWindowHeight; y += rows[row][1]) {
                if (Thread.interrupted() && shouldDrawAll) {
                    return true;
                }
                for (int x = 0; x < actualWindowWidth; x++) {
                    double dx = 4.0 * (x * r + actualWindowPositionX) - 2.0 - sx;
                    double dy = -4.0 * (y * r + actualWindowPositionY) + 2.0 + sy;
                    Color color = getColorForPosition(dx, dy);
                    graphics.setColor(color);
                    graphics.fillRect(x, y - rows[row][2] / 2, 1, rows[row][2]);
                }
            }
            repaint();
        }
        return toDraw();
    }

    private Color getColorForPosition(double x, double y) {
        int functionValue = isJuliaSelected
                ? getFunctionValue(x, y, juliaX, juliaY)
                : getFunctionValue(0.0, 0.0, x, y);
        Color color = getColor(functionValue / 256);
        return shouldSmooth
                ? ColorSmoother.smooth(color, getColor(functionValue / 256 - 1), functionValue)
                : color;
    }

    private int getFunctionValue(double zr, double zi, double cr, double ci) {
        return FunctionProvider.get(actualFractal, maxCount, shouldSmooth).calc(zr, zi, cr, ci);
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
        if (sizeIsNotEqual(size)) {
            redraw();
            return;
        }

        bufferGraphics.drawImage(image, 0, 0, null);
        g.drawImage(bufferImage, 0, 0, null);
        if (dragAvailable) {
            g.setColor(Color.black);
            g.setXORMode(Color.white);
            int x = Math.min(clickMousePositionX, dragMousePositionX);
            int y = Math.min(clickMousePositionY, dragMousePositionY);
            double w = clickMousePositionX + dragMousePositionX - 2 * x;
            double h = clickMousePositionY + dragMousePositionY - 2 * y;
            double r = Math.max(w / actualWindowWidth, h / actualWindowHeight);
            g.drawRect(x, y, (int) (actualWindowWidth * r), (int) (actualWindowHeight * r));
        }
    }

    private boolean sizeIsNotEqual(Dimension size) {
        return size.width != actualWindowWidth || size.height != actualWindowHeight;
    }

    public void changeAccuracyCount(int accuracyCount) {
        maxCount = accuracyCount;
        redraw();
    }

    public void drawNew() {
        actualWindowPositionX = 0.0;
        actualWindowPositionY = 0.0;
        zoom = 1.0;
        redraw();
    }

    public void changeFractal() {
        actualFractal = (actualFractal + 1) % 2;
        actualWindowPositionX = actualWindowPositionY = 0.0;
        zoom = 1.0;
        isJuliaSelected = false;
        if (actualFractal == 0) {
            juliaX = 0.56667;
            juliaY = -0.5;
            isJuliaSelected = true;
        }
        redraw();
    }

    public void zoomIn() {
        actualWindowPositionX += 0.25 * zoom;
        actualWindowPositionY += 0.25 * zoom;
        zoom *= 0.5;
        redraw();
    }

    public void zoomOut() {
        actualWindowPositionX -= 0.5 * zoom;
        actualWindowPositionY -= 0.5 * zoom;
        zoom *= 2.0;
        redraw();

    }

    public void nextFractal() {
        isJuliaSelected = !isJuliaSelected;
        redraw();
    }

    public void smooth() {
        shouldSmooth = !shouldSmooth;
        redraw();
    }

    public void changeColorPalette() {
        actualPalette = (actualPalette + 1) % colors.length;
        redraw();
    }

    private void zoomMouse(int x, int y, double r) {
        int mx = Math.min(x, clickMousePositionX);
        int my = Math.min(y, clickMousePositionY);
        double sx = actualWindowWidth > actualWindowHeight ? actualWindowWidth - actualWindowHeight : 0.0;
        double sy = actualWindowHeight > actualWindowWidth ? actualWindowHeight - actualWindowWidth : 0.0;
        actualWindowPositionX += r * (mx - sx / 2.0);
        actualWindowPositionY += r * (my - sy / 2.0);
        double w = x + clickMousePositionX - 2 * mx;
        double h = y + clickMousePositionY - 2 * my;
        double zoom0 = zoom;
        zoom *= Math.max(w / actualWindowWidth, h / actualWindowHeight);
        actualWindowPositionX += r / zoom0 * zoom * sx / 2.0;
        actualWindowPositionY += r / zoom0 * zoom * sy / 2.0;
        redraw();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickMousePositionX = dragMousePositionX = e.getX();
        clickMousePositionY = dragMousePositionY = e.getY();
        dragAvailable = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragAvailable = false;
        int x = e.getX();
        int y = e.getY();
        if (leftMouseClickIsHold(e)) {
            double r = zoom / Math.min(actualWindowWidth, actualWindowHeight);
            if (x != clickMousePositionX || y != clickMousePositionY) {
                zoomMouse(x, y, r);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (leftMouseClickIsHold(e)) {
            dragMousePositionX = e.getX();
            dragMousePositionY = e.getY();
            repaint();
        }
    }

    private boolean leftMouseClickIsHold(MouseEvent e) {
        return (e.getModifiers() & InputEvent.BUTTON1_MASK) != 0;
    }

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