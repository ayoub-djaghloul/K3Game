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
        int nb_beige_buttons_P1 = 0;
        int nb_beige_buttons_P2 = 0;
        Color beige = new Color(245, 245, 220);
        Color randomColor;
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
                // if we have more than 2 beige buttons, we generate a another color that is not beige
                if (nb_beige_buttons_P1 >= 2) {
                    randomColor = generateRandomColor();
                    while (randomColor == beige) {
                        randomColor = generateRandomColor();
                    }
                    button1.setBackground(randomColor);
                    button1.setOpaque(true);
                } else {
                    button1.setBackground(beige);
                    button1.setOpaque(true);
                    nb_beige_buttons_P1++;
                }

                if (nb_beige_buttons_P2 >= 2) {
                    randomColor = generateRandomColor();
                    while (randomColor == beige) {
                        randomColor = generateRandomColor();
                    }
                    button2.setBackground(randomColor);
                    button2.setOpaque(true);
                } else {
                    button2.setBackground(beige);
                    button2.setOpaque(true);
                    nb_beige_buttons_P2++;
                }


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

                // Set background color only for buttons in the bottom row.
                if (i == 8) {
                    button.setBackground(generateRandomColor());
                    button.setOpaque(true);
                }

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

    private Color generateRandomColor() {
        Random random = new Random();
        int r, g, b;

        // Generate random values for r, g, and b within a smaller range to make the colors softer.
        int minColorValue = 100;
        int maxColorValue = 200;

        // Select a random color from the fixed set of colors.
        String[] allowedColors = {"red", "green", "blue", "yellow", "black", "white","beige"};
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
            case "white":
                r = 255;
                g = 255;
                b = 255;
                break;
            case "beige":
                r = 245;
                g = 245;
                b = 220;
                break;
            default:
                throw new IllegalStateException("Unexpected color: " + selectedColor);
        }

        return new Color(r, g, b);
    }
    public static void main(String[] args) {
        K3GUI game = new K3GUI();
    }
}
