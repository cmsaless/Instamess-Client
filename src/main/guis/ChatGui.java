package main.guis;

import main.client.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLEditorKit;

public class ChatGui {

    private Client client;

    private JFrame mainFrame;

    private JEditorPane chatPane;
    private HTMLEditorKit editor;

    public ChatGui(Client client) {

        this.client = client;

        mainFrame = new JFrame("InstaMess");

        JPanel displayPanel = createDisplayPanel();
        JPanel messagingPanel = createMessagingPanel();

        mainFrame.add(displayPanel, BorderLayout.CENTER);
        mainFrame.add(messagingPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                client.createExitMessage();
                System.exit(0);
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }

    private JPanel createDisplayPanel() {

        JPanel displayPanel = new JPanel();

        JPanel noWrapPanel = new JPanel(new BorderLayout());

        chatPane = new JEditorPane("text/html", null);
        chatPane.setEditable(false);
        chatPane.setText("<body style=\"font-family:consolas; font-size:14pt;\">");

        editor = (HTMLEditorKit) chatPane.getEditorKit();

        noWrapPanel.add(chatPane);

        JScrollPane scrollPane = new JScrollPane(noWrapPanel);
        scrollPane.setViewportView(chatPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(900, 500));

        displayPanel.add(scrollPane);

        return displayPanel;
    }

    private JPanel createMessagingPanel() {

        JPanel messagingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField textField = new JTextField();
        textField.setDocument(new JTextFieldLimit(250));
        textField.setPreferredSize(new Dimension(600, 25));
        textField.setFont(new Font("Consolas", Font.BOLD, 12));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.createMessage(textField.getText());
                textField.setText("");
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        messagingPanel.add(textField);
        messagingPanel.add(sendButton);

        return messagingPanel;
    }

    public void postMessageToGui(String message) {

        StringReader reader = new StringReader(message);

        try {
            editor.read(reader, chatPane.getDocument(), chatPane.getDocument().getLength());
        } catch (IOException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public void setVisible(boolean b) {
        mainFrame.setVisible(b);
    }

}
