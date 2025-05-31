import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;

/**
 * Write a description of class Plains here.
 * 
 * @author (Chris Branton) 
 * @version (2019-11-25)
 */
public class Plains extends World
{
    
    final private int NUM_ASTEROIDS = 4;
    final private int NUM_SPACESTATIONS = 5;
    final private int NUM_MICE = 3;
    private final int NUM_ELEPHANTS = 6;
    int done = 3;
    
    /**
     * Constructor for objects of class Plains.
     * 
     */
    public Plains()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void populate(int numAsteroids, int numSpaceStations)
    {
     for(int asteroids = 0; asteroids < numAsteroids; asteroids++)
     {
     spawnAsteroid();  
     }
     
     for (int spacestations = 0; spacestations < numSpaceStations; spacestations++)
     {
         addObject(new SpaceStation(), 0,Greenfoot.getRandomNumber(getHeight()));
     }
      addObject(new SpaceShip(), (getWidth()/10), (getHeight()/2));
      
     
    }

    public void spawnAsteroid()
        {
            addObject(new Asteroid (180,2), getWidth(),Greenfoot.getRandomNumber(getHeight()));
        }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
     
    }
}
