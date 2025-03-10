package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    /* TODO : implémentation*/
    @Test
    void aGameShouldBeginWithZeroSurvivor() {
        Game game = new Game();

        Assertions.assertEquals(0, game.getNumberSurvivors());
    }
}
