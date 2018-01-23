package pl.witkowski.fractal.generator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FraktalPanel extends JFrame {

    Generator applet = new Generator();
    
    public FraktalPanel() {
        initComponents();
        
        Fraktal.add(applet);
        applet.setSize(Fraktal.getWidth(),Fraktal.getHeight());
        applet.init();
        applet.start();
        setVisible(true);
    }

    private void initComponents() {

        Fraktal = new JPanel();
        ButtonPanel = new JPanel();
        NextButton = new JButton();
        NewButton = new JButton();
        ZoomINButton = new JButton();
        ZoomOUTButton = new JButton();
        JuliaSetButton = new JButton();
        nextPaletteButton = new JButton();
        SmoothButton = new JButton();
        CountSpinner = new JSpinner();
        CountButton = new JButton();
        OProgramieButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generator fraktali v1.0");
        setName("Generator Fraktali");
        setResizable(false);

        GroupLayout FraktalLayout = new GroupLayout(Fraktal);
        Fraktal.setLayout(FraktalLayout);
        FraktalLayout.setHorizontalGroup(
            FraktalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        FraktalLayout.setVerticalGroup(
            FraktalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );

        CountSpinner.setModel(new SpinnerNumberModel(200, 10, 10000, 10));
        CountSpinner.setToolTipText("Ustaw maksymalną ilość iteracji");
        
        NextButton.setText("Następny fraktal");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        NewButton.setText("Rysuj od nowa");
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });

        ZoomINButton.setText("+");
        ZoomINButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomINButtonActionPerformed(evt);
            }
        });

        ZoomOUTButton.setText("-");
        ZoomOUTButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomOUTButtonActionPerformed(evt);
            }
        });

        JuliaSetButton.setText("Zbiór Phoenix");
        JuliaSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JuliaSetButtonActionPerformed(evt);
            }
        });

        nextPaletteButton.setText("Zmień kolory");
        nextPaletteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPaletteButtonActionPerformed(evt);
            }
        });

        SmoothButton.setText("Wygładź");
        SmoothButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmoothButtonActionPerformed(evt);
            }
        });

        CountButton.setText("OK");
        CountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountButtonActionPerformed(evt);
            }
        });

        OProgramieButton.setText("O programie");
        OProgramieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OProgramieButtonActionPerformed(evt);
            }
        });

        GroupLayout ButtonPanelLayout = new GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(CountButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(CountSpinner)
                        .addGap(18, 18, 18)
                        .addComponent(JuliaSetButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(NewButton, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NextButton)))
                .addGap(18, 18, 18)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(nextPaletteButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SmoothButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(ZoomINButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ZoomOUTButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(OProgramieButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
                .addGap(133, 133, 133))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                    .addComponent(nextPaletteButton)
                    .addComponent(SmoothButton)
                    .addComponent(NewButton))
                .addGap(17, 17, 17)
                .addGroup(ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ZoomINButton)
                    .addComponent(ZoomOUTButton)
                    .addComponent(JuliaSetButton)
                    .addComponent(CountSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountButton)
                    .addComponent(OProgramieButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

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
                    .addComponent(Fraktal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fraktal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void CountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int count = (int)CountSpinner.getValue();
        applet.changeCount(count);
    }

    private void SmoothButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.smoothing();
    }

    private void nextPaletteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.nextPalette();
    }

    private void JuliaSetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.space();
    }

    private void ZoomOUTButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.zoomOUT();
    }

    private void ZoomINButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.zoomIN();
    }

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.escape();
    }

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applet.setJulia();
    }

    private void OProgramieButtonActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FraktalPanel().setVisible(true));
    }

    private JPanel ButtonPanel;
    private JButton CountButton;
    private JSpinner CountSpinner;
    private JPanel Fraktal;
    private JButton JuliaSetButton;
    private JButton NewButton;
    private JButton NextButton;
    private JButton OProgramieButton;
    private JButton SmoothButton;
    private JButton ZoomINButton;
    private JButton ZoomOUTButton;
    private JButton nextPaletteButton;
    
}
