import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Elephant here.
 * 
 * @author (Chris Branton) 
 * @version (2019-11-25)
 */
public class Elephant extends Animal
{
    final int ELEPHANT_W = 60;
    final int ELEPHANT_H = 50;
    final int ELEPHANT_SPEED = 1;
    final int MOUSE_RADIUS = 50;
    
    /**
     * Constructor - make the Elephant
     */
    
    public Elephant()
    {
        GreenfootImage image = getImage();  
            image.scale(ELEPHANT_W, ELEPHANT_H);  
            setImage(image);  
    }
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(ELEPHANT_SPEED);
        if(isAtEdge())
        {
            turn(180);
        }
        checkForMice();
    }
    protected void checkForMice()
    {
        // Move slowly "east". Reverse when elephant reaches the edge.
        
        
        // check to see if a mouse is close
        java.util.List<Mouse>closeMice = getObjectsInRange(MOUSE_RADIUS, Mouse.class);
        for  (Mouse mouse : closeMice )
        {
        // We will end up running from the last in the list
            turnTowards(mouse.getX(), mouse.getY());
            turn (180);
        }
    }
   
}
