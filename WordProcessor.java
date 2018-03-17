
//import classes for file input - scanner etc.
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
//import implementing set (eg. TreeSet)




public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		String outString = "";
		Iterator<E> it = inputSet.iterator();
		int count = 1;
		while (it.hasNext()) {
			E elem = it.next();
			outString += "("+elem.toString()+"), ";
			
			// Print a new line character after every 5th object 
			if (count%5 == 0) {
				outString += "\n";
			}
			count++;
		}
		return outString;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<String>(); 
		//create a set of type CountedElement<String> called countedWordSet 
		Set<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();
		
		// for each input file (assume arguments are the names of files)
		for (String filename : args) {
			// Read in each file
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);
			
			// for each word in file
			while (in.hasNext()) {
				String word = in.next();
				// if wordSet doesn't contain word:
				if (!wordSet.contains(word)) {
					// add w to wordSet
					wordSet.add(word);
				    // add new element to countedWordSet
					countedWordSet.add(new CountedElement<String>(word, 1));
				} else {
					// increment numeric part of element in countedWordSet containing word
					
					// iterate through all objects in countedWordSet to find the word
					Iterator<CountedElement<String>> it = countedWordSet.iterator(); 
					while(it.hasNext()) {
						CountedElement<String> element = it.next();
						
						// If the element is the same as word
						if (element.getElement().equals(word)) {
							// increment the count by 1
							element.setCount(element.getCount()+1);
						}
					}
				}
			}
			in.close();
		}

	System.out.println(displaySet(countedWordSet));

	}
}
