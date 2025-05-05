package dev.dojo.game;

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
        Assertions.assertFalse(survivor.isAlive());
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
        boolean firstEquipmentAdded = survivor.addEquipment(new Equipment("Baseball bat"));
        boolean secondEquipmentAdded = survivor.addEquipment(new Equipment("Frying pan"));
        boolean thirdEquipmentAdded = survivor.addEquipment(new Equipment("Katana"));
        boolean fourthEquipmentAdded = survivor.addEquipment(new Equipment("Pistol"));
        boolean fifthEquipmentAdded = survivor.addEquipment(new Equipment("Bottled Water"));
        boolean sixthEquipmentAdded = survivor.addEquipment(new Equipment("Molotov"));

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
        boolean firstEquipmentAdded = survivor.addEquipment(new Equipment("Baseball bat"));
        boolean secondEquipmentAdded = survivor.addEquipment(new Equipment("Frying pan"));
        survivor.addEquipment(new Equipment("Katana"));

        Assertions.assertTrue(firstEquipmentAdded);
        Assertions.assertTrue(secondEquipmentAdded);

        Assertions.assertEquals(2, survivor.getEquipmentsInHand().size());
    }



    @Test
    void survivorCanCarryUpToFourEquipmentsWhenWoundedOnce() {
        Survivor survivor = defaultSurvivorFactory.create("Thierry");

        survivor.addWound();

        Assertions.assertEquals(4, survivor.getNbMaxEquipments());
    }

    @Test
    void survivorShouldDiscardOneEquipmentWhenFiveEquipmentsReachedAndWoundedOnce() {
        Survivor survivor = defaultSurvivorFactory.create("Waldemar");

        boolean firstEquipmentAdded = survivor.addEquipment(new Equipment("Baseball bat"));
        boolean secondEquipmentAdded = survivor.addEquipment(new Equipment("Frying pan"));
        boolean thirdEquipmentAdded = survivor.addEquipment(new Equipment("Katana"));
        boolean fourthEquipmentAdded = survivor.addEquipment(new Equipment("Pistol"));
        Equipment bottledWater = new Equipment("Bottled Water");
        boolean fifthEquipmentAdded = survivor.addEquipment(bottledWater);

        survivor.addWound();

        Assertions.assertEquals(4, survivor.getAllEquipments().size());
        Assertions.assertFalse(survivor.getAllEquipments().contains(bottledWater));
    }

    @Test
    void survivorShouldStartWith0Xp() {
        Survivor survivor = defaultSurvivorFactory.create("Francisco");
        Assertions.assertEquals(0, survivor.getXp());
    }

    @Test
    void survivorShouldStartWithBlueLevel() {
        Survivor survivor = defaultSurvivorFactory.create("Francisco");
        Assertions.assertEquals(Level.BLUE, survivor.getLevel());
    }
}
