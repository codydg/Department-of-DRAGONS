package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

import java.util.HashMap;

public abstract class Monster extends Character {
    //static HashMap<String, Integer> usernameUseCounts = new HashMap<>();

    protected MyProgram.Difficulty difficulty;

    Monster(String monsterType, MyProgram.Difficulty difficulty) {
        super(makeUsername(monsterType));
        this.difficulty = difficulty;
        if (difficulty == MyProgram.Difficulty.HARD) {
            maxHealth = 150;
            health = maxHealth;
        }
    }

    static String makeUsername(String monsterType) {
        int i = 0;
        String username;
        do {
            i++;
            username = monsterType + " " + i;
        } while (!Character.isUsernameAvailable(username));

        return username;
    }
}
