package ru.kuznetsov.seabattle.server.logic;

import ru.kuznetsov.seabattle.server.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private static final List<List<Integer>> WINED_RANGES =
            List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9), List.of(1, 4, 7), List.of(2, 5, 8),
                    List.of(3, 6, 9), List.of(1, 5, 9), List.of(3, 5, 7));

    private final ArrayList<Integer> allowPoints;
    private final List<Pair<Player, List<Integer>>> selectedPointsOfPlayerList;

    public Game(Player playerOne, Player playerTwo) {
        allowPoints = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        selectedPointsOfPlayerList =
                List.of(new Pair<>(playerOne, new ArrayList<>()), new Pair<>(playerTwo, new ArrayList<>()));
    }

    /**
     * A start game point
     *
     * @return winning player or null if nobody not won
     */
    public Player start() {
        while (true) {
            for (int i = 0; i < 2; ) {
                Pair<Player, List<Integer>> selectedPointsOfPlayer = selectedPointsOfPlayerList.get(i);
                try {
                    if (nextSelection(selectedPointsOfPlayer)) {
                        selectedPointsOfPlayer.left()
                                .sendCongratulations("Congratulation, " + selectedPointsOfPlayer.left().name() + "!");
                        return selectedPointsOfPlayer.left();
                    }
                } catch (IllegalStateException e) {
                    selectedPointsOfPlayerList.forEach(
                            playerListPair -> playerListPair.left().sendMessage(e.getMessage()));
                    return null;
                }

                i++;
            }
        }
    }

    private boolean nextSelection(Pair<Player, List<Integer>> selectedPointsOfPlayer) {
        if (allowPoints.isEmpty()) {
            throw new IllegalStateException("All allow points is selected! Nobody not won!");
        }

        Player player = selectedPointsOfPlayer.left();
        List<Integer> selectedPoints = selectedPointsOfPlayer.right();
        int selectedPoint = 0;
        int attempt = 0;

        while (!isValid(selectedPoint) || attempt == 0) {
            if (attempt++ > 0) {
                player.sendMessage("Invalid point! Allow only point of list: " +
                        allowPoints.stream().map(Object::toString).collect(Collectors.joining(", ", "[", "]")));
            }
            selectedPoint = player.selectPoint();
        }

        selectedPoints.add(selectedPoint);
        allowPoints.removeAll(new ArrayList<>(List.of(selectedPoint)));

        return isGameOver(selectedPoints);
    }

    private boolean isValid(int selectedPoint) {
        return allowPoints.contains(selectedPoint);
    }

    public static boolean isGameOver(List<Integer> selectedPoints) {
        return WINED_RANGES.stream().anyMatch(selectedPoints::containsAll);
    }
}
