package com.example.consoleprogram.characters;

import com.example.consoleprogram.game.MyProgram;

public class RatMischief extends Monster {
    static final private double RECRUIT_HEALTH_GAIN = 0.4;
    static final private int BASE_RAT_QUANTITY = 200;

    private int numRats = BASE_RAT_QUANTITY;

    public RatMischief(MyProgram.Difficulty difficulty) {
        super("Rat Mischief", difficulty);
        updateRatsAndEvasion();
    }

    public String getDescription()
    {
        return super.getDescription() +
                "Number of Rats: " + numRats +
                "\nEvasion: " + evasion;
    }

    private enum Attack {
        SWARM,
        BITE,
        RECRUIT
    }

    @Override
    public void damage(int points) {
        super.damage(points);

        updateRatsAndEvasion();
    }

    private void recruit() {
        health += RECRUIT_HEALTH_GAIN * health;
        if (health > maxHealth) {
            health = maxHealth;
        }

        updateRatsAndEvasion();
    }

    private void updateRatsAndEvasion() {
        numRats = health * 2;
        // 200 rats -> 10 evasion
        // ~0 rats -> 90 evasion
        evasion = (int)(-0.4 * numRats) + 90;
    }

    @Override
    public void attack(Character target) {
        Attack attack = null;
        while (attack == null) {
            int attackDecider = rand.nextInt(100);
            if (attackDecider < 15) {
                // At least 100 rats must be alive to swarm
                if (numRats >= 100) {
                    attack = Attack.SWARM;
                }
            } else if (attackDecider < 90) {
                attack = Attack.BITE;
            } else {
                attack = Attack.RECRUIT;
            }
        }

        // Lower damage based on percentage of rats still alive
        double damageDealt = (double)numRats/BASE_RAT_QUANTITY;

        String attackMessage = numRats + " rats have";
        switch (attack) {
            case SWARM:
                damageDealt *= 30;
                attackMessage += " swarmed around ";
                break;
            case BITE:
                damageDealt *= rand.nextInt(5) + 3;
                attackMessage += " bitten ";
                break;
            case RECRUIT:
                System.out.println(username + " is recruiting more rats.");
                recruit();
                return;
        }

        // Archers may fire skunk arrows to avoid RatMischiefs' attacks
        if (target instanceof Archer && rand.nextInt(100) < 8) {
            System.out.println(target.username + " fired a skunk arrow to avoid " + username + "'s attack!");
            return;
        }

        int damage = (int)damageDealt;
        System.out.println(attackMessage + target.username + " for " + damage + " damage!");
        target.damage(damage);
    }
}
