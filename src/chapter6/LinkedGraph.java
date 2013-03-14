package chapter6;

public class LinkedGraph implements Graph{
	private int n; //图的顶点数
	private int e; //图的边数
	private int type; //图的类型
	private EdgeNode []a; //图的邻接表，元素类型为边节点类型
	public EdgeNode[] getArray()
	{
		return a;
	}
	
	/**
	 * 图的邻接表存储类
	 * @param n，顶点的个数
	 * @param t 图的类型，0表示是无向无权邻接图
	 */
	public LinkedGraph(int n, int t){
		if(n<1||t<0||t>3){
			System.out.println("初始化参数错误");
			System.exit(1);
		}
		this.n=n;
		type=t;
		a=new EdgeNode[n];
		for(int i=0;i<n;i++)a[i]=null;
		
	}

	@Override
	public void createGraph(EdgeElement[] d) {
		int i;
		for(i=0;i<d.length;i++){
			if(d[i]==null)break;
			int v1,v2;
			v1=d[i].fromvex;v2=d[i].endvex;
			
			if(v1<0||v2>n-1||v2<0||v2>n-1||v2==v1){
				System.out.println("边集合中的数据无效"+a[i]);
			}
			
			if(type==0){
				a[v1] = new EdgeNode(v2,a[v1]);
				a[v2] = new EdgeNode(v1, a[v2]);
			}
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
		return 0;
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
		
	}

	@Override
	public void breadthFirstSearch(int v) {
		// TODO Auto-generated method stub
		
	}

}
