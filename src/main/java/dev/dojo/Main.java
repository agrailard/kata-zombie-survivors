package dev.dojo;

import dev.dojo.game.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        UiHandler consoleUiHandler = new UiHandler() {
            @Override
            public void interact(String message) {
                System.out.println(message);
            }
        };

        SurvivorFactory survivorFactory = new DefaultSurvivorFactory();

        Survivor michelle = survivorFactory.create("Michelle");
        michelle.addEquipment(new Equipment("Pan"));

        Survivor louis = survivorFactory.create("Louis");
        louis.addEquipment(new Equipment("Lighter"));
        louis.addEquipment(new Equipment("Candle"));
        louis.addEquipment(new Equipment("Chocapics"));
        louis.addEquipment(new Equipment("Laser saber"));
        louis.addEquipment(new Equipment("Keys"));

        Game game = new Game(new DefaultSurvivorValidator(), consoleUiHandler);
        game.addSurvivor(michelle);
        game.addSurvivor(louis);

        game.nextTurn();
        game.hurtSurvivor(michelle);
        game.hurtSurvivor(michelle);
        game.hurtSurvivor(louis);

        game.nextTurn();
        game.hurtSurvivor(louis);
    }
}