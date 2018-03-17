import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	
	private BSTBag.BSTNode<E> root;
	
	public BSTBag() { root = null; }
	
	/**
	 * Inner Node class
	 */
	private static class BSTNode<E extends Comparable<E>> {
		protected CountedElement<E> element;
		protected BSTNode<E> left, right;
		
		protected BSTNode(E elem) {
			this.element = new CountedElement<E>(elem,1);
			this.left = null;
			this.right = null;
		}
		
		public BSTNode<E> deleteTopMost() {
			if (this.left == null) {
				return this.right;
			} else if (this.right == null) {
				return this.left;
			} else {
				this.element = this.right.getLeftMost();
				this.right = this.right.deleteLeftMost();
				return this;
			}
		}
		
		private CountedElement<E> getLeftMost() {
			BSTNode<E> curr = this;
			while (curr.left!=null) {
				curr = curr.left;
			}
			return curr.element;
		}
		
		private BSTNode<E> deleteLeftMost() {
			if (this.left == null) {
				return this.right;
			} else {
				BSTNode<E> parent = this;
				BSTNode<E> curr = this.left;
				while (curr.left != null) {
					parent = curr;
					curr = curr.left;
				}
				return this;
			}
		}
	}
		
	/**
	 * Inner Iterator class
	 */
	private class IteratorObject implements Iterator<E> {
		private Stack<BSTBag.BSTNode<E>> track;
		
		private IteratorObject() {
			track = new LinkedStack<BSTBag.BSTNode<E>>();
			for (BSTBag.BSTNode<E> curr = root ; curr != null ; curr = curr.left) {
				track.push(curr);
			}
		}
		
		@Override
		public boolean hasNext() {
			return (!track.empty());
		}

		@Override
		public E next() {
			if (track.empty()) {
				throw new NoSuchElementException();
			} else {
				BSTBag.BSTNode<E> place = track.pop();
				for (BSTBag.BSTNode<E> curr = place.right ; curr != null ; curr = curr.left) {
					track.push(curr);
				}
				return place.element.getElement();
			}
		}
		
	}
	
	@Override
	public boolean isEmpty() {
		return (root == null ? true : false);
	}

	/*
	 * Use a recursive algorithm to measure the size of a single node, then
	 * find the size of child nodes
	 */
	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return 1 + subTreeSize(root.left) + subTreeSize(root.right);
		}
	}
	private int subTreeSize(BSTBag.BSTNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subTreeSize(node.left) + subTreeSize(node.right);
		}
	}

	@Override
	public boolean contains(E element) {
		BSTBag.BSTNode<E> curr = root;
		while(curr != null) {
			if (curr.element.toString().equals(element.toString())) {
				return true;
			} else if (element.compareTo(curr.element.getElement()) > 0) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Bag<E> that) {
		Iterator<E> it = that.iterator();
		while (it.hasNext()) {
			if (!this.contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public void add(E element) {
		BSTBag.BSTNode<E> curr = root;
		if (curr == null) {
			System.out.println("First node is: "+element.toString());
			root = new BSTBag.BSTNode<E>(element);
			return;
		}
		while (true) {
			BSTBag.BSTNode<E> parent = curr;
			if(element.compareTo(curr.element.getElement()) == 0) {
				System.out.println(element.toString()+" is already in the tree - incrementing");
				curr.element.setCount(curr.element.getCount()+1);
				return;
			} else if (element.compareTo(curr.element.getElement()) > 0) {
				System.out.println(element.toString()+" is right of "+curr.element.toString());
				 curr = curr.right;
				 if (curr == null) {
					 parent.right = new BSTBag.BSTNode<E>(element);
					 return;
				 }
			} else {
				System.out.println(element.toString()+" is left of "+curr.element.toString());
				curr = curr.left;
				if (curr == null) {
					parent.left = new BSTBag.BSTNode<E>(element);
					return;
				}
			}
		}
	}

	@Override
	public void remove(E element) {
		BSTBag.BSTNode<E> parent, curr;
		parent = null;
		curr = root;
		if (curr == null) { return; };
		// 1. find the node containing the value
		// 2. if node has no children, replace parent left/right node with null
		// 3. if has 1 child, replace parent left/right node with child node
		// 4a. if has 2 children, replace parent left/right node with leftmost child of right subtree
		// 4b. if leftmost child has n 
		while (true) {
			if (curr == null) {
				return;
			} else if (element.compareTo(curr.element.getElement()) == 0) {
				if (curr.element.getCount() > 1) {
					System.out.println("There are "+curr.element.getCount()+" instances of "+curr.element.getElement()+" - decrementing");
					curr.element.setCount(curr.element.getCount()-1);
					return;
				} else {
					BSTBag.BSTNode<E> replacement = curr.deleteTopMost();
					if (curr == root) {
						root = replacement;
					} else if (curr == parent.left) {
						parent.left = replacement;
					} else if (curr == parent.right) {
						parent.right = replacement;
					} else {
						// node has no parents :'(
						root = curr;
					}
					return;
				}
			} else {
				parent = curr;
				curr = ((element.compareTo(curr.element.getElement()) > 0) ? parent.right : parent.left);
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorObject();
	}
	
}