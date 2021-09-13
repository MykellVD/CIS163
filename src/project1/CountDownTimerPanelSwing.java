package project1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;

/**
 * Write a description  here.
 *
 * @author Ferguson
 * @version August 7, 2021
 */
public class CountDownTimerPanelSwing extends JPanel {

	private CountDownTimer watch;
    private Timer javaTimer;

    private JButton startButton, stopButton, saveButton, loadButton, addButton, subButton, stringInputButton, continueButton;
    private JTextField hourField, minField, secondField, addSecondsField, subSecondsField, newStringField;

    private JLabel lblTime, hourTxt, minTxt, secondText;

    public CountDownTimerPanelSwing() {

        // create the game object as well as the GUI1024 Frame
        watch = new CountDownTimer();
        javaTimer = new Timer(1000, new TimerListener());

        setLayout(new GridLayout(7, 3));
        setBackground(Color.lightGray);

        hourTxt = new JLabel("Hours:         ", JLabel.RIGHT);

        hourField = new JTextField();

        minTxt = new JLabel("Minutes:     ", JLabel.RIGHT);

        minField = new JTextField();

        secondText = new JLabel("Seconds:    ", JLabel.RIGHT);

        secondField = new JTextField();

        startButton = new JButton("Start");

        stopButton = new JButton("Stop");

        continueButton = new JButton("Continue");

        addButton = new JButton("Add");

        addSecondsField = new JTextField();

        subButton = new JButton("Subtract");

        subSecondsField = new JTextField();

        loadButton = new JButton("Load");

        saveButton = new JButton("Save");

        stringInputButton = new JButton("New");

        newStringField = new JTextField();

        lblTime = new JLabel(watch.toString(), JLabel.CENTER);
//        lblTime.setHorizontalTextPosition();
//        lblTime.setText();


        //Layout
        // hours:     | text field | start button
        // mins       | text field | stop button
        // secs       | text field | continue
        // add button | text field | load
        // sub button | text field | save
        // new button | text field | (blank)
        // (blank)    | Time:      | --:--:--

        add(hourTxt);           add(hourField);         add(startButton);
        add(minTxt);            add(minField);          add(stopButton);
        add(secondText);        add(secondField);       add(continueButton);
        add(addButton);         add(addSecondsField);   add(loadButton);
        add(subButton);         add(subSecondsField);   add(saveButton);
        add(stringInputButton); add(newStringField);    add(new JLabel());
        add(new JLabel()); add(new JLabel("Time:    ", JLabel.RIGHT)); add(lblTime);


        startButton.addActionListener(new ButtonListener());
        stopButton.addActionListener(new ButtonListener());

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
            	int hours, mins, secs;
                try {
                    hours = Integer.parseInt(hourField.getText());
                    mins = Integer.parseInt(minField.getText());
                    secs = Integer.parseInt(secondField.getText());
                    watch = new CountDownTimer(hours, mins, secs);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            lblTime.setText(watch.toString());
        }

    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                watch.sub(1);
                lblTime.setText(watch.toString());
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Happy New Year!!!");
                javaTimer.stop();
			}
        }
    }
}
