package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

public class Dragon extends Monster {
    public Dragon(MyProgram.Difficulty difficulty) {
        super("Dragon", difficulty);
    }

    @Override
    public void attack(Character target) {
        String attackName;
        int damageDealt;
        int evasionDieRoll;

        boolean isEasy = difficulty == MyProgram.Difficulty.EASY;
        int attackDecider = rand.nextInt(100);
        if (attackDecider < (isEasy ? 15 : 30)) {
            attackName = "Breath Fire";
            damageDealt = isEasy ? 50 : 80;
            evasionDieRoll = 1000;
        } else if (attackDecider < (isEasy ? 60 : 80)) {
            attackName = "Claw Slash";
            damageDealt = isEasy ? 15 : 30;
            evasionDieRoll = 100;
        } else {
            attackName = "Bite";
            damageDealt = isEasy ? 5 : 10;
            evasionDieRoll = 100;
        }

        // Determine if target evades attack
        if (rand.nextInt(evasionDieRoll) < target.evasion) {
            System.out.println(target.username + " dodged " + username + "'s attack!");
        } else {
            System.out.println(username + " used " + attackName + " on " + target.username + " for " + damageDealt + " damage!");
            target.damage(damageDealt);
        }
    }
}
