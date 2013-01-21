package com.github.zachmassia.fincher;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow {
    private JSlider speedSlider;
    private JCheckBox avoidObjectsCheckBox;
    private JCheckBox enableAutopilotCheckBox;
    private JButton quitButton;
    private JPanel mainPanel;
    private JPanel optionsPanel;
    private JPanel sensorReadoutPanel;


    FinchController bot = new FinchController();

    public static void main(String[] args) {
        // Set up window
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setFocusable(true);
        frame.setVisible(true);

        //bot.connectToFinch();
    }

    public MainWindow() {

        // Quit
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Avoid objects
        avoidObjectsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement object avoidance
            }
        });

        // Autopilot
        enableAutopilotCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement autopilot
            }
        });

        // Movement speed
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Only update after user is done moving slider
                if (!speedSlider.getValueIsAdjusting()) {
                    int newSpeed = (int)speedSlider.getValue();
                    bot.setMovementSpeed(newSpeed);
                }
            }
        });
    }
}
