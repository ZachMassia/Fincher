package com.github.zachmassia.fincher;

public class Fincher {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainWindow window = new MainWindow();
            }
        });
    }
}
