import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * LobWorld will illustrate the concepts of classes, methods, and inheritance. It also 
 * illustrates the role the act method of a world plays in the game loop.  The scoreboard 
 * keeps track of the number of Lobster objects in the world-initially 2.  
 * 
 * Pressing the "a" key will add a lobster at a random position in the game.  Pressing the
 * "d" key will remove a randomly selected lobster from the game.
 * 
 * @author S. Sigman 
 * @version v2.0 9/2/2020
 */
public class LobWorld extends World
{
    private ScrBoard theBoard;
    
    /**
     * Construct a 1024x576 world that contains a lobster.
     * 
     */
    public LobWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1); 
        
        // add the scoreboard
        theBoard = new ScrBoard();
        this.addObject(theBoard, 864, 60);
    
        // adding the lobsters 
        this.addObject(new Lob(), 40, 100);
        this.addObject(new Lob(), 150, 200);
        
        // Show the initial count of Lob objects
        theBoard.showLobsCnt(2);
    }
    
    /**
     * The act() method dose the following:
     *   *  update the number of objects in the world on the scoreboard,
     *   *  checks if the "a" key has been pressed, if so, adds a Lob object at a 
     *      random position, and
     *   *  checks if the "d" key has been pressed, if so, removes a randomly selected
     *      Lob object from the world.
     */
    public void act() {
        List<Lob> lobs = this.getObjects(Lob.class);
        int numLobs = lobs.size();
        theBoard.showLobsCnt(numLobs);
        
        // add or remove Lob objects
        String key = Greenfoot.getKey();
        if (key != null && key.equals("a")){
            // add a Lob object at a random position
            // calculate a random new position (10 is a fudge factor)
            int offsetX = Greenfoot.getRandomNumber(this.getWidth()-10);
            int offsetY = Greenfoot.getRandomNumber(this.getHeight()-10);
            
            // add a Lob object
            this.addObject(new Lob(),offsetX, offsetY);
        }
        else if (key != null && key.equals("d")) {
           if (lobs.size() != 0) {
               // remove a randomly chosen Lob from the world
               // get a random lob from the list
               int lobIndex = Greenfoot.getRandomNumber(lobs.size());
               Lob oneLob = lobs.remove(lobIndex);
               
               // remove the chosen object
               this.removeObject(oneLob);
            }
        } 
    }    
}
