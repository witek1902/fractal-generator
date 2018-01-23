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

    /* metoda tworzaca okienko JDialog, w ktorym sa informacje dotyczace programu */
    private JDialog createAbout() {

        final JDialog OknoDialogowe = new JDialog(this, "O programie...", true);
        JButton ZamknijButton = new JButton("Zamknij");
        JPanel PanelAbout = new JPanel(new BorderLayout());
        PanelAbout.setBackground(new java.awt.Color(218, 218, 218));
        JTextArea text = new JTextArea("Generator Fraktali\n"
                + "Wersja 1.0, 08.01.2013\n\n"
                + "Robert Witkowski\n"
                + "Politechnika Warszawska\n"
                + "Wydział Elektryczny, Informatyka\n\n"
                + "mail: witek1902@gmail.com\n");
        text.setEditable(false);
        text.setBackground(new java.awt.Color(218, 218, 218));
        ZamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OknoDialogowe.setVisible(false);
            }
        });
        PanelAbout.add(ZamknijButton, BorderLayout.SOUTH);
        PanelAbout.add(text,BorderLayout.NORTH);
        PanelAbout.setVisible(true);
        OknoDialogowe.add(PanelAbout);
        OknoDialogowe.setLocationRelativeTo(this);
        OknoDialogowe.pack();
        OknoDialogowe.setResizable(false);
        return OknoDialogowe;
    }
    private void initComponents() {

        Fraktal = new javax.swing.JPanel();
        ButtonPanel = new javax.swing.JPanel();
        NextButton = new javax.swing.JButton();
        NewButton = new javax.swing.JButton();
        ZoomINButton = new javax.swing.JButton();
        ZoomOUTButton = new javax.swing.JButton();
        JuliaSetButton = new javax.swing.JButton();
        nextPaletteButton = new javax.swing.JButton();
        SmoothButton = new javax.swing.JButton();
        CountSpinner = new javax.swing.JSpinner();
        CountButton = new javax.swing.JButton();
        OProgramieButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generator fraktali v1.0");
        setName("Generator Fraktali");
        setResizable(false);

        javax.swing.GroupLayout FraktalLayout = new javax.swing.GroupLayout(Fraktal);
        Fraktal.setLayout(FraktalLayout);
        FraktalLayout.setHorizontalGroup(
            FraktalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        FraktalLayout.setVerticalGroup(
            FraktalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );

        CountSpinner.setModel(new javax.swing.SpinnerNumberModel(200, 10, 10000, 10));
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

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(CountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(CountSpinner)
                        .addGap(18, 18, 18)
                        .addComponent(JuliaSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(NewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NextButton)))
                .addGap(18, 18, 18)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(nextPaletteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SmoothButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ButtonPanelLayout.createSequentialGroup()
                        .addComponent(ZoomINButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ZoomOUTButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(OProgramieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(133, 133, 133))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                    .addComponent(nextPaletteButton)
                    .addComponent(SmoothButton)
                    .addComponent(NewButton))
                .addGap(17, 17, 17)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ZoomINButton)
                    .addComponent(ZoomOUTButton)
                    .addComponent(JuliaSetButton)
                    .addComponent(CountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountButton)
                    .addComponent(OProgramieButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(Fraktal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fraktal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        this.about = this.createAbout();
        this.about.setVisible(true);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FraktalPanel().setVisible(true);
            }
        });
    }
    
    private javax.swing.JDialog about;
    private javax.swing.JPanel ButtonPanel;
    private javax.swing.JButton CountButton;
    private javax.swing.JSpinner CountSpinner;
    private javax.swing.JPanel Fraktal;
    private javax.swing.JButton JuliaSetButton;
    private javax.swing.JButton NewButton;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton OProgramieButton;
    private javax.swing.JButton SmoothButton;
    private javax.swing.JButton ZoomINButton;
    private javax.swing.JButton ZoomOUTButton;
    private javax.swing.JButton nextPaletteButton;
    
}
