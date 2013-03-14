package chapter7;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SearchUtil {
	/**
	 * 顺序查找
	 * @param a 待查找的数组
	 * @param x 待查找的元素
	 * @param n 数组a的长度
	 * @return
	 */
	public static int seqSearch(Object a[], Object x, int n){
		a[n]=x;
		int i;
		for(i=0;;i++)
			if(a[i].equals(x))break;
		if(i<n)return i;
		else
			return -1;
	}
	
	public static int binSearch(Object a[], Object x, int n){
		int low=0, high=n-1;
		while(low<=high){
			int mid=(low+high)/2;
			if( ((Comparable)x).compareTo( ((Comparable)a[mid])) == 0)
				return mid;
			else if( ((Comparable)x).compareTo( ((Comparable)a[mid]) )<0)
				high=mid-1;
			else
				low=mid+1;
		}
		return -1;
	}
}
