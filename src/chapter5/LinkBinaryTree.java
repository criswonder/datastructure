package chapter5;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkBinaryTree {

	protected BTreeNode root;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 
		 * 测试 linkedList看看是否表现的是queue的行为。
		 */
		/*
		 * Queue que=new LinkedList(); que.add(1); que.add(2); que.add(3);
		 * que.add(4); while(!que.isEmpty()){ System.out.println(que.remove());
		 * 
		 * }
		 */
		String str = "A(B(C),D(E(F,G),H(,I)))";
		LinkBinaryTree fwbn = new LinkBinaryTree();
		fwbn.createBTree(str);
		fwbn.printBTree(fwbn.getRoot());
/*		System.out.println("二叉树的深度为：" + fwbn.depthBTree(fwbn.getRoot()));
		fwbn.levelOrder(fwbn.getRoot());
		System.out.println("");
		System.out.println("先序遍历开始！！！");
		fwbn.preOrder(fwbn.getRoot());
		System.out.println("");
		System.out.println("中序遍历开始！！！");
		fwbn.inOrder(fwbn.getRoot());*/
	}

	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * 层次遍历法
	 * 
	 * @param rt
	 */
	private void levelOrder(BTreeNode rt) {
		Queue<BTreeNode> que = new LinkedList<BTreeNode>();
		BTreeNode p = null;
		que.add(rt);
		while (!que.isEmpty()) {
			p = (BTreeNode) que.remove();
			System.out.println(p.getValue() + " ");
			if (p.getLeft() != null)
				que.add(p.getLeft());
			if (p.getRight() != null)
				que.add(p.getRight());
		}
	}

	/**
	 * 先序遍历法
	 */
	private void preOrder(BTreeNode rt) {
		if (rt != null) {
			System.out.print(rt.getValue() + " ");
			preOrder(rt.getLeft());
			preOrder(rt.getRight());
		}
	}

	/**
	 * 中序遍历法
	 */
	private void inOrder(BTreeNode rt) {
		if (rt != null) {
			inOrder(rt.getLeft());
			System.out.print(rt.getValue() + " ");
			inOrder(rt.getRight());
		}
	}

	/**
	 * 后序遍历法
	 */
	private void postOrder(BTreeNode rt) {
		if (rt != null) {
			postOrder(rt.getLeft());
			postOrder(rt.getRight());
			System.out.print(rt.getValue() + " ");
		}
	}

	/**
	 * 求二叉树的深度
	 */
	public int depthBTree(BTreeNode rt) {
		if (rt == null)
			return 0;
		else {
			int dep1 = depthBTree(rt.getLeft());
			int dep2 = depthBTree(rt.getRight());
			if (dep1 > dep2) {
				return dep1 + 1;
			} else
				return dep2 + 1;
		}
	}

	/**
	 * 求二叉树的节点数
	 */
	public int countBTree(BTreeNode rt) {
		if (rt == null)
			return 0;
		return countBTree(rt.getLeft()) + countBTree(rt.getRight()) + 1;
	}

	/**
	 * 从二叉树查找值为X的节点
	 */
	public Object findBTree(BTreeNode rt, Object x) {
		if (rt == null)
			return null;
		else {
			Object y = null;
			if (rt.getLeft().getValue().equals(x))
				return rt.getLeft();
			if (rt.getRight().getValue().equals(x))
				return rt.getRight();
			y = findBTree(rt.getLeft(), x);
			y = findBTree(rt.getRight(), x);
			if (y == null)
				return y;
			return null;
		}
	}

	/**
	 * 输出二叉树，以广义表的方式
	 */
	public void printBTree(BTreeNode rt) {
		if (rt != null) {
			System.out.print(rt.getValue());
			if (rt.getLeft() != null || rt.getRight() != null) {
				System.out.print('(');
				printBTree(rt.getLeft());
				if (rt.getRight() != null)
					System.out.print(',');
				printBTree(rt.getRight());
				System.out.print(')');
			}
		}
	}

	public void clearBTree() {
		root = null;
	}

	/**
	 * 从字符串 创建二叉树
	 * 
	 * @param gt
	 */
	public void createBTree(String gt) {
		Stack<BTreeNode> sck = new Stack<BTreeNode>();
		root = null;
		BTreeNode p = null;
		int k = 1;
		char[] a = gt.toCharArray();
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " index " + i);

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
				p = new BTreeNode(new NodeValue(a[i] + ""), null, null);
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
