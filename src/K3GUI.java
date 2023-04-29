import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class K3GUI extends JFrame {

    private JButton[][] player1Board;
    private JButton[][] player2Board;
    private JButton[] board;
    private JButton selectedButton;

    public K3GUI() {
        // set up the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("K3 Game");
        setSize(1000, 750);

        // create the board panels
        JPanel leftPanel = new JPanel(new GridLayout(5, 5));
        JPanel centerPanel = new JPanel(new GridLayout(8, 8));
        JPanel rightPanel =  new JPanel(new GridLayout(5, 5));
        JPanel topcenterPanel = new JPanel(new GridLayout(1, 10));
        JPanel topleftPanel = new JPanel(new GridLayout(1, 10));
        JPanel toprightPanel = new JPanel(new GridLayout(1, 10));


// create the player 1 and player 2 boards
        player1Board = new JButton[5][5];
        player2Board = new JButton[5][5];
        int ind=0;
        for (int i = 1; i <= 5; i++) {
            JPanel rowPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            JPanel rowPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));


            // add empty labels to center the buttons
            for (int j = 1; j <= 5 - i; j++) {
                rowPanel1.add(new JLabel(" "));
                rowPanel2.add(new JLabel(" "));
            }

            // add the buttons in a triangular shape
            for (int j = 1; j <= i; j++) {
                JButton button1 = new JButton(String.valueOf(ind));
                JButton button2 = new JButton(String.valueOf(ind));
                ind++;
                button1.setBackground(Color.RED);
                button2.setBackground(Color.BLUE);

                rowPanel1.add(button1);
                rowPanel2.add(button2);

                // add action listener to each button of player 1 board
                button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectedButton = button1;
                    }
                });

                // add action listener to each button of player 2 board
                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectedButton = button2;
                    }
                });
            }

            // decrease the gap between buttons
            rowPanel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            rowPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            leftPanel.add(rowPanel1);
            rightPanel.add(rowPanel2);
        }

// create the center board
        board = new JButton[8];
        ind=0;
        for (int i = 1; i <= 8; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

            // add empty labels to center the buttons
            for (int j = 1; j <= 8 - i; j++) {
                rowPanel.add(new JLabel(" "));
            }

            // add the buttons in a triangular shape
            for (int j = 1; j <= i; j++) {
                JButton button = new JButton(String.valueOf(ind));
                ind++;
                rowPanel.add(button);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (selectedButton != null) {
                            handleBoardMove(selectedButton, button);
                            selectedButton = null;
                        }
                    }
                });

            }

            // decrease the gap between buttons
            rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            centerPanel.add(rowPanel);
        }

        // add empty panels to the bottom of the center panel
        for (int i = 1; i <= 8; i++) {
            topcenterPanel.add(new JPanel());
        }
        // add empty panels to the bottom of the center panel
        for (int i = 1; i <= 5; i++) {
            topleftPanel.add(new JPanel());
            toprightPanel.add(new JPanel());
        }

// add the top panel and center panel to a vertical box layout
        Box vbox = Box.createVerticalBox();
        vbox.add(topcenterPanel);
        vbox.add(centerPanel);
// add the top panel and left panel to a vertical box layout
        Box vbox1 = Box.createVerticalBox();
        vbox1.add(topleftPanel);
        vbox1.add(leftPanel);
// add the top panel and right panel to a vertical box layout
        Box vbox2 = Box.createVerticalBox();
        vbox2.add(toprightPanel);
        vbox2.add(rightPanel);

        // add the panels to the frame
        add(vbox1, BorderLayout.WEST);
        add(vbox, BorderLayout.CENTER);
        add(vbox2, BorderLayout.EAST);

        // show the window
        setVisible(true);
    }
    private void handleBoardMove(JButton selectedButton, JButton currentButton) {
        // print the selected button text and the current button text to the console
        System.out.println("Selected button text: " + selectedButton.getText());
        System.out.println("Current button text: " + currentButton.getText());
        selectedButton.setVisible(false);
        currentButton.setText(selectedButton.getText());
        currentButton.setBackground(selectedButton.getBackground());

    }

    public static void main(String[] args) {
        K3GUI game = new K3GUI();
    }
}
