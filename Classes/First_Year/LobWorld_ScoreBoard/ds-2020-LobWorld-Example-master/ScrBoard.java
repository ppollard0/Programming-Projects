import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScrBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrBoard extends Actor
{
    private int DEFAULT_SCOREBOARD_WIDTH = 300;
    private int DEFAULT_SCOREBOARD_HEIGHT = 100;
    private int FONT_SIZE = 20;
    private int TOP_TITLE_TXT_MARGIN = 20;
    private int SCORE_Y_POSITION =(int)(DEFAULT_SCOREBOARD_HEIGHT*0.7);
    private GreenfootImage scoreboard;
    private String scoreMsg;
    
    public ScrBoard() {
        scoreboard = this.makeScoreboardImage();   
        this.setImage(scoreboard);
    }
    
    /**
     * makeScoreboardImage creates the image for the scoreboard object.  The image
     * initial scoreboard image created has a size of 
     * DEFAULT_SCOREBOARD_WIDTH x DEFAULT_SCOREBOARD_HEIGHT.  The image has the title 
     * "Lob Score Card."
     * 
     * @return The scoreoreboard image is returned.
     */
    private GreenfootImage makeScoreboardImage() {
        GreenfootImage sb = new GreenfootImage(DEFAULT_SCOREBOARD_WIDTH, 
                                               DEFAULT_SCOREBOARD_HEIGHT);
        
        sb.setColor(Color.BLACK);
        sb.fill();
        sb.setTransparency(200);
        
        // Draw the score board title
        Font defFont = sb.getFont();
        Font newFont = defFont.deriveFont(FONT_SIZE);
        sb.setFont(newFont);
        
        sb.setColor(Color.RED);
        sb.drawString("Lob Score Card",DEFAULT_SCOREBOARD_WIDTH/2 - 70, 
                                       TOP_TITLE_TXT_MARGIN);
        
        return sb;
    }
    
    /**
     * showLobCnt displays the number of Lob objects passed to it on the scoreboard.
     * 
     * @param numLob The number of Lob objects in the world.
     */
    public void showLobsCnt(int numLob) {
        scoreboard.clear();
        scoreboard = makeScoreboardImage();
         
        String msg = "Num of Lobs: " + numLob;
        scoreboard.drawString(msg,DEFAULT_SCOREBOARD_WIDTH/2 - 72,
                                  SCORE_Y_POSITION);

        this.setImage(scoreboard);

     
        
    }
    
}
