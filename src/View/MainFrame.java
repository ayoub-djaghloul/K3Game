package View;
import AI.RandomAI;
import Controller.GameController;
import Controller.LesCoutsAccessibles;
import Model.*;
import Model.Pion;
import launcher.GameLauncher;

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
    ImageIcon EXITIMG = new ImageIcon("sources/Images/EXIT.png");
    ImageIcon EXITIMGHover = new ImageIcon("sources/Images/EXIThover.png");
    ImageIcon REPLAYIMG = new ImageIcon("sources/Images/REPLAY.png");
    ImageIcon REPLAYIMGHover = new ImageIcon("sources/Images/REPLAYhover.png");
    ImageIcon undoIMG = new ImageIcon("sources/Images/undo.png");
    ImageIcon undohoverIMG = new ImageIcon("sources/Images/undohover.png");
    JLabel feedbackLabelcenter;
    JButton readyButton;
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
                JPanel table2DP1Panel = tabel2DPanel(table2DP1, 1);
                JPanel p1PyramidPanel = pyramidePanel(p1Pyramide);
                JPanel table2DP2Panel = tabel2DPanel(table2DP2,1);
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
                    JButton readyButton = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 1, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    JButton undoButton = undoButton(table2DP1, table2DP1Panel, p1PyramidPanel, readyButton);
                    panelListener(p1PyramidPanel, null, p1Pyramide, K3, 1, 0, penalitetable, penalitePanel, readyButton, undoButton);
                    addPanel(Phase1(table2DP1Panel, p1PyramidPanel, baseK3Panel, undoButton, readyButton,1), "phase1");
                    cardLayout.show(mainPanel, "phase1");
                }else if(gameMode==2){
                    JButton readyButton1 = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 2, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    JButton readyButton2 = readyButton(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, 3,penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
                    JButton undoButtonP1 = undoButton(table2DP1, table2DP1Panel, p1PyramidPanel, readyButton1);
                    JButton undoButtonP2 = undoButton(table2DP1, table2DP2Panel, p2PyramidPanel, readyButton2);
                    panelListener(p1PyramidPanel, null, p1Pyramide, K3, 1, 0, penalitetable, penalitePanel, readyButton1, undoButtonP1);
                    panelListener(p2PyramidPanel, null, p2Pyramide, K3, 1, 0, penalitetable, penalitePanel, readyButton2, undoButtonP2);
                    addPanel(Phase1(table2DP1Panel, p1PyramidPanel, baseK3Panel, undoButtonP1, readyButton1,1), "phase10");
                    addPanel(Phase1(table2DP2Panel, p2PyramidPanel, baseK3Panel2, undoButtonP2, readyButton2,2), "phase11");
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

    public JPanel tabel2DPanel(Table2D table2D, int mode) {
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
                        switch (mode){
                            case 1:{
                                pionSource[0]=table2D.getPion(finalI, finalJ);
                                labelr = table2DLabel;
                                history2Dtable.push(pionSource[0]);
                            }
                            break;
                            case 2: {
                                if (new GameController().testTourSeule(tour, pionSource[0])==false) {
                                    pionSource[0]=table2D.getPion(finalI, finalJ);
                                    labelr = table2DLabel;
                                    history2Dtable.push(pionSource[0]);
                                }
                                break;
                            }

                        }
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

        }
        return pionPanel;
    }


    private void panelListener(JPanel pyramidPanel, JPanel K3Panel, Pyramide pyramide, Pyramide K3, int s, int option, Table2D penalitetable, JPanel penalitePanel, JButton readyButton, JButton undoButton){
        for(int i = 0; i < pyramidPanel.getComponentCount(); i++){
            JPanel pionPanel = (JPanel) pyramidPanel.getComponent(i);
            for(int j = 0; j < pionPanel.getComponentCount(); j++){
                JLabel pyramideLabel = (JLabel) pionPanel.getComponent(j);
                int finalRow = i;
                int finalCol = j;
                pyramideLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        pyramideLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    public void mouseClicked(java.awt.event.MouseEvent evt) {

                        handleMouseClicked(K3Panel,s, option, finalRow, finalCol, pyramide, K3, pyramideLabel, pyramidPanel, penalitetable, penalitePanel, readyButton, undoButton);
                    }
                });
            }
        }
    }

    private void handleMouseClicked(JPanel k3Panel, int s, int option, int row, int col, Pyramide pyramide, Pyramide K3, JLabel pyramideLabel, JPanel pyramidPanel,Table2D penalitetable, JPanel penalitePanel, JButton readyButton, JButton undoButton) {
        switch (s) {
            case 1:
                handleCase1(option, row, col, pyramide, pyramideLabel, readyButton, undoButton);
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

    private void handleCase1(int option, int row, int col, Pyramide pyramide,JLabel pyramideLabel, JButton readyButton, JButton undoButton) {
        {
            if (labelr != null) {
                Pion pionDestination = pyramide.getPion(row, col);
                if(option ==1) {//construction de la pyramide K3 avec ordre

                    if (tour!=1){
                        //set the color of the label
                        feedbackLabelcenter.setForeground(Color.BLACK);
                        feedbackLabelcenter.setText("Player 1 (Left Pyramid)");
                    }else {
                        feedbackLabelcenter.setForeground(Color.BLACK);
                        feedbackLabelcenter.setText("Player 2 (Right Pyramid)");
                    }

                    if(new GameController().testDeplacementPion(pionSource[0], pionDestination, pyramide)==true) {
                        //handle case when the move is possible
                        penalite=new GameController().getPenalite(pionSource[0], pionDestination, pyramide);
                        pyramideLabel.setIcon(labelr.getIcon());
                        labelr.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                        labelr = null;
                        if(!penalite){
                            tour=changertour(tour);
                            penalite=false;
                        }
                        else{
                            feedbackLabelcenter.setText("penalization");
                            feedbackLabelcenter.setBackground(Color.BLACK);
                            feedbackLabelcenter.setForeground(Color.RED);
                            penalite=true;
                        }
                    }
                    else {
                        //handle case when the move is not possible
                        labelr = null;
                    }
                }else{//construction du premiere pyramide sans ordre
                    if(pionDestination.estVide()==true) {
                        pionDestination.replacePion(pionSource[0]);
                        pyramideLabel.setIcon(labelr.getIcon());
                        pionCount[0]++;
                        System.out.println(pionCount[0]);
                        labelr.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                        labelr = null;
                        pionDestination.setVideCase(false);
                        if (pionCount[0] == 21) {
                            readyButton.setEnabled(true);
                        }
                        if (pionCount[0] <= 0) {
                            undoButton.setEnabled(false);
                        } else {
                            undoButton.setEnabled(true);
                        }
                    }
                }
                historyPyramid.push(pionDestination);
            }
            else{
                if(feedbackLabelcenter!=null) {
                    feedbackLabelcenter.setText("Pion is selected");
                    feedbackLabelcenter.setForeground(Color.GREEN);
                }else{
                    feedbackLabelcenter.setText("Select a pion");
                    feedbackLabelcenter.setForeground(Color.RED);
                }
            }
        }
    }

    private int changertour(int tour) {
        tour=(tour == 1) ? 2 : 1;
        return tour;
    }


    private JPanel phase3(String message){
        JPanel phase3Panel = new JPanel();
        JButton quitterBox = quitter();
        JButton rejouerBox = rejouer();
        Box quitterRejouerBox = Box.createHorizontalBox();
        quitterRejouerBox.add(quitterBox);
        quitterRejouerBox.add(rejouerBox);
        quitterRejouerBox.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        //display the winner
        JLabel winnerLabel = new JLabel(message);
        // Text BLACK and big in the center
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setVerticalAlignment(JLabel.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winnerLabel.setForeground(Color.BLACK);
        //add the winner label to the panel
        JPanel winnerPanel = new JPanel();
        winnerPanel.setLayout(new BorderLayout());
        winnerPanel.add(winnerLabel, BorderLayout.CENTER);
        winnerPanel.setOpaque(false);
        //add margin to the panel
        phase3Panel.setLayout(new BorderLayout());
        phase3Panel.add(quitterRejouerBox, BorderLayout.SOUTH);

        phase3Panel.setOpaque(false);
        phase3Panel.add(winnerPanel, BorderLayout.CENTER);
        //add margin to the panel
        phase3Panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 200, 300));
        //
        return phase3Panel;
    }






    private void handleCase2(int row, int col, Pyramide pyramide, JLabel pyramideLabel, Pyramide K3, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        {
            if (!pyramideLabel.getIcon().toString().equals("sources/Images/EMPTY.png")&&labelr==null) {
                if(new LesCoutsAccessibles().choisirUnPionAjouerSource1(pyramide, K3,penalitetable)==null){
                    String message = "THE WINNER IS THE PLAYER " + changertour(tour);
                    addPanel(phase3(message), "phase3");
                    cardLayout.show(mainPanel, "phase3");
                }
                else{
                    pionSource[0] = pyramide.getPion(row, col);
                    if (new GameController().testTour(tour, pionSource[0], pyramide, K3, penalitetable)) {
                        if (!penalite) {
                            if (new GameController().testAvantDeplacement(pionSource[0], pyramide) == false) {

                                feedbackLabelcenter.setText("pion not naccessible");
                                labelr = null;
                            } else
                            {
                                if (pionSource[0].getCouleur() == CouleurPion.BLANC) {
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
                        feedbackLabelcenter.setText("It's not your turn");
                    }
                }
            } else {
                System.out.println();
                feedbackLabelcenter.setText("Choose a pion");
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
                    if(pionSource[0]==null){
                        String message = "The winner is the player " + changertour(tour) ;
                        addPanel(phase3(message), "phase3");
                        cardLayout.show(mainPanel, "phase3");
                    }else{
                        JLabel pyramideLabel = LabelSource(pionSource[0], pyramidPanel);
                        if(pionSource[0].getCouleur()==CouleurPion.BLANC){
                            tour = changertour(tour);
                            pyramideLabel.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                            pionSource[0].setVideCase(true);
                        }
                        else if (pionDestination != null) {

                            // Get the JLabel from k3 panel using the pionDestination's coordinates
                            JLabel distinationLabel = LabelSource(pionDestination, k3);
                            distinationLabel.setIcon(pionSource[0].getImageIcon());
                            pionDestination.replacePion(pionSource[0]);
                            pionDestination.setVideCase(false);
                            JLabel sourcePanel = LabelSource(pionSource[0], pyramidPanel);
                            sourcePanel.setIcon(new ImageIcon("sources/Images/EMPTY.png"));
                            pionSource[0].setVideCase(true);
                            //System.out.println("pionSource {" + pionSource[0].getX() + " ; " + pionSource[0].getY() + " } + Couleur " + pionSource[0].getCouleur() + "--> pionDestination {" + pionDestination.getX() + " ; " + pionDestination.getY() + " } + Couleur " + pionDestination.getCouleur());
                            penalite = new GameController().getPenalite(pionDestination, pionDestination, K3);
                            if (!penalite) {

                                tour = changertour(tour);
                                penalite = false;
                            } else {
                                feedbackLabelcenter.setText("penalization");
                                //feedbackLabelcenter.setBackground(Color.BLACK);
                                feedbackLabelcenter.setForeground(Color.RED);
                                penalite = true;
                            }
                        } else {
                            feedbackLabelcenter.setText("Ce n'est pas votre tour");
                            //System.out.println("Ce n'est pas votre tour");
                        }
                        labelr = null;
                    }

                } else {
                    penalite_func(row, col, pyramide, pyramidPanel, penalitetable, penalitePanel);
                }
            } else {
                labelr = null;
            }
        }
    }

    private void penalite_func(int row,int col,Pyramide pyramide, JPanel pyramidPanel, Table2D penalitetable, JPanel penalitePanel) {
        if(new GameController().testAvantDeplacement(pionSource[0],pyramide)==false){
            labelr = null;
        }
        else{
            penalite = false;
            tour=changertour(tour);
            //choisi un pion a enlever from peramite panel
            pionSource[0] = pyramide.getPion(row, col);
            pionSource[0].setVideCase(true);
            Pion piondestination = le_premier_libre_penalite(penalitetable);
            //System.out.println("piondestination x et y:  " + piondestination.getX() + "  " + piondestination.getY());

            piondestination.replacePion(pionSource[0]);
            piondestination.setVideCase(false);
            //System.out.println("piondestination: couleur " + piondestination.getCouleur() + " vide ou nn !  " + piondestination.estVide());
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



    public JPanel Phase1(JPanel pyramid, JPanel table2D, JPanel baseK3, JButton undoButton, JButton readyButton,int num) {
        JPanel phase1 = new JPanel();
        JLabel feedbackLabelcenter = new JLabel("Pyramide Du Player "+ num);
        feedbackLabelcenter.setFont(new Font("Serif", Font.BOLD, 30));
        feedbackLabelcenter.setForeground(Color.BLACK);
        Box boxBaseReady = Box.createVerticalBox();
        boxBaseReady.add(baseK3);
        boxBaseReady.add(readyButton);
        Box boxUndoButton = Box.createHorizontalBox();
        boxUndoButton.add(undoButton);
        phase1.setLayout(new BorderLayout(30, 30));
        phase1.add(feedbackLabelcenter, BorderLayout.WEST);
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
        feedbackLabelcenter = new JLabel("Player 1 (Left Pyramid)");
        feedbackLabelcenter.setFont(new Font("Serif", Font.BOLD, 30));
        feedbackLabelcenter.setForeground(Color.BLACK);
        //add margin to feedbackLabelcenter
        feedbackLabelcenter.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
        penalitePanel1.setLayout(new GridLayout());
        penalitePanel2.setLayout(new GridLayout());
        //add quitter button
        JButton quitter = quitter();
        JButton rejouer = rejouer();
        Box boxQuitter = Box.createHorizontalBox();
        boxQuitter.add(quitter);
        boxQuitter.add(rejouer);
        //add margin to boxQuitter
        boxQuitter.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 0));


        //leftPanel
        JPanel leftPanel = phase2Panel(p1Pyramide, penalitePanel1, null);
        phase2.add(leftPanel);
        //centerPanel
        JPanel centerPanel = phase2Panel(feedbackLabelcenter, K3, boxQuitter);
        phase2.add(centerPanel);
        //rightPanel
        JPanel rightPanel = phase2Panel(p2Pyramide, penalitePanel2, null);

        rightPanel.setOpaque(false);
        phase2.add(rightPanel);

        phase2.setOpaque(false);
        phase2.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 200));


        return phase2;
    }



    JPanel phase2Panel(Component C1, Component C2, Component C3){
        //distance between the two components
        JPanel Panel = new JPanel(new BorderLayout());
        Panel.add(C1, BorderLayout.NORTH);
        Panel.add(C2, BorderLayout.CENTER);
        if(C3!=null)
            Panel.add(C3, BorderLayout.SOUTH);
        Panel.setOpaque(false);
        //add margin to the panel
        Panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0 ));
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
    public JButton undoButton(Table2D table2D , JPanel tablePanel, JPanel pyramidPanel, JButton readyButton){
        JButton undoButton = new JButton();
        if(pionCount[0]<=0){undoButton.setEnabled(false);}
        else{undoButton.setEnabled(true);}
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
                    if(pionCount[0]==0){undoButton.setEnabled(false);}
                    else{undoButton.setEnabled(true);}
                    if(pionCount[0]<21){readyButton.setEnabled(false);}

                }
            }
        });


        styleButton(undoButton, undoIMG);
        hover(undoButton, undoIMG, undohoverIMG);


        return undoButton;
    }

    public JButton readyButton(Pyramide p1Pyramide,Pyramide p2Pyramide, Pyramide K3, JPanel p1PyramidPanel, JPanel p2PyramidPanel, int PlayerNumber, Table2D penalitetable1, JPanel penalitePanel1, Table2D penalitetable2, JPanel penalitePanel2 ){
        readyButton = new JButton();
        readyButtonListener(p1Pyramide, p2Pyramide, K3, p1PyramidPanel, p2PyramidPanel, PlayerNumber, penalitetable1, penalitePanel1, penalitetable2, penalitePanel2);
        ImageIcon img = new ImageIcon("sources/Images/READY.png");

        ImageIcon imghover = new ImageIcon("sources/Images/READYhover.png");
        // styleButton(readyButton, img);
        hover(readyButton,img, imghover);
        styleButton(readyButton, img);
        //add padding to the button
        readyButton.setBorder(BorderFactory.createEmptyBorder(0,80,0 , 470));
        readyButton.setEnabled(false);
        return readyButton;
    }

    private void readyButtonListener(Pyramide p1Pyramide,Pyramide p2Pyramide, Pyramide K3, JPanel p1PyramidPanel, JPanel p2PyramidPanel, int GameModeHandler, Table2D penalitetable1, JPanel penalitePanel1, Table2D penalitetable2, JPanel penalitePanel2){

        //TODO: change the name of the paremtre to numberplayer

        readyButton.addActionListener(new ActionListener() {
            //TODO: pass the panels by the parameters
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO if contre ia k==3 else k==2
                pionCount[0] = 0;
                //switch between the panels
                JPanel K3Panel = pyramidePanel(K3);
                panelListener(K3Panel, K3Panel, K3, K3, 1, 1, null, null, null, null);//TODO: change the penalite table and panel

                if (GameModeHandler == 1) {
                    panelListener(p1PyramidPanel, K3Panel, p1Pyramide, K3, 2, 0, penalitetable2, penalitePanel2, null, null);
                    panelListener(p2PyramidPanel, K3Panel, p2Pyramide, K3, 3, 0, penalitetable1, penalitePanel1, null, null);
               /* LesCoutsAccessibles liste=new LesCoutsAccessibles();
                liste.afficherCoutsAccessibles(p2Pyramide, K3);*/
                    addPanel(Phase2(p1PyramidPanel, p2PyramidPanel, K3Panel, penalitePanel1, penalitePanel2), "phase2");
                    // mainFrame.setSize(1550,720);
                    cardLayout.show(mainPanel, "phase2");
                }else if(GameModeHandler == 2){
                    cardLayout.show(mainPanel, "phase11");
                }else if(GameModeHandler == 3){
                    panelListener(p1PyramidPanel, K3Panel, p1Pyramide, K3, 2, 1 , penalitetable2, penalitePanel2, null, null);
                    panelListener(p2PyramidPanel, K3Panel, p2Pyramide, K3, 2, 1 , penalitetable1, penalitePanel1, null, null );
                    addPanel(Phase2(p1PyramidPanel, p2PyramidPanel, K3Panel, penalitePanel1, penalitePanel2), "phase2");
                    cardLayout.show(mainPanel, "phase2");
                }
            }
        });
    }

    public JButton quitter(){
        JButton quitter = new JButton();
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

        styleButton(quitter, EXITIMG);
        hover(quitter, EXITIMG, EXITIMGHover);
        return quitter;
    }

    public JButton rejouer(){
        JButton rejouer = new JButton();
        //size of the button
        rejouer.setPreferredSize(new Dimension(50, 50));
        //width of button border
        rejouer.setBorder(new LineBorder(Color.BLACK, 5));
        rejouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new GameLauncher().launcher();

            }
        });


        styleButton(rejouer, REPLAYIMG);
        hover(rejouer, REPLAYIMG, REPLAYIMGHover);
        return rejouer;
    }



    public JPanel penalitePanel(Table2D penalité){
        JPanel pionPenalité;
        pionPenalité = tabel2DPanel(penalité, 2);
        return pionPenalité;
    }
    private void hover(JButton button, ImageIcon img, ImageIcon imgHover){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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