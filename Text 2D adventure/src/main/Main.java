package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");//create a windows named 2D adventure.

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);//add the gamePanel to this windows

        window.pack();//we can see the actual screen
        //window.pach(): causes this window to be sized to fit the preferred size
        // and layouts of its subcomponents(=GamePanel)

        window.setLocationRelativeTo(null);
        //Not specify the location of the window == this window will be displayed
        // at the center of the screen.
        window.setVisible(true);//so we can see this window

    }
}