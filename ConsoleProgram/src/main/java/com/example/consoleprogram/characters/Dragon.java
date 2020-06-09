package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

public class Dragon extends Monster {
    static private int numDragons = 0;

    public Dragon(MyProgram.Difficulty difficulty) {
        super("Dragon " + (++numDragons), difficulty);
    }
    
    public String getDescription()
    {
        return super.getDescription() +
            "Monster: Dragon\n";
    }

    @Override
    public void attack(Character target) {
        String attackName;
        int damageDealt;

        boolean isEasy = difficulty == MyProgram.Difficulty.EASY;
        int attackDecider = rand.nextInt(100);
        if (attackDecider < (isEasy ? 15 : 30)) {
            attackName = "Breath Fire";
            damageDealt = isEasy ? 50 : 80;
        } else if (attackDecider < (isEasy ? 60 : 80)) {
            attackName = "Claw Slash";
            damageDealt = isEasy ? 15 : 30;
        } else {
            attackName = "Bite";
            damageDealt = isEasy ? 5 : 10;
        }

        System.out.println(username + " used " + attackName + " on " + target.username + " for " + damageDealt + " damage!");
        target.damage(damageDealt);
    }
}
