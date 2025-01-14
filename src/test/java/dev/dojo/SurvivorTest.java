package dev.dojo;

import dev.dojo.game.Survivor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SurvivorTest {

    @Test
    public void survivorShouldHaveAName() {

        Survivor survivor = new Survivor.Builder().setName("Jean").build();
        Assertions.assertEquals("Jean", survivor.getName());
    }

}
