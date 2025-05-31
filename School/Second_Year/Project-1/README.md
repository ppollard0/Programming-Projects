# Programming Assignment 1 - Greenfoot Game

## Assignment Details

### General Directions

Create a Greenfoot game that meets the following requirements.  To get started, clone this empty directory using IntelliJ.  Close IntelliJ and work in Greenfoot.

### Requirements

Your game must implement the following:
*  Have a clearly define objective that defines how the game is won.
*  Have at least one character whose behavior is controlled by the computer (the adversary or adversaries).
*  Have a single character whose behavior is controlled by the person playing the game (the protagonist).
*  For a single game, game play must not exceed a maximum of 5 minutes.
*  Your game should implement states: Started, Playing, Win, Loose.
*  Game Challenge: Implement two levels of play-A beginning level and an advanced play level. The advanced play level should be entered when the beginning level is won.  Play at the advanced level should be faster, or require more skill, or have players with advanced capabilities.

### Checkpoints

Make sure that:
*  you test your game before submitting it.
*  all your methods are documented following Javadoc standards.
*  your code in all methods is commented and formatted so it is easy to read.
*  you hae not used magic numbers.
*  all your image files are placed in the *image folder* of your game project.
*  you reference images using only their name (i.e., splash.png) and not their storage path.

## Submission

When your game is finished, use IntelliJ to commit your changes to your local repo and then push the game back to your repo.  (Note:  You can do this step at any point in the development of the game that you wish.  If it were me, I would commit my changes and push the game back to my repo at the end of every work session.  The approach of committing and pushing often is not paranoid. It is good practice.)  In addition to pushing your code back to your repo, Export your game using the Share button as a Java Application.  Upload the executable .jar file that results to the Moodle assignment.

## Grading Rubric

The game must run in order to be assigned a grade.  I will grade your game using the program grading rubric posted on Moodle.  The rubric describes the grading criteria for each component of the program grade-correctess, readability, documentation, code design, and discretionary points .   Please note that you must use Javadoc comments  for methods that have a statement describing the purpose of the method, @param and @retun statements as appropriate.  Each class must also have a completed class comment.  Use complete sentences in the class and method comments.  The readability of your code is an important component of the grade.  To judge readability I will be looking at the use of indentation, blank lines, meaningful variable names, adherence to Java naming conventions, line width that fits on a screen, and the avoidance of magic numbers.

### Example Javadoc comments

#### Example Class Comment
```
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
 ```
 #### Example Method Comment
 ```
 /**
   * showLobCnt displays the number of Lob objects passed to it on the scoreboard.
   * 
   * @param numLob The number of Lob objects in the world.
   */
 ```
