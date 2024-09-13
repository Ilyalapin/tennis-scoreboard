package com.example.tennis_scoreboard.service.match_score_calculation;

public enum GamePoint {
    LOVE("LOVE"), FIFTEEN("15"), THIRTY("30"), FOURTY("40"), ADVANTAGE("AD");

    private final String value;

    GamePoint(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }


    public GamePoint increment() {

        switch (this) {
            case LOVE:
                return FIFTEEN;
            case FIFTEEN:
                return THIRTY;
            case THIRTY:
                return FOURTY;
            case FOURTY:
                return ADVANTAGE;
            default:
                if (this == ADVANTAGE) {
                    throw new IllegalArgumentException("Unknown GamePoint: " + this);
                }
        }
        return this;
    }
}