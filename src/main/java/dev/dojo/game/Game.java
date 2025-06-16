package dev.dojo.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {

    public static final int NB_XP_GAINED_WHEN_KILL_ZOMBIE = 1;
    private final SurvivorValidator survivorValidator;
    private List<Survivor> survivors;
    private boolean isFinished;
    private int turn;
    private History history;

    public Game(SurvivorValidator survivorValidator) {
        this.survivorValidator = survivorValidator;
        this.isFinished = false;
        this.history = new History();
        turn = 0;
    }

    public Level getLevel() {
        return getSurvivors().stream().filter(Survivor::isAlive).map(Survivor::getLevel).max(Comparator.naturalOrder()).orElse(Level.initialLevel());
    }

    public List<Survivor> getSurvivors() {
        if (survivors == null) {
            survivors = new ArrayList<>();
        }
        return survivors;
    }

    public int getNumberSurvivors() {
        return getSurvivors().size();
    }

    public void addSurvivor(Survivor survivor) {
        if (survivorValidator.survivorValid(survivor, getSurvivors())) {
            this.getSurvivors().add(survivor);
            this.getHistory().addEvent("The survivor " + survivor.getName() + " has been added");
        }
    }

    public void lootEquipment(Survivor survivor, Equipment equipment) {
        if (survivor.addEquipment(equipment)) {
            getHistory().addEvent("The survivor " + survivor.getName() + " get an equipment : " + equipment.getName());
        }
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void finish() {
        isFinished = true;
    }

    public boolean checkIfAllSurvivorsDied(){
        return getSurvivors().stream().noneMatch(Survivor::isAlive);
    }

    public void hurtSurvivor(Survivor survivor){
        survivor.addWound();
        getHistory().addEvent("The survivor " + survivor.getName() + " got hurted");
        if (!survivor.isAlive()) {
            getHistory().addEvent("The survivor " + survivor.getName() + " died");
            if (checkIfAllSurvivorsDied()){
                finish();
            }
        }
    }

    public void nextTurn() {
        turn++;
    }

    public void killZombie(Survivor survivor) {
        Level levelBefore = Level.clone(survivor.getLevel());
        survivor.killZombie(NB_XP_GAINED_WHEN_KILL_ZOMBIE);
        if (levelBefore.ordinal() < survivor.getLevel().ordinal()) {
            getHistory().addEvent("The survivor " + survivor.getName() + " has leveled up to " + survivor.getLevel().name());
        }
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
