package main.client;

import main.socketthread.SocketThread;

import java.net.Socket;

public class ClientSocketThread extends SocketThread {

    Client client;

    public ClientSocketThread(Client client, Socket socket) {
        super(socket);
        this.client = client;
    }

    @Override
    public void recv(String input) {
        client.recvFromSocket(input);
    }
}
