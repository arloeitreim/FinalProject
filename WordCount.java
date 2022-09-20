   /**
    * stores a word and "count" for the number of times the word appears
    */
   public class WordCount {
   
   private int count = 0;
   private String word = null;
   
   /**
    * constructor for WordCount object
    * @param word - word to represent
    * @param count - amount of times the word appears
    */
   public WordCount(String word, int count) {
      this.word = word;
      this.count = count;
   }
   
   /**
    * constructor for WordCount object with the default count of 1
    * @param word - word to represent
    */
   public WordCount(String word) {
      this.word = word;
      this.count = 1;
   }
   
   /**
    * adds to the count instance field by an increment of 1
    */
   public void addCount() {
      count++;
   }
   
   /**
    * adds the specified amount, count, to the count instance field
    * @param num - increment that count is being increased by
    */
   public void addCount(int num) {
      this.count += count;
   }
   
   public int getCount() {
      return count;
   }

   public String getWord() {
      return word;
   }
   
   /**
    * changes the word instance field
    * @param word - the new word
    */
   public void changeWord(String word) {
      this.word = word;
   }
   
   /**
    * @param other - the String to compare 
    * @return wether the WordCount object represents the same word as the given String
    */
   public boolean equals(String other) {
      return this.word.equals(other);
   }
   
   /**
    * @param other - the WordCount object to compare
    * @return - wether the two WordCount objects represent the same word
    */
   public boolean equals(WordCount other) {
      String otherWord = other.getWord();
      return this.word.equals(otherWord);
   }
}