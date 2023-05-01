import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;
public class K3GUI extends JFrame {

    private JButton[][] player1Board;
    private JButton[][] player2Board;
    private JButton[] board;
    private JButton selectedButton;

    int tour=0;

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
                rowPanel1.add(button1);
                rowPanel2.add(button2);

                button1.setBackground(generateRandomColor());
                button1.setOpaque(true);
                button2.setBackground(generateRandomColor());
                button2.setOpaque(true);


                // add action listener to each button of player 1 board
                button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("tour ==="+tour);
                        if(tour%2==0)
                            selectedButton = button1;
                        else
                            selectedButton=null;

                    }
                });

                // add action listener to each button of player 2 board
                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("tour ==="+tour);
                        if(tour%2==1)
                            selectedButton = button2;
                        else
                            selectedButton=null;
                    }
                });
            }


            // decrease the gap between buttons
            rowPanel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            rowPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            leftPanel.add(rowPanel1);
            rightPanel.add(rowPanel2);
        }


        board = new JButton[36];
        ind=0;
        for (int i = 0; i < 8; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

            // add empty labels to center the buttons
            for (int j = 0; j < 8 - i; j++) {
                rowPanel.add(new JLabel(" "));
            }

            // add the buttons in a triangular shape
            for (int j = 0; j < i+1; j++) {
                JButton button = new JButton(String.valueOf(ind));
                board[ind] = button; // add button to board array
                ind++;
                rowPanel.add(button);

               // button.setEnabled(false);

                // Set background color only for buttons in the bottom row.
                if (i == 7) {
                    button.setBackground(generateRandomColor());
                    button.setOpaque(true);
                }

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (selectedButton != null) {
                            tour++;
                            getLine(button);
                            handleBoardMove(selectedButton, button);
                            selectedButton = null;
                        }
                    }
                });
//                for(int c = 0; c < 36; c++) {
//                    JButton b0 = board[c];
//                   if(checkButton(b0, board)) {
//                          b0.setEnabled(true);
//                   }
//                }

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
        if (currentButton.getBackground().getRed() == 238 && currentButton.getBackground().getGreen() == 238 && currentButton.getBackground().getBlue() == 238) {
            System.out.println("Selected button text: " + selectedButton.getText());
            System.out.println("Current button text: " + currentButton.getText());
            System.out.println("old: " + board[Integer.parseInt(currentButton.getText())].getBackground().getBlue());
            selectedButton.setEnabled(false);
            currentButton.setBackground(selectedButton.getBackground());
            //board[Integer.parseInt(currentButton.getText())]=currentButton;
            System.out.println("new: " + board[Integer.parseInt(currentButton.getText())].getBackground().getBlue());
        }else {
            System.out.println("You can't move to this button");
            return;
        }
    }

    private Color generateRandomColor() {
        Random random = new Random();
        int r, g, b;

        // Generate random values for r, g, and b within a smaller range to make the colors softer.
        int minColorValue = 100;
        int maxColorValue = 200;

        // Select a random color from the fixed set of colors.
        String[] allowedColors = {"red", "green", "blue", "yellow", "black"};
        int colorIndex = random.nextInt(allowedColors.length);
        String selectedColor = allowedColors[colorIndex];

        // Generate softer shades of the selected color.
        switch (selectedColor) {
            case "red":
                r = 244;
                g = 84;
                b = 63;
                break;
            case "green":
                r = 119;
                g = 250;
                b = 89;
                break;
            case "blue":
                r = 56;
                g = 174;
                b = 234;
                break;
            case "yellow":
                r = 224;
                g = 213;
                b = 59;
                break;
            case "black":
                r = 59;
                g = 59;
                b = 59;
                break;
            default:
                throw new IllegalStateException("Unexpected color: " + selectedColor);
        }

        return new Color(r, g, b);
    }


    //method give it the button it return the line of the button
    private int getLine(JButton button) {
        int line = 0;
        int number = Integer.parseInt(button.getText()); // Get the number from the button text
        double root = Math.sqrt(8 * number + 1); // Calculate the square root of (8 * number + 1)
        line = (int) Math.ceil((root-1) / 2); // Use the formula to calculate the line number and round up to the nearest integer
        System.out.println("line = " + line);
        return line;
    }

    //method to check if the button has two colored buttons
//    private boolean checkButton(JButton button, JButton[] board) {
//        int line;
//        int i;
//        //get the index where the button is located
//        i = Integer.parseInt(button.getText());
//        //check if the button has two colored buttons
//        line = getLine(button);
//        if(line==8) {
//            return false;
//        }else if (board[i + line].getBackground().getRGB()!=238 && board[i + line + 1].getBackground().getRGB()!=238) {
//            return true;
//        }
//        return false;
//    }



    public static void main(String[] args) {
        K3GUI game = new K3GUI();
    }
}