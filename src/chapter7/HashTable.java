package chapter7;

public interface HashTable {

	boolean insert(Object theKey, Object obj);
	
	Object search(Object theKey);
	
	boolean delete(Object theKey);
	
	int size();
	
	int capacity();
	
	boolean isEmpty();
	
	void clear();
	
	void output();
}
