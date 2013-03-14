package chapter7;

public class HashNode {
	Object key;
	Object element;
	HashNode next;
	public HashNode(Object theKey, Object obj)
	{
		key = theKey;
		element = obj;
		next = null;
	}
}
