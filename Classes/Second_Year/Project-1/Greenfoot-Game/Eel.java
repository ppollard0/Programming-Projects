import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Eel class is the enemy character
 * 
 * @author Paul Pollard 
 * @version 2/11/22
 */
public class Eel extends Actor
{
    private final int SPEED = 6;
    private final int SPEED_BOOST = 20;
    private final int TURN_RAD = 180;
    /**
     * Moves eel in a line and has them turn randomly when touching the edge
     * and turning around when touching an Anemone.
     * 
     * Eats clownfish when touching.
     */
    public void act()
    {
        if (this.isAtEdge()) {
            int degToTurn = Greenfoot.getRandomNumber(TURN_RAD);
            this.turn(degToTurn);
        }
        
        if (this.isTouching(Anemone.class)) {
            this.turn(TURN_RAD);
            this.move(SPEED_BOOST);
        }
        
        if (this.getOneIntersectingObject(Clownfish.class) != null) {
            Clownfish myMeal =
            (Clownfish)this.getOneIntersectingObject(Clownfish.class);
            eatC(myMeal);
        }
        this.move(SPEED);
    }
    
    /**
     * Removes the Clownfish class when eaten.
     */
    private void eatC(Clownfish meal) {
        World myWrld = (MyWorld)this.getWorld();
        myWrld.removeObject(meal);
    }
}
