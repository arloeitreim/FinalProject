import java.awt.*;
import java.util.*;

/**
 *contains a methods for drawing a bar graph that displays the most frequent words for the given news website 
 */
public class BarGraph extends Graph {

   ArrayList<WordCount> counter = null;
   private String name = null;
   private int headlines = 0;
   
   /**
    * Constructor for BarGraph object
    * @param length - the length of the graph
    * @param height - the height of the graph
    * @param name - the name of the news site being scraped
    * @param headlines - the number of headlines scraped from the site
    * @param counter - the ArrayList containing all the words scraped from the site
    */
   public BarGraph(int length, String name, int headlines, ArrayList<WordCount> counter ) {
      super(length, counter.get(0).getCount());
      this.name = name;
      this.headlines = headlines;
      this.counter = counter;
   }
   
   /**
    * draws the bargraph of the given site 
    */
   public void draw() {
      super.draw();
      
      // Name of News Outlet
      g.drawString(name, 80*length/2, 20);
      g.drawString( "# of Headlines: " + headlines, 80*length/2, 40 );
      
      // x-axis labels and Bars
      for (int i = 0; i < length; i++) {
         int j = i + 1;
         g.drawString( counter.get( i ).getWord(), 8*height + 70*i, 75*height - 7*height + 20 );
         g.fillRect( height*8 + 70*i, 75*height - 7 * height - 29 * ( counter.get( i ).getCount() ),
                     50, 29 * ( counter.get( i ).getCount() ) );
      }          
   }
}  