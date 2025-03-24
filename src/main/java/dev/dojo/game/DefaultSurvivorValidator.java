package dev.dojo.game;

import java.util.List;

public class DefaultSurvivorValidator implements SurvivorValidator {

    public boolean survivorValid(Survivor survivor, List<Survivor> listSurvivorsInGame) {
        return survivorNameNotUsed(survivor, listSurvivorsInGame);
    }

    private boolean survivorNameNotUsed(Survivor survivor, List<Survivor> listSurvivorsInGame) {
        return listSurvivorsInGame==null || listSurvivorsInGame.stream().noneMatch(s -> s.getName().equalsIgnoreCase(survivor.getName()));
    }
}
