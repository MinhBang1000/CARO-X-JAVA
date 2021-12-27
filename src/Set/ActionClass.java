package Set;


import javax.swing.*;
import java.awt.*;
public class ActionClass{
   public static boolean checkWinText(JButton b[][], int i, int j, int line){
       int countX=1, countY=1, countXY=1, countYX=1;
       int x = i, y = j, n=0;
       int row = MainClassTwo.r, column = MainClassTwo.c;
       String cross="";
       if (MainClassTwo.count!=0)
           cross = MainClassTwo.crossO;
       else
           cross = MainClassTwo.crossX;
       
       //doc
       x=i-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countX++;
           }else{
               break;
           }
           x--;
           n++;
       }
       n=0;
       x=i+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countX++;
           }else{
               break;
           }
           x++;
           n++;
       }
       if (countX>=5)
           return true;
       
       //ngang
       n=0;
       x=i;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countY++;
           }else{
               break;
           }
           y--;
           n++;
       }
       n=0;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countY++;
           }else{
               break;
           }
           y++;
           n++;
       }
       if (countY>=5)
           return true;
       
       //xuyen phai
       n=0;
       x=i+1;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countYX++;
           }else{
               break;
           }
           x++;
           y++;
           n++;
       }
       n=0;
       x=i-1;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countYX++;
           }else{
               break;
           }
           x--;
           y--;
           n++;
       }
       if (countYX>=5)
           return true;
       
       //xuyen trai
       n=0;
       x=i-1;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countXY++;
           }else{
               break;
           }
           x--;
           y++;
           n++;
       }
       n=0;
       x=i+1;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getText())){
               countXY++;
           }else{
               break;
           }
           x++;
           y--;
           n++;
       }
       if (countXY>=5)
           return true;
       return false;
   }
   public static boolean checkWinIcon(JButton b[][], int i, int j, int line){
       int countX=1, countY=1, countXY=1, countYX=1;
       int x = i, y = j, n=0;
       int row = MainClassTwo.r, column = MainClassTwo.c;
       ImageIcon cross= new ImageIcon();
       if (MainClassTwo.count!=0)
           cross = MainClassTwo.icoO;
       else
           cross = MainClassTwo.icoX;
       
       //doc
       x=i-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countX++;
           }else{
               break;
           }
           x--;
           n++;
       }
       n=0;
       x=i+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countX++;
           }else{
               break;
           }
           x++;
           n++;
       }
       if (countX==5)
           return true;
       
       //ngang
       n=0;
       x=i;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countY++;
           }else{
               break;
           }
           y--;
           n++;
       }
       n=0;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countY++;
           }else{
               break;
           }
           y++;
           n++;
       }
       if (countY==5)
           return true;
       
       //xuyen phai
       n=0;
       x=i+1;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countYX++;
           }else{
               break;
           }
           x++;
           y++;
           n++;
       }
       n=0;
       x=i-1;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countYX++;
           }else{
               break;
           }
           x--;
           y--;
           n++;
       }
       if (countYX==5)
           return true;
       
       //xuyen trai
       n=0;
       x=i-1;
       y=j+1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countXY++;
           }else{
               break;
           }
           x--;
           y++;
           n++;
       }
       n=0;
       x=i+1;
       y=j-1;
       while (x<row && x>=0 && y>=0 && y<column && n<4){
           if (cross.equals(b[x][y].getIcon())){
               countXY++;
           }else{
               break;
           }
           x++;
           y--;
           n++;
       }
       if (countXY==5)
           return true;
       return false;
   }
}
