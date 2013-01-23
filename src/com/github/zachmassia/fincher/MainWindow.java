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

    private boolean[] isMoving = new boolean[4]; // { UP, DOWN, LEFT, RIGHT }


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
        bot.setMovementTime(75);
        Runtime.getRuntime().addShutdownHook(new FinchShutdownHook(bot.finch));
    }

    public MainWindow() {
        // Set initial speed to slider's initial position
        bot.setMovementSpeed(speedSlider.getValue());

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
                    int newSpeed = speedSlider.getValue();
                    bot.setMovementSpeed(newSpeed);
                }
            }
        });

        // Bind up arrow to FinchController.moveUp()
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        mainPanel.getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isMoving[0]) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            isMoving[0] = true;
                            bot.moveUp();
                            return null;
                        }

                        @Override
                        protected void done() {
                            isMoving[0] = false;
                        }
                    };
                    worker.execute();
                }
            }
        });

        // Bind down arrow to FinchController.moveDown()
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        mainPanel.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isMoving[1]) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            isMoving[1] = true;
                            bot.moveDown();
                            return null;
                        }

                        @Override
                        protected void done() {
                            isMoving[1] = false;
                        }
                    };
                    worker.execute();
                }
            }
        });

        // Bind left arrow to FinchController.pivotLeft()
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "pivotLeft");
        mainPanel.getActionMap().put("pivotLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isMoving[2]) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            isMoving[2] = true;
                            bot.pivotLeft();
                            return null;
                        }

                        @Override
                        protected void done() {
                            isMoving[2] = false;
                        }
                    };
                    worker.execute();
                }
            }
        });

        // Bind right arrow to FinchController.pivotRight()
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "pivotRight");
        mainPanel.getActionMap().put("pivotRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isMoving[3]) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            isMoving[3] = true;
                            bot.pivotRight();
                            return null;
                        }

                        @Override
                        protected void done() {
                            isMoving[3] = false;
                        }
                    };
                    worker.execute();
                }
            }
        });
    }
}
