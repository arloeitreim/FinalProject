import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.*;
import java.io.*;
import java.lang.Object.*;

/**
 *stores given website URLs and contains methods for scraping and breaking down the given URL
 */
public class WebScraper {

   private String URL = null;
   private Document site = null;
   private ArrayList<WordCount> counter = new ArrayList<WordCount>();
   private ArrayList<String> headlines = new ArrayList<String>();
   
   /**
    * Constructor for WebScraper object
    * Connects to the given website URL using Jsoup
    * @throws IOException
    */ 
   public WebScraper(String URL) throws IOException {
      this.URL = URL;
      this.site = Jsoup.connect(URL).get();
   }   
      
   /**
    * gets headlines from Newsmax 
    * @return News Max's headlines in element form
    */ 
   public Elements scanFrontHead() {
      return site.getElementsByClass("nmNewsfrontHead");
   }
   
   // new headline scanner for issue #1
   public Elements scanHed() {
      return site.getElementsByClass("item__hed");
   }
   
   /**
    * gets headlines from The Washington Post
    * @return The Washington Post's headlines in element form
    */
   public Elements scanHeadlines() {
      return site.getElementsByClass("headline");
   }
   
   /**
    * gets headlines from Fox News and NPR
    * @return NPR or Fox News' headlines in element form
    */
   public Elements scanTitles() {
      return site.getElementsByClass("title");
   }
  
   /**
    * converts from Elements to ArrayList
    * @return headlines in the form of ArrayList<String> 
    */
   public void convertToAL(Elements elements) {                
      for (Element element : elements) { // goes through the Elements
         String text = element.text().replace("‘", "'")
                                     .replace("’", "'"); // remove special characters from The Washington Post
         if (headlines.size() != 0) { // if the length of headlines is not zero
            for (int i = 0; i < headlines.size(); i++) { // for the length of headlines
               if (headlines.get( i ).equals( text ) ) { // if the text doesnt match any previous headlines 
                  break;
               }
               if ( i + 1 == headlines.size() ) { // if the for loop is on its last loop
                  headlines.add(text);
               }
            }
         }
         else { // if the length of headlines is zero
            headlines.add( text );
         } 
      }
   }

   /**
    * breaks headlines down into WordCount objects
    * stores WordCount objects in an ArrayList<WordCount>
    */
   public void wordCounter() {
      for (int i = 0; i < headlines.size(); i++) {
      
         // removes all puntuation and sets to lowercase
         Scanner s = new Scanner( headlines.get( i ).replaceAll( "\\p{Punct}", "" ).toLowerCase() ); 
         
         while ( s.hasNext() ) { // While the headline has a next word 
            String word = s.next(); // Get next word
            if ( checker( word ) ) { // if the word is not already in counter
               counter.add( new WordCount ( word ) ); // add it to counter
            }
         }
      }
      Collections.sort(counter, new sortByCount() ); // Sort counter from greatest to least greatest
   }
   
   /**
    * checks if the word already exists inside of the ArrayList<WordCount>
    * if it does, adds to that words count
    * @return true if it is already in the ArrayList, false if it is not
    */
   private boolean checker(String word) {
      for (int j = 0; j < counter.size(); j++) { // for the length of counter
         if ( counter.get( j ).equals( word ) ) { // if the word matches any words already stored in counter 
            counter.get( j ).addCount();
            return false;
         }
      }
      return true;
   }
      
   /**
    * finds the count of the WordCount object that matches with the given word
    * @param word - the specified word thats count will be returned
    * @return the count of the given word
    */ 
   public int findCount(String word) {
      for (WordCount wc : counter) {
         if (wc.getWord().equals( word ) ) { // if the WordCount matches the given word
            return wc.getCount();
         }
      }
      return 0;
   }
   
   /**
    * returns the index that corresponds with the given word
    * @param word - the specified word thats index will be returned
    * @return the index of the given word
    */  
   public int findIndex(String word) {
      for (int i = 0; i < counter.size(); i++) { // for the length of counter
         WordCount wc = counter.get(i);
         if ( wc.getWord().equals(word) ) { // if the WordCount matches the given word
            return i; // return the index of the WordCount
         }
      } 
      return -1; // if the given word doesnt match any WordCounts, return -1
   }
   
   /**
    * resorts counter from greatest to least based on the count
    */   
   public void resort() {
      Collections.sort( counter, new sortByCount() );
   }
   
   /**
    * Removes junk words (e.g "the", "and")
    */  
   public void removeJunk() {
   
   // Junk Words
   String[] junkArray = {"the", "in", "to", "a", "for", "of", "on", "with", "and", "is", "new", "that", "as", "at", "could", "has",
                     "what", "an", "are", "how", "when", "i", "back", "your", "was", "thats", "its", "us", "this", "some", "after", 
                     "says", "day", "about", "from", "you", "may", "", "v", "will", "say", "can", "over", "out", "want", "if", "news",
                     "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
                     "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "downs", "gets", "get", "make", "need", "down", "make",
                     "be", "why", "more", "2022", "removed", "exclusive", "s", "t", "c", "not", "u", "it", "they", "their", "by", "into", "one",
                     "d", "but", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "no", "up", "fox", "all", "there",
                     "show", "so", "live", "we", "far", "have", "like", "long", "tv", "year", "faces", "yo", "—", "o", "here", "now", "during",
                     "way", "top", "our", "week", "shows", "off", "first", "june", "jan", "who", "than", "60", "cbs"};
                     
   // Removes words from counter if they are present in the above String[]
   List<String> junk = new ArrayList<String>( Arrays.asList(junkArray) ); // convert junkArray into an ArrayList
         for (int i = 0; i < counter.size() ; i++) { // for the length of counter
            WordCount s = counter.get( i );
            if (junk.contains( s.getWord() ) ) { // if any of junk's strings match the current WordCount
               counter.remove( s ); 
               i--;
         }
      }
   } 
   
   /**
    * gets the ArrayList<WordCount> counter
    * @return the ArrayList<WordCount> counter
    */ 
   public ArrayList<WordCount> getCounter() {
      return counter;
   }  
   
   /**
    * gets the ArrayList<String> headlines
    * @return the ArrayList<String> headlines
    */ 
   public ArrayList<String> getHeadlines() {
      return headlines;
   }
}
