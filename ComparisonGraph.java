import java.util.*;
import java.awt.*;

/**
 * contains a method for drawing a graph that compares the frequency of a given keyword on different news sites
 */
public class ComparisonGraph extends Graph {

   private WebScraper FN = null;
   private WebScraper WP = null;
   private WebScraper NM = null;
   private WebScraper NPR = null;
   private WebScraper CBS = null;
   private String keyWord = null;
   
   /**
    * Constructor for ComparisonGraph
    * @param keyWord - the word that will be compared 
    * @param length - the length of the graph
    * @param height - the height of the graph
    * @param FN - the WebScraper object for Fox News
    * @param WP - the WebScraper object for the Washington Post
    * @param NM - the WebScraper object for News Max
    * @param NPR - the WebScraper object for NPR
    */  
   public ComparisonGraph (String keyWord, int length, int height, WebScraper FN, WebScraper WP, WebScraper NM, WebScraper NPR, WebScraper CBS) {
      super( length, height );
      this.keyWord = keyWord;
      this.FN = FN;
      this.WP = WP;
      this.NM = NM;
      this.NPR = NPR;
      this.CBS = CBS;
   }
   
   /**
    * draws the comparison graph of the given keyword
    */  
   public void draw() {
      super.draw();
      
      // Keyword
      g.drawString(keyWord, 80*length/2, 20);
      
      // x-axis labels and bars
      String[] names = {"Fox News", "Wash Post", "News Max", "NPR", "CBS"};
      ArrayList<WebScraper> scrapers = new ArrayList<WebScraper>();
      scrapers.add(FN);
      scrapers.add(WP);
      scrapers.add(NM);
      scrapers.add(NPR);
      
      for (int i = 0; i < 5; i++) {
         g.drawString(names[i], 8*height + 70*i, 75*height - 7*height + 20 );
         g.fillRect( height*8 + 70*i, 75*height - 7 * height - 29 * ( scrapers.get(i).findCount(keyWord) ),
                     50, 29 * ( scrapers.get(i).findCount(keyWord) ) );
      }
   }
}         