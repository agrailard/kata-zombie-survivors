package dev.dojo.game;

public class DefaultSurvivorFactory implements SurvivorFactory {
    public static final int DEFAULT_NUMBER_OF_ACTIONS_PER_TURN = 3;

    @Override
    public Survivor create(String name) {
        Survivor survivor = new Survivor.Builder()
                .setName(name)
                .setNumberOfActionsPerTurn(DEFAULT_NUMBER_OF_ACTIONS_PER_TURN)
                .build();

        return survivor;
    }
}
