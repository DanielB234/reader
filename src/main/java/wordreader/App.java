package wordreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        try {
            File testFile = new File("test.txt"); // get file 
            Scanner myReader = new Scanner(testFile);
            String fileText = "";
            while (myReader.hasNextLine()) { // read file contents line by line
                String fileLine = myReader.nextLine();
                fileText = fileText + " " + fileLine;
            }
            myReader.close();
            fileData(fileText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 
        
        
        
    }
    public static void fileData(String fileText) {
        try {
            Map<Integer,Integer> countOfLengths = new HashMap<>(); // hashmap to count occurunces of each word length
            String replaced = fileText.replaceAll("[.,?!]", " "); // remove innapropriate special characters 
            String[] words = replaced.split("\\s+"); // split string by whitespace
            double sum = 0;
            for (String word : words) { // iterate through each word to return the length
                Integer val = countOfLengths.get(word.length()); 
                if (word.length() > 0) {
                    if (val!=null) {  // increase count for word of length val
                        countOfLengths.put(word.length(),val+1);
                    } else { // otherwise add new map entry for word of length val
                        countOfLengths.put(word.length(),1); 
                    }
                    sum += word.length(); // caclulate total number of letters
                }   
            }
            Map.Entry<Integer, Integer> maxEntry = null; // find word with longest length
            System.out.println("Word count = " + (words.length-1));
            System.out.println("Average word length = " + Math.round(sum/(words.length-1)* 100.0) / 100.0);
            for (Map.Entry<Integer,Integer> entry:countOfLengths.entrySet()) {
                System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
            }
            for (Map.Entry<Integer, Integer> entry : countOfLengths.entrySet()) { // find most common word length
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
                    maxEntry = entry; 
                }
            }
            System.out.print("The most frequently occuring word length is " + maxEntry.getValue() + ", for word lengths of ");
            boolean multiple = false;
            for (Map.Entry<Integer, Integer> entry : countOfLengths.entrySet()) { // print word length(s) with the most common word length
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
                    if (!multiple) {
                        
                        multiple = true;
                    } else {
                        System.out.print(" & ");
                    }
                    System.out.print(entry.getKey());
                }
            }
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("This file is empty");
        }
    }
}
