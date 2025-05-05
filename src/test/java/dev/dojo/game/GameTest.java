package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    DefaultSurvivorFactory survivorFactory = new DefaultSurvivorFactory();

    SurvivorValidator survivorValidatorMock = new SurvivorValidator() {
        @Override
        public boolean survivorValid(Survivor survivor, List<Survivor> survivors) {
            return true;
        }
    };

    SurvivorValidator survivorValidatorMockFalse = new SurvivorValidator() {
        @Override
        public boolean survivorValid(Survivor survivor, List<Survivor> survivors) {
            return false;
        }
    };

    UiHandler handlerMock = new UiHandler() {
        @Override
        public void interact(String message) {
            // do nothing
        }
    };

    @Test
    void aGameShouldBeginWithZeroSurvivor() {

        Game game = new Game(survivorValidatorMock, handlerMock);

        assertEquals(0, game.getNumberSurvivors());
    }

    @Test
    void shouldBeAbleToAddASurvivor() {
        Game game = new Game(survivorValidatorMock, handlerMock);
        Survivor survivor = survivorFactory.create("Pedro");

        game.addSurvivor(survivor);
        assertEquals(1, game.getNumberSurvivors());
        assertEquals("Pedro", game.getSurvivors().get(0).getName());
    }

    @Test
    void shouldNotBeAbleToCreateSurvivorWhenSurvivorIsInvalid(){
        Game game = new Game(survivorValidatorMockFalse, handlerMock);

        Survivor survivor = survivorFactory.create("Pedro");
        game.addSurvivor(survivor);

        assertEquals(0, game.getNumberSurvivors());

    }

    @Test
    void gameShouldFinishWhenNoMoreSurvivor() {
        Game game = new Game(survivorValidatorMock, handlerMock);

        Survivor survivor = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).build();
        game.addSurvivor(survivor);

        game.hurtSurvivor(survivor);

        Assertions.assertTrue(game.isFinished());
    }

    @Test
    void gameShouldGive1XpToSurvivorWhenKillZombie() {
        Game game = new Game(survivorValidatorMock, handlerMock);

        Survivor survivor = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).setXp(0).setLevel(Level.BLUE).build();
        game.addSurvivor(survivor);

        game.killZombie(survivor);

        assertEquals(1, survivor.getXp());
    }

    @Test
    void gameShouldStartAtBlueLevel() {
        Game game = new Game(survivorValidatorMock, handlerMock);

        assertEquals(Level.BLUE, game.getLevel());
    }

    @Test
    void gameLevelShouldBeTheSameAsTheHighestLivingSurvivorLevel() {
        Game game = new Game(survivorValidatorMock, handlerMock);
        Survivor survivorPedro = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).setXp(10).setLevel(Level.YELLOW).build();
        Survivor survivorPascal = new Survivor.Builder().setName("Pascal").setMaxNbWoundBeforeDie(1).setXp(20).setLevel(Level.ORANGE).build();
        game.addSurvivor(survivorPedro);
        game.addSurvivor(survivorPascal);

        assertEquals(Level.ORANGE, game.getLevel());
    }
}
