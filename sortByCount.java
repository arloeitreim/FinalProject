import java.util.*;

/**
 * Sorts lists from greatest to least greatest based on the count of each WordCount object
 */
public class sortByCount implements Comparator<WordCount> {

   /**
    * compares the count of two WordCount objects
    * @param a - the first WordCount object
    * @param b - the second WordCount object
    * @return an integer that represents if its larger or smaller than the object its being compared to
    */
   public int compare (WordCount a, WordCount b) { 
      return b.getCount() - a.getCount();
   }
}