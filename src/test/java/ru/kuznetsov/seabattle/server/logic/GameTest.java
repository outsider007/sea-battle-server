package ru.kuznetsov.seabattle.server.logic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void isGameOver() {
        assertTrue("Is not win points combination", Game.isGameOver(Arrays.asList(1, 3, 5, 7)));
    }
}