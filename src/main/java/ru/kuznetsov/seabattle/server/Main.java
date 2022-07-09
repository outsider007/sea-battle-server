package ru.kuznetsov.seabattle.server;

import ru.kuznetsov.seabattle.server.logic.Game;
import ru.kuznetsov.seabattle.server.util.ConsolePlayer;

public class Main {
    public static void main(String[] args) {
       new Game(new ConsolePlayer("X"), new ConsolePlayer("0")).start();
    }
}