package dev.dojo.game;

import java.util.ArrayList;
import java.util.List;

public class Survivor {

    private String name;
    private int numberOfWounds;
    private boolean isAlive;
    private int numberOfActionsPerTurn;
    private int maxNbWoundBeforeDie;
    private List<Object> equipmentsInReserve;
    private int nbMaxEquipments;
    private List<Object> equipmentsInHand;
    private int nbMaxEquipmentsInHand;

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

    public void addWound() {
        if(this.numberOfWounds < getMaxNbWoundBeforeDie()) {
            ++numberOfWounds;
        }
        if(this.numberOfWounds >= getMaxNbWoundBeforeDie()){
            die();
        }
    }

    private void die() {
        this.isAlive = false;
    }

    public int getNumberOfActionsPerTurn() {
        return this.numberOfActionsPerTurn;
    }

    public int getMaxNbWoundBeforeDie() {
        return maxNbWoundBeforeDie;
    }

    public void setMaxNbWoundBeforeDie(int maxNbWoundBeforeDie) {
        this.maxNbWoundBeforeDie = maxNbWoundBeforeDie;
    }

    public List<Object> getEquipmentsInReserve() {
        if(equipmentsInReserve == null){
            equipmentsInReserve = new ArrayList<>();
        }
        return equipmentsInReserve;
    }

    public void setEquipmentsInReserve(List<Object> equipmentsInReserve) {
        this.equipmentsInReserve = equipmentsInReserve;
    }

    public void setNbMaxEquipments(int nbMaxEquipments) {
        this.nbMaxEquipments = nbMaxEquipments;
    }

    public int getNbMaxEquipments() {
        return nbMaxEquipments;
    }



    /**
     * To add an equipment to the survivor
     *
     * @param equipment the equipment to add
     * @return true if equipment is added, false otherwise
     */
    public boolean addEquipment(Object equipment) {
        boolean equipmentAdded = false;
        if(this.getEquipmentsInHand().size() < this.getNbMaxEquipmentsInHand()){
            this.getEquipmentsInHand().add(equipment);
            equipmentAdded = true;
            return equipmentAdded;
        }

        if(this.getAllEquipments().size() < this.getNbMaxEquipments()){
            this.getEquipmentsInReserve().add(equipment);
            equipmentAdded = true;
        }
        return equipmentAdded;
    }

    public List<Object> getAllEquipments() {
        List<Object> allEquipments = new ArrayList<>();
        allEquipments.addAll(this.getEquipmentsInHand());
        allEquipments.addAll(this.getEquipmentsInReserve());
        return allEquipments;
    }

    public List<Object> getEquipmentsInHand() {
        if(equipmentsInHand == null){
            equipmentsInHand = new ArrayList<>();
        }
        return equipmentsInHand;
    }

    public void setNbMaxEquipmentsInHand(int nbMaxEquipmentsInHand) {
        this.nbMaxEquipmentsInHand = nbMaxEquipmentsInHand;
    }

    public int getNbMaxEquipmentsInHand() {
        return nbMaxEquipmentsInHand;
    }

    public static class Builder{
        private String name;
        private int numberOfActionsPerTurn;
        private int maxNbWoundBeforeDie;
        private int maxNbEquipments;
        private int maxNbEquipmentsInHand;

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
            survivor.setMaxNbWoundBeforeDie(this.maxNbWoundBeforeDie);
            survivor.setNbMaxEquipments(this.maxNbEquipments);
            survivor.setNbMaxEquipmentsInHand(this.maxNbEquipmentsInHand);
            return survivor;
        }

        public Builder setNumberOfActionsPerTurn(int numberOfActionsPerTurn) {
            this.numberOfActionsPerTurn = numberOfActionsPerTurn;
            return this;
        }

        public Builder setMaxNbWoundBeforeDie(int maxNbWoundBeforeDie) {
            this.maxNbWoundBeforeDie = maxNbWoundBeforeDie;
            return this;
        }

        public Builder setMaxNbEquipments(int maxNbEquipments) {
            this.maxNbEquipments = maxNbEquipments;
            return this;
        }

        public Builder setMaxNbEquipmentsInHand(int maxNbEquipmentsInHand) {
            this.maxNbEquipmentsInHand = maxNbEquipmentsInHand;
            return this;
        }
    }


}
