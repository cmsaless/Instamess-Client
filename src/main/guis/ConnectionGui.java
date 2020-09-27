package main.guis;

import main.client.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionGui {

    public ConnectionGui(Client client) {

        JFrame mainFrame = new JFrame("InstaMess Connect");

        JPanel mainPanel = new JPanel(new GridLayout(2,1));

        JLabel nameInfoLabel = createNameInfoLabel();
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

        JTextField ipField = new JTextField(25);
        JTextField portField = new JTextField(25);

        JButton connectBtn = new JButton("Connect");
        connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ip = ipField.getText();
                String port = portField.getText();

                client.attemptToConnectToServer(ip, port);
                frame.dispose();
            }
        });

        typingPanel.add(ipField);
        typingPanel.add(portField);
        typingPanel.add(connectBtn);

        return typingPanel;
    }

    private JLabel createNameInfoLabel() {
        JLabel nameInfoLabel = new JLabel("<html>Enter the server IP address and port</html>");
        nameInfoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        nameInfoLabel.setBorder(new EmptyBorder(0, 3, 0, 0));
        return nameInfoLabel;
    }

}
