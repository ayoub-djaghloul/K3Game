package View;
import Controller.Feedback;
import Controller.GameController;
import Controller.LesCoutsAccessibles;
import Model.*;
import Model.Pion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class MainFrame extends JFrame { // this class is the main frame of the game
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Pion[] pionSource = new Pion[1];
    private JLabel labelr;
    final int[] pionCount = {0};
    public int tour = 1;
    private int iterateur_penalite = 0%5;
    JLabel feedbackLabelcenter;
    JButton readyButton;
    Feedback example = new Feedback();
        private Stack<Pion> history2Dtable = new Stack<Pion>();
    private Stack<Pion> historyPyramid = new Stack<Pion>();
    public boolean penalite=false;

    public MainFrame(Table2D table2DP1, Table2D table2DP2, Table2D baseK3, Pyramide p1Pyramide, Pyramide p2Pyramide, Pyramide K3) {
        initializeFrame();
        // Create a panel to hold the background image
        addBackgroundPanel();
        Box box =addButton(table2DP1, baseK3, p1Pyramide, p2Pyramide, K3, null, null);
        mainFrame.setVisible(true);
        mainFrame.add(box, BorderLayout.CENTER);
    }





/*    public JPanel phase0(){

    }
    */









    private Box addButton(Table2D table2DP1, Table2D baseK3, Pyramide p1Pyramide, Pyramide p2Pyramide, Pyramide K3, Table2D penalitetable, JPanel penalitePanel) {
        JButton addButton = new JButton();
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(addButton);
        box.add(Box.createHorizontalGlue());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Start Game");
               // example.showFeedback("Start Game", 10000);
                JPanel table2DP1Panel = tabel2DPanel(table2DP1);
                JPanel p1PyramidPanel = pyramidePanel(p1Pyramide);
                panelListener(p1PyramidPanel, null, p1Pyramide, K3, 1, 0, penalitetable, penalitePanel);
                JPanel baseK3Panel = baseK3(baseK3);
                JButton undoButton = undoButton(table2DP1, table2DP1Panel, p1PyramidPanel);
                JButton readyButton = readyButton(p1Pyramide, p2Pyramide, K3);
                addPanel(Phase1(table2DP1Panel, p1PyramidPanel, baseK3Panel, undoButton, readyButton), "phase1");
                cardLayout.show(mainPanel, "phase1");
                addButton.setVisible(false);
            }
        });

        //set a background picture to the button and make it transparent
        addButton.setIcon(new ImageIcon("sources/Images/1v1.png"));
        addButton.setOpaque(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.setBorder(null);
        //add animation when i pass the cursor on the button
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addButton.setIcon(new ImageIcon("sources/Images/1v1hover.png"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addButton.setIcon(new ImageIcon("sources/Images/1v1.png"));
            }
        });

        return box;
    }


    //private void startGameListener()










    private void addBackgroundPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("sources/Images/bg.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        mainFrame.setContentPane(backgroundPanel);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setLayout(cardLayout);
        mainPanel.setOpaque(false);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    private void initializeFrame() {
        mainFrame = new JFrame("K3");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());


    }

    public JPanel tabel2DPanel(Table2D table2D) {
        JPanel tablePanel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth(), 0, 0));
        int k = 0;

        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                k++;
                Pion pion = table2D.getCases()[i][j];
                ImageIcon ImagePion = pion.getImageIcon();
                // reduce the size of the image
                ImagePion.setImage(ImagePion.getImage().getScaledInstance(55, 40, Image.SCALE_DEFAULT));
                JLabel table2DLabel = new JLabel(ImagePion);
                if (k == table2D.getHeight() * table2D.getWidth()) {
                    table2DLabel.setVisible(false);
                    tablePanel.remove(table2DLabel);
                } else {
                    tablePanel.add(table2DLabel);
                }
                int finalI = i;
                int finalJ = j;
                table2DLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pionSource[0]=table2D.getPion(finalI, finalJ);
                        labelr = table2DLabel;
                        history2Dtable.push(pionSource[0]);
                    }
                });
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
            JPanel pionPanel = createPionPanel(pyramide, i);
            pyramidePanel.add(pionPanel);
        }
        pyramidePanel.setOpaque(false);
        return pyramidePanel;
    }

    private JPanel createPionPanel(Pyramide pyramide, int i) {
        JPanel pionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        for (int j = 0; j <= i; j++) {
            Pion pion = pyramide.getPion(i, j);
            JLabel pyramideLabel = createPyramideLabel(pion);
            pionPanel.add(Integer.toString(i+j),pyramideLabel);
            //get a label from pionpanel  by name
            pyramideLabel.setName(Integer.toString(i+j));
            pionPanel.setOpaque(false);
            pionPanel.setBorder(BorderFactory.createEmptyBorder());
           /* int finalRow = i;
            int finalCol = j;
            pyramideLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    handleMouseClicked(k3Panel,s, option, finalRow, finalCol, pyramide, K3, pyramideLabel);
                }
            });*/
        }
        return pionPanel;
    }


    private void panelListener(JPanel pyramidPanel, JPanel K3Panel, Pyramide pyramide, Pyramide K3, int s, int option, Table2D penalitetable, JPanel penalitePanel){
        for(int i = 0; i < pyramidPanel.getComponentCount(); i++){
            JPanel pionPanel = (JPanel) pyramidPanel.getComponent(i);
            for(int j = 0; j < pionPanel.getComponentCount(); j++){
                JLabel pyramideLabel = (JLabel) pionPanel.getComponent(j);
                int finalRow = i;
                int finalCol = j;
                pyramideLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        handleMouseClicked(K3Panel,s, option, finalRow, finalCol, pyramide, K3, pyramideLabel, pyramidPanel, penalitetable, penalitePanel);
                    }
                });
            }
        }
    }




    private void handleMouseClicked(JPanel k3Panel, int s, int option, int row, int col, Pyramide pyramide, Pyramide K3, JLabel pyramideLabel, JPanel pyramidPanel,Table2D penalitetable, JPanel penalitePanel) {
        switch (s) {
            case 1:
                handleCase1(option, row, col, pyramide, pyramideLabel);
                break;
            case 2:
                handleCase2(row, col, pyramide, pyramideLabel,K3,pyramidPanel, penalitetable, penalitePanel);
                break;
            case 3:
                handleCase3(row, col, pyramide, k3Panel,K3, pyramidPanel,penalitetable,penalitePanel);//IA
                break;
            default:
                break;
        }
    }

    private void handleCase1(int option, int row, int col, Pyramide pyramide,JLabel pyramideLabel) {
        {
            if (labelr != null) {
                Pion pionDestination = pyramide.getPion(row, col);
                if(option ==1) {//construction de la pyramide K3 avec ordre
                    if(new GameController().testDeplacementPion(pionSource[0], pionDestination, pyramide)==true) {
                        //handle case when the move is possible
                        penalite=new GameController().getPenalite(pionSource[0], pionDestination, pyramide);
                        pyramideLabel.setIcon(labelr.getIcon());
                        labelr.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                        labelr = null;
                        if(!penalite){
                            feedbackLabelcenter.setText("Deplacement effectué");
                            feedbackLabelcenter.setBackground(Color.BLACK);
                            feedbackLabelcenter.setForeground(Color.GREEN);
                            tour=changertour(tour);
                            penalite=false;
                        }
                        else{
                            feedbackLabelcenter.setText("Deplacement effectué avec penalité(choisir un pion pour l'enlever)");
                            feedbackLabelcenter.setBackground(Color.BLACK);
                            feedbackLabelcenter.setForeground(Color.RED);
                            penalite=true;
                        }
                    }
                    else {
                        //handle case when the move is not possible
                        example.setVisible(true);
                        example.showFeedback("Deplacement non effectué", 1000);
                        feedbackLabelcenter.setText("Deplacement non effectué");
                        feedbackLabelcenter.setForeground(Color.RED);
                        feedbackLabelcenter.setBackground(Color.BLACK);
                        labelr = null;
                    }
                }else{//construction du premiere pyramide sans ordre
                    pionDestination.replacePion(pionSource[0]);
                    pyramideLabel.setIcon(labelr.getIcon());
                    pionCount[0]++;
                    labelr.setVisible(false);
                    labelr = null;
                    pionDestination.setVideCase(false);
                    if(pionCount[0]==21){
                        readyButton.setVisible(true);                                        }
                }
                historyPyramid.push(pionDestination);

            }
            else{
                if(feedbackLabelcenter!=null) {
                    feedbackLabelcenter.setText("selectionner un pion");
                    feedbackLabelcenter.setForeground(Color.RED);
                    feedbackLabelcenter.setBackground(Color.BLACK);
                }else{
                    example.setVisible(true);
                    example.showFeedback("selectionner un pion SVP", 2000);

                }
            }
        }
    }

    private int changertour(int tour) {
        tour=(tour == 1) ? 2 : 1;
        return tour;
    }

    private void handleCase2(int row, int col, Pyramide pyramide, JLabel pyramideLabel, Pyramide K3, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        {
            pionSource[0] = pyramide.getPion(row, col);
            //joueur =!joueur;
            if (new GameController().testTour(tour, pionSource[0], pyramide,K3)) {
                if(!penalite){
                if (new GameController().testAvantDeplacement(pionSource[0], pyramide) == false) {
                    example.setVisible(true);
                    example.showFeedback("pion non accessible", 1000);
                    System.out.println("pion non accessible");
                    labelr = null;
                } else if(pionSource[0].getCouleur()==CouleurPion.BLANC){
                    example.setVisible(true);
                    example.showFeedback("Tour du joueur Passé avec succés", 1000);
                    feedbackLabelcenter.setText("Tour du joueur Passé avec succés");
                    feedbackLabelcenter.setForeground(Color.GREEN);
                    pionSource[0].setVideCase(true);
                    if (tour == 1) {
                        tour = 2;
                        System.out.println(tour);
                    } else {
                        tour = 1;
                        System.out.println(tour);
                    }
                    labelr = pyramideLabel;
                    labelr.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                    labelr =null;
                }else {labelr = pyramideLabel;}
                } else {
                    penalite_func(row, col, pyramide, pyramidPanel, penalitetable, penalitePanel);
                }

            }else {
                example.setVisible(true);
                example.showFeedback("ce n'est pas votre tour", 1000);
                System.out.println("ce n'est pas votre tour");
                labelr = null;
            }
        }

    }

    private void handleCase3(int row, int col, Pyramide pyramide, JPanel k3, Pyramide K3, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        pionSource[0] = pyramide.getPion(0, 0);
        Pion pionDestination;
        // joueur =!joueur;
        if (new GameController().testTour(tour, pionSource[0], pyramide, K3)) {
            if(!penalite){
            pionDestination = new LesCoutsAccessibles().choisirUnPionAjouer(pyramide, K3);
            pionSource[0]=new LesCoutsAccessibles().choisirUnPionAjouerSource(pyramide, K3);
            if (pionDestination != null) {
                example.setVisible(true);
                example.showFeedback("Il y a une possibilité de coup", 1000);

                // Get the JLabel from k3 panel using the pionDestination's coordinates
                JLabel distinationLabel = LabelSource(pionDestination, k3);
                distinationLabel.setIcon(pionSource[0].getImageIcon());
                pionDestination.replacePion(pionSource[0]);
                pionDestination.setVideCase(false);
                JLabel sourcePanel = LabelSource(pionSource[0], pyramidPanel);
                sourcePanel.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                pionSource[0].setVideCase(true);
                System.out.println("pionSource {" + pionSource[0].getX() + " ; " + pionSource[0].getY() + " } + Couleur " +pionSource[0].getCouleur() +"--> pionDestination {" + pionDestination.getX() + " ; " + pionDestination.getY() + " } + Couleur " +pionDestination.getCouleur());
                penalite=new GameController().getPenalite(pionDestination, pionDestination, K3);
                if(!penalite){
                    feedbackLabelcenter.setText("Deplacement effectué");
                    feedbackLabelcenter.setBackground(Color.BLACK);
                    feedbackLabelcenter.setForeground(Color.GREEN);
                    tour=changertour(tour);
                    penalite=false;
                }
                else{
                    feedbackLabelcenter.setText("Deplacement effectué avec penalité(choisir un pion pour l'enlever)");
                    feedbackLabelcenter.setBackground(Color.BLACK);
                    feedbackLabelcenter.setForeground(Color.RED);
                    penalite=true;
                }
            } else {
                example.setVisible(true);
                example.showFeedback("Ce n'est pas votre tour", 1000);
                System.out.println("Ce n'est pas votre tour");
            }
            labelr = null;
        }
        else{
                penalite_func(row,col,pyramide,pyramidPanel,penalitetable,penalitePanel);
            }
        }
        else {
            example.setVisible(true);
            example.showFeedback("ce n'est pas votre tour", 1000);
            labelr = null;
        }
    }

    private void penalite_func(int row,int col,Pyramide pyramide, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        //deplacer le pion source vers la case vide du panel penalite
        example.showFeedback("penalité a executer-------------------------------", 5000);
        System.out.println("penalité a executer11111");
        if(new GameController().testAvantDeplacement(pionSource[0],pyramide)==false){
            example.showFeedback("pion non accessible choisie un autre", 1000);
            System.out.println("pion non accessible choisie un autre");
            labelr = null;
        }
        else{
        penalite = false;
        tour=changertour(tour);
        //choisi un pion a enlever from peramite panel
        pionSource[0] = pyramide.getPion(row, col);
        pionSource[0].setVideCase(true);
        Pion piondestination = le_premier_libre_penalite(penalitetable);
        System.out.println("piondestination x et y:  " + piondestination.getX() + "  " + piondestination.getY());

        piondestination.replacePion(pionSource[0]);
        piondestination.setVideCase(false);
        System.out.println("piondestination: couleur " + piondestination.getCouleur() + " vide ou nn !  " + piondestination.estVide());
        JLabel labelSource = LabelSource(pionSource[0],pyramidPanel);
        JLabel labelDestination = le_premier_libre_penalite_label(penalitePanel, piondestination.getY());
        labelDestination.setIcon(labelSource.getIcon());
        labelSource.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
        labelSource.setVisible(true);
        //enlever le pion de la pyramide
    }}

    private JLabel LabelSource(Pion source, JPanel Pyramid){
        int panelIndex = source.getX();
        int labelIndex = source.getY();
        Component[] components = Pyramid.getComponents();
        if (panelIndex < components.length) {
            Component component = components[panelIndex];
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                if (labelIndex < panel.getComponentCount()) {
                    Component innerComponent = panel.getComponent(labelIndex);
                    if (innerComponent instanceof JLabel) {
                        JLabel label = (JLabel) innerComponent;
                        return label;
                    }
                }
            }
        }
        return null;
    }
    public Pion le_premier_libre_penalite(Table2D penalite){
        Pion pion = penalite.getPion(0,0);
        for(int i=0; i<4; i++){
            System.out.println("couleur du pion "+i+"  "+penalite.getPion(0,i).getCouleur());
            if(penalite.getPion(0,i).estVide()){
                pion = penalite.getPion(0,i);
                pion.setY(i);
                break;
            }
        }

        return pion;
    }
    public JLabel le_premier_libre_penalite_label(JPanel penalite,int sourceY){
        int labelIndex = sourceY;
        if (labelIndex < penalite.getComponentCount()) {
        Component innerComponent = penalite.getComponent(labelIndex);
            System.out.println("labelIndex "+labelIndex);
            if (innerComponent instanceof JLabel) {
            return (JLabel) innerComponent;
        }}
        return null;
    }

    private JLabel createPyramideLabel(Pion pion) {
        ImageIcon ImagePion = pion.getImageIcon();
        ImagePion.setImage(ImagePion.getImage().getScaledInstance(55, 40, Image.SCALE_DEFAULT));
        JLabel pyramideLabel = new JLabel(ImagePion);
        return pyramideLabel;
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


    public JPanel Phase1(JPanel pyramid, JPanel table2D, JPanel baseK3, JButton undoButton, JButton readyButton) {
        JPanel phase1 = new JPanel();
        Box boxReadyButton = Box.createHorizontalBox();
        boxReadyButton.add(readyButton);
        Box boxUndoButton = Box.createHorizontalBox();
        boxUndoButton.add(undoButton);
        phase1.setLayout(new BorderLayout(30, 30));
        phase1.add(table2D, BorderLayout.CENTER);
        phase1.add(pyramid, BorderLayout.NORTH);
        phase1.add(baseK3, BorderLayout.SOUTH);
        phase1.add(boxUndoButton, BorderLayout.WEST);
        phase1.add(boxReadyButton, BorderLayout.EAST);


        // transparent background
        phase1.setOpaque(false);
        return phase1;
    }

    public JPanel Phase2(JPanel p1Pyramide, JPanel p2Pyramide, JPanel K3, JPanel penalitePanel1, JPanel penalitePanel2){
        //Box phase2 = Box.createHorizontalBox();
        JPanel phase2 = new JPanel();
        phase2.setLayout(new BoxLayout (phase2, BoxLayout.X_AXIS));
        feedbackLabelcenter = new JLabel(" ");
        penalitePanel1.setLayout(new GridLayout());
        penalitePanel2.setLayout(new GridLayout());
        //add quitter button
        Box quitter = quitter();
        //leftPanel
        JPanel leftPanel = phase2Panel(penalitePanel1, p1Pyramide);
        phase2.add(leftPanel);
        //centerPanel
        JPanel centerPanel = phase2Panel(feedbackLabelcenter, K3);
        phase2.add(centerPanel);
        //rightPanel
        JPanel rightPanel = phase2Panel(penalitePanel2, p2Pyramide);
        rightPanel.setOpaque(false);
        phase2.add(rightPanel);
        // transparent background
        phase2.setOpaque(false);
        //JPanel p = new JPanel();
        //p.add(phase2);
        return phase2;
    }



    JPanel phase2Panel(Component C1, Component C2){
        JPanel Panel = new JPanel(new BorderLayout());
        Panel.add(C1, BorderLayout.CENTER);
        Panel.add(C2, BorderLayout.NORTH);
        Panel.setOpaque(false);
        return Panel;
    }


    public void addPanel(JPanel panelParam , String name) {
        // Create a new panel to hold the tabel2DPanel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(panelParam, BorderLayout.CENTER);

        // Add the new panel to the main panel
        mainPanel.add(panel, name);
    }
    public JButton undoButton(Table2D table2D , JPanel tablePanel, JPanel pyramidPanel){
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tableX = history2Dtable.peek().getX();
                int tableY = history2Dtable.peek().getY();
                int pyramidX = historyPyramid.peek().getX();
                int pyramidY = historyPyramid.peek().getY();
                if (!history2Dtable.empty() && !historyPyramid.empty()) {
                    Pion pion2D = history2Dtable.pop();
                    Pion pionPyramid = historyPyramid.pop();
                    pionPyramid.resetPion();
                    table2D.getCases()[tableX][tableY].replacePion(pion2D);
                    pion2D.setVideCase(true);
                    // update the table's label with the pion's image icon
                    JLabel tableLabel = (JLabel) tablePanel.getComponent(tableX * table2D.getWidth() + tableY);
                    tableLabel.setIcon(pion2D.getImageIcon());
                    tableLabel.setVisible(true);
                    // update the pyramid's label with the pion's image icon
                    JPanel pionPanel = (JPanel) pyramidPanel.getComponent(pyramidX);
                    JLabel pyramidLabel = (JLabel) pionPanel.getComponent(pyramidY);
                    pyramidLabel.setIcon(pionPyramid.getImageIcon());
                    pionCount[0]--;
                }
            }
        });
        return undoButton;
    }

    public JButton readyButton(Pyramide p1Pyramide,Pyramide p2Pyramide, Pyramide K3){
        Icon icon = new ImageIcon("sources/Images/READY.png");
        readyButton = new JButton(icon);
        Dimension d = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        readyButton.setPreferredSize(d);
/*        if(pionCount[0]<21){
            readyButton.setEnabled(true);
        }
        else{
            readyButton.setEnabled(false);
            readyButton.setVisible(false);}*/
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch between the panels
                JPanel K3Panel = pyramidePanel(K3);
                JPanel p1PyramidPanel = pyramidePanel(p1Pyramide);
                JPanel p2PyramidPanel = pyramidePanel(p2Pyramide);
                Table2D penalitetable1 = new Table2D(1,5);
                JPanel penalitePanel1 = penalitePanel(penalitetable1);
                Table2D penalitetable2 = new Table2D(1,5);
                JPanel penalitePanel2 = penalitePanel(penalitetable2);


                    panelListener(K3Panel, K3Panel, K3, K3, 1, 1, penalitetable1, penalitePanel1);
                    panelListener(p1PyramidPanel, K3Panel, p1Pyramide, K3, 2, 1, penalitetable1, penalitePanel1);
                    panelListener(p2PyramidPanel, K3Panel, p2Pyramide, K3, 3, 1, penalitetable2, penalitePanel2);

               /* LesCoutsAccessibles liste=new LesCoutsAccessibles();
                liste.afficherCoutsAccessibles(p2Pyramide, K3);*/
                addPanel(Phase2(p1PyramidPanel, p2PyramidPanel, K3Panel, penalitePanel1,penalitePanel2), "phase2");
                mainFrame.setSize(1550,720);
                cardLayout.show(mainPanel, "phase2");
            }
        });
        readyButton.setVisible(true);
        return readyButton;
    }

    public Box quitter(){
        JButton quitter = new JButton("quitter");
        //size of the button
        quitter.setPreferredSize(new Dimension(50, 50));
        //width of button border
        quitter.setBorder(new LineBorder(Color.BLACK, 5));
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Box boxQuitter = Box.createHorizontalBox();
        boxQuitter.add(Box.createHorizontalGlue());
        boxQuitter.add(quitter);
        boxQuitter.setOpaque(false);
        return boxQuitter;
    }
    public JPanel penalitePanel(Table2D penalité){
        JPanel pionPenalité;
        pionPenalité = tabel2DPanel(penalité);
        return pionPenalité;
    }

}