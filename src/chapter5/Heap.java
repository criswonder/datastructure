package chapter5;

public interface Heap {
	void insert(final Object obj);
	Object delete();
	int size();
	void output();
	boolean isEmpty();
	void clear();
}
