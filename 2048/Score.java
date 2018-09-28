/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java Score
 * 
 * This program will create a score board and will track the number of valid 
 * moves made by the player. It also includes the drawings for the aesthetic 
 * of the game and the message outputted when someone wins or loses.
 */
public class Score {
    private static int numMoves; // number of moves made throughout the game
    private double x; // x coordinate
    private double y; // y coordinate
    private String message; // message that will be drawn
    
    
    /**
     * Description: constructor for the score board with given (x, y)
     * Input: double x is x-coordinate and double y is the y-coordinate that 
     *        represent where the score board will be drawn
     * Output: None 
     */
    public Score() {
        resetNumMoves(); //sets number of moves to 0
    }
    
    /**
     * Description: resets the number of moves to 0
     * Input: none
     * Output: return type is void
     */
    private void resetNumMoves() {
        numMoves = 0;
    }
    
    /**
     * Description: returns the  number of moves made by the player
     * Input: none
     * Output: an int representing the number of moves made by the player
     */
    private int getNumMoves() {
        return numMoves;
    }
    
    /**
     * Description: will increase the number of moves by 1
     * Input: none
     * Output: return type is void
     */
    public static void increase() {
        numMoves++;
    }
    
    /**
     * Description: will draw the board with the specified message
     * Input: none
     * Output: No return type is specified
     */
    public void drawBoard(double x, double y) {
        if (x < 0 || x > 1) {
            throw new IllegalArgumentException("x must be in [0, 1]");
        }
        if (y < 0 || y > 1) {
            throw new IllegalArgumentException("y must be in [0, 1]");
        }
        PennDraw.setFontSize(15);
        PennDraw.text(x, y, "Number of Moves: " + getNumMoves());
    }
    
    /**
     * Description: will draw the instructions and the 2048 logo
     * Input: none
     * Output: return type is void
     */
    public void draw2048() {
        //instructions to play game
        PennDraw.setFontBold();
        PennDraw.setFontSize(15);
        PennDraw.text(.70, .9, "Combine the Tiles to Reach 2048");
        
        // draws the 2048 logo
        PennDraw.setFontSize(60);
        PennDraw.text(.2, .9, "2048");
    }
    
    /**
     * Description: will draw the victory message
     * Input: none
     * Output: return type is void
     */
    public void messageWin() {
        PennDraw.setPenColor(255, 250, 150);
        PennDraw.filledSquare(.5, .5, .5);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(20);
        message = "You Win! Click the Space Bar to Play Again";
        PennDraw.text(.5, .5, message);
        
    }
    
    /**
     * Description: will draw the losing message
     * Input: none
     * Output: return type is void
     */
    public void messageLose() {
        PennDraw.setPenColor(255, 250, 150);
        PennDraw.filledSquare(.5, .5, .5);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(20);
        message = "You Lose! Click the Space Bar to Play Again";
        PennDraw.text(.5, .5, message);
        
    }
}