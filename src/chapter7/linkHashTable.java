package chapter7;


/**
 * 同义词节点被链接在同一个散列地址上
 * @author MS
 *
 */
public class linkHashTable implements HashTable{
	
	private int m;
	private HashNode[] ht;
	private int n;
	
	private int h(Object theKey){
		return (Integer)theKey%m;
	}
	
	public linkHashTable(int m){
		this.m = m;
		n=0;
		ht = new HashNode[m];
	}

	@Override
	public boolean insert(Object theKey, Object obj) {
		int d = h(theKey);
		HashNode p = ht[d];
		while(p!=null){
			if(p.key.equals(theKey))break;
			else
				p = p.next;
		}
		if(p!=null){    //用新元素修改已有结点的元素并返回false
			p.element = obj;
			return false;
		}
		else{
			p = new HashNode(theKey, obj);
			p.next = ht[d];
			ht[d] = p;
			//元素个数加1
			n++;
			return true;
		}
	}

	@Override
	public Object search(Object theKey) {
		int d = h(theKey);
		HashNode p = ht[d];
		if(p!=null){
			if(p.key.equals(theKey))
				return p.element;
			else
				p = p.next;
		}
		return null;
	}

	@Override
	public boolean delete(Object theKey) {
		int d =h(theKey);
		HashNode p = ht[d], q = null;
		while(p!=null){
			if(p.key.equals(theKey))break;
			else{q = p;p = p.next;}
		}
		if(p==null)return false;
		else if(q==null)ht[d]=p.next;	//删除的是表头节点
		else q.next = p.next;			//删除的是非表头节点
		n--;
		return false;
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public int capacity() {
		return m;
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}

	@Override
	public void clear() {
		for(int i=0;i<m;i++)
			ht[i] = null;
		n=0;
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}

}
