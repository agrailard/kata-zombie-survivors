package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EquipmentTest {
    @Test
    void equipmentShouldHaveAName() {
        Equipment equipment = new Equipment("Baseball bat");
        Assertions.assertEquals("Baseball bat", equipment.getName());
    }
}
