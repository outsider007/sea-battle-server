package ru.kuznetsov.seabattle.server.network;

import ru.kuznetsov.seabattle.server.logic.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private final int port;
    private final List<Client> clients;
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }

    public void run() throws IOException, InterruptedException {
        init();
        waitJoiningClients();
        runGameLogic();
        exit();
    }

    private void init() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void waitJoiningClients() throws IOException, InterruptedException {
        for (int i = 0; i < 2; i++) {
            System.out.println("Waiting a connection of client...");
            Socket clientSocket = serverSocket.accept();
            Client client = new Client(clientSocket);
            System.out.println("Client " + client.name() + " with ip '" + client.host() + "' and port '" +
                    client.port() + "' successful connected!");
            client.sendContent("Hi player " + client.name() + "!");
            clients.add(client);
        }
        System.out.println("All players connected!");
    }

    private void runGameLogic() {
        new Game(clients.get(0), clients.get(1)).start();
    }

    private void exit() throws IOException {
        clients.forEach(client -> {
            client.sendContent("Bye");
            client.disconnect();
        });

        while (clients.stream().allMatch(Client::isAlive)) {
            System.out.println("Clients connections is open. Waiting closing all connection...");
        }

        System.out.println("All players exited");
        serverSocket.close();
    }
}
