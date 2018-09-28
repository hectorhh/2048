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
 *  Have you entered all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/
I did not receive any help outside of TA office hours. I did not collaborate 
with anyone, and I did not use any resources beyond the standard course 
materials.

/**********************************************************************
 *  How do you execute your program? Which class do you run, are there
 *  any command line arguments?
 **********************************************************************/
You run Game.java and there are no command line arguments. You will use
the four keys - w, s, a, d which represent the directions up, down, left,
right and if you win or lose you can press the space bar to restart the 
game. There are instructions included when you lose so the user knows 
what to do.
    
/**********************************************************************
 *  Did you add any additional features to your project beyond the
 *  specification that you added? If so, describe them here.
 **********************************************************************/
No I did not

/**********************************************************************
 *  Explain how you went about approaching the problem using
 *  object oriented programming.
 **********************************************************************/
I thought of the possible objects that I would need. I realized that
I could use a 2D int array and every position in the array would include
a number and that would be the grid. In it I determined what the game 
would need, such as the movements and if the user has won or lost and 
a function that will add a number randomly.
    
I also realized I would need a score board class so i created it, along
with other drawins and i realized that the score must be able to reset
or increase so i created methods for those.
    
Those were all the objects I would need because there already exists 
integers so I did not have to create an integer object. So I used 
my classes and methods to make the game.

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

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
It was really fun! Also i included PennDraw.java in the zipped file 
so the syntax errors outputted are probably from PennDraw.java
