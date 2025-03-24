package dev.dojo.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final SurvivorValidator survivorValidator;
    private List<Survivor> survivors;
    private boolean isFinished;
    private int turn;
    private UiHandler uiHandler;

    public Game(SurvivorValidator survivorValidator, UiHandler uiHandler) {
        this.survivorValidator = survivorValidator;
        this.uiHandler = uiHandler;
        this.isFinished = false;
        turn = 0;
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
        }
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void finish() {
        isFinished = true;
        uiHandler.interact("Game over !");
    }

    public boolean checkIfAllSurvivorsDied(){
        return getSurvivors().stream().noneMatch(Survivor::isAlive);
    }

    public void hurtSurvivor(Survivor survivor){
        survivor.addWound();
        uiHandler.interact(survivor.getName() + " wounded !");
        if (!survivor.isAlive()) {
            uiHandler.interact(survivor.getName() + " died !");
        }
        if (checkIfAllSurvivorsDied()){
            finish();
        }
    }

    public void nextTurn() {
        turn++;

        uiHandler.interact("Begin of turn " + turn);

        getSurvivors().forEach(survivor -> uiHandler.interact(survivor.toString()));
    }
}
