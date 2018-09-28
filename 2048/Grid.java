/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java Grid
 * 
 * This program will create the grid, deal with the movements of the numbers,
 * combine the numbers when needed, and add a random number to the grid at an 
 * open location. It will also determine when the game has been won or lost
 */
public class Grid {
    private double x; // x coordinate
    private double y; // y coordinate
    
    private int [][] arr; // an int array that will make the grid
    
    private int value; // the value of a number in the location of arr[][]
    
    // represents the rows in the 2D Array but are drawn as columns
    private int row;
    // reprsents the columns in the 2D Array but are drawn as rows
    private int col;
    
    private int SIZE = 4; // the length and height of the array
    private int WIN_VALUE = 2048; // the number needed to win the game
    
    /**
     * Description: constructor for the Grid
     * Input: none
     * Output: none
     */
    public Grid() {
        arr = new int [SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = 0;
            }
        }
        this.x = .125;
        this.y = .125;
        // adds two 2's into the array at a random position
        addNumber(2, 2);
        addNumber(2, 2);
    }
    
    /**
     * Description: draws the Grid 
     * Input: none
     * Output: return type is void
     */
    public void drawGrid() {
        //set the background color to a light brown gray
        PennDraw.clear(150, 135, 110);
        //iterate through the array to draw the tiles
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // will get the value from the array
                value = arr[i][j];
                
                // turns value into a String to find for its equivalent picture
                String pic = Integer.toString(value) + ".png";
                PennDraw.picture(x + .25 * i, y + .2 * j, pic, 80, 80);
            }
        }
    }
    
    /**
     * Description: will determine if you have won the game
     * Input: none
     * Output: a boolean that is true if you win the game and false if you have 
     *         not won the game
     */
    public boolean winGame() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // if a 2048 is anywhere on the board you win
                if (arr[i][j] == WIN_VALUE) return true;
            }
        }
        return false;
    }
    
    /**
     * Description: will determine if you have lost the game or not
     * Input: none
     * Output: a boolean that is true if you have lost the game and false if 
     *         you have not lost
     */
    public boolean gameLost() {
        // if there is an empty space on the grid return false
        if (hasEmptySpace()) {
            return false;
        }
        // checks to see if numbers can be combined
        else return !equalValueAround();
    }
    
    /**
     * Description: determines if there is an empty space on the grid
     * Input: none
     * Output: a boolean that is true if there is an empty space on the grid
     */
    private boolean hasEmptySpace() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // if there is a zero return true
                if (arr[i][j] == 0) return true;
            }
        }
        // if there is no zero on the grid return false
        return false;
    }
    
    /**
     * Description: will check to see if a number has the same value as the 
     *              number to the right or top
     * Input: none
     * Output: boolean that will return true if a number has the same value 
     *         as the number to the right or top.
     */
    private boolean equalValueAround() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //check the tile to the right
                if (j < SIZE - 1) {
                    if (arr[i][j] == arr[i][j + 1]) return true;
                }
                //check the tile on top
                if (i < SIZE - 1) {
                    if (arr[i][j] == arr[i + 1][j]) return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Description: will pick between 2 values randomly and place it in a random 
     *              location on the grid
     * Input: int value1 that represents one value and int value2 represents 
     *        the second value
     * Output: return type is void
     */
    private void addNumber(int value1, int value2) {
        // if there is an empty space on the board
        if (hasEmptySpace()) {
            int randRow = (int) (4 * Math.random()); // number between 0 and 3
            int randCol = (int) (4 * Math.random()); // number between 0 and 3
            
            //determines if you get value1 or value2
            double ranNum = Math.random(); 
            if (ranNum < .5) value = value1;
            else value = value2;
            
            // add a number to a random location as long as it has a 0
            if (arr[randRow][randCol] == 0) arr[randRow][randCol] = value;
            
            // if location picked does not have a 0, call the function again
            else addNumber(value1, value2); 
        }
        else return;
    }
      
// THE FOLLOWING FUNCTIONS HANDLE THE LEFT MOVEMENT
    /**
     * Description: calls on the helper functions to appropriatley add the 
     *              numbers to the left and shifts them to the left,and places a 
     *              new number randomly
     * Input: none
     * Output: return type is void
     */
    public void moveLeft() {
        boolean youCanAdd = false;
        //will determine if there's a possible movement or addition to the left
        for (int i = 0; i < SIZE; i++) {
            if (checkGridLeft(i)) {
                youCanAdd = true; 
                break;
            }
        }
        // shift numbers to the left, add them and shift them again
        for (int i = 0; i < SIZE; i++) {
            while (checkPreZeroLeft(i)) {
                shiftLeft(i); 
            }
            addLeft(i); 
            while (checkPreZeroLeft(i)) {
                shiftLeft(i);  
            }
        }
        if (youCanAdd) {
            Score.increase(); // increase score
            addNumber(2, 4); // add a number in randomly
        }
    }
    
    /**
     * Description: helper function that will add numbers if they are equal
     * Input: will take in an int representing the column which will be added
     * Output: return type is void
     */
    private void addLeft(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        for (int i = 0; i < SIZE - 1; i++) {
            // if the next number is equal to the current number add them
            if (arr[i][col] == arr[i + 1][col]) {
                arr[i][col] += arr[i + 1][col];
                arr[i + 1][col] = 0; // set the next element equal to 0
            }
        }
    }
    
    /**
     * Description: will shift all the numbers in a column to the left
     * Input: an int representing a column
     * Output: return type is void
     */
    private void shiftLeft(int col) {
        if (col > SIZE - 1 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        // shift to the left if there is a 0 prior to a number that is not 0
        for (int i = 0; i < SIZE - 1; i++) {
            if (arr[i][col] == 0 && arr[i + 1][col] != 0) {
                arr[i][col] = arr[i + 1][col];
                arr[i + 1][col] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that'll check if the number to the left is 0
     * Input: an int representing a column
     * Output: boolean that'll return true if the number to the left of it is 0
     */
    private boolean checkPreZeroLeft(int col) {
        if (col > SIZE - 1 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        for (int i = 0; i < SIZE - 1; i++) {
            // checks if there is a 0 to the left of a non-0 number
            if (arr[i][col] == 0 && arr[i + 1][col] != 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Description: helper function that will tell you if there is a possible 
     *              movement to the left
     * Input: an int representing a column
     * Output: boolean that will return true if there is a possible movement
     *         to the left
     */
    private boolean checkGridLeft(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        // will keep track of how many non-0 numbers there are
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (arr[i][col] != 0) count++;
        }
        
        if (count == 0) return false;
        if (count == 1) {
            // if the non-0 number is in the left most position
            if (arr[0][col] != 0) return false;
        }
        if (count == 2) {
            // if the non-0 numbers are in the left most position
            if (arr[0][col] != 0 && arr[1][col] != 0) {
                // if the non-0 numbers cannot be added
                if (arr[0][col] != arr[1][col]) return false;
            }
        }
        if (count == 3) {
            if (arr[3][col] == 0) {
                // if the non-0 numbers cannot be added
                if (arr[0][col] != arr[1][col] && arr[1][col] != arr[2][col]) {
                    return false;
                }
            }
        }
        if (count == 4) {
            // if the non-0 numbers cannot be added
            if (arr[0][col] != arr[1][col] && arr[1][col] != arr[2][col] && 
                arr[2][col] != arr[3][col]) {
                return false;  
            }
        }
        return true;
    }
    
// THE FOLLOWING FUNCTIONS HANDLE THE RIGHT MOVEMENT
    /**
     * Description: calls on the helper functions to appropriatley add the 
     *              numbers to the right and shifts them right,and places a 
     *              new number randomly
     * Input: none
     * Output: return type is void
     */
    public void moveRight() {
        boolean youCanAdd = false;
        //will determine if there's a possible movement or addition to the right
        for (int i = 0; i < SIZE; i++) {
            if (checkGridRight(i)) {
                youCanAdd = true;
                break;
            }
        }
        //shift numbers to right, add them the right, shift numbers right again
        for (int i = 0; i < 4; i++) {
            while (checkPreZeroRight(i)) {
                shiftRight(i);
            }
            addRight(i);
            while (checkPreZeroRight(i)) {
                shiftRight(i);
            }
        }
        if (youCanAdd) {
            addNumber(2, 4); // add a number in randomly 
            Score.increase(); // increase score
        }
    }
    
    /**
     * Description: helper function that will add elements if they are equal
     *              towards the right
     * Input: a int representing the column to which it will be added to
     * Output: return type is void
     */
    private void addRight(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        for (int i = SIZE - 1; i > 0; i--) {
            // if the number to the left equals the current number add them
            if (arr[i][col] == arr[i - 1][col]) {
                arr[i][col] += arr[i - 1][col];
                arr[i - 1][col] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that will shift the numbers in the grid to 
     *              the right
     * Input: an int representing a row of the 2D array
     * Output: return type is void
     */
    private void shiftRight(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        for (int i = 3; i > 0; i--) {
            // shift numbers right if there is a 0 to the right a non-0 number
            if (arr[i][col] == 0 && arr[i - 1][col] != 0) {
                arr[i][col] = arr[i - 1][col];
                arr[i - 1][col] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that'll check if the number to the right of 
     *              a non-0 number is a 0
     * Input: an int representing a column of the 2D array
     * Output: boolean that will return true if there is a 0 to the right of a 
     *         non-0 number
     */
    private boolean checkPreZeroRight(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        for (int i = 1; i < SIZE; i++) {
            // checks if there is a 0 to the right of a non-0 number
            if (arr[i][col] == 0 && arr[i - 1][col] != 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * Description: helper function that will tell you if there is a possible 
     *              movement right or a possible addition can be made towards
     *              the riht
     * Input: an int representing a column of the 2D array
     * Output: boolean that will return true if there is a possible movement
     *         right or if an addition can be made towards the right
     */
    private boolean checkGridRight(int col) {
        if (col > 3 || col < 0) {
            throw new IllegalArgumentException("col must be in [0, 3]");
        }
        // iterate thrpugh a column to find the number of non-0 numbers
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (arr[i][col] != 0) count++;
        }
        if (count == 0) return false;
        if (count == 1) {
            // if the non-0 number is in the right-most position 
            if (arr[3][col] != 0) return false;
        }
        if (count == 2) {
            // if the non-0 numbers are in the right-most positiosns
            if (arr[3][col] != 0 && arr[2][col] != 0) {
                // will determine if no possible additions can be made 
                if (arr[3][col] != arr[2][col]) return false;
            }
        }
        if (count == 3) {
            // if the non-0 numbers are in the right-most positions
            if (arr[0][col] == 0) {
                // will determine if no possible additions can be made 
                if (arr[3][col] != arr[2][col] && arr[2][col] != arr[1][col]) {
                    return false;
                }
            }
        }
        
        if (count == 4) {
            // will determine if no possible additions can be made 
            if (arr[3][col] != arr[2][col] && arr[2][col] != arr[1][col] && 
                arr[1][col] != arr[0][col]) {
                return false;
            }
        }
        return true;
    } 
    
    
    
    
    
// THE FOLLOWING FUNCTIONS WILL HANDLE ALL THE MOVEMENT DOWNWARDLY    
    /**
     * Description: calls on the helper functions to appropriatley add the 
     *              numbers downwardly and shifts them downs,and places a 
     *              new number randomly
     * Input: none
     * Output: return type is void
     */
    public void moveDown() {
        boolean youCanAdd = false;
        // will determine if there is a possible movement or addition downwardly
        for (int i = 0; i < SIZE; i++) {
            if (checkGridDown(i)) {
                youCanAdd = true;
                break;
            }
        }
        // shift numbers down, add them downwardly, shift numbers down again
        for (int i = 0; i < 4; i++) {
            while (checkPreZeroDown(i)) {
                shiftDown(i);
            }
            addDown(i);
            while (checkPreZeroDown(i)) {
                shiftDown(i);
            }
        }
        if (youCanAdd) {
            addNumber(2, 4); // will add a number randmly 
            Score.increase(); // will increase the number of moves
        }      
    }
    
    /**
     * Description: helper function that will add the numbers downwardly
     * Input: an int representing a row of the 2D array
     * Output: return type is void
     */
    private void addDown(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will add numbers if the number above equals the current number 
        for (int i = 0; i < SIZE - 1; i++) {
            if (arr[row][i] == arr[row][i + 1]) {
                arr[row][i] += arr[row][i + 1];
                arr[row][i + 1] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that will shift the numbers in the grid down
     * Input: an int representing a row of the 2D array
     * Output: return type is void
     */
    private void shiftDown(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will shift all the numbers down if there is a 0 below a non-0 number
        for (int i = 0; i < SIZE - 1; i++) {
            if (arr[row][i] == 0 && arr[row][i + 1] != 0) {
                arr[row][i] = arr[row][i + 1];
                arr[row][i + 1] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that'll check if the number below a non-0 
     *              number is a 0
     * Input: an int representing a row of the 2D array
     * Output: boolean that will return true if there is a 0 below a non-0 
     *         number
     */
    private boolean checkPreZeroDown(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will check if there is a 0 below a non-0 number
        for (int i = 0; i < SIZE - 1; i++) {
            if (arr[row][i] == 0 && arr[row][i + 1] != 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Description: helper function that will tell you if there is a possible 
     *              movement down or if possible addition can be made downwards
     * Input: an int representing a row of the 2D array
     * Output: boolean that will return true if there is a possible shift down
     *         or if an addition can be made downwards
     */
    private boolean checkGridDown(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will keep track of the non-0 numbers in a row of the 2D array
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (arr[row][i] != 0) count++;
        }
        if (count == 0) return false;
        if (count == 1) {
            // if the number is in the downward most position
            if (arr[row][0] != 0) return false;
        }
        if (count == 2) {
            // if the numbers are in the downward most positions
            if (arr[row][0] != 0 && arr[row][1] != 0) {
                // will determine if possible additions can be made downwards
                if (arr[row][0] != arr[row][1]) return false;
            }
        }
        if (count == 3) {
            // if the numbers are in the downward most positions
            if (arr[row][3] == 0) {
                // will determine if possible additions can be made downwards
                if (arr[row][0] != arr[row][1] && arr[row][1] != arr[row][2]) {
                    return false;
                }
            }
        }
        if (count == 4) {
            // will determine if possible additions can be made downwards
            if (arr[row][0] != arr[row][1] && arr[row][1] != arr[row][2] &&
                arr[row][2] != arr[row][3]) {
                return false;    
            }
        }
        return true;
    }
     
// THE FOLLOWING FUNCTIONS WILL HANDLE ALL THE MOVEMENT UPWARDLY    
    /**
     * Description: calls on the helper functions to appropriatley add the 
     *              numbers upwardly and shifts them up,and places a 
     *              new number randomly
     * Input: none
     * Output: return type is void
     */
    public void moveUp() {
        boolean youCanAdd = false;
        // will determine if there is a possible movement or addition upwardly
        for (int i = 0; i < SIZE; i++) {
            if (checkGridUp(i)) {
                youCanAdd = true;
                break;
            }
        }
        // shift everything up, add the numbers upwardly, shift numbers up again
        for (int i = 0; i < SIZE; i++) {
            while (checkPreZeroUp(i)) {
                shiftUp(i);
            }
            addUp(i);
            while (checkPreZeroUp(i)) {
                shiftUp(i);
            }
        }
        if (youCanAdd) {
            addNumber(2, 4); // will add a number randmly 
            Score.increase(); // will increase the number of moves
        }
    }
    
    /**
     * Description: helper function that will add the numbers upwardly
     * Input: an int representing a row of the 2D array
     * Output: return type is void
     */
    private void addUp(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will add numbers if the number below equals the current number 
        for (int i = SIZE - 1; i > 0; i--) {
            if (arr[row][i] == arr[row][i - 1]) {
                arr[row][i] += arr[row][i - 1];
                arr[row][i - 1] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that will shift the numbers in the grid up
     * Input: an int representing a row of the 2D array
     * Output: return type is void
     */
    private void shiftUp(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will shift all the numbers up if there is a 0 above a non-0 number
        for (int i = SIZE - 1; i > 0; i--) {
            if (arr[row][i] == 0 && arr[row][i - 1] != 0) {
                arr[row][i] = arr[row][i - 1];
                arr[row][i - 1] = 0;
            }
        }
    }
    
    /**
     * Description: helper function that'll check if the number above a non-0
     *              number is a 0
     * Input: an int representing a row of the 2D array
     * Output: boolean that will return true if there is a 0 above a non-0 
     *         number
     */
    private boolean checkPreZeroUp(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will check if there is a 0 above a non-0 number
        for (int i = 1; i < SIZE; i++) {
            if (arr[row][i] == 0 && arr[row][i - 1] != 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Description: helper function that will tell you if there is a possible 
     *              movement up or if a possible addition can be made upwards
     * Input: an int representing a row of the 2D array
     * Output: boolean that will return true if there is a possible shift up
     *         or if an addition can be made upwards
     */
    private boolean checkGridUp(int row) {
        if (row > 3 || row < 0) {
            throw new IllegalArgumentException("row must be in [0, 3]");
        }
        // will keep track of the non-0 numbers in the row of the 2D array
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (arr[row][i] != 0) count++;
        }
        if (count == 0) return false;
        if (count == 1) {
            // if the non-0 number is in the up-most position
            if (arr[row][3] != 0) return false;
        }
        if (count == 2) {
            // if the non-0 numbers are in the up-most position 
            if (arr[row][3] != 0 && arr[row][2] != 0) {
                // will determine if no possible additions can be made
                if (arr[row][3] != arr[row][2]) return false;
            }
        }
        if (count == 3) {
            // if the non-0 numbers are in the up-most position
            if (arr[row][0] == 0) {
                // will determine if no possible additions can be made 
                if (arr[row][3] != arr[row][2] && arr[row][2] != arr[row][1]) {
                    return false;
                }
            }
        }
        if (count == 4) {
            // will determine if no possible additions can be made 
            if (arr[row][3] != arr[row][2] && arr[row][2] != arr[row][1] && 
                arr[row][1] != arr[row][0]) {
                return false;
            }
        }
        return true;
    }
}