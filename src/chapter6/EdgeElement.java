package chapter6;

public class EdgeElement {
	int fromvex;
	int endvex;
	int weight;
	public EdgeElement(int v1, int v2){
		this.fromvex=v1;
		endvex=v2;
		weight = 1;
	}
	
	public EdgeElement(int v1, int v2, int wgt){
		this.fromvex=v1;
		endvex=v2;
		weight = wgt;
	}
	
}
