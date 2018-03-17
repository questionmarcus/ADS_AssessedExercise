import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class WordProcessor2 {
	public static void main(String[] args) throws FileNotFoundException {
		BSTBag<String> b = new BSTBag<String>();
		System.out.println("Tree is empty? "+b.isEmpty());
		b.add("Marcus");
		b.add("Is");
		b.add("A");
		b.add("Cool");
		b.add("Dude");
		b.add("Right");
		System.out.println("Tree is empty? "+b.isEmpty());
		System.out.println("Size test should be 6: "+(b.size()));
		
		System.out.println("Removing \"A\"");
		b.remove("A");
		System.out.println("\"A\" no longer in BST? "+!b.contains("A"));
		System.out.println("Adding \"A\" back in - should go to left of \"Cool\"");
		b.add("A");
		
		System.out.println("Removing \"Cool\"");
		b.remove("Cool");
		System.out.println("\"Cool\" no longer in BST? "+!b.contains("Cool"));
		System.out.println("Adding \"Cool\" back in - should go to right of \"A\"");
		b.add("Cool");
		
		Iterator<String> it = b.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
		BSTBag<String> a = new BSTBag<String>();
		System.out.println("Tree is empty? "+a.isEmpty());
		a.add("Marcus");
		a.add("Is");
		a.add("A");
		a.add("Cool");
		a.add("Dude");
		a.add("Right");
		
		System.out.print("Testing if two bags are equal (with different ordering):"+a.equals(b));
		BSTBag<String> c = new BSTBag<String>();
		System.out.println("Tree is empty? "+c.isEmpty());
		c.add("Marcus");
		c.add("Is");
		c.add("A");
		c.add("Cool");
		c.add("Dude");
		c.add("Right");
		c.add("Marcus");
		c.remove("Marcus");
		Iterator<String> iter = c.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		BSTBag<String> bag = new BSTBag<String>();
		
		for (String filename : args) {
			// Read in each file
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);

			// for each word in file
			while (in.hasNext()) {
				bag.add(in.next());
			}
			
			in.close();
		}
	}
}
