import java.util.Iterator;
import java.util.Stack;
import java.util.LinkedList;

class Node{
	private int value;
	
	public Node(int value){
		this.value=value;
	}
	private Node lnode;
	private Node rnode;
	public void setlnode(Node lnode){
		this.lnode=lnode;
	}
	public Node getlnode(){
		return this.lnode;
	}
	public void setrnode(Node rnode){
		this.rnode=rnode;
	}
	public Node getrnode(){
		return this.rnode;
	}
	public String toString( ){
		return "Node:"+this.value;
	}
}
public class Traverse{
	public static void main(String args[]){
		Node root=new Node(56);
		Node rootLeft=new Node(43);
		Node rootRight=new Node(86);
		//n多少 表示的是节点的值
		Node n25=new Node(25);
		Node n12=new Node(12); 
		Node n27=new Node(27);
		Node n65=new Node(65);
		Node n88=new Node(88);
		Node n95=new Node(95);
		Node n101=new Node(101);
		
		root.setlnode(rootLeft);
		root.setrnode(rootRight);
		
		rootLeft.setlnode(n25);
		n25.setlnode(n12);
		n25.setrnode(n27);
		
		rootRight.setlnode(n65);
		rootRight.setrnode(n88);
		
		n88.setlnode(n95);
		n88.setrnode(n101);
		//把这颗树放到stack
		Stack<Node> st=new Stack<Node>();
		putIntoStack(st, root);
		
		while(!st.isEmpty()){
			System.out.println(st.pop());
		}
		//把这棵树放到队列
		System.out.println("after put into queue");
		LinkedList<Node> lst=new LinkedList<Node>();
		putIntoQueue(lst, root);
		
		Iterator< Node> irt=lst.iterator();
		while(irt.hasNext()){
			System.out.println(irt.next());
		}
	}
	
	public static void putIntoStack(Stack<Node> st,Node n){
		
		if(n.getlnode()!=null){
			putIntoStack(st, n.getlnode());
		}
		if(n.getrnode()!=null){
			putIntoStack(st, n.getrnode());
		}
		st.add(n);
	}
	
	public static void putIntoQueue(LinkedList<Node> st,Node n){
		
		if(n.getlnode()!=null){
			putIntoQueue(st, n.getlnode());
		}
		if(n.getrnode()!=null){
			putIntoQueue(st, n.getrnode());
		}
		st.add(n);
	}
}