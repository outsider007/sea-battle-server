package ru.kuznetsov.seabattle.server;

import ru.kuznetsov.seabattle.server.network.GameServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        new Game(new ConsolePlayer("X"), new ConsolePlayer("0")).start();
        new GameServer(6666).run();
    }
}