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
    @SuppressWarnings("unused")
    private JPanel optionsPanel;
    @SuppressWarnings("unused")
    private JPanel sensorReadoutPanel;


    private static FinchController bot;

    public static void main(String[] args) {

        // Set up the GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Fincher");
                frame.setContentPane(new MainWindow().mainPanel);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setFocusable(true);
                frame.setVisible(true);
            }
        });

        // Set up Finch
        bot = new FinchController();
        bot.connectToFinch();
        Runtime.getRuntime().addShutdownHook(new FinchShutdownHook(bot.finch));
    }

    public MainWindow() {

        // Quit
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(WindowConstants.EXIT_ON_CLOSE);
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
                    int newSpeed = speedSlider.getValue();
                    bot.setMovementSpeed(newSpeed);
                }
            }
        });
    }
}
