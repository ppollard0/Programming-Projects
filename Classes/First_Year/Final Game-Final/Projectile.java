import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    static final protected int EXPLOSION_RADIUS = 20;
    private int speed;
    
    public Projectile(int direction, int speed)
    {
        this.setRotation(direction);
        this.speed = speed;
        
        
    }
    /**
     * Act - do whatever the projectile wants to do. This method is called whenever
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
        }
    }    
    private boolean objectsInExplosion() 

    { 

        java.util.List<Actor> inExplosionList = getObjectsInRange(EXPLOSION_RADIUS, Actor.class); 
        boolean objectsBlownUp = false; 
        World myWorld = this.getWorld(); 
        for(Actor curAnim: inExplosionList)  
        { 
                if(!(curAnim instanceof DrivableElephant))
                {
                    if(!(curAnim instanceof Projectile))
                    {
                        if(!(curAnim instanceof SpaceShip))
                    {
                        objectsBlownUp = true; 
                        myWorld.removeObject(curAnim);
                    }
                    }
                }
        } 
        return objectsBlownUp; 
    } 
}
