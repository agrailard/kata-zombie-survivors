package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void aGameShouldBeginWithZeroSurvivor() {

        Game game = new Game(survivorValidatorMock);

        assertEquals(0, game.getNumberSurvivors());
    }

    @Test
    void shouldBeAbleToAddASurvivor() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivor = survivorFactory.create("Pedro");

        game.addSurvivor(survivor);
        assertEquals(1, game.getNumberSurvivors());
        assertEquals("Pedro", game.getSurvivors().get(0).getName());
    }

    @Test
    void shouldNotBeAbleToCreateSurvivorWhenSurvivorIsInvalid(){
        Game game = new Game(survivorValidatorMockFalse);

        Survivor survivor = survivorFactory.create("Pedro");
        game.addSurvivor(survivor);

        assertEquals(0, game.getNumberSurvivors());

    }

    @Test
    void gameShouldFinishWhenNoMoreSurvivor() {
        Game game = new Game(survivorValidatorMock);

        Survivor survivor = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).build();
        game.addSurvivor(survivor);

        game.hurtSurvivor(survivor);

        Assertions.assertTrue(game.isFinished());
    }

    @Test
    void gameShouldGive1XpToSurvivorWhenKillZombie() {
        Game game = new Game(survivorValidatorMock);

        Survivor survivor = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).setXp(0).setLevel(Level.BLUE).build();
        game.addSurvivor(survivor);

        game.killZombie(survivor);

        assertEquals(1, survivor.getXp());
    }

    @Test
    void gameShouldStartAtBlueLevel() {
        Game game = new Game(survivorValidatorMock);

        assertEquals(Level.BLUE, game.getLevel());
    }

    @Test
    void gameLevelShouldBeTheSameAsTheHighestLivingSurvivorLevel() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).setXp(10).setLevel(Level.YELLOW).build();
        Survivor survivorPascal = new Survivor.Builder().setName("Pascal").setMaxNbWoundBeforeDie(1).setXp(20).setLevel(Level.ORANGE).build();
        game.addSurvivor(survivorPedro);
        game.addSurvivor(survivorPascal);

        assertEquals(Level.ORANGE, game.getLevel());
    }

    @Test
    void gameHistoryShouldBeginWithGameStartDate() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = survivorFactory.create("Pedro");
        game.addSurvivor(survivorPedro);

        Event firstEvent = game.getHistory().firstEvent();
        assertNotNull(firstEvent.getDate());
        assertEquals("Début du jeu", firstEvent.getDescription());
    }

    @Test
    void gameHistoryShouldNoteThatASurvivorHasBeenAddedToTheGame() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = survivorFactory.create("Pedro");
        game.addSurvivor(survivorPedro);

        History history = game.getHistory();
        assertEquals("The survivor Pedro has been added", history.lastEvent().getDescription());
    }

    @Test
    void gameHistoryShouldNoteThatASurvivorAcquiresAPieceOfEquipment() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = survivorFactory.create("Pedro");
        game.addSurvivor(survivorPedro);
        game.lootEquipment(survivorPedro, new Equipment("Sword"));

        History history = game.getHistory();
        assertEquals("The survivor Pedro get an equipment : Sword", history.lastEvent().getDescription());
    }

    @Test
    void gameHistoryShouldNoteThatASurvivorIsWounded() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = survivorFactory.create("Pedro");
        game.addSurvivor(survivorPedro);
        game.hurtSurvivor(survivorPedro);

        History history = game.getHistory();
        assertEquals("The survivor Pedro got hurted", history.lastEvent().getDescription());
    }

    @Test
    void gameHistoryShouldNoteThatASurvivorDied() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = new Survivor.Builder().setName("Pedro").setMaxNbWoundBeforeDie(1).build();
        game.addSurvivor(survivorPedro);
        game.hurtSurvivor(survivorPedro);

        History history = game.getHistory();
        assertEquals("The survivor Pedro died", history.lastEvent().getDescription());
    }

    @Test
    void gameHistoryShouldNoteThatASurvivorLevelUp() {
        Game game = new Game(survivorValidatorMock);
        Survivor survivorPedro = new Survivor.Builder().setName("Pedro").setXp(6).setLevel(Level.BLUE).build();
        game.addSurvivor(survivorPedro);

        assertEquals(Level.BLUE, survivorPedro.getLevel());

        game.killZombie(survivorPedro);

        assertEquals(Level.YELLOW, survivorPedro.getLevel());

        History history = game.getHistory();
        assertEquals("The survivor Pedro has leveled up to YELLOW", history.lastEvent().getDescription());

    }
}
