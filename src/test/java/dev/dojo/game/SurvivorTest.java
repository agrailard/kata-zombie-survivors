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
}
