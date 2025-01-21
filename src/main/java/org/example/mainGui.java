package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class mainGui extends JFrame {
    // Variables
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel sidebarPanel;
    private JLabel panelTitle;
    private JTextField passField;
    private JButton testButton;
    private JLabel passwordLabel;
    private JProgressBar progressBar1;
    private JPanel centrePanel;
    private JButton closeButton;
    private JButton menuButton;
    private JTextField ffffffffffffefrefrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrTextField;

    // Offset For Mouse
    private int xOffset = 0;
    private int yOffset = 0;

    // True or False For Side Panel
    private boolean isExpanded = false;

    // GUI Function
    public mainGui () {
        // Remove Window Frame
        setUndecorated(true);

        centrePanel.setBackground(new Color(44, 44, 59));

        // Set Window Icon
        URL iconURL = getClass().getResource("/images/lgogo.jpg");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        // ContentPane to mainPanel
        setContentPane(mainPanel);

        // Window Size and Title
        setTitle("Password Analyser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Close Button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });

        titlePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - xOffset, e.getYOnScreen() - yOffset);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });
    }

    private void toggleSidebar(){
        int targetWitdh = isExpanded ? 60 : 200;
        isExpanded = !isExpanded;

        sidebarPanel.setPreferredSize(new Dimension(targetWitdh, getHeight()));
        mainPanel.revalidate();
    }

    public static void main(String[] args) {
        new mainGui();
    }
}
