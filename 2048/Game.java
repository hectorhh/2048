/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java Game
 * 
 * This program will combine the Score class and the Grid class to create the 
 * game and allow the user to play it
 */
public class Game {
    public static void main(String[] args) {
        // will start a new game
        newGame();
    }
    
    /**
     * Description: function that will recursively call itself if the game is
     *              lost or won so that the user can continue to play
     * Input: none
     * Output: return type is void
     */
    public static void newGame() {
        // create and draw the grid
        Grid grid = new Grid();
        grid.drawGrid();
        
        // create and draw the score board
        Score score = new Score();
        score.draw2048(); // will draw the 2048 logo with instructions
        score.drawBoard(.75, .85);
        
        while (true) {
            // will move up, down, left, or right depending on the key typed
            if (PennDraw.hasNextKeyTyped()) {
                char c = PennDraw.nextKeyTyped();
                switch (c) {
                    case 'W':
                    case 'w': grid.moveUp();
                    break;
                    case 'S':
                    case 's': grid.moveDown();
                    break;
                    case 'D':
                    case 'd': grid.moveRight();
                    break;
                    case 'A':
                    case 'a': grid.moveLeft();
                    break;
                }
                // draw the grid, board and logo again
                grid.drawGrid();
                score.draw2048();
                score.drawBoard(.75, .85);
                
                // if you win the game
                if (grid.winGame()) {
                    break;
                }
                
                // if you lose the game
                if (grid.gameLost()) {
                    break;
                }
            }
        }
        
        // if you win the game
        if (grid.winGame()) {
            // output the victory message and draw your final score
            score.messageWin();
            score.drawBoard(.5, .4);
            
            while (true) {
                // if the space bar is pressed, the game restarts
                if (PennDraw.hasNextKeyTyped()) {
                    char c = PennDraw.nextKeyTyped();
                    if (c == ' ' || c == ' ') { 
                        newGame();
                    }
                }
            }
        }
        
        // if you lose the game
        if (grid.gameLost()) {
            // outputs the lost message and draws your final score
            score.messageLose();
            score.drawBoard(.5, .4);
            
            while (true) {
                // if the space bar is pressed, the game restarts
                if (PennDraw.hasNextKeyTyped()) {
                    char c = PennDraw.nextKeyTyped();
                    if (c == ' ' || c == ' ') { 
                        newGame();
                    }
                }
            }
        }
    }
}