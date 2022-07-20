package ru.kuznetsov.seabattle.server.util;

public enum Action {
    NAME("!NAME!"),
    START("!START!"),
    WAIT("!WAIT!"),
    STOP("!STOP!"),
    NEXT_POINT("!NEXT_POINT!"),
    DISPLAY_MESSAGE("!DISPLAY_MESSAGE!"),
    WIN("!WIN!"),
    FAIL("!FAIL!"),
    INVALID_ACTION("!INVALID!");

    private final String command;

    Action(String command) {
        this.command = command;
    }

    public Action resolve(String command) {
        try {
            return Action.valueOf(command);
        } catch (IllegalArgumentException e) {
            return INVALID_ACTION;
        }
    }

    public String command() {
        return command;
    }
}
