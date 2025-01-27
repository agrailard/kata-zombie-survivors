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

    @Test
    public void survivorCanCarryUpToFiveEquipments(){
        Survivor survivor = defaultSurvivorFactory.create("Ursule");
        boolean firstEquipmentAdded = survivor.addEquipment(new Object());
        boolean secondEquipmentAdded = survivor.addEquipment(new Object());
        boolean thirdEquipmentAdded = survivor.addEquipment(new Object());
        boolean fourthEquipmentAdded = survivor.addEquipment(new Object());
        boolean fifthEquipmentAdded = survivor.addEquipment(new Object());
        boolean sixthEquipmentAdded = survivor.addEquipment(new Object());

        Assertions.assertTrue(firstEquipmentAdded);
        Assertions.assertTrue(secondEquipmentAdded);
        Assertions.assertTrue(thirdEquipmentAdded);
        Assertions.assertTrue(fourthEquipmentAdded);
        Assertions.assertTrue(fifthEquipmentAdded);

        Assertions.assertFalse(sixthEquipmentAdded);

        Assertions.assertEquals(5, survivor.getAllEquipments().size());
    }

    @Test
    public void survivorCanCarryUpToTwoEquipmentsInHand() {

        Survivor survivor = defaultSurvivorFactory.create("Roger");
        boolean firstEquipmentAdded = survivor.addEquipment(new Object());
        boolean secondEquipmentAdded = survivor.addEquipment(new Object());
        survivor.addEquipment(new Object());

        Assertions.assertTrue(firstEquipmentAdded);
        Assertions.assertTrue(secondEquipmentAdded);

        Assertions.assertEquals(2, survivor.getEquipmentsInHand().size());
    }
}
