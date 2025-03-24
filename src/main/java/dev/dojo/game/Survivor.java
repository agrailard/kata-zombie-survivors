package dev.dojo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Survivor {

    private String name;
    private int numberOfWounds;
    private boolean isAlive;
    private int numberOfActionsPerTurn;
    private int maxNbWoundBeforeDie;
    private List<Equipment> equipmentsInReserve;
    private int nbMaxEquipmentsDefault;
    private List<Equipment> equipmentsInHand;
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
            if (getAllEquipments().size() > getNbMaxEquipments()){
                discardEquipment();
            }
        }
        if(this.numberOfWounds >= getMaxNbWoundBeforeDie()){
            die();
        }
    }

    private void discardEquipment() {
        if (!equipmentsInReserve.isEmpty()){
            equipmentsInReserve.remove(equipmentsInReserve.size()-1);
        }else if (!equipmentsInHand.isEmpty()){
            equipmentsInHand.remove(equipmentsInHand.size()-1);
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

    public List<Equipment> getEquipmentsInReserve() {
        if(equipmentsInReserve == null){
            equipmentsInReserve = new ArrayList<>();
        }
        return equipmentsInReserve;
    }

    public void setEquipmentsInReserve(List<Equipment> equipmentsInReserve) {
        this.equipmentsInReserve = equipmentsInReserve;
    }

    private void setNbMaxEquipmentsDefault(int nbMaxEquipmentsDefault) {
        this.nbMaxEquipmentsDefault = nbMaxEquipmentsDefault;
    }

    public int getNbMaxEquipments() {
        return nbMaxEquipmentsDefault - this.getNumberOfWounds();
    }



    /**
     * To add an equipment to the survivor
     *
     * @param equipment the equipment to add
     * @return true if equipment is added, false otherwise
     */
    public boolean addEquipment(Equipment equipment) {
        boolean equipmentAdded = false;
        if (this.getAllEquipments().size() >= this.getNbMaxEquipments()) {
            return equipmentAdded;
        }
        if(this.getEquipmentsInHand().size() < this.getNbMaxEquipmentsInHand()){
            equipmentAdded = this.getEquipmentsInHand().add(equipment);
            return equipmentAdded;
        }

        equipmentAdded = this.getEquipmentsInReserve().add(equipment);

        return equipmentAdded;
    }

    public List<Equipment> getAllEquipments() {
        List<Equipment> allEquipments = new ArrayList<>();
        allEquipments.addAll(this.getEquipmentsInHand());
        allEquipments.addAll(this.getEquipmentsInReserve());
        return allEquipments;
    }

    public List<Equipment> getEquipmentsInHand() {
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
            survivor.setNbMaxEquipmentsDefault(this.maxNbEquipments);
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

    @Override
    public String toString() {
        if (!isAlive()) {
            return getName() +" is dead !";
        }
        String s = getName() + " has " + getAllEquipments().stream().map(Equipment::getName).collect(Collectors.joining(", "));
        return s;
    }
}
