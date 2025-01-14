package dev.dojo.game;

public class Survivor {

    public static final int MAX_NB_WOUNDS_BEFORE_DIE = 2;

    private String name;
    private int numberOfWounds;
    private boolean isAlive;
    private int numberOfActionsPerTurn;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setNumberOfWounds(int numberOfWounds) {
        this.numberOfWounds = numberOfWounds;
    }

    public void setNumberOfActionsPerTurn(int numberOfActionsPerTurn) {
        this.numberOfActionsPerTurn = numberOfActionsPerTurn;
    }

    private Survivor() {

    }

    private void setName(String name) {
        this.name = name;
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

    //TODO : REFACTO : move vers builder et factory
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

    public static class Builder{
        private String name;
        private int numberOfActionsPerTurn;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Survivor build(){
            Survivor survivor = new Survivor();
            survivor.setName(this.name);
            survivor.setNumberOfActionsPerTurn(this.numberOfActionsPerTurn);
            survivor.setAlive(true);
            survivor.setNumberOfWounds(0);
            return survivor;
        }

        public Builder setNumberOfActionsPerTurn(int numberOfActionsPerTurn) {
            this.numberOfActionsPerTurn = numberOfActionsPerTurn;
            return this;
        }
    }


}
