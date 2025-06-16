package dev.dojo.game;

public enum Level {
    BLUE(6),
    YELLOW(18),
    ORANGE(42),
    RED(Integer.MAX_VALUE);

    private final int maxXp;

    Level(int maxXp) {
        this.maxXp = maxXp;
    }

    public int getMaxXp() {
        return maxXp;
    }

    public static Level initialLevel() {
        Level[] values = values();
        return values[0];
    }

    public Level getLevelFromXp(int xp) {
        if (xp >= maxXp) {
            return getNextLevel();
        }
        return this;
    }

    public Level getNextLevel() {
        Level[] values = values();
        return values[(this.ordinal()+1)];
    }

    public static Level getLevelFromNbXp(int nbXp) {
        Level finalLevel = initialLevel();
        for (Level level : values()) {
            if (nbXp <= level.getMaxXp()) {
                break;
            }
            finalLevel = level.getNextLevel();
        }
        return finalLevel;
    }

    public static Level clone(Level level) {
        return Level.valueOf(level.name());
    }
}
