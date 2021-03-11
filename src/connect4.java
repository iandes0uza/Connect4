import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
//import packages
public class connect4 extends JFrame
{
 
 JButton start, close, info, closeStart, infoStart, returnB;
 //declares JButtons
 JPanel infoPanel, game, startScreen;
 //declares JPanels
 JLabel player1, player2, backDrop, playerTurnAlert, board2, infoMessage1, infoMessage2, infoMessage3, infoMessage4, infoTitle, player1Puck, player2Puck;
 //declares JLabels
 JLabel [][] puck = new JLabel[2][21];
 //declares JLabel puck array
 JButton [] dropcol = new JButton[7];
 //declares drop puck buttons in an array
 public int plays = 0;
 //sets the current amount of plays to 0
 Color [] colors = new Color[2];
 //defines two colours in an array
 ImageIcon boardImage, dropImage, backDropImage;
 //declares Image Icons
 int [][] mapChip = {
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},  
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},  
   {0, 0, 0, 0, 0, 0, 0},
 };
 //declares 2D array of puck positions
 int [] location = {85, 160, 235, 310, 385, 460};
 //declares an array of all vertical positions
 int [] colpos = {61, 138, 216, 293, 370, 448, 525};
 //declares an array of all horizontal positions
 public int playerTurn = 1;
 //declares a public int determining which player's turn it is, defaults to player1
 public int place = 1;
 //declares a public int on how many turns have passed, in order to keep track of pucks
 public int placeChip(int x, int player)
 //places a chip in a theoretical grid
  {
    int g = 6;
    //sets g to 6; being the highest it can go
    while  (g>=0)
    //loop runs cycling to find place in the col where it can drop
    {
      g--;
      //subtracts 1 from inital value starting the checking at 5, this is so when the loop ends it doesn't subtract 1
      if (mapChip[g][x] == 0)
      //code runs if position is empty 
      {
        mapChip[g][x] = player;
        //set position to the player's number
        break;
        //breaks the loop 
      }
    }
    return g;
    //returns the value with g, which is used to determine where the physical puck goes
  }
  public boolean checkChip(int player)
  //this checks if a player has won
  {
    int count = 0;
    //initial counter set to 0
    int count2 = 0;
    //2nd counter set to 0
    int count3 = 0;
    //3rd counter set to 0
    int count4 = 0;
    //4th counter set to 0
    for (int x = 0; x<6; x++)
    {
     for (int y = 0; y<7; y++)
     {
      if (mapChip[x][y] == player)
      // if it finds a puck which belongs to the player
      {
       count++;
       //add one if loop finds puck
       if (count == 4)
       {
    	//if 4 pucks are found in a row return true
        return true;
       }
      }
      else
      {
    //if pucks aren't 4 in a row reset counter to 0 to prevent error
       count = 0;
      }
     }
    }
    //^this checks left to right to find 4 pucks in a row
    //I would have reversed the coordinates to easily search up & down as well but due to the 6x7 ratio it's not possible, as there would be a OutOfIndex error
    for (int y = 0; y<7; y++)
    {
     for (int x = 0; x<6; x++)
     {
      if (mapChip[x][y] == player)
      {
       count++;
     //add one if loop finds puck
       if (count == 4)
       {
    	 //if 4 pucks are found in a row return true
        return true;
       }      
      }
      else
      {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
       count = 0;
      }
     }
    }
    for (int x = 0; x<6; x++)//these loops check diagonals
     /////6th
    {
     if (mapChip[5-x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count++;
     }
     if (mapChip[5-x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count = 0;
     }
     if (mapChip[5-x][1+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count2++;
     }
     if (mapChip[5-x][1+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count2 = 0;
     }
     if (mapChip[0+x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count3++;
     }
     if (mapChip[0+x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count3 = 0;
     }
     if (mapChip[0+x][1+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count4++;
     }
     if (mapChip[0+x][1+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
    	//if 4 pucks are found in a row return true
      return true;
     }
    }
    for (int x = 0; x<5; x++)//these loops check diagonals
     //5
    {
     if (mapChip[4-x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count++;
     }
     if (mapChip[4-x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count = 0;
     }
     if (mapChip[5-x][2+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count2++;
     }
     if (mapChip[5-x][2+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count2 = 0;
     }
     if (mapChip[0+x][2+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count3++;
     }
     if (mapChip[0+x][2+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count3 = 0;
     }
     if (mapChip[1+x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count4++;
     }
     if (mapChip[1+x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
    	//if 4 pucks are found in a row return true
      return true;
     }
    }
    for (int x = 0; x<4; x++)//these loops check diagonals
    {
     if (mapChip[3-x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count++;
     }
     if (mapChip[3-x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count = 0;
     }
     if (mapChip[5-x][3+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count2++;
     }
     if (mapChip[5-x][3+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count2 = 0;
     }
     if (mapChip[0+x][3+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count3++;
     }
     if (mapChip[0+x][3+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count3 = 0;
     }
     if (mapChip[2+x][0+x] == player)
     {
    	//if a puck is placed of the matching player add 1 to the corresponding counter
      count4++;
     }
     if (mapChip[2+x][0+x] != player)
     {
    	//if pucks aren't 4 in a row reset counter to 0 to prevent error
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
    	 ////if 4 pucks are found in a row return true
      return true;
     }
    }
    return false;
    //if nothing proves valid
  }
 public static void main(String [] args)
 {
  new connect4();
 }
 connect4() 
 {
  startScreen = new JPanel();
  //sets a Jpanel which the game will start from
  game = new JPanel();
  //this Jpanel is where the game takes place
  infoPanel = new JPanel();
  //this Jpanel is where the instructions are shown
  colors[0] = Color.RED;
  //sets position 0 of the colour array to the colour red
  colors[1] = Color.BLUE;
  //sets position 1 of the colour array to the colour blue
  player1 = new JLabel("Player 1");
  //created a label which deines which puck belongs to which player
  player2 = new JLabel("Player 2");
  //created a label which deines which puck belongs to which player
  player1Puck = new JLabel();
  //creates JLabel which shows players which puck they're going to be using
  player2Puck = new JLabel();
  //creates JLabel which shows players which puck they're going to be using
  returnB = new JButton("Return");
  //creates a return button, to return from info panel
  player1Puck.setIcon(new ImageIcon("puck1.png"));
  //adds the puck which they're going to be using
  player2Puck.setIcon(new ImageIcon("puck2.png"));
  //adds the puck which they're going to be using
  infoMessage1 = new JLabel("The aim for both players is to make a straight line of");
  //instructions split up
  infoMessage2 = new JLabel("four own pieces; the line can be vertical, horizontal or");
  //instructions split up
  infoMessage3 = new JLabel("diagonal. As the game starts, player #1 will be the");
  //instructions split up
  infoMessage4 = new JLabel("beginner; moves are made alternatively, one by turn. ");
  //instructions split up
  infoMessage1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
  //sets font & font size of 1st part of the message
  infoMessage2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
  //sets font & font size of 2nd part of the message
  infoMessage3.setFont(new Font("TimesRoman", Font.PLAIN, 20));
  //sets font & font size of 3rd part of the message
  infoMessage4.setFont(new Font("TimesRoman", Font.PLAIN, 20));
  //sets font & font size of 4th part of the message
  player1.setFont(new Font("TimesRoman", Font.PLAIN, 30));
  //sets font and font size of player1's label
  player2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
  //sets font and font size of player1's label
  player1.setForeground(Color.BLUE);
  //sets colour of player 1 to blue
  player2.setForeground(Color.RED);
  //sets colour of player 2 to red
  infoTitle = new JLabel("Connect Child: Rules");
  //sets the title of the info panel to the Rules
  infoTitle.setFont(new Font("TimesRoman", Font.PLAIN, 40));
  //sets font and font size of the title of the infoPanel
  returnB.setFont(new Font("TimesRoman", Font.PLAIN, 20));
  //sets font and font size of return button
  returnB.addActionListener(new ButtonListener());
  //adds action listener to the return button
  infoPanel.add(infoTitle);
  //add infoTitle to the infoPanel
  infoPanel.add(player1);
  //add player1 to the infoPanel
  infoPanel.add(player2);
  //add player2 to the infoPanel
  infoPanel.add(player1Puck);
  //add player1Puck to the infoPanel
  infoPanel.add(player2Puck);
  //add player2Puck to the infoPanel
  infoPanel.add(infoMessage1);
  //add infoMessage1 to the infoPanel
  infoPanel.add(infoMessage2);
  //add infoMessage2 to the infoPanel
  infoPanel.add(infoMessage3);
  //add infoMessage3 to the infoPanel
  infoPanel.add(infoMessage4);
  //add infoMessage4 to the infoPanel
  infoPanel.add(returnB);
  //add returnB to the infoPanel
  infoPanel.setLayout(null);
  //sets layout of the infoPane to null
  infoMessage1.setBounds(100,200, 500,25);
  //sets position and size of infoMessage1
  infoMessage2.setBounds(100,225, 500,25);
  //sets position and size of infoMessage2
  infoMessage3.setBounds(100,250, 500,25);
  //sets position and size of infoMessage3
  infoMessage4.setBounds(100,275, 500,25);
  //sets position and size of infoMessage4
  infoTitle.setBounds(130, 20, 400, 40);
  //sets position and size of infoTitle
  returnB.setBounds(200, 590, 200, 50);
  //sets position and size of returnB
  player1.setBounds(105, 450, 100, 40);
  //sets position and size of player1
  player2.setBounds(430, 450, 100, 40);
  //sets position and size of player2
  player1Puck.setBounds(130, 500, 75, 75);
  //sets position and size of player1Puck
  player2Puck.setBounds(440, 500, 75, 75);
  //sets position and size of player2Puck
  backDrop = new JLabel();
  //declares a JLabel, the backdrop of the game
  playerTurnAlert = new JLabel("It's Player "+playerTurn+"'s Turn");
  //declares JLabel, which indicates to the players who's turn it is
  playerTurnAlert.setFont(new Font("TimesRoman", Font.PLAIN, 40));
  //sets font and font size of the player turn indicator
  board2 = new JLabel();
  //creates the physical board
  dropImage = new ImageIcon("dropbutton.png");
  //creates image icon of the down pointing arrow
  backDropImage = new ImageIcon("backDrop.jpg");
  //sets the backdrop of the image to backDrop.jpg
  backDrop.setIcon(backDropImage);
  //applies backDropImage to the backDrop Label
  start = new JButton("Start Game");
  //creates a start button 
  closeStart = new JButton("Close");
  //creates a close button for startScreen
  infoStart = new JButton("Game Rules");
  //creates a infoStart button for the startScreen
  info = new JButton("Game Rules");
  //creates an info button for the gameboard
  info.addActionListener(new ButtonListener());
  //adds action listener to the info button
  start.setFont(new Font("TimesRoman", Font.PLAIN, 40));
  //sets font and font size of the start button
  infoStart.setFont(new Font("TimesRoman", Font.PLAIN, 25));
  //sets font and font size of the info button on the start screen
  closeStart.setFont(new Font("TimesRoman", Font.PLAIN, 25));
  //sets font and font size of the close button on the start screen
  start.addActionListener(new ButtonListener());
  //adds action listener to the start button
  close = new JButton("Close");
  close.addActionListener(new ButtonListener());
  //adds action listener to the close button
  closeStart.addActionListener(new ButtonListener());
  //adds action listener to the closeStart (close button on start screen) button
  infoStart.addActionListener(new ButtonListener());
  //adds action listener to the infoStart (info button on start screen) button
  close.setFont(new Font("TimesRoman", Font.PLAIN, 25));
  //sets font and font size of the close button
  boardImage = new ImageIcon("board_90.png");
  //creates image of physical board, board_90.png
  board2.setIcon(boardImage);
  //sets the physical board's image to the board_90.png
  startScreen.setLayout(null);
  //sets layout of the startScreen to null
  startScreen.add(backDrop);
  //add backDrop to the startScreen
  backDrop.setBounds(0,0,650,700);
  //sets position and size of the backdrop
  startScreen.add(start);
  //add start to the startScreen
  startScreen.add(infoStart);
  //add infoStart to the startScreen
  start.setBounds(220,500,230,40);
  //sets position and size of the start button
  startScreen.add(closeStart);
  //add closeStart to the startScreen
  closeStart.setBounds(350,600,160,35);
  //sets position and size of the close button the start screen
  infoStart.setBounds(150,600,160,35);
  //sets position and size of the info button on the start screen
  setContentPane(startScreen);
  //sets the content pane to startScreen
  game.setLayout(null);
  //sets layout of the game panel to null
  for (int x = 0; x<=6; x++)
  {
    dropcol[x] = new JButton();
    //declares Jbutton in array
    dropcol[x].setIcon(dropImage);
    //adds down pointing arrow to button array
    dropcol[x].addActionListener(new ButtonListener());
    //adds actionlistener to button
    game.add(dropcol[x]);
    //adds button array to the game panel
  }
  //this loops creats, paints and adds multiple buttons to the board
  for (int x = 0; x<2; x++)
  {
    for (int y = 0; y<21; y++)
    {
      puck[x][y] = new JLabel();
      //creates Jlabel of the puck
      ImageIcon p = new ImageIcon("puck"+(x+1)+".png");
      //creates image icon of puck, cycles between 0 and 1. 0 being a different player's puck
      puck[x][y].setIcon(p);
      //sets the image of the puck to the JLabel array
      game.add(puck[x][y]);
      //adds puck to the game panel
      puck[x][y].setVisible(false);
      //sets visibility of the puck to false, so initially you do not see the puck
    }
  }
  //this loops fills the 2D JLabel array with puck images
  game.add(board2);
  //adds board2 to the game panel
  game.add(info);
  //adds info to the game panel
  game.add(close);
  //adds close to the game panel
  game.add(playerTurnAlert);
  //adds playerTurnAlert to the game panel
  dropcol[0].setBounds(68,5,45,65);
  //sets position and size of the 1st drop button
  dropcol[1].setBounds(148,5,45,65);
  //sets position and size of the 2nd drop button
  dropcol[2].setBounds(225,5,45,65);
  //sets position and size of the 3rd drop button
  dropcol[3].setBounds(302,5,45,65);
  //sets position and size of the 4th drop button
  dropcol[4].setBounds(378,5,45,65);
  //sets position and size of the 5th drop button
  dropcol[5].setBounds(455,5,45,65);
  //sets position and size of the 6th drop button
  dropcol[6].setBounds(533,5,45,65);
  //sets position and size of the 7th drop button
  board2.setBounds(49,75,549,503);
  //sets position and size of the physical board
  info.setBounds(15, 600, 140, 40);
  //sets position and size of the info button
  playerTurnAlert.setBounds(165,560,400,100);
  //sets position and size of the player turn alert
  close.setBounds(510,600,100,40);
  //sets position and size of the start button
  for (int x = 0; x<=6; x++)
   {
     dropcol[x].setBackground(colors[1]);
     //sets the background colour of all drop buttons to red
   }
  setTitle("Connect Child");
  //sets title of program to "Connect Child"
  setSize(650,700);
  //sets size of program
  setResizable(false);
  //sets the ability to resize the window
  setVisible(true);
  //sets the visibility of the program to true
 }
 public void changePlayers(int y)
 //method which runs everytime the player needs to be changed
 {
   for (int x = 0; x<=6; x++)
   {
	   dropcol[x].setBackground(colors[y-1]);
	   //this takes the current player's turn number, subtracts 1 to locate its colour and apply it to the buttons
   }
   if (playerTurn == 1)
   {
     playerTurn = 2;
     //if the current player's turn is 
   }
   else
   {
     playerTurn = 1;
     plays++;
     //if its player 2's turn set player turn to 1, since 2 turns have been made add 1 to plays
   }
   playerTurnAlert.setText("It's Player "+playerTurn+"'s Turn");
   //this label changes the text to indicate to the player who's turn it is
   
 }

 public void move(int g, int c)
 {
     puck[playerTurn-1][plays].setVisible(true);
     //this displays the puck to the player
	 puck[playerTurn-1][plays].setBounds(colpos[c], location[g], 75, 75);
	 //this sets the position of the puck
 }
 public void endGame()
 {
	 for (int x = 0; x<2; x++)
	 {
		 for (int y = 0; y<21; y++)
		 {
			 puck[x][y].setVisible(false);
			 //this loop removes all physical pucks from the board
		 }
	 }
	 for (int x = 0; x<6; x++)
	 {
		for (int y = 0; y<7; y++)
		{
			 mapChip[x][y] = 0;
			 //this loops removes all positions of pucks saved
		}
	 }
	 for (int x = 0; x<7; x++)
	 {
		 dropcol[x].setBackground(colors[0]);
		 //this sets the drop button's colour to red, player1's colour
		 dropcol[x].setVisible(true);
		 //this adds back all buttons in case they were removed during gameplay
	 }
	 plays = 0;
	 //this sets the current amount of plays to 0, allowing to start placing pucks from position 0
 }
 public void playerWins()
 {
	 JOptionPane.showMessageDialog(null,  "Player "+playerTurn+" Wins!", "CONNECT CHILD", JOptionPane.PLAIN_MESSAGE, (new ImageIcon("puck"+playerTurn+".png")));
	 //this displays to the user who won the game as well as their puck
	 int o = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
	 //this asks the user if they would like to play again
	 if (o == 1)
		 //if they select no
	 {
		 while (true)
			 //create an infinite loop until given an answer
		 {
			 int j = JOptionPane.showConfirmDialog(null, "Would you like to return to the main menu?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
			 //ask player if they would like to retun to the startScreen
			 if(j == 0)
				 //if they select yes
			 {
				 endGame();
				 //run endGame
                 setContentPane(startScreen);
                 //set content pane to start screen
                 setVisible(true);
                 //set visibility to true
                 break;
                 //break loop
             }
		     else
		    	 //if they select no
		     {
		    	 int i = JOptionPane.showConfirmDialog(null, "Would you like to end the game?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
		    	 //ask player if they would like to close the game
		     	 if (i == 0)
		     		 //if yes
		     	 {
		     		 System.exit(0);
		     		 //close game
		     	 }
			 }
		 }
	 }
	 endGame();
	 //runs endGame
 }
 public class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      int g;
      //creates variable g to temporarily store values
      if (start == e.getSource())
      //if start button is pressed
      {
        setContentPane(game);
        //set the content pane to game panel
        setVisible(true);
        //set visibility to true
        place = 0;
        //set place of board to 0
        playerTurn = 1;
        //sets the default player number to 1
      }
      if (close == e.getSource())
   	  //if close button is pressed
      {
        int o = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
        //ask player if they want to end the game
        if (0 == o)
       	//if yes
        {
          int j = JOptionPane.showConfirmDialog(null, "Would you like to return to the main menu?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
          //ask player if they would like to return to the menu
          {
            if (j == 1)
           	//if no close game
            {
              System.exit(0);
              //close game
            }
            else
           	//if yes go to main menu
            {
              endGame();
              //run endGame
              setContentPane(startScreen);
              //set content pane to startScreen
              setVisible(true);
              //set visibility to true
              place++;
              //set place to any number but 0
            }
          }
        }
      }
      if (closeStart == e.getSource())
      //if closeStart button is pressed
      {
    	  int o = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "CONNECT CHILD", JOptionPane.YES_NO_OPTION);
    	  //ask the player if they would like to close the program
    	  if (o == 0)
    	  //if yes
    	  {
    		  System.exit(0);
    		  //close game
    	  }
      }
      if (infoStart == e.getSource() || info == e.getSource())
      //if infoStart or info button is pressed
      {

          setContentPane(infoPanel);
          //set content pane to infoPanel
          setVisible(true);
          //set visibility to true
      }
      if (returnB == e.getSource())
      //if return button is pressed
      {
    	  if (place == 0)
    	  //if content pane was previously on the game
    	  {
    		  setContentPane(game);
    		  //set content pane to game panel
              setVisible(true);
              //set visibility to true
              place = 0;
              //set placement to 0
    	  }
    	  else
    	  //if content pane was previously on the start screen
    	  {
    		  setContentPane(startScreen);
    		  //set content pane to the start screen
              setVisible(true);
              //set visibility to true
              place++;
              //set place to any number but 0
    	  }
    	  
      }
      
      
      
      
      
      if (dropcol[0] == e.getSource())
      //if 1st drop button is pressed
      {
        g = placeChip(0, playerTurn);
        //save the returned value of "placeChip" into g
        move(g, 0);
        //move puck(y value, x value)
        if (g == 0)
        //if reached last place-able puck remove button to prevent issues
        {
          dropcol[0].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
       	//check if player wins
        {
          playerWins();
      	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      if (dropcol[1] == e.getSource())
      //if 2nd drop button is pressed
      {
    	g = placeChip(1, playerTurn);
    	//save the returned value of "placeChip" into g
    	move(g, 1);
    	//move puck(y value, x value)
        if (g == 0)
        //if reached last place-able puck remove button to prevent issues
        {
          dropcol[1].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
       	//check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      if (dropcol[2] == e.getSource())
      //if 3rd drop button is pressed
      {
      	g = placeChip(2, playerTurn);
        //save the returned value of "placeChip" into g
      	move(g, 2);
        //move puck(y value, x value)
        if (g == 0)
       	//if reached last place-able puck remove button to prevent issues
        {
          dropcol[2].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
       	//check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      
      if (dropcol[3] == e.getSource())
      //if 4th drop button is pressed
      {
      	g = placeChip(3, playerTurn);
        //save the returned value of "placeChip" into g
      	move(g, 3);
        //move puck(y value, x value)
      	if (g == 0)
      	//if reached last place-able puck remove button to prevent issues
        {
          dropcol[3].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
        //check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      
      if (dropcol[4] == e.getSource())
      //if 5th drop button is pressed
      {
      	g = placeChip(4, playerTurn);
        //save the returned value of "placeChip" into g
      	move(g, 4);
        //move puck(y value, x value)
        if (g == 0)
        //if reached last place-able puck remove button to prevent issues
        {
          dropcol[4].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
        //check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      
      if (dropcol[5] == e.getSource())
      //if 6th drop button is pressed
      {
      	g = placeChip(5, playerTurn);
        //save the returned value of "placeChip" into g
      	move(g, 5);
        //move puck(y value, x value)
        if (g == 0)
        //if reached last place-able puck remove button to prevent issues
        {
          dropcol[5].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
        //check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
      
      
      
      
      
      if (dropcol[6] == e.getSource())
      //if 7th drop button is pressed
      {
      	g = placeChip(6, playerTurn);
        //save the returned value of "placeChip" into g
      	move(g, 6);
        //move puck (y value, x value)
        if (g == 0)
        //if reached last place-able puck remove button to prevent issues
        {
          dropcol[6].setVisible(false);
          //removes button
        }
        if (checkChip(playerTurn) == true)
        //check if player wins
        {
        	playerWins();
        	//run playerWins
        }
        changePlayers(playerTurn);
        //run changePlayers
      }
    }
 }
}