import java.awt.*;
import java.util.*;

/**
 * provides constructor for the length and height of the graph, and draws the foundations of a graph
 */
public abstract class Graph {

   int length = 0;
   int height = 0;
   DrawingPanel panel = null;
   Graphics g = null;
   
   /**
    * Constructor for use in subclasses 
    * @param length - the length of the graph
    * @param height - the height of the graph
    */
   public Graph( int length, int height ) {
      if (height < 10) {
         this.height = 5;
      }
      else {
         this.height = height/2;
      }
      this.length = length;
      this.panel = new DrawingPanel(80*this.length, 75*this.height);
      this.g = panel.getGraphics();
   }
   
   /**
    * draws the x and y axis, as well as the y axis labels.
    */  
   public void draw() {
      // vertical line
      g.drawLine(7*height, 6*height, 7*height, 75*height - 7*height);
      g.drawLine(7*height + 1, 6*height, 7*height + 1, 75*height - 7*height);
         
      // horizontal line
      
      g.drawLine(7*height, 75*height - 7*height, 100*length - 12*length, 75*height - 7*height);
      g.drawLine(7*height, 75*height - 7*height + 1, 100*length - 12*length,75*height - 7*height + 1); 
      
      // y-axis labels
      for (int i = 1; i <=height * 2 + 1; i++) {
         g.drawString( i+"" , 7*height - 15, 75*height - 7*height - 30*i + 25);
      }
   }
}   
   
   