package chapter5;
import java.util.LinkedList;
import java.util.Stack;

public class LinkBinarySearchTree extends LinkBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LinkBinarySearchTree lbst = new LinkBinarySearchTree();
		/*String str = "30(15(12,23(18,26)),52(,74(63)))";
		lbst.createBTree(str);
		lbst.printBTree(lbst.getRoot());
		lbst.find(new BTreeNode(new NodeValue("26"), null, null));*/
		
		//二叉搜索树的插入操作
		Object[] nodes=new Object[]{new NodeValue("38"),new NodeValue("26"),
				new NodeValue("62"),new NodeValue("94"),new NodeValue("35"),
				new NodeValue("50"),new NodeValue("28"),new NodeValue("55")
		};
		for(int i=0;i<nodes.length;i++){
			lbst.insert(nodes[i]);
		}
		lbst.printBTree(lbst.getRoot());
	}

	public LinkBinarySearchTree() {
		super();
	}

	@Override
	public void createBTree(String gt) {
		Stack<BTreeNode> sck = new Stack<BTreeNode>();
		LinkedList<Character> nums = new LinkedList<Character>();
		root = null;
		BTreeNode p = null;
		int k = 1;

		int w = 0;// 表示第几位的数
		char[] a = gt.toCharArray();
		for (int i = 0; i < a.length; i++) {
			// System.out.println(a[i] + " index " + i);

			switch (a[i]) {
			case ' ':
				break;
			case '(':
				sck.push(p);
				k = 1;
				break;
			case ')':
				if (sck.isEmpty()) {
					System.out.println("二叉树广义表字符串出错，退出运行！");
					System.exit(1);
				}
				sck.pop();
				break;
			case ',':
				k = 2;
				break;
			default:
				nums.add(a[i]);
				if (nums.size() == 2) {
					String str = "";
					while (!nums.isEmpty()) {
						str = str + nums.remove();
					}
					p = new BTreeNode(new NodeValue(str), null, null);
					if (root == null)
						root = p;
					else {
						if (k == 1)
							sck.peek().setLeft(p);
						else
							sck.peek().setRight(p);
					}
				}
			}

		}

	}

	public Object find(final Object obj) {
		if (root == null)
			return null;
		BTreeNode rt = root;
		while (rt != null) {
			if (((Comparable) obj).compareTo(rt.getValue()) == 0)
				return rt.getValue();
			else if (((Comparable) obj).compareTo(rt.getValue()) < 0)
				rt = rt.getLeft();
			else
				rt = rt.getRight();
		}
		return null;
	}

	public boolean insert(final Object obj1){
		NodeValue obj = (NodeValue)obj1;
		BTreeNode rt=root, pt=null;
		while(rt!=null){
			pt=rt;
			if(((Comparable)obj).compareTo((Comparable)rt.getValue())==0){
				return false; //有元素重复
			}
			else if(((Comparable)obj).compareTo((Comparable)rt.getValue())<0){
				rt = rt.getLeft();
			}
			else
				rt = rt.getRight();
		}
		BTreeNode s=new BTreeNode((NodeValue)obj, null, null);
		if(pt==null)root=s;
		else if(((Comparable)obj).compareTo(pt.getValue())<0){
			pt.setLeft(s);
		}
		else 
			pt.setRight(s);
		return true;
		}
		
	public boolean delete(final Object obj1){
		NodeValue obj = (NodeValue)obj1;
		if(root == null)return false;
		BTreeNode rt=root,pt=null;
		while(rt!=null){
			if (((Comparable) obj).compareTo(rt.getValue()) == 0)
				break;
			else if (((Comparable) obj).compareTo(rt.getValue()) < 0){
				pt=rt;rt=rt.getLeft();
			}
			else
			{
				pt=rt;rt=rt.getRight();
			}
		}
		if(rt==null)return false; // 表示没有找到待删除的节点。
		if(rt.getLeft()==null&&rt.getRight()==null){
			if(rt==root)root =null;
			else if(pt.getLeft()==rt)pt.setLeft(null);
			else pt.setRight(null);
		}
		else if(rt.getLeft()==null||rt.getRight()==null){
			if(rt==root)
			{
				if(rt.getLeft()==null)root=rt.getRight();
				else
					root=rt.getLeft();
			}
			else if(pt.getLeft()==rt&&rt.getLeft()==null)pt.setLeft(rt.getRight());
			else if(pt.getLeft()==rt&&rt.getRight()==null)pt.setLeft(rt.getLeft());
			else if(pt.getRight()==rt&&rt.getRight()==null)pt.setRight(rt.getLeft());
			else if(pt.getRight()==rt&&rt.getLeft()==null)pt.setRight(rt.getRight());
			
		}
		else if(rt.getLeft()!=null&&rt.getRight()!=null){
			BTreeNode s1=rt, s2=rt.getLeft();
			while(s2.getRight()!=null){
				s1=s2;s2=s2.getRight();
			}
			rt.setValue(s2.getValue());
			if(s1==rt)
				rt.setLeft(s2.getLeft());
			else
				s1.setRight(s2.getLeft());
		}
		return true;
	}
}
