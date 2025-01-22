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
    private JLabel strengthLabel;
    private JLabel label;
    private JPanel rec2Panel;

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

        rec1Panel.setBackground(new Color(44,44,59));
        recPanel.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        recPanel.setForeground(Color.WHITE);
        recPanel.setBackground(new Color(44,44,59));
        rec2Panel.setBackground(new Color(44,44,59));

        // Set Window Icon
        URL iconURL = getClass().getResource("/images/lgogo.jpg");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        // ContentPane to mainPanel
        setContentPane(mainPanel);

        // Window Size and Title
        setTitle("Password Analyser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 450);
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
        rec2Panel.removeAll();

        // Font and Colour For Labels
        Font font = new Font("JetBrains Mono", Font.PLAIN,14);
        Color fontColor = Color.WHITE;

        boolean addedRecommendation = false;

        // Add recommendations
        if (password.length() < 8) {
            JLabel label = new JLabel("- Use at least 8 characters.");
            label.setFont(font);
            label.setForeground(fontColor);
            rec2Panel.add(label);
            addedRecommendation = true;
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            JLabel label = new JLabel("- Include uppercase letters.");
            label.setFont(font);
            label.setForeground(fontColor);
            rec2Panel.add(label);
            addedRecommendation = true;
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            JLabel label = new JLabel("- Include lowercase letters.");
            label.setFont(font);
            label.setForeground(fontColor);
            rec2Panel.add(label);
            addedRecommendation = true;
        }

        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            JLabel label = new JLabel("- Include digits.");
            label.setFont(font);
            label.setForeground(fontColor);
            rec2Panel.add(label);
            addedRecommendation = true;
        }

        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            JLabel label = new JLabel("- Include special characters.");
            label.setFont(font);
            label.setForeground(fontColor);
            rec2Panel.add(label);
            addedRecommendation = true;
        }

        // Add a message if no specific recommendation is needed
        if (!addedRecommendation) {
            JLabel allGoodLabel = new JLabel("Your password meets all recommendations!");
            allGoodLabel.setFont(font);
            allGoodLabel.setForeground(new Color(0, 255, 0)); // Green color for success
            recPanel.add(allGoodLabel);
        }

        // Strength level
        JLabel strengthLabel = getjLabel(strength, font);
        recPanel.add(strengthLabel);

        // Refresh Panel
        recPanel.revalidate();
        recPanel.repaint();
        rec2Panel.revalidate();
        rec2Panel.repaint();

    }

    private static JLabel getjLabel(int strength, Font font) {
        JLabel strengthLabel;
        if (strength < 50) {
            strengthLabel = new JLabel("Password strength: Weak");
            strengthLabel.setForeground(new Color(255, 0, 0)); // Red color for weak strength
        } else if (strength < 80) {
            strengthLabel = new JLabel("Password strength: Moderate");
            strengthLabel.setForeground(new Color(255, 165, 0)); // Orange color for moderate strength
        } else {
            strengthLabel = new JLabel("Password strength: Strong");
            strengthLabel.setForeground(new Color(0, 255, 0)); // Green color for strong strength
        }

        strengthLabel.setFont(font);
        return strengthLabel;
    }

    public static void main(String[] args) {
        new mainGui();
    }
}
