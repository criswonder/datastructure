package chapter8;

import java.io.File;
import java.io.IOException;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*insertSortTest();
		
		outSortTest();
		
		mergeSortTest();
		heapSortTest();*/
		quickSortTest();
		
		
/*		int a[]={1,-1,-4,33,-3,4,4,6,-5};
		insertSort1(a,a.length);
*/	
	
	}
	public static void insertSort1(int[] a, int n)
	{
		int j;
		for(int i=1;i<n;i++){
			int x=a[i];
			for(j=i-1;j>=0;j--){
				if(a[j]<0&& x>0)
					break;
				if(a[j]<0&& x<0)
					break;
				if(a[j]>0&& x>0)
					break;
				if(a[j]>0&& x<0)
				{
					a[j+1]=a[j];
					continue;
				}
			}
			a[j+1]=x;
		}
	}
	/**
	 * 外排序
	 */
	private static void outSortTest() {
		String fn1="xxkf1", fn2="xxkf2", fn3="xxkf3", fn4="xxkf4", fn5="xxkf5";
		
		int n=105;
		try {
			SortUtil.loadFile(fn1, n);
			System.out.println("排序前文件xxkf1中的数据：");
			SortUtil.printFile(fn1, n);
			int BlockSize=10;
			
			int n1=SortUtil.createSegment(fn1, fn2, fn3, n, BlockSize);
			System.out.println("建立初始归并段后文件xxkf2中的数据：");
			SortUtil.printFile(fn2, n1);
			System.out.println("建立初始归并段后文件xxkf3中的数据：");
			SortUtil.printFile(fn3, n-n1);
			
			SortUtil.fileMergeSort(fn2, fn3, fn4, fn5, BlockSize, n1, n-n1);
			System.out.println("建立初始归并段后文件xxkf2中的数据：");
			SortUtil.printFile(fn2, n);
			
			File fd3=new File("xxkf3");
			File fd4=new File("xxkf4");
			File fd5=new File("xxkf5");
			fd3.getAbsolutePath();
			fd3.delete();
			fd4.delete();
			fd5.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void mergeSortTest() {
		Object[] a={45,53,18,36,72,30,48,93,15,36};
		a = SortUtil.mergeSort(a, a.length);
		for(int i=0;i<a.length;i++)
			System.out.println(a[i]);
	}

	private static void insertSortTest() {
		SortType []a={
			new SortType(12, 24, "xxk"),	
			new SortType(45, 90, "weir"),	
			new SortType(25, 50, "xucong"),	
			new SortType(38, 76, "tongt"),	
			new SortType(60, 50, "baoj"),	
			new SortType(52, 104, "pangh"),	
			new SortType(100, 200, "1234")
		};
		
		SortUtil.insertSort(a, 6);
		
		for(int i=0;i<7;i++)
			System.out.println(a[i].num + " "+a[i].stn +" " +a[i].rest);
	}
	
	private static void heapSortTest() {
		//堆排序测试
		SortType []a={
				new SortType(12, 36, "xxk"),	
				new SortType(45, 25, "weir"),	
				new SortType(25, 48, "xucong"),	
				new SortType(38, 12, "tongt"),	
				new SortType(60, 65, "baoj"),	
				new SortType(52, 43, "pangh"),	
				new SortType(100, 20, "1234"),
				new SortType(100, 58, "1234")
		};
		
		SortUtil.heapSort(a, 8);
		
		for(int i=0;i<8;i++)
			System.out.println(a[i].num + " "+a[i].stn +" " +a[i].rest);
	}

	private static void quickSortTest() {
		//快速排序测试
		Object[] a={45,53,18,36,72,30,48,93,15,36};
		SortUtil.quickSort(a, 0, a.length-1);
		for(int i=0;i<a.length;i++)
			System.out.println(a[i]);
	}

}
