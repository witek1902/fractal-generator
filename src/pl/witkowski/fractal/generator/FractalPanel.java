package pl.witkowski.fractal.generator;

import javax.swing.*;
import java.awt.*;

public class FractalPanel extends JFrame {

    Generator applet = new Generator();
    private JPanel ButtonPanel = new JPanel();
    private JButton countButton = new JButton();
    private JSpinner countSpinner = new JSpinner();
    private JPanel fractal = new JPanel();
    private JButton juliaSetButton = new JButton();
    private JButton newButton = new JButton();
    private JButton nextButton = new JButton();
    private JButton aboutButton = new JButton();
    private JButton smoothButton = new JButton();
    private JButton zoomInButton = new JButton();
    private JButton zoomOutButton = new JButton();
    private JButton nextPaletteButton = new JButton();
    
    public FractalPanel() {
        initComponents();
        fractal.add(applet);
        applet.setSize(fractal.getWidth(), fractal.getHeight());
        applet.init();
        applet.start();
        setVisible(true);
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

        countSpinner.setModel(new SpinnerNumberModel(200, 10, 10000, 10));
        countSpinner.setToolTipText("Set maximum number of iteration");
        
        nextButton.setText("Next fractal");
        nextButton.addActionListener(evt -> applet.setJulia());

        newButton.setText("Draw new");
        newButton.addActionListener(evt -> applet.escape());

        zoomInButton.setText("+");
        zoomInButton.addActionListener(evt -> applet.zoomIN());

        zoomOutButton.setText("-");
        zoomOutButton.addActionListener(evt -> applet.zoomOUT());

        juliaSetButton.setText("Phoenix");
        juliaSetButton.addActionListener(evt -> applet.space());

        nextPaletteButton.setText("Change color");
        nextPaletteButton.addActionListener(evt -> applet.nextPalette());

        smoothButton.setText("Smooth");
        smoothButton.addActionListener(evt -> applet.smoothing());

        countButton.setText("OK");
        countButton.addActionListener(evt -> applet.changeCount((int) countSpinner.getValue()));

        aboutButton.setText("About");

        initButtonPanelLayout();
        initMainLayout();

        pack();
    }

    private void initFractalLayout() {
        GroupLayout FraktalLayout = new GroupLayout(fractal);
        fractal.setLayout(FraktalLayout);
        FraktalLayout.setHorizontalGroup(
            FraktalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        FraktalLayout.setVerticalGroup(
            FraktalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
    }

    private void initButtonPanelLayout() {
        GroupLayout ButtonPanelLayout = new GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(countButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(countSpinner)
                        .addGap(18, 18, 18)
                        .addComponent(juliaSetButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(newButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton)))
                .addGap(18, 18, 18)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(nextPaletteButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(smoothButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(zoomInButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zoomOutButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(aboutButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
                .addGap(133, 133, 133))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(nextPaletteButton)
                    .addComponent(smoothButton)
                    .addComponent(newButton))
                .addGap(17, 17, 17)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomInButton)
                    .addComponent(zoomOutButton)
                    .addComponent(juliaSetButton)
                    .addComponent(countSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(countButton)
                    .addComponent(aboutButton))
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
                        .addComponent(ButtonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(fractal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fractal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
}
