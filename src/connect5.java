import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import javax.swing.border.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
public class connect5 extends JFrame
{
 
 JButton start, close;
 JPanel main, game, startScreen;
 JTextField inputName1, inputName2;
 JLabel title, backDrop, playerTurnAlert, board1, board2;
 JLabel [][] puck = new JLabel[2][21];
 JButton [] dropcol = new JButton[7];
 public int plays = 0;
 Color [] colors = new Color[2];
 ImageIcon puckImage1, puckImage2, boardImage, dropImage, backDropImage;
 int [][] mapChip = {
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},  
   {0, 0, 0, 0, 0, 0, 0},
   {0, 0, 0, 0, 0, 0, 0},  
   {0, 0, 0, 0, 0, 0, 0},
 };
 int [] location = {600, 500, 400, 300, 200, 100};
 public int playerTurn = 1;
     
  //for 7 x 6 board
 public int placeChip(int x, int player)
  {
    int g = 6;
    while  (g>=0)
    {
      g--;
      if (mapChip[g][x] == 0)
      {
        mapChip[g][x] = player;
        break;
      }
    }
    return g;
  }
  public boolean checkChip(int player)
  {
    int count = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    for (int x = 0; x<6; x++)
    {
     for (int y = 0; y<7; y++)
     {
      if (mapChip[x][y] == player)
      {
       count++;
       if (count == 4)
       {
        return true;
       }
      }
      else
      {
       count = 0;
      }
     }
    }
    //I would have reversed the coordinates to easily search up & down as well but due to the 6x7 ratio it's not possible, as there would be a OutOfIndex error
    for (int y = 0; y<7; y++)
    {
     for (int x = 0; x<6; x++)
     {
      if (mapChip[x][y] == player)
      {
       count++;
       if (count == 4)
       {
        return true;
       }      
      }
      else
      {
       count = 0;
      }
     }
    }
    for (int x = 0; x<6; x++)
     /////6th
    {
     if (mapChip[5-x][0+x] == player)
     {
      count++;
     }
     if (mapChip[5-x][0+x] != player)
     {
      count = 0;
     }
     if (mapChip[5-x][1+x] == player)
     {
      count2++;
     }
     if (mapChip[5-x][1+x] != player)
     {
      count2 = 0;
     }
     if (mapChip[0+x][0+x] == player)
     {
      count3++;
     }
     if (mapChip[0+x][0+x] != player)
     {
      count3 = 0;
     }
     if (mapChip[0+x][1+x] == player)
     {
      count4++;
     }
     if (mapChip[0+x][1+x] != player)
     {
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
      return true;
     }
    }
    for (int x = 0; x<5; x++)
     //5
    {
     if (mapChip[4-x][0+x] == player)
     {
      count++;
     }
     if (mapChip[4-x][0+x] != player)
     {
      count = 0;
     }
     if (mapChip[5-x][2+x] == player)
     {
      count2++;
     }
     if (mapChip[5-x][2+x] != player)
     {
      count2 = 0;
     }
     if (mapChip[0+x][2+x] == player)
     {
      count3++;
     }
     if (mapChip[0+x][2+x] != player)
     {
      count3 = 0;
     }
     if (mapChip[1+x][0+x] == player)
     {
      count4++;
     }
     if (mapChip[1+x][0+x] != player)
     {
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
      return true;
     }
    }
    for (int x = 0; x<4; x++)
    {
     if (mapChip[3-x][0+x] == player)
     {
      count++;
     }
     if (mapChip[3-x][0+x] != player)
     {
      count = 0;
     }
     if (mapChip[5-x][3+x] == player)
     {
      count2++;
     }
     if (mapChip[5-x][3+x] != player)
     {
      count2 = 0;
     }
     if (mapChip[0+x][3+x] == player)
     {
      count3++;
     }
     if (mapChip[0+x][3+x] != player)
     {
      count3 = 0;
     }
     if (mapChip[2+x][0+x] == player)
     {
      count4++;
     }
     if (mapChip[2+x][0+x] != player)
     {
      count4 = 0;
     }
     if (count == 4 || count2 == 4 || count3 == 4 || count4 == 4)
     {
      return true;
     }
    }
    return false;
    //if nothing proves valid
  }
  public void clearChips()
  {
   for (int x = 0; x<6; x++)
   {
    for (int y = 0; y<7; y++)
    {
     mapChip[x][y] = 0;
    }
   }
  }
  public void printChips()
  {
   for (int x = 0; x<6; x++)
   {
    for (int y = 0; y<7; y++)
    {
     System.out.print(mapChip[x][y]);
    }
    System.out.println("");
   }
  }
 public static void main(String [] args)
 {
  new connect5();
 }
 connect5() 
 {
  startScreen = new JPanel();
  game = new JPanel();
  colors[0] = Color.RED;
  colors[1] = Color.BLUE;
  inputName1 = new JTextField();
  inputName2 = new JTextField();
  backDrop = new JLabel();
  playerTurnAlert = new JLabel();
  board1 = new JLabel();
  board2 = new JLabel();
  dropImage = new ImageIcon("dropbutton.png");
  backDropImage = new ImageIcon("backDrop.jpg");
  backDrop.setIcon(backDropImage);
  start = new JButton("start");
  start.setFont(new Font("TimesRoman", Font.PLAIN, 40));
  start.addActionListener(new ButtonListener());
  close = new JButton("close");
  close.addActionListener(new ButtonListener());
  boardImage = new ImageIcon("board_90.png");
  board1.setIcon(boardImage);
  board2.setIcon(boardImage);
  startScreen.setLayout(null);
  startScreen.add(backDrop);
  backDrop.setBounds(0,0,650,700);
  startScreen.add(start);
  start.setBounds(200,600,80,20);
  setContentPane(startScreen);
  game.setLayout(null);
  for (int x = 0; x<=6; x++)
  {
    dropcol[x] = new JButton();
    dropcol[x].setIcon(dropImage);
    dropcol[x].addActionListener(new ButtonListener());
    game.add(dropcol[x]);
  }
  game.add(board1);
  for (int x = 0; x<2; x++)
  {
    for (int y = 0; y<21; y++)
    {
      puck[x][y] = new JLabel();
      ImageIcon p = new ImageIcon("puck"+(x+1)+".png");
      puck[x][y].setIcon(p);
      game.add(puck[x][y]);
      puck[x][y].setVisible(false);
    }
  }
  game.add(board2);
  game.add(close);
  dropcol[0].setBounds(68,5,45,65);
  dropcol[1].setBounds(148,5,45,65);
  dropcol[2].setBounds(225,5,45,65);
  dropcol[3].setBounds(302,5,45,65);
  dropcol[4].setBounds(378,5,45,65);
  dropcol[5].setBounds(455,5,45,65);
  dropcol[6].setBounds(533,5,45,65);
  board1.setBounds(49,75,549,503);
  board2.setBounds(49,75,549,503);
  close.setBounds(250,600,100,20);
  for (int x = 0; x<=6; x++)
   {
     dropcol[x].setBackground(colors[0]);
   }
  setTitle("Connect 4");
  setSize(650,700);
  setResizable(false);
  setVisible(true);
 }
 public void changePlayers(int y)
 {
   for (int x = 0; x<=6; x++)
   {
     dropcol[x].setBackground(colors[y-1]);
   }
 }
 public void sleep()
 {
  long start = System.currentTimeMillis();
  while (System.currentTimeMillis() - start < 900)
  {
  }
 }
 public class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      int g;
      if (start == e.getSource())
      {
        setContentPane(game);
        setVisible(true);
      }
      if (close == e.getSource())
      {
        int o = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "CONNECT 4", JOptionPane.YES_NO_OPTION);
        if (0 == o)
        {
          int j = JOptionPane.showConfirmDialog(null, "Would you like to return to the main menu?", "CONNECT 4", JOptionPane.YES_NO_OPTION);
          {
            if (j == 1)
            {
              System.exit(0);
            }
            else
            {
              setContentPane(startScreen);
              setVisible(true);
            }
          }
        }
      }
      if (dropcol[0] == e.getSource())
      {
        g = placeChip(0, playerTurn);
        puck[playerTurn-1][plays].setVisible(true);
        for (int pos = 0; pos<=location[g]; pos++)
        {
          puck[playerTurn-1][plays].setBounds(68, pos, 100, 100);
          sleep();
        }
        if (g == 0)
        {
          dropcol[0].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
        plays++;
      }
      if (dropcol[1] == e.getSource())
      {
        if (placeChip(1, playerTurn) == 0)
        {
          dropcol[1].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
      if (dropcol[2] == e.getSource())
      {
        if (placeChip(2, playerTurn) == 0)
        {
          dropcol[2].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
      if (dropcol[3] == e.getSource())
      {
        if (placeChip(3, playerTurn) == 0)
        {
          dropcol[3].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
      if (dropcol[4] == e.getSource())
      {
        if (placeChip(4, playerTurn) == 0)
        {
          dropcol[4].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
      if (dropcol[5] == e.getSource())
      {
        if (placeChip(5, playerTurn) == 0)
        {
          dropcol[5].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
      if (dropcol[6] == e.getSource())
      {
        if (placeChip(6, playerTurn) == 0)
        {
          dropcol[6].setVisible(false);
        }
        if (checkChip(playerTurn) == true)
        {
          System.out.println("Player "+playerTurn+" WINS!");
        }
        if (playerTurn == 1)
        {
          playerTurn = 2;
          changePlayers(playerTurn);
        }
        else
        {
          playerTurn = 1;
          changePlayers(playerTurn);
        }
        printChips();
        System.out.println("\n\n\n");
      }
    }
 }
}