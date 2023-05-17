package Controller;
import Model.Pion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Feedback extends JFrame {
    private JLabel feedbackLabel;
    public Feedback() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 100);
        setLayout(new java.awt.FlowLayout());

        feedbackLabel = new JLabel();
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(feedbackLabel);
    }

    public void showFeedback(String feedback, int duration) {
        feedbackLabel.setText(feedback);

        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedbackLabel.setText("");
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void showFeedbackArraylist(ArrayList<Pion> listePionSource, ArrayList<Pion> listePionDestination, int duration) {
        feedbackLabel.setText("Les couts accessibles sont : ");
        for (int i = 0; i < listePionSource.size(); i++) {
            feedbackLabel.setText(feedbackLabel.getText() + "[" + listePionSource.get(i).getX() + ";" + listePionSource.get(i).getY() + "] --> [" + listePionDestination.get(i).getX() + ";" + listePionDestination.get(i).getY() + "]    |   ");
        }
        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedbackLabel.setText("");
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

}
