import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class WordProcessor2 {
	public static void main(String[] args) throws FileNotFoundException {
        // Create a new binary search tree
        BSTBag<String> bag = new BSTBag<String>();

		for (String filename : args) {
			// Read in each file
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);

			// for each word in file
			while (in.hasNext()) {
                // Add word to BST
				bag.add(in.next());
			}
			
            // close input scanner
			in.close();
			
			bag.remove("CALL");
			Iterator<String> e = bag.iterator();
			while (e.hasNext()) {
				System.out.println(e.next());
			}
		}
	}
}
