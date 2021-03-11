public class ChipPlacement
{
  public int [][] mapChip = new int[6][7];
  //for 7 x 6 board
  public int placeChip(int x, int player)
  {
    int g = 0;
    for (int a = 5; a<=0; a--)
    {
      if (mapChip[a][x] == 0)
      {
        mapChip[a][x] = player;
        g = a;
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
     System.out.println(mapChip[x][y]);
    }
    System.out.println("/n");
   }
  }
}