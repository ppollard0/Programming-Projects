import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A an instance of the Lob class is a lobster object.
 * The lobster object moves in a simple random motion.
 * 
 * @author S. Sigman 
 * @version v1.0 8/28/2020
 */
public class Lob extends Actor
{
    private static final int TURN_LIMIT = 15;
    private static final int AMOUNT_2_MOVE = 5;
    private static final int TURN_PERCENTAGE = 30;
    /**
     * Act - turn the Lob object through a random angle that is between 0 and (+/-)30 
     * degrees, and then move the Lob object AMOUNT_2_MOVE pixels on the screen. 
     */
    public void act() 
    {
        int deg2Turn = Greenfoot.getRandomNumber(TURN_LIMIT);
        int percent = Greenfoot.getRandomNumber(100);
        if (percent < TURN_PERCENTAGE) {
           // Turn clockwise
           this.turn(deg2Turn);
        }
        else if (percent < TURN_PERCENTAGE*3)
        {
            // turn counterclockwise 
            this.turn(-1*deg2Turn);
        }
        
        this.move(AMOUNT_2_MOVE);
        
       
    }    
}
