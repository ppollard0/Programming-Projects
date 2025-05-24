import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.Actor;

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Actor
{
    final int ASTEROID_W = 50;
    final int ASTEROID_H = 50;
    static final protected int EXPLOSION_RADIUS = 20;
    static final protected int direction = 180;
    static final protected int speed = 2;
    
    public Asteroid(int direction, int speed)
    {
        GreenfootImage image = getImage();  
            image.scale(ASTEROID_W, ASTEROID_H);  
            setImage(image); 
        
        this.setRotation(direction);
    } 
    
    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.move(speed);
        if(this.objectsInExplosion())
        {
            this.getWorld().removeObject(this);
        }
        else if(this.isAtEdge())
        {
            this.getWorld().removeObject(this);
            //Plains plains = (Plains)getWorld().getObjects(Plains.class).get(0);
            //plains.spawnAsteroid();
        }
    }   
    private boolean objectsInExplosion() 

    { 

        java.util.List<Actor> inExplosionList = getObjectsInRange(EXPLOSION_RADIUS, Actor.class); 
        boolean objectsBlownUp = false; 
        World myWorld = this.getWorld(); 
        for(Actor curAnim: inExplosionList)  
        { 
                if(!(curAnim instanceof Asteroid))
                {
                        objectsBlownUp = true; 
                        myWorld.removeObject(curAnim);
                }
        } 
        return objectsBlownUp; 
    } 
}
