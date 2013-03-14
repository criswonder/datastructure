package chapter6;

public class LinkedGraphTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 建立一个6个顶点的，无向无权图，共有九条边
		 */
		LinkedGraph lg= new LinkedGraph(6, 0);
		EdgeElement e01 = new EdgeElement(0, 1);
		EdgeElement e02 = new EdgeElement(0, 2);
		EdgeElement e03 = new EdgeElement(0, 3);
		EdgeElement e04 = new EdgeElement(0, 4);
		EdgeElement e14 = new EdgeElement(1, 4);
		EdgeElement e24 = new EdgeElement(2, 4);
		EdgeElement e25 = new EdgeElement(2, 5);
		EdgeElement e35 = new EdgeElement(3, 5);
		EdgeElement e45 = new EdgeElement(4, 5);
		
		EdgeElement ee[]=new EdgeElement[9];
		ee[0] = e01;
		ee[1] = e02;
		ee[2] = e03;
		ee[3] = e04;
		ee[4] = e14;
		ee[5] = e24;
		ee[6] = e25;
		ee[7] = e35;
		ee[8] = e45;
		
		lg.createGraph(ee);
		
	}

}
