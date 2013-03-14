package chapter6;

public class Chap6_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n,t;
		AdjacencyGraph g =new AdjacencyGraph(7, 1);
		int [][]a ={{0,1,8},{0,3,5},
				{1,2,12},{1,3,3},{1,4,10},
				{2,4,6},{2,5,2},
				{3,5,7},{3,6,15},
				{4,5,9}};
		EdgeElement[] dd= new EdgeElement[a.length];
		for(int i=0;i<a.length;i++){
			dd[i]=new EdgeElement(a[i][0],a[i][1],a[i][2]);
		}
		g.createGraph(dd);
		EdgeElement[] ed;
		ed = new EdgeElement[g.vertices()-1];
		
		prim(g,ed);
		System.out.println("从初始点0开始得到的深度优先搜索遍历序列：");
		g.depthFirstSearch(0);
		System.out.println("从初始点0开始得到的广度优先搜索遍历序列：");
		g.breadthFirstSearch(0);
		g.output();
		System.out.println("得到图g的最小生成树的边集为：");
		for(int i=0;i<ed.length;i++)
			System.out.println("("+ed[i].fromvex+","+ed[i].endvex+")"+ed[i].weight+" ");
		System.out.println();
	}

	public static void prim(AdjacencyGraph gr, EdgeElement[] ed) {
		int n = gr.vertices(); //获得顶点数
		int[][] a = gr.getArray();
		for(int i=0;i<n-1;i++)
			ed[i] = new EdgeElement(0, i+1, a[0][i+1]);
		
		for(int k=1; k<n; k++){
			int min = gr.MaxValue();
			int j, m=k-1;
			for(j=k-1;j<n-1;j++)
				if(ed[j].weight<min){
					min=ed[j].weight;
					m=j;
				}
			//把最短边对调到下标为k-1的元素位置
			EdgeElement temp =ed[k-1];
			ed[k-1] = ed[m];
			ed[m]=temp;
			
			j= ed[k-1].endvex;
			
			for(int i=k;i<n-1;i++){
				int t= ed[i].endvex;
				int w = a[j][t];
				
				if(w<ed[i].weight){
					ed[i].weight =w ;
					ed[i].fromvex = j;
				}
			}
		}
	}
}
