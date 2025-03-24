package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        Assertions.assertEquals(0, game.getNumberSurvivors());
    }

    @Test
    void shouldBeAbleToAddASurvivor() {
        Game game = new Game(survivorValidatorMock, handlerMock);
        Survivor survivor = survivorFactory.create("Pedro");

        game.addSurvivor(survivor);
        Assertions.assertEquals(1, game.getNumberSurvivors());
        Assertions.assertEquals("Pedro", game.getSurvivors().get(0).getName());
    }

    @Test
    void shouldNotBeAbleToCreateSurvivorWhenSurvivorIsInvalid(){
        Game game = new Game(survivorValidatorMockFalse, handlerMock);

        Survivor survivor = survivorFactory.create("Pedro");
        game.addSurvivor(survivor);

        Assertions.assertEquals(0, game.getNumberSurvivors());

    }

    @Test
    void gameShouldFinishWhenNoMoreSurvivor() {
        Game game = new Game(survivorValidatorMock, handlerMock);

        Survivor survivor = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).build();
        game.addSurvivor(survivor);

        game.hurtSurvivor(survivor);

        Assertions.assertTrue(game.isFinished());
    }
}
