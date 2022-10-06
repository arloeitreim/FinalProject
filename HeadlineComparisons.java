import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.*;
import java.io.*;

/**
 * creates WebScraper objects for multiple news sites; calls methods in the WebScraper class; displays data in graph/chart form
 */
public class HeadlineComparisons {

   public static void main(String[] args) throws IOException {
      WebScraper WP = new WebScraper("https://www.washingtonpost.com/");
      WebScraper FN = new WebScraper("https://www.foxnews.com/");
      WebScraper NM = new WebScraper("https://www.newsmax.com/");
      WebScraper NPR = new WebScraper("https://www.npr.org/"); 
      WebScraper CBS = new WebScraper("https://www.cbsnews.com/");  
      
      results( FN, FN.scanTitles() );
      barGraph( FN, "Fox News", FN.getHeadlines().size() );
      //print(10, "Fox News", FN);
      
      results( WP, WP.scanHeadlines() );
      barGraph( WP, "Washington Post", WP.getHeadlines().size() );
      //print(10, "Washington Post", WP);
      
      results( NM, NM.scanFrontHead() );
      barGraph( NM, "News Max", NM.getHeadlines().size() );
      //print(10, "News Max", NM);
      
      results( NPR, NPR.scanTitles() );
      barGraph( NPR, "NPR", NPR.getHeadlines().size() );
      //print(10, "NPR", NPR);
      
      results( CBS, CBS.scanHed() );
      barGraph( CBS, "CBS", CBS.getHeadlines().size() );
      
      comparisonGraph(WP, NPR, FN, NM, CBS, "queen");
      
   }
   
   /**
    * prints the top words into the console
    * @param topWords - determines how much of the data will be printed
    * @param name - the name of the news site
    * @param webScraper - the WebScraper object for the news site
    */
   public static void print(int topWords, String name, WebScraper webScraper) {
   
      System.out.println(name + ":");
      System.out.println();
      System.out.println( "# of Headlines: " + webScraper.getHeadlines().size() );
      System.out.println(); 
      
      // prints out the top words and the number of times they appear
      for (int i = 0; i < topWords; i++) { // for the length of topWords
         System.out.println("\"" + webScraper.getCounter().get(i).getWord() + "\" appears " 
                                 + webScraper.getCounter().get(i).getCount() + " times"  );
      }
      System.out.println();
   }
      
   /**
    * compiles every headlines, breaks them down into words and their frequencies
    * orders them greatest to least greatest
    * @param webScraper - the WebScraper object for the news site being shown
    * @param headlines - elements containing headlines scraped from the news site 
    */   
   public static void results( WebScraper webScraper, Elements headlines ) {
      webScraper.convertToAL(headlines); // Converts headlines to an ArrayList
      webScraper.wordCounter(); // Converts headers to WordCount objects
      webScraper.removeJunk(); // removes junk words in counter
   }
   

   /**
    * graphs the most frequent words
    * @param webScraper - the WebScraper object for the news site being shown
    * @param name - the name of the news site
    * @param headlines - the total number of headlines
    */
   public static void barGraph(WebScraper webScraper, String name, int headlines) {
      Graph topWords = new BarGraph( 10, name, headlines, webScraper.getCounter() );
      topWords.draw();
   }
   
   /**
    * draws a graph the compares how frequently a keyword appears in each news site
    * @param WP - the WebScraper object for the Washington Post
    * @param NPR - the WebScraper object for NPR
    * @param FN - the WebScraper object for Fox News
    * @param NM - the WebScraper object for News Max
    * @param keyWord - the word that will be compared among every news site
    */
   public static void comparisonGraph(WebScraper WP, WebScraper NPR, WebScraper FN, WebScraper NM, WebScraper CBS, String keyWord) {
      int height = WP.findCount(keyWord);
      if ( FN.findCount(keyWord) > height ) {
         height = FN.findCount(keyWord);
      }
      if( NM.findCount(keyWord) > height ) {
         height = NM.findCount(keyWord);
      }
      if ( NPR.findCount(keyWord) > height ) {
         height = NPR.findCount(keyWord);
      }
      Graph comparison = new ComparisonGraph( keyWord, 5, height, FN, WP, NM, NPR, CBS );
      comparison.draw();
   } 
}