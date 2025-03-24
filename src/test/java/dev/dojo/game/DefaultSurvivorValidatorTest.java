package dev.dojo.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultSurvivorValidatorTest {

    DefaultSurvivorFactory survivorFactory = new DefaultSurvivorFactory();

    @Test
    void survivorShouldNotBeValidWhenOtherSurvivorHasSameName() {
        SurvivorValidator survivorValidator = new DefaultSurvivorValidator();
        List<Survivor> otherSurvivors = new ArrayList<>();
        Survivor survivor1 = survivorFactory.create("Pedro");
        otherSurvivors.add(survivor1);
        Survivor survivor2 = survivorFactory.create("Pedro");

        Assertions.assertFalse(survivorValidator.survivorValid(survivor2, otherSurvivors));
    }
}
