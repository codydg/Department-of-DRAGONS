package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

public abstract class Monster extends Character {
    protected MyProgram.Difficulty difficulty;

    Monster(String username, MyProgram.Difficulty difficulty) {
        super(username);
        this.difficulty = difficulty;
        if (difficulty == MyProgram.Difficulty.HARD) {
            maxHealth = 150;
            health = maxHealth;
        }
    }
}
