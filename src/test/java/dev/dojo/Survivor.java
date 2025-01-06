package dev.dojo;

public class Survivor {

    public static final int MAX_NB_WOUNDS_BEFORE_DIE = 2;
    private final String name;
    private int numberOfWounds;
    private boolean isAlive;
    private int numberOfActionsPerTurn;

    public Survivor(String name) {
        this.name = name;
        this.numberOfWounds = 0;
        this.isAlive = true;
        this.numberOfActionsPerTurn = 3;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfWounds() {
        return numberOfWounds;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void addWound() {
        if(this.numberOfWounds < MAX_NB_WOUNDS_BEFORE_DIE) {
            ++numberOfWounds;
        }
        if(this.numberOfWounds >= MAX_NB_WOUNDS_BEFORE_DIE){
            die();
        }
    }

    private void die() {
        this.isAlive = false;
    }

    public int getNumberOfActionsPerTurn() {
        return this.numberOfActionsPerTurn;
    }
}
