class Archer extends Player
{
    // Store whether or not archer is handicapped
    private boolean aimbotEnabled;
    
    // Archer-specific variables
    private int numberOfArrows;
    
    public Archer(String username)
    {
        super(username);
        
        aimbotEnabled = false;
        
        numberOfArrows = 100;
    }
    
    public String toString()
    {
        return super.toString() +
            "Class: Archer" +
            "\nAimBot Enabled: " + aimbotEnabled + 
            "\nNumber of Arrows: " + numberOfArrows + "\n";
    }
    
    public void shootArrow(Player target)
    {
        // Ensure we have arrows
        if (numberOfArrows <= 0)
        {
            System.out.println(username + " tried to shoot an arrow, but has none.");
            return;
        }
        
        // Shoot arrow
        numberOfArrows--;
        boolean hitTarget = true;
        if (!aimbotEnabled && rand.nextInt(2) == 0)
        {
            hitTarget = false;
        }
        if (rand.nextInt(100) < target.evasion)
        {
            hitTarget = false;
        }
        
        if (hitTarget)
        {
            int damageDealt = 5;
            System.out.println(username + " shot " + target.username + " for " + damageDealt + " damage!");
            target.damage(damageDealt);
            addXp(damageDealt);
        } else {
            System.out.println(username + " shot at " + target.username + ", but missed.");
        }
        
        // Warn user if they're out of arrows
        if (numberOfArrows <= 0)
        {
            System.out.println(username + " is out of arrows!");
        }
    }
    
    public void enableAimbot()
    {
        System.out.println(username + " enabled aimbot");
        aimbotEnabled = true;
    }
    
    public void disableAimbot()
    {
        System.out.println(username + " disabled aimbot");
        aimbotEnabled = false;
    }
    
    // Level 1 range: 50m
    // Each levelup adds 10m
    
}
