import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class drivable_elephant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DrivableElephant extends Elephant
{
    static final protected int TURN_DELTA=2;
    static final protected int MOVE_SPEED=5;
    static final protected int PROJECTILE_SPEED=10;
    
    private int turnDegrees;
    private int moveSpeed;
    
    public void DrivableElephant()
    {
        turnDegrees = TURN_DELTA;
        moveSpeed = MOVE_SPEED;
    }
    private void launch()
    {
        World myWorld = this.getWorld();
        Projectile p1 = new Projectile(this.getRotation(), PROJECTILE_SPEED);
        myWorld.addObject(p1,this.getX(),this.getY());
    }
    /**
     * Act - do whatever the drivable_elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown("left")){
            this.turn(-1*TURN_DELTA);
        }    
        if (Greenfoot.isKeyDown("right")){
            this.turn(TURN_DELTA);
        }  
        if (Greenfoot.isKeyDown("up")){
            this.move(MOVE_SPEED);
        }
        if (Greenfoot.isKeyDown("down")){
            this.move(-1*MOVE_SPEED);
        }
        if(Greenfoot.isKeyDown("space"))
        {
            this.launch();
        }
    }
}