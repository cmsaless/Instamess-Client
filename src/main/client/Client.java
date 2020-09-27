package main.client;

import main.guis.ChatGui;
import main.guis.ConnectionGui;
import main.guis.LoginGui;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private ClientSocketThread clientSocketThread;
    private String username;

    private ChatGui chatGui;

    public Client() {
        chatGui = new ChatGui(this);
        new ConnectionGui(this);
    }

    public void attemptToConnectToServer(String serverIp, String serverPort) {

        try {
            Socket socket = new Socket(serverIp, Integer.parseInt(serverPort));
            clientSocketThread = new ClientSocketThread(this, socket);
            clientSocketThread.start();

            new LoginGui(this);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void createUsername(String username) {

        JSONObject json = new JSONObject();
        json.put("type", "NEW USER");
        json.put("username", username);

        this.username = username;
        sendThroughSocket(json.toJSONString());

        chatGui.setVisible(true);
    }

    public void createMessage(String message) {
        JSONObject json = new JSONObject();
        json.put("type", "MESSAGE");
        json.put("user", getUsername());
        json.put("content", message);

        sendThroughSocket(json.toJSONString());
    }

    public void createExitMessage() {
        JSONObject json = new JSONObject();
        json.put("type", "EXIT");
        json.put("user", getUsername());

        sendThroughSocket(json.toJSONString());
    }

    public void recvFromSocket(String message) {
        JSONObject json = convertMessageToJson(message);
        chatGui.postMessageToGui((String) json.get("content"));
    }

    private void sendThroughSocket(String message) {
        clientSocketThread.send(message);
    }

    public String getUsername() {
        return username;
    }

    private JSONObject convertMessageToJson(String message) {

        JSONParser parser = new JSONParser();
        JSONObject json = null;

        try {
            json = (JSONObject) parser.parse(message);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return json;
    }
}
