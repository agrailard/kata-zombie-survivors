package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SurvivorTest {

    @Test
    public void survivorShouldHaveAName() {

        Survivor survivor = new Survivor.Builder().setName("Jean").build();
        Assertions.assertEquals("Jean", survivor.getName());
    }

    @Test
    void survivorShouldDiscardEquipmentInHandWhenWoundedAndMaxEquipmentReachedAndNoEquipmentInReserve() {
        Survivor survivor = new Survivor.Builder()
                .setName("Bérénice")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .build();

        boolean firstEquipmentAdded = survivor.addEquipment(new Equipment("Baseball bat"));
        Equipment fryingPan = new Equipment("Frying pan");
        boolean secondEquipmentAdded = survivor.addEquipment(fryingPan);

        survivor.addWound();

        Assertions.assertEquals(1, survivor.getAllEquipments().size());
        Assertions.assertFalse(survivor.getAllEquipments().contains(fryingPan));
    }

    @Test
    void survivorShouldNotAddEquipmentWhenWoundedAndMaxEquipmentReached() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .build();

        boolean firstEquipmentAdded = survivor.addEquipment(new Equipment("Baseball bat"));
        Equipment fryingPan = new Equipment("Frying pan");
        boolean secondEquipmentAdded = survivor.addEquipment(fryingPan);

        survivor.addWound();

        boolean thirdEquipmentAdded = survivor.addEquipment(new Equipment("Katana"));

        Assertions.assertFalse(thirdEquipmentAdded);
        Assertions.assertEquals(1, survivor.getAllEquipments().size());
    }

    @Test
    void survivorShouldGetXpWhenKillZombie() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(0)
                .setLevel(Level.BLUE)
                .build();

        survivor.killZombie(1);

        Assertions.assertEquals(1,survivor.getXp());
    }

    @Test
    void survivorShouldNotLevelUpWhenReaches6Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(5)
                .setLevel(Level.BLUE)
                .build();

        Assertions.assertEquals(Level.BLUE, survivor.getLevel());

        survivor.killZombie(1);

        Assertions.assertEquals(Level.BLUE, survivor.getLevel());
    }

    @Test
    void survivorShouldLevelUpToYellowWhenExceeds6Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(6)
                .setLevel(Level.BLUE)
                .build();

        Assertions.assertEquals(Level.BLUE, survivor.getLevel());

        survivor.killZombie(1);

        Assertions.assertEquals(Level.YELLOW, survivor.getLevel());
    }

    @Test
    void survivorShouldNotLevelUpToOrangeWhenReaches18Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(17)
                .setLevel(Level.YELLOW)
                .build();

        Assertions.assertEquals(Level.YELLOW, survivor.getLevel());

        survivor.killZombie(1);

        Assertions.assertEquals(Level.YELLOW, survivor.getLevel());
    }

    @Test
    void survivorShouldLevelUpToOrangeWhenExceeds18Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(18)
                .setLevel(Level.YELLOW)
                .build();

        survivor.killZombie(1);

        Assertions.assertEquals(Level.ORANGE, survivor.getLevel());
    }

    @Test
    void survivorShouldNotLevelUpToRedWhenReaches42Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(41)
                .setLevel(Level.ORANGE)
                .build();

        Assertions.assertEquals(Level.ORANGE, survivor.getLevel());

        survivor.killZombie(1);

        Assertions.assertEquals(Level.ORANGE, survivor.getLevel());
    }

    @Test
    void survivorShouldLevelUpToRedWhenExceeds42Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(42)
                .setLevel(Level.ORANGE)
                .build();

        survivor.killZombie(1);

        Assertions.assertEquals(Level.RED, survivor.getLevel());
    }

    @Test
    void newSurvivorShouldLevelUpToRedWhenGain43Xp() {
        Survivor survivor = new Survivor.Builder()
                .setName("Michel")
                .setNumberOfActionsPerTurn(3)
                .setMaxNbWoundBeforeDie(3)
                .setMaxNbEquipments(2)
                .setMaxNbEquipmentsInHand(2)
                .setXp(0)
                .setLevel(Level.BLUE)
                .build();

        survivor.killZombie(43);

        Assertions.assertEquals(Level.RED, survivor.getLevel());
    }
}
