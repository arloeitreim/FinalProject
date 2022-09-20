// Arlo Eitreim
// 2021 - 12 - 10
// CSE 142 AP CS A
// Café Wall  
//
// Replicating the Cafe Wall Illusion using java.awt
//
// main method 
// call each method in order
// 
// black box method (cord x, cord y, space)
// set color to black
// fill rectangle (cord x, cord y, space, space)
// 
// white box method (cord x, cord y, space)
// set color to white
// fill rectangle (cord x+20, cord y, space, space)
//
// x method (cord x, cord y, space)
// set color to blue
// draw line (cord x, cord y, cord x + spc, cord y + spc)
// draw line (cord x + space, cord y, cord x, cord y + space)
// 
// line method (cord x, cord y, space between, amount)
// for (# of parts)
// bb method (cord x, cord y, space)
// wb method (cord x, cord y, space)
// x method (cord x, cord y, space)
//
// draw method
// for (# of lines)
// line method
// 
// end

import java.awt.*;



// Replicating the Cafe Wall Illusion using java.awt
// Awt Class, Parameters, Class Constant, For Loops, Methods,
public class CafeWall{

public static int mortar = 2;

   // Method for ordering method calls     
   public static void main(String []args) {
      DrawingPanel panel = new DrawingPanel(650,400); // creates a 650x400 pixel canvas
      Graphics g = panel.getGraphics();
      panel.setBackground(Color.GRAY); // sets background to grey
      line(g,0,0,20,4); // top left
      line(g,50,70,30,5); // mid left
      draw(g,10,150,25,8,4,0); // bottom left
      draw(g,250,200,25,6,3,10); // mid
      draw(g,400,20,35,4,2,35); // top right
      draw(g,425,180,20,10,5,10); // bottom right
   }
   
   // draws a black box and a blue X at the designated position
   // Parameters:
   // Graphics g - Allows the use of Graphics
   // int x - The starting x-axis value
   // int y - The starting y-axis value
   // int spc - The length/width of the black box
   public static void blackboxX(Graphics g, int x, int y, int spc) {
      g.setColor(Color.BLACK); // sets color to black
      g.fillRect(x,y,spc,spc);
      g.setColor(Color.BLUE); // sets color to blue
      g.drawLine(x,y,x+spc,y+spc);
      g.drawLine(x+spc, y, x, y+spc);
      }
      
   // draws a white box at the designated position 
   // Parameters:
   // Graphics g - Allows the use of Graphics
   // int x - The starting x-axis value
   // int y - The starting y-axis value
   // int spc - The length/width of the white box
   public static void whitebox(Graphics g,int x, int y, int spc) {
      g.setColor(Color.WHITE); // sets color to white
      g.fillRect(x+spc,y,spc,spc);
   }
   
   // puts the whitebox, and the blackboxX method together
   // specifies the amount of times to repeat to form the line
   // Parameters:
   // Graphics g - Allows the use of Graphics
   // int x - The starting x-axis value
   // int y - The starting y-axis value
   // int spc - The length/width of the black box
   // int parts - the amount of times the for loop should run
   public static void line(Graphics g, int x, int y, int spc, int parts) {
       for (int a = 0; a < parts; a++) {
           blackboxX(g, x+a*(spc*2),y,spc);
           whitebox(g, x+a*(spc*2),y,spc);
       }
   }
   
   // draws every figure, depending on the parameters given
   // Parameters:
   // Graphics g - Allows the use of Graphics
   // int x - The starting x-axis value
   // int y - The starting y-axis value
   // int spc - The length/width of the black box
   // int amnt - determines how many times the for loop repeates
   // int parts - used in the parameter for the line method call
   // int off - determines the offset of each row
   public static void draw(Graphics g, int x, int y, int spc, int amnt, int parts, int off) {
      int pn = -1; // pn stands for positve/negative
      for (int a = 0; a < amnt; a++) {
         pn = pn*-1; // rotates between positive and negative
         line(g,x,y+a*(spc+mortar),spc,parts);
         x = x+off*pn;
       }
   }
 }