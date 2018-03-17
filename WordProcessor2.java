import java.io.FileNotFoundException;
import java.util.Iterator;

public class WordProcessor2 {
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
//		BSTBag<CountedElement<String>> b = new BSTBag<CountedElement<String>>();
//		System.out.println("Tree is empty? "+b.isEmpty());
//		b.add(new CountedElement<String>("Marcus"));
//		b.add(new CountedElement<String>("Is"));
//		b.add(new CountedElement<String>("A"));
//		b.add(new CountedElement<String>("Cool"));
//		b.add(new CountedElement<String>("Dude"));
//		b.add(new CountedElement<String>("Right"));
//		System.out.println("Tree is empty? "+b.isEmpty());
//		
//		CountedElement<String> test1 = new CountedElement<String>("WORD");
//		CountedElement<String> test2 = new CountedElement<String>("WORD");
//		
//		System.out.println("Testing if two instances of the same word are equal: "+(test1.compareTo(test2)));
//		System.out.println("Size test should be 6: "+(b.size()));
//		
//		System.out.println("Removing \"A\"");
//		b.remove(new CountedElement<String>("A"));
//		System.out.println("\"A\" no longer in BST? "+!b.contains(new CountedElement<String>("A")));
//		System.out.println("Adding \"A\" back in - should go to left of \"Cool\"");
//		b.add(new CountedElement<String>("A"));
//		
//		System.out.println("Removing \"Cool\"");
//		b.remove(new CountedElement<String>("Cool"));
//		System.out.println("\"Cool\" no longer in BST? "+!b.contains(new CountedElement<String>("Cool")));
//		System.out.println("Adding \"Cool\" back in - should go to right of \"A\"");
//		b.add(new CountedElement<String>("Cool"));
//		
//		Iterator<CountedElement<String>> it = b.iterator();
//		
//		while (it.hasNext()) {
//			System.out.println(it.next().toString());
//		}
//		
//		BSTBag<CountedElement<String>> a = new BSTBag<CountedElement<String>>();
//		System.out.println("Tree is empty? "+a.isEmpty());
//		a.add(new CountedElement<String>("Marcus"));
//		a.add(new CountedElement<String>("Is"));
//		a.add(new CountedElement<String>("A"));
//		a.add(new CountedElement<String>("Cool"));
//		a.add(new CountedElement<String>("Dude"));
//		a.add(new CountedElement<String>("Right"));
//		
//		System.out.print("Testing if two bags are equal (with different ordering):"+a.equals(b));
		BSTBag<String> a = new BSTBag<String>();
		System.out.println("Tree is empty? "+a.isEmpty());
		a.add("Marcus");
		a.add("Is");
		a.add("A");
		a.add("Cool");
		a.add("Dude");
		a.add("Right");
		a.add("Marcus");
		a.remove("Marcus");
		Iterator<String> it = a.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		
	}
}
