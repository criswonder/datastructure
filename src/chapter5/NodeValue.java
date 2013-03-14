package chapter5;

public class NodeValue implements Comparable{
		String val;
		
		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

		public NodeValue(String val){
			this.val = val;
		}

		@Override
		/**
		 * 假设只有在是数字节点的时候才用到。
		 */
		public int compareTo(Object arg0) {
			if(!(arg0 instanceof NodeValue))throw new UnsupportedOperationException();
			if(Integer.parseInt(val)>Integer.parseInt(((NodeValue)arg0).getVal()))return 1;
			else if(Integer.parseInt(val)<Integer.parseInt(((NodeValue)arg0).getVal()))
				return -1;
			return 0;
		}
		public String toString(){
			return val;
		}
		
	}