package generics;

public class Node<T> {
	private T info;
	private Node<T> left;
	private Node<T> right;
	
	public Node(T i) {
		this(i, null, null);
	}
	
	public Node(T i, Node<T> l, Node<T> r) {
		info = i;
		left = l;
		right = r;
	}
	
	public T getInfo() {
		return info;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setInfo(T i) {
		info = i;
	}
	
	public void setLeft(Node<T> l) {
		left = l;
	}
	
	public void setRight(Node<T> r) {
		right = r;
	}
	
	@Override
	public boolean equals(Object e) {
		if(this == e)
			return true;
		if(e == null)
			return false;
		if(!(e instanceof Node))
			return false;
		Node<?> n = (Node<?>) e;
		if(info.equals(n.info) && left==n.left && right==n.right)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "[" + info + ", " + left + ", " + right + "]";
	}
	
}
