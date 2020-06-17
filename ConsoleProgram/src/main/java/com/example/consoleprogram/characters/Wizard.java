package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

public class Wizard extends Monster {
    private int evasionBuff = 0;

    public Wizard(MyProgram.Difficulty difficulty) {
        super("Wizard", difficulty);
    }

    @Override
    public void attack(Character target) {
        evasion -= evasionBuff;
        evasionBuff = 0;

        int moveDecider = rand.nextInt();

        if (moveDecider < 5)
            castInvisibility();
        else
            castFireball(target);
    }

    private void castInvisibility() {
        evasionBuff = 100;
        evasion += evasionBuff;

        System.out.println(username + " seems to have disappeared...");
    }

    private void castFireball(Character target) {
        boolean isEasy = difficulty == MyProgram.Difficulty.EASY;

        int damageDealt = isEasy ? 8 : 15;

        if (rand.nextInt(20) == 0) {
            // 1 in 20 chance of explosion
            System.out.println(username + "'s fireball exploded, dealing extra damage.");
            damageDealt = isEasy ? 30 : 50;
        }

        if (rand.nextInt(200) < target.evasion) {
            System.out.println(target.username + " dodged " + username + "'s attack!");
        } else {
            System.out.println(username + " fireballed " + target.username + " for " + damageDealt + " damage!");
            target.damage(damageDealt);
        }
    }
}
