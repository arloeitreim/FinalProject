import java.awt.*;
import java.util.*;

public class Grapher {

   private int length = 0;
   private int height = 0;
   private DrawingPanel panel = null;
   private Graphics g = null;
   private ArrayList<WordCount> counter = null;
   private String name = null;
   private int headlines = 0;
   
   public Grapher(int height, int length, ArrayList<WordCount> counter, String name, int headlines ) {
   if (height < 10) {
      this.height = 5;
   }
   else {
      this.height = height/2;
   }
      this.counter = counter;
      this.length = length;
      this.panel = new DrawingPanel(80*this.length, 75*this.height);
      this.g = panel.getGraphics();
      this.name = name;
      this.headlines = headlines;
   }
   
   //public void comparisonGraph() {
      
   public void barGraph() {
      
      // Name of News Outlet
      g.drawString(name, 80*length/2, 20);
      
      // # of Headlines
      g.drawString( "# of Headlines: " + headlines, 80*length/2, 40 );
      
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
      
      // x-axis labels and Bars
         for (int i = 0; i < length; i++) {
            int j = i + 1;
            g.drawString( counter.get( i ).getWord(), 8*height + 70*i, 75*height - 7*height + 20 );
            g.fillRect( height*8 + 70*i, 75*height - 7 * height - 29 * ( counter.get( i ).getCount() ),
                       50, 29 * ( counter.get( i ).getCount() ) );
      }     
   }
}  