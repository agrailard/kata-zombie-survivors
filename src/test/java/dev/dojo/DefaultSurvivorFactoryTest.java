package dev.dojo;

import dev.dojo.game.DefaultSurvivorFactory;
import dev.dojo.game.Survivor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultSurvivorFactoryTest {

    DefaultSurvivorFactory defaultSurvivorFactory = new DefaultSurvivorFactory();

    @Test
    void survivorShouldBeCreatedWithoutWound() {
        Survivor survivor = defaultSurvivorFactory.create("Jean");
        Assertions.assertEquals(0, survivor.getNumberOfWounds());
    }

    @Test
    void survivorShouldDieWhenGetTwoWounds() {

        Survivor survivor = defaultSurvivorFactory.create("Michel");
        survivor.addWound();
        survivor.addWound();
        Assertions.assertEquals(false, survivor.isAlive());
    }

    @Test
    void survivorShouldNotReceiveMoreThanTwoWounds(){

        Survivor survivor = defaultSurvivorFactory.create("Michel");
        survivor.addWound();
        survivor.addWound();
        survivor.addWound();
        Assertions.assertEquals(2, survivor.getNumberOfWounds());
    }

    @Test
    public void survivorShouldHaveThreeActionsPerTurnByDefault(){
        Survivor survivor = defaultSurvivorFactory.create("Arsène");
        Assertions.assertEquals(3, survivor.getNumberOfActionsPerTurn());
    }
}
