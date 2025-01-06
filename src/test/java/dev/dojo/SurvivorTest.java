package dev.dojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SurvivorTest {

    @Test
    public void survivorShouldHaveAName() {

        Survivor survivor = new Survivor("Jean");
        Assertions.assertEquals("Jean", survivor.getName());
    }

    @Test
    void survivorShouldBeCreatedWithoutWound() {
        Survivor survivor = new Survivor("Jean");
        Assertions.assertEquals(0, survivor.getNumberOfWounds());
    }

    @Test
    void survivorShouldDieWhenGetTwoWounds() {

        Survivor survivor = new Survivor("Michel");
        survivor.addWound();
        survivor.addWound();
        Assertions.assertEquals(false, survivor.isAlive());
    }

    @Test
    void survivorShouldNotReceiveMoreThanTwoWounds(){

        Survivor survivor = new Survivor("Michel");
        survivor.addWound();
        survivor.addWound();
        survivor.addWound();
        Assertions.assertEquals(2, survivor.getNumberOfWounds());
    }

    //TODO : refacto
    @Test
    public void survivorShouldHaveThreeActionsPerTurnByDefault(){
        Survivor survivor = new Survivor("Arsène");
        Assertions.assertEquals(3, survivor.getNumberOfActionsPerTurn());
    }
}
