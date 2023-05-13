package View;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;
import Model.Pion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class MainFrame extends JFrame { // this class is the main frame of the game

    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;



    public MainFrame(Table2D table2D, Pyramide pyramide, Table2D baseK3) {
        mainFrame = new JFrame("K3");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        // Create a panel to hold the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image from a file
                Image backgroundImage = new ImageIcon("../sources/Images/bg.png").getImage();
                // Draw the image on the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };


        JButton addButton = new JButton("Add Panel");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel table2DPanel = tabel2DPanel(table2D);
                JPanel pyramidPanel = pyramidePanel(pyramide);
                JPanel baseK3Panel = baseK3(baseK3);
                addPanel(Phase1(table2DPanel, pyramidPanel,baseK3Panel), "phase1");

            }
        });

        backgroundPanel.setLayout(new BorderLayout());

        // Add the background panel to the main frame
        mainFrame.setContentPane(backgroundPanel);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setLayout(cardLayout);
        mainPanel.setOpaque(false);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);

        // Add the button to the main frame
        mainFrame.add(addButton, BorderLayout.SOUTH);
    }

    public JPanel tabel2DPanel(Table2D table2D) {
        JPanel tablePanel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth(), 0, 0));
        int k = 0;
        //final int[] pionCount = {0};

        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                k++;
                Pion pion = table2D.getCases()[i][j];
                JLabel table2DLabel = new JLabel(pion.getImageIcon());
                if (k == table2D.getHeight() * table2D.getWidth()) {
                    table2DLabel.setVisible(false);
                } else {
                    tablePanel.add(table2DLabel);
                }
            }
        }
        // transparent background
        tablePanel.setOpaque(false);
        //style of the panel
        return tablePanel;
    }

    public JPanel pyramidePanel(Pyramide pyramide) {
        JPanel pyramidePanel = new JPanel(new GridLayout(pyramide.getHight(), pyramide.getHight(), 0, 0));
        for (int i = 0; i < pyramide.getHight(); i++) {
            JPanel pionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j <= i; j++) {
                Pion pion = pyramide.getPion(i, j);
                JLabel pyramideLabel = new JLabel(pion.getImageIcon());
                pionPanel.add(pyramideLabel);
                pionPanel.setOpaque(false);
            }
            pyramidePanel.add(pionPanel);
        }
        // transparent background
        pyramidePanel.setOpaque(false);
        return pyramidePanel;
    }

    public JPanel baseK3(Table2D table2){
        JPanel baseK3 = new JPanel(new GridLayout(table2.getHeight(), table2.getWidth(), 0, 0));
        for (int i = 0; i < table2.getHeight(); i++) {
            for (int j = 0; j < table2.getWidth(); j++) {
                Pion pion = table2.getCases()[i][j];
                JLabel table2DLabel = new JLabel(pion.getImageIcon());
                    baseK3.add(table2DLabel);
                }
            }
        // transparent background
        baseK3.setOpaque(false);
        //style of the panel
        return baseK3;
    }


    public JPanel Phase1(JPanel pyramid, JPanel table2D, JPanel baseK3){
        JPanel phase1 = new JPanel();
        phase1.setLayout(new BorderLayout(2, 1));
        phase1.add(table2D, BorderLayout.CENTER);
        phase1.add(pyramid, BorderLayout.NORTH);
        phase1.add(baseK3, BorderLayout.SOUTH);
        // transparent background
        phase1.setOpaque(false);
        return phase1;
    }

    public void addPanel(JPanel panelParam , String name) {
        // Create a new panel to hold the tabel2DPanel
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(panelParam);

        // Add the new panel to the main panel
        mainPanel.add(panel, name);
        cardLayout.show(mainPanel, name);
    }



}
