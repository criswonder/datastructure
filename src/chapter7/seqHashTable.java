package chapter7;

@SuppressWarnings(value={"unused"})
public class seqHashTable implements HashTable{
	
	private int m;
	private Object [] key;  //定义保存元素关键字的数组
	private Object[] ht;    //定义保存散列表的数组
	private int n;          //散列表中已有的元素个数
	private Object tag;
	
	private int h(Object theKey){
		return (Integer)theKey%m;
	}
	
	public seqHashTable(int m,Object tag){
		if(m<13){
			System.out.println("散列表的长度太小");
			System.exit(1);
		}
		this.m = m;
		n=0;
		key = new Object[m];
		ht = new Object[m];
		this.tag = tag;
	}
	

	@Override
	public boolean insert(Object theKey, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 根据theKey 在 key[] 里面查找，如果有相同的则表示有值
	 * 然后返回ht[] 里面相同索引的，也就是下标的值
	 */
	@Override
	public Object search(Object theKey) {
		int d = h(theKey);
		int temp = d;
		while(key[d]!=null){
			if(key[d].equals(theKey))
				return ht[d];
			else d = (d+1)%m; //如果不相等 则继续向下查找
			if(d==temp)return null;
		}
		return null;
	}

	@Override
	public boolean delete(Object theKey) {
		int d = h(theKey);
		int temp = d;			//为了保存theKey的初始位置
		while(key[d]!=null){
			
			if(key[d].equals(theKey)){
				key[d] = tag;
				n--;
				ht[d] = null;
				return true;
			}else{
				d = (d+1)%m;
			}
			// 扫描一周后删除失败 返回false
			if(d==temp) return false;
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return m;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n==0;
	}

	@Override
	public void clear() {
		for(int i=0;i<m;i++){
			key[i] = null;
			ht[i] = null;
		}
		n=0;
	}

	@Override
	public void output() {
		for(int i=0;i<m;i++){
			if(key[i]==null||key[i].equals(tag))continue;
			System.out.print("("+key[i]+" " +ht[i]+"),");
			System.out.println();
		}
	}

}
