import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clownfish class is the player character.
 * 
 * @author Paul Pollard 
 * @version 2/11/22
 */
public class Clownfish extends Actor
{
    private final int SPEED = 5;
    private final int TURN_SPEED = 5;
    /**
     * Controls fish to turn and move forwards and backwards.
     * 
     * Eats shrimp when touching.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("right")) {
            this.move(SPEED);
        }
        
        if (Greenfoot.isKeyDown("left")) {
            this.move(-SPEED);
        }
        
        if (Greenfoot.isKeyDown("up")) {
            this.turn(TURN_SPEED);
        }
        
        if (Greenfoot.isKeyDown("down")) {
            this.turn(-TURN_SPEED);
        }
        
        if (this.getOneIntersectingObject(Shrimp.class) != null) {
            Shrimp myMeal =
            (Shrimp)this.getOneIntersectingObject(Shrimp.class);
            eatS(myMeal);
        }
    }
    
    /**
     * Removes the Clownfish class when eaten.
     */
    private void eatS(Shrimp meal) {
        World myWrld = (MyWorld)this.getWorld();
        myWrld.removeObject(meal);
    }
}
