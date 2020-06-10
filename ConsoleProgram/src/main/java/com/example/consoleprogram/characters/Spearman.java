package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

import java.util.Arrays;
import java.util.List;

public class Spearman extends Player {
    private int energy = 100;

    public Spearman(String username) {
        super(username);
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() +
                "Class: Spearman\n";
    }

    @Override
    public String toString() {
        return super.toString() + " Energy: " + energy;
    }

    @Override
    public void attack(Character target) {
        // Determine if attack misses due to fatigue
        // Note: Chance is 0% when energy is 100% and Chance is 75% when energy is at 5%
        int chanceToMiss = (int) ((-15.0/19.0) * energy + (1500.0/19.0));
        if (rand.nextInt(100) < chanceToMiss)
        {
            System.out.println(username + " missed " + target.username + " due to fatigue!");
            return;
        }
        // Determine if target dodges attack
        if (rand.nextInt(75) < target.evasion)
        {
            System.out.println(target.username + " dodged " + username +   "'s attack!");
            return;
        }

        String attackName;
        int damageDealt;
        int energyTaken;
        List<String> attacks = Arrays.asList("Jab (15 E)", "Poke (5 E)", "Thrust (20 E)");
        int choice = MyProgram.readOption(attacks);
        switch (choice)
        {
            case 1:
                attackName = "Jab";
                damageDealt = rand.nextInt(6) + 25;
                energyTaken = 15;
                break;
            case 2:
                attackName = "Poke";
                damageDealt = rand.nextInt(6) + 5;
                energyTaken = 5;
                break;
            case 3:
            default:
                attackName = "Thrust";
                damageDealt = rand.nextInt(11) + 30;
                energyTaken = 20;
                break;
        }

        if (energy >= energyTaken) {
            System.out.println(username + " used " + attackName + " on " + target.username + " for " + damageDealt + " damage!");
            target.damage(damageDealt);
            energy -= energyTaken;
            addXp(damageDealt);
        } else {
            System.out.println(username + " tried to attack " + target.username + ", but doesn't have the energy.");
        }
    }

    @Override
    public void pass() {
        energy += 20;
        System.out.println(this + " has regained some energy.");
    }
}
