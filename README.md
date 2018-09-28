# 2048
Coded the Iphone app 2048 via Java
/**********************************************************************
 *  readme template                                                   
 *  Project
 **********************************************************************/

Name: Hector Herrera 
PennKey: hectorh
Recitation: 216
Project choice: 2048
/**********************************************************************
 *  List all the files in project.zip and explain their purpose.
 **********************************************************************/
Score.java will create a score board and will track the number of valid 
moves made by the player. It also includes the drawings for the aesthetic 
of the game and the message outputted when someone wins or loses.
    
Grid.java will create the grid, deal with the movements of the numbers,
combine the numbers when needed, and add a random number to the grid at an 
open location. It will also determine when the game has been won or lost.
    
Game.java will combine the Score class and the Grid class to create the 
game and allow the user to play it.

PennDraw.java to run the game and draw the methods that use it.
    
0.png, 2.png, 4.png, 8.png, 16.png, 32.png, 64.png, 128.png, 256.png, 512.png
1024.png, 2048.png were pictues used to make the game look realistic
 
/**********************************************************************
 *  Please explain how we should use your program.                    
 **********************************************************************/
Just run Game.java You will use the four keys - w, s, a, d which represent 
the directions up, down, left,right and if you win or lose you can press 
the space bar to restart the game. There are instructions included when 
you lose so the user knows what to do.
