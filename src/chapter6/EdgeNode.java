package chapter6;

public class EdgeNode {
	int adjvex;
	int weight;
	EdgeNode next;
	public EdgeNode(int adj, EdgeNode nt){
		adjvex = adj; weight =1; next = nt; 
	}
	public EdgeNode(int adj, int wgt, EdgeNode nt){
		adjvex = adj; weight =1; next = nt; 
	}
	
}
