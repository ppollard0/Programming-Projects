import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * MyWorld sets the start, win, and loss screens and initilaizes the levels.
 * 
 * @author Paul Pollard 
 * @version 2/11/22
 */
public class MyWorld extends World
{
    // Variables
    private static int WorldX = 1000;
    private static int WorldY = 500;
    
    private final int START = 0;
    private final int PLAYING1 = 1;
    private final int PLAYING2 = 3;
    private final int SUSPENDED = 4;
    private final int WIN = 5;
    private final int LOSE = 6;
    
    private int curState;
        
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1000x500 cells with a cell size of 1x1 pixels.
        super(WorldX, WorldY, 1);
        
        curState = START;
        
        this.setBackground("Start.png");
    }
    
    /**
     * Changes states for the level
     */
    public void act() {
        List<Shrimp> shrimps = this.getObjects(Shrimp.class);
        int numShrimp = shrimps.size();
    
        List<Clownfish> clownfish = this.getObjects(Clownfish.class);
        int numClownfish = clownfish.size();
        
        if (curState == START && Greenfoot.isKeyDown("space")) {
            curState = PLAYING1;
            this.setBackground("wet-blue.jpg");
            initializeLevel1();
        }
        
        if (curState == PLAYING1 &&
        numShrimp == 0 && Greenfoot.isKeyDown("N")) {
            curState = PLAYING2;
            removeLevelObjs();
            this.setBackground("wet-blue.jpg");
            initializeLevel2();
        }
        
        if (curState == PLAYING2 &&
        numShrimp == 0 && Greenfoot.isKeyDown("W")) {
            curState = WIN;
            removeLevelObjs();
            this.setBackground("Winner-Background.png");
        }
        
        if ((curState == PLAYING2 || curState == PLAYING1) && 
        numClownfish == 0 && Greenfoot.isKeyDown("L")) {
                curState = LOSE;
                removeLevelObjs();
                this.setBackground("Eel-Lose.png");
        }
        
        if ((curState == WIN || curState == LOSE) && Greenfoot.isKeyDown("R")) {
            curState = START;
            this.setBackground("Start.png");
        }
    }
    
    /**
     * Spawns level one objects.
     */
    private void initializeLevel1() {
        // Add eels
        int eel1X = Greenfoot.getRandomNumber(WorldX/2);
        int eel1Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel1X, eel1Y);
        
        int eel2X = Greenfoot.getRandomNumber(WorldX/2);
        int eel2Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel2X, eel2Y);
        
        int eel3X = Greenfoot.getRandomNumber(WorldX/2);
        int eel3Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel3X, eel3Y);
        
        // Add clownfish
        
        int cfX = Greenfoot.getRandomNumber(WorldX/2);
        int cfY = Greenfoot.getRandomNumber(WorldY/2);
        
        cfX = cfX + WorldX/2;
        cfY = cfY + WorldY/2;
        
        this.addObject(new Clownfish(), cfX, cfY);
        
        // Add shrimp
        
        int sX = Greenfoot.getRandomNumber(WorldX);
        int sY = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX, sY);
        
        int sX1 = Greenfoot.getRandomNumber(WorldX);
        int sY1 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX1, sY1);
        
        int sX2 = Greenfoot.getRandomNumber(WorldX);
        int sY2 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX2, sY2);
        
        int sX3 = Greenfoot.getRandomNumber(WorldX);
        int sY3 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX3, sY3);
        
        int sX4 = Greenfoot.getRandomNumber(WorldX);
        int sY4 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX4, sY4);
        
        // Add anemone
        
        int aX = Greenfoot.getRandomNumber(WorldX);
        int aY = Greenfoot.getRandomNumber(WorldX);
        
        if (aY <= WorldY/2){
            aX = (aX - 100) + WorldX/2;
        }
        else {
            aX = aX;
        }
        aY = (aY - 100);
        
        this.addObject(new Anemone(), aX, aY);
        
        int aX1 = Greenfoot.getRandomNumber(WorldX);
        int aY1 = Greenfoot.getRandomNumber(WorldX);
        
        if (aY1 <= WorldY/2){
            aX1 = (aX1 - 100) + WorldX/2;
        }
        else {
            aX1 = aX1;
        }
        aY1 = (aY1 - 100);
        
        this.addObject(new Anemone(), aX1, aY1);
        
        int aX2 = Greenfoot.getRandomNumber(WorldX);
        int aY2 = Greenfoot.getRandomNumber(WorldX);
        
        if (aY2 <= WorldY/2){
            aX2 = (aX2 - 100) + WorldX/2;
        }
        else {
            aX2 = aX2;
        }
        
        aY2 = aY2 - 100;
        
        this.addObject(new Anemone(), aX2, aY2);
    }
    
    /**
     * Spawns level 2 objects.
     */
    private void initializeLevel2() {
        // Add eels
        int eelX = Greenfoot.getRandomNumber(WorldX/2);
        int eelY = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eelX, eelY);
        
        int eel1X = Greenfoot.getRandomNumber(WorldX/2);
        int eel1Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel1X, eel1Y);
        
        int eel2X = Greenfoot.getRandomNumber(WorldX/2);
        int eel2Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel2X, eel2Y);
        
        int eel3X = Greenfoot.getRandomNumber(WorldX/2);
        int eel3Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel3X, eel3Y);
        
        int eel4X = Greenfoot.getRandomNumber(WorldX/2);
        int eel4Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel4X, eel4Y);
        
        int eel5X = Greenfoot.getRandomNumber(WorldX/2);
        int eel5Y = Greenfoot.getRandomNumber(WorldY/2);
        
        this.addObject(new Eel(), eel5X, eel5Y);
        
        // Add clownfish
        
        int cfX = Greenfoot.getRandomNumber(WorldX/2);
        int cfY = Greenfoot.getRandomNumber(WorldY/2);
        
        cfX = cfX + WorldX/2;
        cfY = cfY + WorldY/2;
        
        this.addObject(new Clownfish(), cfX, cfY);
        
        // Add shrimp
        
        int sX = Greenfoot.getRandomNumber(WorldX);
        int sY = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX, sY);
        
        int sX1 = Greenfoot.getRandomNumber(WorldX);
        int sY1 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX1, sY1);
        
        int sX2 = Greenfoot.getRandomNumber(WorldX);
        int sY2 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX2, sY2);
        
        int sX3 = Greenfoot.getRandomNumber(WorldX);
        int sY3 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX3, sY3);
        
        int sX4 = Greenfoot.getRandomNumber(WorldX);
        int sY4 = Greenfoot.getRandomNumber(WorldY);
        this.addObject(new Shrimp(), sX4, sY4);
        
        // Add anemone
        
        int aX = Greenfoot.getRandomNumber(WorldX);
        int aY = Greenfoot.getRandomNumber(WorldX);
        
        if (aY <= WorldY/2){
            aX = (aX - 100) + WorldX/2;
        }
        else {
            aX = aX;
        }
        aY = (aY - 100);
        
        this.addObject(new Anemone(), aX, aY);
        
        int aX1 = Greenfoot.getRandomNumber(WorldX);
        int aY1 = Greenfoot.getRandomNumber(WorldX);
        
        if (aY1 <= WorldY/2){
            aX1 = (aX1 - 100) + WorldX/2;
        }
        else {
            aX1 = aX1;
        }
        aY1 = (aY1 - 100);
        
        this.addObject(new Anemone(), aX1, aY1);
        
        int aX2 = Greenfoot.getRandomNumber(WorldX);
        int aY2 = Greenfoot.getRandomNumber(WorldX);
        
        if (aY2 <= WorldY/2){
            aX2 = (aX2 - 100) + WorldX/2;
        }
        else {
            aX2 = aX2;
        }
        
        aY2 = aY2 - 100;
        
        this.addObject(new Anemone(), aX2, aY2);
    }
    
    /**
     * Removes all objects from level
     */
    private void removeLevelObjs() {
        List<Eel> myEList = this.getObjects(Eel.class);
        for(Eel curEel : myEList) {
            this.removeObject(curEel);
        }
        
        List<Clownfish> myCList = this.getObjects(Clownfish.class);
        for(Clownfish curClownfish : myCList) {
            this.removeObject(curClownfish);
        }
        
        List<Shrimp> mySList = this.getObjects(Shrimp.class);
        for(Shrimp curShrimp : mySList) {
            this.removeObject(curShrimp);
        }
        
        List<Anemone> myAList = this.getObjects(Anemone.class);
        for(Anemone curAnemone : myAList) {
            this.removeObject(curAnemone);
        }
    }
}