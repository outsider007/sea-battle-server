package ru.kuznetsov.seabattle.server.util;

import ru.kuznetsov.seabattle.server.logic.Player;

import java.util.Scanner;

public class ConsolePlayer implements Player {
    private final String name;

    public ConsolePlayer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int selectPoint() {
        Scanner scanner = new Scanner(System.in);
        int selectedPoint;
        int attempt = 0;

        while (true) {
            if (attempt++ > 0) {
                System.out.println("Input points must be digit in rage (1, 9]!");
            }
            System.out.print(name + ", input point: ");
            String input = scanner.next();
            try {
                selectedPoint = Integer.parseInt(input);
                return selectedPoint;
            } catch (NumberFormatException ignore) {
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void sendCongratulations(String message) {
        System.out.println(message);
    }
}
