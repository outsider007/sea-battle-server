package ru.kuznetsov.seabattle.server.logic;

public interface Player {
    String name();

    int selectPoint();

    void sendMessage(String message);

    void sendCongratulations(String message);
}
