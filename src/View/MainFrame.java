package View;
import AI.RandomAI;
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

    ImageIcon startIMG1v1 = new ImageIcon("sources/Images/1v1.png");
    ImageIcon startIMG1v1Hover = new ImageIcon("sources/Images/1v1hover.png");
    ImageIcon startIMG1vAI = new ImageIcon("sources/Images/1vAI.png");
    ImageIcon startIMG1vAIHover = new ImageIcon("sources/Images/1vAIhover.png");

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
        mainFrame.setVisible(true);
        addPanel(phase0(table2DP1, table2DP2, baseK3, p1Pyramide, p2Pyramide, K3, null, null), "phase0");
    }


    public JPanel phase0(Table2D table2DP1, Table2D table2DP2, Table2D baseK3, Pyramide p1Pyramide, Pyramide p2Pyramide, Pyramide K3, Table2D penalitetable, JPanel penalitePanel){
        Box start1vAI = startGame(table2DP1, table2DP2, baseK3, p1Pyramide, p2Pyramide, K3, 1, startIMG1vAI, startIMG1vAIHover, penalitetable, penalitePanel);
        Box start1v1 = startGame(table2DP1, table2DP2, baseK3, p1Pyramide, p2Pyramide, K3, 2, startIMG1v1, startIMG1v1Hover, penalitetable, penalitePanel);
        JPanel phase0 = new JPanel();
        phase0.setLayout(new BorderLayout());
        phase0.add(start1v1, BorderLayout.WEST);
        phase0.add(start1vAI, BorderLayout.EAST);
        phase0.setOpaque(false);
        //add margin to the panel
        phase0.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        return phase0;
    }


    private Box startGame(Table2D table2DP1, Table2D table2DP2, Table2D baseK3, Pyramide p1Pyramide, Pyramide p2Pyramide, Pyramide K3, int gameMode, ImageIcon img, ImageIcon imgHover, Table2D penalitetable, JPanel penalitePanel){
        JButton startButton = new JButton();
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(startButton);
        box.add(Box.createHorizontalGlue());
        startGameListener(startButton, table2DP1, table2DP2, baseK3, p1Pyramide,  p2Pyramide, K3, gameMode, img, imgHover, penalitetable, penalitePanel);
        //set a background picture to the button and make it transparent
        startButton.setIcon(img);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setMargin(new Insets(0, 0, 0, 0));
        startButton.setBorder(null);
        //add animation when i pass the cursor on the button
        return box;
    }

    private void startGameListener(JButton startButton, Table2D table2DP1, Table2D table2DP2, Table2D baseK3, Pyramide p1Pyramide, Pyramide p2Pyramide, Pyramide K3, int gameMode, ImageIcon img, ImageIcon imgHover, Table2D penalitetable, JPanel penalitePanel){
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Start Game");
                // example.showFeedback("Start Game", 10000);
                JPanel table2DP1Panel = tabel2DPanel(table2DP1);
                JPanel p1PyramidPanel = pyramidePanel(p1Pyramide);
                JPanel table2DP2Panel = tabel2DPanel(table2DP2);
                JPanel p2PyramidPanel = pyramidePanel(p2Pyramide);
                JPanel baseK3Panel = baseK3(baseK3);
                JPanel baseK3Panel2 = baseK3(baseK3);
                Table2D penalitetable1 = new Table2D(1,5);
                Table2D penalitetable2 = new Table2D(1,5);
                JPanel penalitePanel1 = penalitePanel(penalitetable1);
                JPanel penalitePanel2 = penalitePanel(penalitetable2);
                if(gameMode==1){
                    new RandomAI(p2Pyramide, table2DP2,baseK3);
                    p2PyramidPanel = pyramidePanel(p2Pyramide);
                    JButton undoButton = undoButton(table2DP1, table2DP1Panel, p1PyramidPanel);
                    JButton readyButton = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 1, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    panelListener(p1PyramidPanel, null, p1Pyramide, K3, 1, 0, penalitetable, penalitePanel);
                    addPanel(Phase1(table2DP1Panel, p1PyramidPanel, baseK3Panel, undoButton, readyButton), "phase1");
                    cardLayout.show(mainPanel, "phase1");
                }else if(gameMode==2){
                    JButton undoButtonP1 = undoButton(table2DP1, table2DP1Panel, p1PyramidPanel);
                    JButton undoButtonP2 = undoButton(table2DP1, table2DP2Panel, p2PyramidPanel);
                    JButton readyButton1 = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 2, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    JButton readyButton2 = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 3,penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    panelListener(p1PyramidPanel, null, p1Pyramide, K3, 1, 0, penalitetable, penalitePanel);
                    panelListener(p2PyramidPanel, null, p2Pyramide, K3, 1, 0, penalitetable, penalitePanel);
                    addPanel(Phase1(table2DP1Panel, p1PyramidPanel, baseK3Panel, undoButtonP1, readyButton1), "phase10");
                    addPanel(Phase1(table2DP2Panel, p2PyramidPanel, baseK3Panel2, undoButtonP2, readyButton2), "phase11");
                    cardLayout.show(mainPanel, "phase10");
                    //startButton.setVisible(false);
                }
                startButton.setVisible(false);
            }
        });

        hover(startButton, img, imgHover);
    }
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
                JLabel table2DLabel = createPyramideLabel(pion);
                if (k == table2D.getHeight() * table2D.getWidth()) {
                    table2DLabel.setVisible(false);
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
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 125, 0, 90));
        //style of the panel
        return tablePanel;
    }

    public JPanel pyramidePanel(Pyramide pyramide) {
        JPanel pyramidePanel = new JPanel(new GridLayout(pyramide.getHight(), pyramide.getHight(), 0, 0));

        for (int i = 0; i < pyramide.getHight(); i++) {
            //JPanel pionPanel =new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            JPanel pionPanel = createPionPanel(pyramide, i);
            pyramidePanel.add(pionPanel);
        }
        pyramidePanel.setOpaque(false);
        pyramidePanel.setBorder(BorderFactory.createEmptyBorder(29, 0, 29, 32));
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
                    labelr.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
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
            if (!pyramideLabel.getIcon().toString().equals("sources/Images/EMPTY.png")&&labelr==null) {
                pionSource[0] = pyramide.getPion(row, col);
                if (new GameController().testTour(tour, pionSource[0], pyramide, K3, penalitetable)) {
                    if (!penalite) {
                        if (new GameController().testAvantDeplacement(pionSource[0], pyramide) == false) {
                            example.setVisible(true);
                            example.showFeedback("pion non accessible", 1000);
                            System.out.println("pion non accessible");
                            labelr = null;
                        } else
                        {
                            if (pionSource[0].getCouleur() == CouleurPion.BLANC) {
                            example.setVisible(true);
                            example.showFeedback("Tour du joueur Passé avec succés", 1000);
                            feedbackLabelcenter.setText("Tour du joueur Passé avec succés");
                            feedbackLabelcenter.setForeground(Color.GREEN);
                            tour = changertour(tour);
                            pyramideLabel.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                            pionSource[0].setVideCase(true);
                            labelr = null;
                        } else {
                            labelr = pyramideLabel;
                        }
                        }
                    } else {
                        penalite_func(row, col, pyramide, pyramidPanel, penalitetable, penalitePanel);
                    }

                } else {
                    example.setVisible(true);
                    example.showFeedback("ce n'est pas votre tour", 1000);
                    System.out.println("ce n'est pas votre tour");
                }
            }
            else
            {
                System.out.println("labelr = " + labelr + " pyramideLabel = " + pyramideLabel.getIcon());
                example.setVisible(true);
                example.showFeedback("selectionner un pion SVPppppp", 2000);
                System.out.println("selectionner un pion SVP");
            }
        }
    }

    private void handleCase3(int row, int col, Pyramide pyramide, JPanel k3, Pyramide K3, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        if(labelr==null) {
            pionSource[0] = pyramide.getPion(0, 0);
            Pion pionDestination;
            if (new GameController().testTour(tour, pionSource[0], pyramide, K3,penalitetable)) {
                if (!penalite) {
                    pionDestination = new LesCoutsAccessibles().choisirUnPionAjouer(pyramide, K3,penalitetable);
                    pionSource[0] = new LesCoutsAccessibles().choisirUnPionAjouerSource(pyramide, K3,penalitetable);
                    JLabel pyramideLabel = LabelSource(pionSource[0], pyramidPanel);
                    if(pionSource[0].getCouleur()==CouleurPion.BLANC){
                        tour = changertour(tour);
                        pyramideLabel.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                        pionSource[0].setVideCase(true);
                    }
                    else if (pionDestination != null) {
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
                        System.out.println("pionSource {" + pionSource[0].getX() + " ; " + pionSource[0].getY() + " } + Couleur " + pionSource[0].getCouleur() + "--> pionDestination {" + pionDestination.getX() + " ; " + pionDestination.getY() + " } + Couleur " + pionDestination.getCouleur());
                        penalite = new GameController().getPenalite(pionDestination, pionDestination, K3);
                        if (!penalite) {
                            feedbackLabelcenter.setText("Deplacement effectué");
                            feedbackLabelcenter.setBackground(Color.BLACK);
                            feedbackLabelcenter.setForeground(Color.GREEN);
                            tour = changertour(tour);
                            penalite = false;
                        } else {
                            feedbackLabelcenter.setText("Deplacement effectué avec penalité(choisir un pion pour l'enlever)");
                            feedbackLabelcenter.setBackground(Color.BLACK);
                            feedbackLabelcenter.setForeground(Color.RED);
                            penalite = true;
                        }
                    } else {
                        example.setVisible(true);
                        example.showFeedback("Ce n'est pas votre tour", 1000);
                        System.out.println("Ce n'est pas votre tour");
                    }
                    labelr = null;
                } else {
                    penalite_func(row, col, pyramide, pyramidPanel, penalitetable, penalitePanel);
                }
            } else {
                example.setVisible(true);
                example.showFeedback("ce n'est pas votre tour", 1000);
                labelr = null;
            }
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
        //ImagePion.setImage(ImagePion.getImage().getScaledInstance(55, 40, Image.SCALE_DEFAULT));
        JLabel pyramideLabel = new JLabel(ImagePion);
        return pyramideLabel;
    }

    public JPanel baseK3(Table2D table2){
        JPanel baseK3 = new JPanel(new GridLayout(table2.getHeight(), table2.getWidth(), 0, 0));
        for (int i = 0; i < table2.getHeight(); i++) {
            for (int j = 0; j < table2.getWidth(); j++) {
                Pion pion = table2.getCases()[i][j];
                JLabel table2DLabel = createPyramideLabel(pion);
                baseK3.add(table2DLabel);
            }
        }

        ImageIcon img = new ImageIcon("sources/Images/K3baseFrame.png");

        // transparent background
        baseK3.setOpaque(false);
        baseK3.setBorder(BorderFactory.createEmptyBorder(0, 175, 0, 180));
        //set the panenl in the center
        baseK3.setAlignmentX(Component.CENTER_ALIGNMENT);
        return baseK3;
    }



    public JPanel Phase1(JPanel pyramid, JPanel table2D, JPanel baseK3, JButton undoButton, JButton readyButton) {
        JPanel phase1 = new JPanel();
        Box boxBaseReady = Box.createVerticalBox();
        boxBaseReady.add(baseK3);
        boxBaseReady.add(readyButton);
        Box boxUndoButton = Box.createHorizontalBox();
        boxUndoButton.add(undoButton);
        phase1.setLayout(new BorderLayout(30, 30));
        phase1.add(table2D, BorderLayout.CENTER);
        phase1.add(pyramid, BorderLayout.NORTH);
        //phase1.add(baseK3, BorderLayout.SOUTH);
        phase1.add(boxUndoButton, BorderLayout.EAST);
        phase1.add(boxBaseReady, BorderLayout.SOUTH);
        //add maring to the panel
        phase1.setBorder(BorderFactory.createEmptyBorder(90, 160, 50, 75));
        // transparent background
        //add margin to boxReadyButton
        boxBaseReady.setBorder(BorderFactory.createEmptyBorder(0,0,0 , 0));
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

        //add margin to the panel
        phase2.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 100));


        return phase2;
    }



    JPanel phase2Panel(Component C1, Component C2){
        //distance between the two components
        JPanel Panel = new JPanel(new BorderLayout());
        Panel.add(C1, BorderLayout.CENTER);
        Panel.add(C2, BorderLayout.NORTH);
        Panel.setOpaque(false);
        //add margin to the panel
        Panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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
                    ImageIcon ImagePion = pionPyramid.getImageIcon();
                    //ImagePion.setImage(ImagePion.getImage().getScaledInstance(55, 40, Image.SCALE_DEFAULT));
                    pyramidLabel.setIcon(ImagePion);
                    pionCount[0]--;

                }
            }
        });
        return undoButton;
    }

    public JButton readyButton(Pyramide p1Pyramide,Pyramide p2Pyramide, Pyramide K3, JPanel p1PyramidPanel, JPanel p2PyramidPanel, int PlayerNumber, Table2D penalitetable1, JPanel penalitePanel1, Table2D penalitetable2, JPanel penalitePanel2 ){
        readyButton = new JButton();
/*        if(pionCount[0]<21){
            readyButton.setEnabled(true);
        }
        else{
            readyButton.setEnabled(false);
            readyButton.setVisible(false);}*/

        readyButtonListener(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, PlayerNumber, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
        ImageIcon img = new ImageIcon("sources/Images/READY.png");

        ImageIcon imghover = new ImageIcon("sources/Images/READYhover.png");
        // styleButton(readyButton, img);
        hover(readyButton,img, imghover);
        styleButton(readyButton, img);
        //add padding to the button
        readyButton.setBorder(BorderFactory.createEmptyBorder(0,80,0 , 470));
        return readyButton;
    }

    private void readyButtonListener(Pyramide p1Pyramide,Pyramide p2Pyramide, Pyramide K3, JPanel p1PyramidPanel, JPanel p2PyramidPanel, int GameModeHandler, Table2D penalitetable1, JPanel penalitePanel1, Table2D penalitetable2, JPanel penalitePanel2){

        //TODO: change the name of the paremtre to numberplayer

        readyButton.addActionListener(new ActionListener() {
            //TODO: pass the panels by the parameters
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO if contre ia k==3 else k==2
                //switch between the panels
                JPanel K3Panel = pyramidePanel(K3);
                panelListener(K3Panel, K3Panel, K3, K3, 1, 1, null, null);//TODO: change the penalite table and panel

                if (GameModeHandler == 1) {
                    panelListener(p1PyramidPanel, K3Panel, p1Pyramide, K3, 2, 0, penalitetable2, penalitePanel2);
                    panelListener(p2PyramidPanel, K3Panel, p2Pyramide, K3, 3, 0, penalitetable1, penalitePanel1);
               /* LesCoutsAccessibles liste=new LesCoutsAccessibles();
                liste.afficherCoutsAccessibles(p2Pyramide, K3);*/
                    addPanel(Phase2(p1PyramidPanel, p2PyramidPanel, K3Panel, penalitePanel1, penalitePanel2), "phase2");
                    // mainFrame.setSize(1550,720);
                    cardLayout.show(mainPanel, "phase2");
                }else if(GameModeHandler == 2){
                    cardLayout.show(mainPanel, "phase11");
                }else if(GameModeHandler == 3){
                    panelListener(p1PyramidPanel, K3Panel, p1Pyramide, K3, 2, 1 , penalitetable2, penalitePanel2);
                    panelListener(p2PyramidPanel, K3Panel, p2Pyramide, K3, 2, 1 , penalitetable1, penalitePanel1);
                    addPanel(Phase2(p1PyramidPanel, p2PyramidPanel, K3Panel, penalitePanel1, penalitePanel2), "phase2");
                    cardLayout.show(mainPanel, "phase2");
                }
            }
        });
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
    private void hover(JButton button, ImageIcon img, ImageIcon imgHover){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(imgHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(img);
            }
        });

    }
    private void styleButton(JButton button, ImageIcon img){
        button.setIcon(img);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(null);
    }
}