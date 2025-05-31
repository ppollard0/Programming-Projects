import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpaceStation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceStation extends Actor
{
    final int SPACESTATION_W = 60;
    final int SPACESTATION_H = 50;
    
    public SpaceStation()
    {
        GreenfootImage image = getImage();  
            image.scale(SPACESTATION_W, SPACESTATION_H);  
            setImage(image); 
    }    
    
    /**
     * Act - do whatever the SpaceStation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
