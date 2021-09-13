package project1;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.io.File;

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

    private JFileChooser file;

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

        file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text", ".txt");
        file.setFileFilter(filter);


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
        continueButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        subButton.addActionListener(new ButtonListener());
        stringInputButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());

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
                    JOptionPane.showMessageDialog(null, "Enter an integer in the fields: \n\"Hours\", \"Minutes\", \"Seconds\"");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error found in fields:  \n\"Hours\", \"Minutes\", \"Seconds\"");
                }
            }

            if (event.getSource() == continueButton) {
                javaTimer.start();
            }

            //Adds the number of seconds indicated in the textbox (as an int) to the associated CountDowntimer
            if (event.getSource() == addButton) {
                int seconds;
                try {
                    seconds = Integer.parseInt(addSecondsField.getText());
                    watch.add(seconds);
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in \"Addition\" field");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in \"Addition\" field");
                }

            }

            if (event.getSource() == subButton) {
                int seconds;
                try {
                    seconds = Integer.parseInt(addSecondsField.getText());
                    watch.sub(seconds);
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in \"Subtraction\" field");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in \"Subtraction\" field");
                }
            }

            if (event.getSource() == stringInputButton) {
                try {
                    watch = new CountDownTimer(newStringField.getText());
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter a time in \"New\" field");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in \"New\" field");
                }

            }

            if (event.getSource() == loadButton) {
                file.setDialogTitle("Select a file to load");
                int returnVal = file.showOpenDialog(file);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File opened_file = file.getSelectedFile();
                        watch.load(opened_file.getAbsolutePath());
                    } catch (NullPointerException io) {
                        JOptionPane.showMessageDialog(null, "Select a file to load");
                    }
                }
            }

            if (event.getSource() == saveButton) {
                file.setDialogTitle("Specify a file to save");
                int returnVal = file.showOpenDialog(file);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File save_file = file.getSelectedFile();
                        watch.save(save_file.getAbsolutePath());
                    } catch (IllegalArgumentException io) {
                        JOptionPane.showMessageDialog(null, "Select a file to save");
                    }
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
