package dev.dojo.game;

public class DefaultSurvivorFactory implements SurvivorFactory {

    public static final int DEFAULT_NUMBER_OF_ACTIONS_PER_TURN = 3;
    public static final int MAX_NB_WOUNDS_BEFORE_DIE = 2;
    public static final int MAX_NB_EQUIPMENTS = 5;
    public static final int MAX_NB_EQUIPMENTS_IN_HAND = 2;
    public static final int INITIAL_XP = 0;
    public static final Level INITIAL_LEVEL = Level.BLUE;

    @Override
    public Survivor create(String name) {

        return new Survivor.Builder()
                .setName(name)
                .setNumberOfActionsPerTurn(DEFAULT_NUMBER_OF_ACTIONS_PER_TURN)
                .setMaxNbWoundBeforeDie(MAX_NB_WOUNDS_BEFORE_DIE)
                .setMaxNbEquipments(MAX_NB_EQUIPMENTS)
                .setMaxNbEquipmentsInHand(MAX_NB_EQUIPMENTS_IN_HAND)
                .setXp(INITIAL_XP)
                .setLevel(INITIAL_LEVEL)
                .build();
    }
}
