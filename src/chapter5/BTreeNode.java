package chapter5;

	class BTreeNode implements Comparable {

/**
	 * 表示二叉树中的节点。
	 * 
	 * @author MS
	 * 
	 */
		private NodeValue value;
		private BTreeNode left;
		private BTreeNode right;

		public BTreeNode(NodeValue val, BTreeNode left, BTreeNode right) {
			this.value = val;
			this.left = left;
			this.right = right;
		}

		public NodeValue getValue() {
			return value;
		}

		public void setValue(NodeValue value) {
			this.value = value;
		}

		public BTreeNode getLeft() {
			return left;
		}

		public void setLeft(BTreeNode left) {
			this.left = left;
		}

		public BTreeNode getRight() {
			return right;
		}

		public void setRight(BTreeNode right) {
			this.right = right;
		}
		public String toString(){
			return value.toString();
		}

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return this.getValue().compareTo((NodeValue)arg0);
		}}
	