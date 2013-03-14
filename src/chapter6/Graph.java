package chapter6;

public interface Graph {

	void createGraph(EdgeElement[] a);
	int graphType();
	int vertices();
	int edges();
	boolean find(int i, int j);
	void putEdge(EdgeElement theEdge);
	void removeEdge(int i, int j);
	int degree(int i);
	int outDegree(int i);
	void output();
	void depthFirstSearch(int v	);
	void breadthFirstSearch( int v);
}
