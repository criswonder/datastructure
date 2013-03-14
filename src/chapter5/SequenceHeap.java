package chapter5;

public class SequenceHeap implements Heap {
	final int maxSize=10;
	private Object[] heapArray;
	private int length; //当前的长度
	
	public SequenceHeap(){
		length=0;
		heapArray=new Object[maxSize];
	}
	
	public SequenceHeap(int n){
		if(n<=0){
			System.out.println("数组长度需大于0");
			System.exit(1);
		}
		length=0;
		heapArray=new Object[n];
	}

	@Override
	public void insert(Object obj) {
		if(length==heapArray.length){
			Object []p = new Object[2*length];
			for(int i=0;i<length;i++){
				p[i]=heapArray[i];
			}
			heapArray = p;
		}
		heapArray[length++] = obj;
		int i=length-1;
		while(i!=0){
			int j=(i-1)/2;
			if(((Comparable)obj).compareTo(heapArray[j])>=0){
				break;
			}
			heapArray[i]=heapArray[j];
			i=j;
		}//end while
		heapArray[i]=obj;
	}

	@Override
	public Object delete() {
		if(length==0){
			System.out.println("当前堆为空，无法删除元素，返回空值");
			return null;
		}
		Object temp=heapArray[0];
		length--;
		if(length==0)return temp;
		Object x=heapArray[length];
		int i=0,j=1;
		while(j<length-1){
			if(j<length-1&&((Comparable)heapArray[j]).compareTo(heapArray[j+1])>0)
				j++;
			if(((Comparable)x).compareTo(heapArray[j])<=0)
				break;
			heapArray[i] = heapArray[j];
			i=j;
			j=2*i+1;
		}
		heapArray[i] =x ;
		return temp;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public void output() {
		output(0);
		System.out.println();
	}
	public void output(int rt){
		if(rt<length){
			System.out.print(heapArray[rt]);
			if(2*rt+1<length){
				System.out.print('(');
				output(2*rt+1);
				if(2*rt+2<length)
					System.out.print(',');
				output(2*rt+2);
				System.out.print(')');
			}
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return length==0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]){
		SequenceHeap hp = new SequenceHeap();
		Integer []a={23,45,89,40,73,12,49,72,20,44,86,33};
		for(int i=0;i<a.length;i++){
			hp.insert(a[i]);
		}
		System.out.println("hp堆的广义表形式");
		hp.output();
		System.out.println("元素个数："+hp.size());
		while(!hp.isEmpty())
			System.out.print(hp.delete()+" ");
		System.
		out.println();
	}

}
