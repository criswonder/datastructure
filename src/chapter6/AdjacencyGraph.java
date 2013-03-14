package chapter6;

public class AdjacencyGraph implements Graph {
	
	private final int MaxValue=1000;
	private int n; //图的顶点数
	private int e; //图的边数
	private int type; //图的类型，0-3 表示4中类型
	private int [][]a; //图的邻接矩阵，假定元素类型为int
	public int MaxValue(){return MaxValue;}
	public int[][] getArray(){return a;}
	
	public AdjacencyGraph(int n, int t){
		if(n<1||t<0||t>3){
			System.out.println("init error, exit ....");
			System.exit(1);
		}
		this.n=n;e=0;
		type=t;
		a=new int[n][n];
		//初始化数组a中元素的值
		for(int i=0;i<n;i++){
			for( int j=0;j<n;j++){
				if(i==j)a[i][j]=0;
				else if(type==0||type==2)
					a[i][j]=0;//对无权图的元素初始化为0
				else a[i][j]=MaxValue;
			}
		}
	}
	
	@Override
	public void createGraph(EdgeElement[] e) {
		for(int i=0;i<e.length;i++){
			a[e[i].fromvex][e[i].endvex] = e[i].weight;
			a[e[i].endvex][e[i].fromvex] = e[i].weight;
		}
	}

	@Override
	public int graphType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int vertices() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public int edges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean find(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putEdge(EdgeElement theEdge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int degree(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int outDegree(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depthFirstSearch(int v) {
		// TODO Auto-generated method stub
		boolean []visited = new boolean[n];
		for(int i=0;i<n;i++){visited[i]=false;}
		dfs(v, visited);
		System.out.println();
	}

	private void dfs(int v, boolean[] visited) {
		
	}
	@Override
	public void breadthFirstSearch(int v) {
		boolean []visited = new boolean[n];
		for(int i=0;i<n;i++){visited[i]=false;}
		bfs(v, visited);
		System.out.println();
	}
	private void bfs(int v, boolean[] visited) {
		
	}

}
