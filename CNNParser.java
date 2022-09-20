import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.*;
import java.io.*;

public class CNNParser {
   //Document site = Jsoup.connect( "https://www.cnn.com/" ).get();
   public static void main(String[] args) throws IOException {
   File input = new File("C:/School/AP Comp Sci/Final Project/Final-Project-/input.html");
   Document doc = Jsoup.parse(input, "UTF-8", "https://www.cnn.com/");
   }
}
   