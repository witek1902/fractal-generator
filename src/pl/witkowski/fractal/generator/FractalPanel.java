package pl.witkowski.fractal.generator;

import javax.swing.*;
import java.awt.*;

public class FractalPanel extends JFrame {

    private FractalDrawer fractalDrawer = new FractalDrawer();

    private JPanel buttonPanel = new JPanel();
    private JButton countButton = new JButton();
    private JSpinner countSpinner = new JSpinner();
    private JPanel fractal = new JPanel();
    private JButton changeToPhoenixButton = new JButton();
    private JButton newButton = new JButton();
    private JButton nextButton = new JButton();
    private JButton smoothButton = new JButton();
    private JButton zoomInButton = new JButton();
    private JButton zoomOutButton = new JButton();
    private JButton nextPaletteButton = new JButton();

    public FractalPanel() {
        initComponents();
        fractal.add(fractalDrawer);
        initDrawer();
        setVisible(true);
    }

    private void initDrawer() {
        fractalDrawer.setSize(fractal.getWidth(), fractal.getHeight());
        fractalDrawer.init();
        fractalDrawer.start();
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new FractalPanel().setVisible(true));
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fractal Generator");
        setName("Fractal Generator");
        setResizable(false);

        initFractalLayout();
        initCountSpinner();
        initNextButton();
        initNewButton();
        initZoomInButton();
        initZoomOutButton();
        initPhoenixSetButton();
        initNextPaletteButton();
        initSmoothButton();
        initCountButton();
        initButtonPanelLayout();
        initMainLayout();

        pack();
    }

    private void initCountButton() {
        countButton.setText("OK");
        countButton.addActionListener(evt -> fractalDrawer.changeAccuracyCount((int) countSpinner.getValue()));
    }

    private void initSmoothButton() {
        smoothButton.setText("Smooth");
        smoothButton.addActionListener(evt -> fractalDrawer.smooth());
    }

    private void initNextPaletteButton() {
        nextPaletteButton.setText("Change color");
        nextPaletteButton.addActionListener(evt -> fractalDrawer.changeColorPalette());
    }

    private void initPhoenixSetButton() {
        changeToPhoenixButton.setText("Phoenix");
        changeToPhoenixButton.addActionListener(evt -> fractalDrawer.changeFractal());
    }

    private void initZoomOutButton() {
        zoomOutButton.setText("-");
        zoomOutButton.addActionListener(evt -> fractalDrawer.zoomOut());
    }

    private void initZoomInButton() {
        zoomInButton.setText("+");
        zoomInButton.addActionListener(evt -> fractalDrawer.zoomIn());
    }

    private void initNewButton() {
        newButton.setText("Draw new");
        newButton.addActionListener(evt -> fractalDrawer.drawNew());
    }

    private void initNextButton() {
        nextButton.setText("Next fractal");
        nextButton.addActionListener(evt -> fractalDrawer.nextFractal());
    }

    private void initCountSpinner() {
        countSpinner.setModel(new SpinnerNumberModel(200, 10, 10000, 10));
        countSpinner.setToolTipText("Set maximum number of iteration");
    }

    private void initFractalLayout() {
        GroupLayout fractalLayout = new GroupLayout(fractal);
        fractal.setLayout(fractalLayout);
        fractalLayout.setHorizontalGroup(
                fractalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        fractalLayout.setVerticalGroup(
                fractalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 559, Short.MAX_VALUE)
        );
    }

    private void initButtonPanelLayout() {
        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addContainerGap(117, Short.MAX_VALUE)
                                .addComponent(countButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(countSpinner)
                                                .addGap(18, 18, 18)
                                                .addComponent(changeToPhoenixButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addComponent(newButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nextButton)))
                                .addGap(18, 18, 18)
                                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addComponent(nextPaletteButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(smoothButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addComponent(zoomInButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(zoomOutButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)))
                                .addGap(133, 133, 133))
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nextButton)
                                        .addComponent(nextPaletteButton)
                                        .addComponent(smoothButton)
                                        .addComponent(newButton))
                                .addGap(17, 17, 17)
                                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(zoomInButton)
                                        .addComponent(zoomOutButton)
                                        .addComponent(changeToPhoenixButton)
                                        .addComponent(countSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(countButton))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
    }

    private void initMainLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addComponent(fractal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(fractal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
}
