import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shrimp class is the collectable for the player.
 * 
 * @author Paul Pollard 
 * @version 2/11/22
 */
public class Shrimp extends Actor
{
    private final int SPEED = 1;
    private final int SPEED_BOOST = 20;
    private final int TURN_RAD = 180;
    /**
     * Moves shrimp in a line and has them turn randomly when touching the edge
     * and turning around when touching an Anemone.
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
        this.move(SPEED);
    }
}
