import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mouse extends Animal
{
    final int MOUSE_W = 16;
    final int MOUSE_H = 16;
    final int MOUSE_SPEED = 2;

    public Mouse()
    {
          GreenfootImage image = getImage();  
            image.scale(MOUSE_W, MOUSE_H);  
            setImage(image);  
    }
    /**
     * Act - do whatever the Mouse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        move(MOUSE_SPEED);
        if (Greenfoot.getRandomNumber(50) < 5)
        {
            turn(Greenfoot.getRandomNumber(90));
            eat();
        }
    }    
    /**
     *If the mouse runs across some cheese, the mouse eats the cheese.
     */
    protected void eat()
    {
       Actor cheese;
       // Offset of 0,0 means mouse is in the same cell as the Cheese
       cheese = getOneObjectAtOffset(0,0,Cheese.class);
       if (cheese != null)
       {
           World w;
           w = getWorld();
           w.removeObject(cheese);
        }
    }
}
