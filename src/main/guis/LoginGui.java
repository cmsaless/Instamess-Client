package main.guis;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.client.Client;

public class LoginGui {

    public LoginGui(Client client) {

        JFrame mainFrame = new JFrame("InstaMess Login");

        JPanel mainPanel = new JPanel(new GridLayout(2,1));

        JLabel nameInfoLabel = new JLabel("<html>Enter a username for yourself<br>(20 characters or less):</html>");
        nameInfoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        nameInfoLabel.setBorder(new EmptyBorder(0, 3, 0, 0));

        JPanel typingPanel = createTypingPanel(client, mainFrame);

        mainPanel.add(nameInfoLabel);
        mainPanel.add(typingPanel);

        mainFrame.add(mainPanel);

        mainFrame.pack();

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    private JPanel createTypingPanel(Client client, JFrame frame) {

        JPanel typingPanel = new JPanel();

        JTextField nameField = new JTextField(25);
        typingPanel.add(nameField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.createUsername(nameField.getText());
                frame.dispose();
            }
        });

        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        typingPanel.add(nameField);
        typingPanel.add(loginButton);

        return typingPanel;
    }
}
