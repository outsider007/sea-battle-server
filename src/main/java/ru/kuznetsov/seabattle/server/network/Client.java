package ru.kuznetsov.seabattle.server.network;

import ru.kuznetsov.seabattle.server.logic.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Player {
    private final Socket socket;
    private final PrintWriter outputStream;
    private final BufferedReader inputStream;
    private String name;

    public Client(Socket socket) {
        this.socket = socket;
        try {
            outputStream = new PrintWriter(socket.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String host() {
        return socket.getInetAddress().getHostAddress();
    }

    public int port() {
        return socket.getPort();
    }

    public boolean isAlive() {
        return socket.isClosed();
    }

    public void sendContent(String content) {
        outputStream.println(content);
        outputStream.flush();
    }

    public String receiveContent() throws IOException {
        return inputStream.readLine();
    }

    public void disconnect() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int selectPoint() {
        return 0;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendCongratulations(String message) {

    }
}
