package chapter8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 排序的工具类
 * @author MS
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class SortUtil {
	
	/**
	 * 插入排序
	 * 它把数组a中待排序的n个元素（n<=a.length) 看成为一个有序表和一个无序表, 开始时有序表中只包含
	 * 一个元素a[0], 无序表中包含有n-1个元素a[1] --- a[n-1]， 排序过程中每次从无序表中取出第一个元素，把
	 * 它插入到有序表中的适当的位置，使之成为新的有序表。
	 * 最终的数组的排序顺序是 小----> 大
	 */
	public static void insertSort(Object[] a,int n ){
		Object x;
		int i,j;
		//循环n-1次
		for(i=1;i<n;i++){
			x=a[i];
			for(j=i-1;j>=0;j--){
				if( ((Comparable)x) .compareTo( (Comparable)a[j] ) <0 )
					a[j+1] = a[j];
				else
					break;
			}
			a[j+1]=x;
		}
	}
	
	public static void selectSort(Object[] a, int n){
		int i,j,k;
		for(i=1;i<=n-1;i++){ //i表示次数，共进行N-1次选择和交换
			k=i-1;
			for(j=i;j<=n-1;j++){
				if(((Comparable)a[j]).compareTo((Comparable)a[k])<0)
					k=j;
			}
			if(k!=i-1){
				Object x=a[i-1];
				a[i-1] = a[k];
				a[k] = x;
			}
		}
	}
	
	/**
	 * 用于堆排序
	 * 对数组a[n]中的a[i]元素进行筛运算
	 */
	public static void sift(Object []a, int n, int i){
		Object x=a[i];
		int j=2*i + 1; //左孩子
		while(j<=n-1){
			if(j<n-1 && ((Comparable)a[j]).compareTo((Comparable)a[j+1]) < 0)
				j++;
			if(((Comparable)x).compareTo((Comparable)a[j]) < 0)
			{
				a[i] = a[j];
				i = j; //这里的目的是为了下一次交换
				j= 2*i + 1;
			}
			else break;
		}
		a[i] = x;
	}
	/**
	 * 堆排序法
	 * @param a 待排序的数组
	 * @param n 数组的长度，大小
	 */
	public static void heapSort(Object []a, int n){
		Object x;
		int i;
		for(i=n/2-1; i>=0; i--)
			sift( a, n, i);
		for(i = 1; i<=n-1;i++){
			x=a[0];a[0]=a[n-i];a[n-i]=x;
			sift( a, n-i, 0);
		}
	}
	
	public static void bubbleSort(Object []a, int n){
		int i,j, flag;
		for(i=0;i<n;i++){ // i表示趟数，共进行N-1趟
			flag=0; // flag用来标志每趟是否有交换
			for(j=n-1;j>=i;j--){
				if(((Comparable)a[j]).compareTo((Comparable)a[j-1]) < 0)
				{
					Object temp=a[j-1];
					a[j-1]=a[j];
					a[j]=temp;
					flag=1;
				}
			}
			if(flag==0)return;
		}
		
	}
	
	/**
	 * 采用快速排序方法对数组a中 s-t区间内的元素进行排序
	 * @param a
	 * @param s start index
	 * @param t tail index
	 */
	public static void quickSort(Object a[], int s, int t){
		int i=s, j=t+1;
		Object x=a[s];//把基准元素的值暂存X中
		do{
			do 
				i++; 
			while( i<a.length && ((Comparable)a[i]).compareTo( (Comparable)x ) < 0  );
			do 
				j--; 
			while( ((Comparable)a[j]).compareTo( (Comparable)x ) > 0 );
			
			if(i<j){
				Object temp = a[i];
				a[i]=a[j];a[j]=temp;
			}
		}while(i<j);
		
		a[s] = a[j];
		a[j]=x;
		if(s<j-1)
			quickSort(a,s,j-1); //在当前左区间超过一个元素的情况下 递归处理左区间
		if(j+1<t)
			quickSort(a,j+1,t); //在当前右区间超过一个元素的情况下递归处理右区间
	}
	/**
	 * Todo, 把相邻有序表a[s]~a[m]和a[m+1]~a[t] 归并为一个有序表r[s]~r[t]
	 * @param a 待归并的数组
	 * @param r 归并结果存放的数组;result
	 * @param s a[] 中待归并有序表1的起始元素的位置;start Index
	 * @param m a[] 中待归并有序表1的最后一个元素的位置;middle Index
	 * @param t a[] 中待归并有序表2的最后一个元素的位置;tail Index
	 */
	public static void twoMerge(Object[] a, Object[] r, int s, int m, int t){
		int i,j,k;
		i=s;j=m+1;k=s;
		while(i<=m && j<=t)
		{
			if( ((Comparable)a[i]).compareTo( (Comparable)a[j] )<0){
				r[k]=a[i];
				i++;
				k++;
			}
			else{
				r[k] =a[j];
				j++;
				k++;
			}
		}
		
		while(i<=m){
			r[k] = a[i];
			i++;
			k++;
		}
		while(j<=t){
			r[k]=a[j];
			j++;
			k++;
		}
	}
	
	public static void mergePass(Object[] needSort, Object[] resultArray, int resultArrayLength, int len){
		int p=0;
		while(p+2*len-1<=resultArrayLength-1){
			twoMerge(needSort,resultArray,p,p+len-1,p+2*len-1);
			p+=2*len;
		}
		if(p+len-1<resultArrayLength-1)  //归并最后两个长度不等的有序表
			twoMerge(needSort,resultArray,p,p+len-1,resultArrayLength-1);
		else			 //把最后一个有序表复制到r中
			for(int i=p;i<=resultArrayLength-1;i++)
				resultArray[i]=needSort[i];
	}
	/**
	 * 外部调用这个方法的时候,n传入的是a.length
	 * @param a
	 * @param n
	 * @return
	 */
	public static Object[] mergeSort(Object []a, int n){
		int len=1;
		while(len<n){
			Object []r= new Object[n];
			mergePass(a,r,n,len);
			//让a 里面存储的都是归并后的结果
			a=r;
			len*=2;
		}
		return a;
	}
	/**
	 * todo把文件f2和f3中的当前位置上的长度分别为ca和cb的有序表归并到文件f4
	 * @param f2
	 * @param f3
	 * @param f4
	 * @param ca
	 * @param cb
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void fileTwoMerge(ObjectInputStream f2, ObjectInputStream f3,
			ObjectOutputStream f4, int ca, int cb) throws IOException, ClassNotFoundException{
		//i,j分别表示f2,和f3中被处理的记录个数
		int i=0, j=0;
		//xa, xb 分别表示读出的记录。
		Object xa=null, xb=null;
		//当ba和bb为true时表示读取f2, f3中下一个记录。
		boolean ba=true, bb=true;
		// ca, cb 为两个有序表的长度。
		while(i<ca && j<cb){
			if(ba)
				xa= f2.readObject();
			if(bb)
				xb= f3.readObject();
			if(((Comparable)xa).compareTo((Comparable)xb)<0)
			{
				f4.writeObject(xa);
				i++;
				ba=true;
				bb=false;
			}
			else{
				f4.writeObject(xb);
				j++;
				bb=true;
				ba=false;
			}
		}
		
		if(i<ca){
			f4.writeObject(xa);
			i++;
		}
		while(i<ca){
			xa= f2.readObject();
			f4.writeObject(xa);
			i++;
		}
		if(j<cb){
			f4.writeObject(xb);
			j++;
		}
		while(j<cb){
			xb= f3.readObject();
			f4.writeObject(xb);
			j++;
		}
		
	}
	/**
	 * 把归并段长度为len的文件A1和A2，进行对应归并段归并到文件R1和R2中，归并后的归并长度为2*len, 当然末尾长度可能短些，n1和n2分别表示文件A1和A2的长度
	 * @param A1
	 * @param A2
	 * @param R1
	 * @param R2
	 * @param len
	 * @param n1
	 * @param n2
	 * @return 返回归并后文件R1的长度, 可以肯定的是R1的长度一定大于等于R2的长度
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static int fileMergePass(ObjectInputStream A1, ObjectInputStream A2,
								ObjectOutputStream R1, ObjectOutputStream R2,
								int len, int n1, int n2) throws IOException, ClassNotFoundException{
		int p=0;
		while(p+len<=n1 && p+len<=n2){
			if(p%(2*len)==0)
				fileTwoMerge(A1, A2, R1, len, len);
			else
				fileTwoMerge(A1, A2, R2, len, len);
			p+=len;
		}
		if(p<n1 && p<n2){
			if(p%(2*len)==0){
				fileTwoMerge(A1, A2, R1, n1-p, n2-p);
				return n1+n2-p;
			}
			else{
				fileTwoMerge(A1, A2, R2, n1-p, n2-p);
				return p+len;
			}
		}
		else{
			for(int i=p;i<n1;i++){
				Object x=A1.readObject();
				if(p%(2*len)==0)
					R1.writeObject(x);
				else
					R2.writeObject(x);
				
			}
			if(p%(2*len)==0)
				return n1;
			else
				return p+len;
		}
	}
	
	/**
	 * 采用二路归并排序的方法对长度分别为n1和n2的文件fn2，fn3，从归并长度为BlockSize开始进行归并排序，结果存入fn2文件中
	 * @param fn2
	 * @param fn3
	 * @param fn4
	 * @param fn5
	 * @param BlockSize
	 * @param n1
	 * @param n2
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void fileMergeSort(String fn2, String fn3,String fn4,String fn5, int BlockSize, int n1,int n2) throws FileNotFoundException, IOException, ClassNotFoundException{
			ObjectInputStream A1;
			ObjectInputStream A2;
			ObjectOutputStream R1;
			ObjectOutputStream R2;
			
			int len=BlockSize;
			int k;
			while(len < n1+n2){
				A1=new ObjectInputStream(new FileInputStream(fn2));
				A2=new ObjectInputStream(new FileInputStream(fn3));
				
				R1 = new ObjectOutputStream(new FileOutputStream(fn4));
				R2 = new ObjectOutputStream(new FileOutputStream(fn5));
				
				k=fileMergePass(A1,A2,R1,R2,len,n1,n2);
				len*=2;
				n2=n1+n2-k;
				n1=k;
				A1.close();
				A2.close();
				R1.close();
				R2.close();
				
				A1=new ObjectInputStream(new FileInputStream(fn4));
				A2=new ObjectInputStream(new FileInputStream(fn5));
				
				R1 = new ObjectOutputStream(new FileOutputStream(fn2));
				R2 = new ObjectOutputStream(new FileOutputStream(fn3));
				
				k=fileMergePass(A1,A2,R1,R2,len,n1,n2);
				len*=2;
				n2=n1+n2-k;
				n1=k;
				A1.close();
				A2.close();
				R1.close();
				R2.close();
			}
	}
	
	public static void loadFile(String fname, int n) throws IOException
	{
		FileOutputStream fos= new FileOutputStream(fname);
		ObjectOutputStream ff=new ObjectOutputStream(fos);
		
		RecordType x;
		for(int i=0;i<n;i++){
			int r=(int)(Math.random()*500);
			x = new RecordType("",r,"")	;
			ff.writeObject(x);
		}
		ff.close();
	}
	
	public static void printFile(String fname, int n) throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(fname);
		ObjectInputStream ff= new ObjectInputStream(fis);
		RecordType x;
		for(int i=1;i<=n;i++){
			x=(RecordType)ff.readObject();
			System.out.printf("%4d", x.stn);
			if(i%15==0)
				System.out.println();
		}
		System.out.println();
		ff.close();
	}	
	
	/**
	 * 根据长度为n的原数据文件fn1 建立两个初始归并段文件fn2，fn3
	 * @param fn1
	 * @param fn2
	 * @param fn3
	 * @param n
	 * @param BlockSize 每个归并段的长度
	 * @return 返回较长的fn2的长度（记录个数）
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static int createSegment(String fn1,String fn2,String fn3, int n, int BlockSize) throws IOException, ClassNotFoundException{
		ObjectInputStream f1= new ObjectInputStream(new FileInputStream(fn1));
		ObjectOutputStream f2= new ObjectOutputStream(new FileOutputStream(fn2));
		ObjectOutputStream f3= new ObjectOutputStream(new FileOutputStream(fn3));
		
		//定义和创建具有初始归并段长度的对象数组a
		Object []a=new Object[BlockSize];
		//求出文件fn1中包含的初始归并段的整数赋值给K
		int k=n/BlockSize;
		//求出最后剩余的记录个数并赋值给M
		int m=n%BlockSize;
		int i;
		//依次建立好k个归并段，并相间地写入到数据文件fn2，fn3
		for(i=0; i<k; i++){
			for(int j=0;j<BlockSize;j++)
				a[j]=f1.readObject();
			insertSort(a,BlockSize);
			if(i%2==0){
				for(int j=0;j<BlockSize; j++)
					f2.writeObject(a[j]);
			}else
			{
				for(int j=0;j<BlockSize; j++)
					f3.writeObject(a[j]);
			}
		}
		
		if(m>0){
			for(int j=0; j<m; j++)
				a[j]=f1.readObject();
			insertSort(a,m);
			if(i%2==0)
				for(int j=0;j<m;j++)f2.writeObject(a[j]);
			else
				for(int j=0;j<m; j++)f3.writeObject(a[j]);
		}
		
		f1.close(); f2.close(); f3.close();
		//这里K%2==1如果是true则表明k是奇数，否则是偶数
		if(k%2==1)return (k+1)/2*BlockSize;
		else
			return k/2*BlockSize +m;
	}
}

