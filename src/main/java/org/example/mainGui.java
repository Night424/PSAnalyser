package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.regex.Pattern;

public class mainGui extends JFrame {
    // Variables
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel sidebarPanel;
    private JLabel panelTitle;
    private JTextField passField;
    private JButton testButton;
    private JLabel passwordLabel;
    private JProgressBar passStrength;
    private JPanel centrePanel;
    private JButton closeButton;
    private JButton menuButton;
    private JPanel recPanel;
    private JPanel rec1Panel;

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

        recPanel.setBackground(new Color(44,44,59));
        rec1Panel.setBackground(new Color(44,44,59));
        recPanel.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        recPanel.setForeground(Color.WHITE);

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

        // Analyse Button
        testButton.addActionListener(e -> analysePassword());

        // Close Button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Move Window
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

        // Sidebar
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });

    }

    private void toggleSidebar(){
        int targetWidth = isExpanded ? 60 : 200;
        isExpanded = !isExpanded;

        sidebarPanel.setPreferredSize(new Dimension(targetWidth, getHeight()));
        mainPanel.revalidate();
    }

    // Analyse Password Function
    private void analysePassword(){
        String password = passField.getText();

        //Check Empty Field
        if (password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Analyse Password Strength
        int strength = calculateStrength(password);

        // Update Progress Bar
        passStrength.setValue(strength);

        // Update Recommendations
        updateRecommendations(password, strength);

    }


    private int calculateStrength(String password){
        int strength = 0;

        // Length check
        if (password.length() >= 8) strength += 30;

        // Check For Uppercase Letters
        if (Pattern.compile("[A-Z]").matcher(password).find()) strength += 20;

        // Lower Case
        if (Pattern.compile("[a-z]").matcher(password).find()) strength += 20;

        // Digits
        if (Pattern.compile("[0-9]").matcher(password).find()) strength += 20;

        // Special Characters
        if (Pattern.compile("[!@#$%^&*(),.?\":{}|<>]]").matcher(password).find()) strength += 20;

        // Cap Strength
        return Math.min(strength, 100);
    }

    private void updateRecommendations(String password, int strength){
        recPanel.removeAll();

        // Add recommendations
        if (password.length() < 8){
            recPanel.add(new JLabel("- Use at least 8 characters."));
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()){
            recPanel.add(new JLabel("- Include uppercase letters."));
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()){
            recPanel.add(new JLabel("- Include lowercase letters."));
        }

        if (!Pattern.compile("[0-9]").matcher(password).find()){
            recPanel.add(new JLabel("- Include digits."));
        }

        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()){
            recPanel.add(new JLabel("- Include special characters"));
        }

        // Strength Message
        if (strength < 50){
            recPanel.add(new JLabel("Password strength: Weak"));
        } else if (strength < 80) {
            recPanel.add(new JLabel("Password strength: Moderate"));
        } else {
            recPanel.add(new JLabel("Password strength: Strong"));
        }

        // Refresh Panel
        recPanel.revalidate();
        recPanel.repaint();

    }

    public static void main(String[] args) {
        new mainGui();
    }
}
