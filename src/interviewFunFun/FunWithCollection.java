package interviewFunFun;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 刚开始的题目是: 已知数组A,B 要求通过操作使A变成B, 也就是A的内容和B的一样的. 假设A与B有公共部分. 那么结果是: A中减去B中没有的部分;
 * 加上B中的A 没有的部分. 如果A,B的公共部分求出来了,就比较好解开了. 所以问题简化为找出A, B的公共部分. 传统方法. 很简单就是两重for循环.
 * 但是效率不高. 要求用一种比较高效率的方式来 找出公共部分. 特别是在两个数组比较大的时候.
 * 
 * 1 首先想到的时候给数组排序, 然后决定公共元素肯定所在的区域. 这样会减少循环的次数.
 * 2 在for循环查找的时候使用二分查找.减少查找次数
 * 3 使用二路归并法 排序和查找公共元素
 * @author MS
 * 
 */
public class FunWithCollection {
	private static List result1,result2,result3, result4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arrayA = getArray(60000, 70000);
		int[] arrayB = getArray(60000, 80000);

		Arrays.sort(arrayA);
		Arrays.sort(arrayB);

		long startPoint = System.currentTimeMillis();
		result1 = getCommonObjects1(arrayA, arrayB);
		long method1 = System.currentTimeMillis() - startPoint;

		startPoint = System.currentTimeMillis();
		result2 = getCommonObjects2(arrayA, arrayB);
		long method2 = System.currentTimeMillis() - startPoint;

		startPoint = System.currentTimeMillis();
		result3 = getCommonObjects3(arrayA, arrayB);
		long method3 = System.currentTimeMillis() - startPoint;
		
		startPoint = System.currentTimeMillis();
		result4 = getCommonObjectsNormally(arrayA, arrayB);
		long method4 = System.currentTimeMillis() - startPoint;

		System.out.println("method 1 used:" + method1);
		System.out.println("method 2 used:" + method2);
		System.out.println("method 3 used:" + method3);
		System.out.println("method 4 used:" + method4);

		System.out.println(result1.size() + "," + result2.size() + ","
				+ result3.size()+","+result4.size());
	}

	/**
	 * 获得最大值为max，长度为len的int数组
	 * 
	 * @param len
	 * @param max
	 * @return
	 */
	public static int[] getArray(int len, int max) {
		int[] result = new int[len];
		Random rd = new Random();
		for (int i = 0; i < len; i++) {
			result[i] = rd.nextInt(max);
		}
		return result;
	}

	/**
	 * 假定传入的数组时进过排序的
	 * 
	 * @param arrayA
	 * @param arrayB
	 */
	private static void getCommonObjects(int[] arrayA, int[] arrayB) {
		int[] temp;
		if (arrayA[arrayA.length - 1] > arrayB[arrayB.length - 1]) {
			temp = arrayA;
			arrayA = arrayB;
			arrayB = temp;
		}

		List same = new ArrayList<Integer>();
		printArray(arrayA);
		String delimiter = "******************************************************************************";
		System.out.println(delimiter);
		printArray(arrayB);
		System.out.println(delimiter);

		if (arrayA[arrayA.length - 1] < arrayB[0]
				|| arrayB[arrayB.length - 1] < arrayA[0]) {
			System.out.println("没有公共元素！");
			return;
		}

		int endIndex = findEndIndex(arrayA, arrayB);
		int beginIndex = findBeginIndex(arrayA, arrayB);

		for (int i = beginIndex; i < arrayA.length; i++) {
			int headIndex = 0, tailIndex = endIndex;
			while (headIndex <= tailIndex) {
				int middle = (headIndex + tailIndex) / 2;
				if (arrayA[i] == arrayB[middle]) {
					same.add(arrayA[i]);
					break;
				}
				if (arrayA[i] < arrayB[middle]) {
					tailIndex = middle - 1;
					continue;
				}
				if (arrayA[i] > arrayB[middle]) {
					headIndex = middle + 1;
				}
			}

			/*
			 * for(int j=0;j<=endIndex;j++){ if(arrayA[i] == arrayB[j]) {
			 * same.add(arrayA[i]); } }
			 */
		}
		printArray(same.toArray());
	}

	/**
	 * 去掉重复后 进行操作。
	 * 去重后使用:Arrays.sort排序
	 * 使用二分查找
	 * @param arrayA
	 * @param arrayB
	 * @return
	 */
	private static List getCommonObjects1(int[] arrayA, int[] arrayB) {
		long start = System.currentTimeMillis();
		Integer[] afterProcess = removeDuplicate(arrayA);
		Integer[] afterProcess1 = removeDuplicate(arrayB);
		int[] arrayA1 = new int[afterProcess.length];
		int[] arrayA2 = new int[afterProcess1.length];
		for (int z = 0; z < arrayA1.length; z++) {
			arrayA1[z] = afterProcess[z];
		}
		for (int z = 0; z < arrayA2.length; z++) {
			arrayA2[z] = afterProcess1[z];
		}
		System.out.println(System.currentTimeMillis() - start);
		arrayA = arrayA1;
		arrayB = arrayA2;

		Arrays.sort(arrayA);
		Arrays.sort(arrayB);

		int[] temp;
		if (arrayA[arrayA.length - 1] > arrayB[arrayB.length - 1]) {
			temp = arrayA;
			arrayA = arrayB;
			arrayB = temp;
		}

		List same = new ArrayList<Integer>();
		printArray(arrayA);
		String delimiter = "******************************************************************************";
		System.out.println(delimiter);
		printArray(arrayB);
		System.out.println(delimiter);

		if (arrayA[arrayA.length - 1] < arrayB[0]
				|| arrayB[arrayB.length - 1] < arrayA[0]) {
			System.out.println("没有公共元素！");
			return null;
		}

		int endIndex = findEndIndex(arrayA, arrayB);
		int beginIndex = findBeginIndex(arrayA, arrayB);

		for (int i = beginIndex; i < arrayA.length; i++) {
			int headIndex = 0, tailIndex = endIndex;
			while (headIndex <= tailIndex) {
				int middle = (headIndex + tailIndex) / 2;
				if (arrayA[i] == arrayB[middle]) {
					same.add(arrayA[i]);
					break;
				}
				if (arrayA[i] < arrayB[middle]) {
					tailIndex = middle - 1;
					continue;
				}
				if (arrayA[i] > arrayB[middle]) {
					headIndex = middle + 1;
				}
			}
		}
		printArray(same.toArray());
		return same;
	}

	/**
	 * 去掉重复后 进行操作。
	 * 去重后使用:Arrays.sort排序
	 * 使用二路归并查找相同元素
	 * @param arrayA
	 * @param arrayB
	 * @return
	 */
	private static List getCommonObjects2(int[] arrayA, int[] arrayB) {
		List same = new ArrayList<Integer>();

		long start = System.currentTimeMillis();
		Integer[] afterProcess = removeDuplicate(arrayA);
		Integer[] afterProcess1 = removeDuplicate(arrayB);
		Arrays.sort(afterProcess);
		Arrays.sort(afterProcess1);
		int ca = afterProcess.length, cb = afterProcess1.length;
		Integer[] mergedResult = new Integer[afterProcess.length
				+ afterProcess1.length];
		// i,j分别表示afterProcess,和afterProcess1中被处理的记录个数
		int i = 0, j = 0, k = 0;
		// xa, xb 分别表示读出的记录。
		Integer xa = null, xb = null;
		// 当ba和bb为true时表示读取f2, f3中下一个记录。
		boolean ba = true, bb = true;
		// ca, cb 为两个有序表的长度。
		while (i < ca && j < cb) {
			if (ba)
				xa = afterProcess[i];
			if (bb)
				xb = afterProcess1[j];
			if (((Comparable) xa).compareTo((Comparable) xb) < 0) {
				mergedResult[k] = xa;
				k++;
				i++;
				ba = true;
				bb = false;
			} else if (xa > xb) {
				mergedResult[k] = xb;
				k++;
				j++;
				bb = true;
				ba = false;
			} else {
				mergedResult[k] = xb;
				k++;
				j++;

				mergedResult[k] = xa;
				k++;
				i++;
				bb = true;
				ba = true;

				same.add(xa);

			}
		}
		printArray(same.toArray());
		return same;
	}
	/**
	 * 去掉重复后 进行操作。
	 * 去重后使用:二路归并排序
	 * 使用二路归并查找相同元素
	 * @param arrayA
	 * @param arrayB
	 * @return
	 */
	private static List getCommonObjects3(int[] arrayA, int[] arrayB) {
		List same = new ArrayList<Integer>();
		
		long start = System.currentTimeMillis();
		Integer[] afterProcess = removeDuplicate(arrayA);
		Integer[] afterProcess1 = removeDuplicate(arrayB);
		afterProcess= twoMerge(afterProcess);
		afterProcess1= twoMerge(afterProcess1);
		int ca = afterProcess.length, cb = afterProcess1.length;
		Integer[] mergedResult = new Integer[afterProcess.length
		                                     + afterProcess1.length];
		// i,j分别表示afterProcess,和afterProcess1中被处理的记录个数
		int i = 0, j = 0, k = 0;
		// xa, xb 分别表示读出的记录。
		Integer xa = null, xb = null;
		// 当ba和bb为true时表示读取f2, f3中下一个记录。
		boolean ba = true, bb = true;
		// ca, cb 为两个有序表的长度。
		while (i < ca && j < cb) {
			if (ba)
				xa = afterProcess[i];
			if (bb)
				xb = afterProcess1[j];
			if (xa<xb) {
				mergedResult[k] = xa;
				k++;
				i++;
				ba = true;
				bb = false;
			} else if (xa > xb) {
				mergedResult[k] = xb;
				k++;
				j++;
				bb = true;
				ba = false;
			} else {
				mergedResult[k] = xb;
				k++;
				j++;
				
				mergedResult[k] = xa;
				k++;
				i++;
				bb = true;
				ba = true;
				
				same.add(xa);
				
			}
		}
		printArray(same.toArray());
		return same;
	}

	private static Integer[] twoMerge(Integer[] needSort) {
		int len = 1;
		while (len < needSort.length) {
			Integer[] outPut =  new Integer[needSort.length];
			mergeIt(needSort,outPut ,len);
			needSort = outPut;
			len = len * 2;
		}
		return needSort;
	}

	private static void mergeIt(Integer[] needSort,Integer[] outPut, int len) {
		int p=0;
		while(p+2*len-1< needSort.length){
			mergeFun(needSort, outPut, p,p+len-1,p+2*len-1);
			p+=2*len;
		}
		if(p+len-1<needSort.length-1)  //归并最后两个长度不等的有序表
			mergeFun(needSort,outPut,p,p+len-1,needSort.length-1);
		else			 //把最后一个有序表复制到r中
			for(int i=p;i<=needSort.length-1;i++)
				outPut[i]=needSort[i];
	}

	private static void mergeFun(Integer[] needSort, Integer[] outPut,
			int s, int m, int t) {
		int i,j,k;
		i=s;j=m+1;k=s;
		while(i<=m && j<=t)
		{
			if( needSort[i]<needSort[j]){
				outPut[k]=needSort[i];
				i++;
				k++;
			}
			else{
				outPut[k] =needSort[j];
				j++;
				k++;
			}
		}
		
		while(i<=m){
			outPut[k] = needSort[i];
			i++;
			k++;
		}
		while(j<=t){
			outPut[k]=needSort[j];
			j++;
			k++;
		}
	}

	/**
	 * 去掉重复的记录
	 * 
	 * @param arrayA
	 * @return
	 */
	private static Integer[] removeDuplicate(int[] arrayA) {
		Set<Integer> lst = new HashSet<Integer>();
		for (int i = 0; i < arrayA.length; i++) {
			lst.add(arrayA[i]);
		}
		return (Integer[]) lst.toArray(new Integer[lst.size()]);
	}

	/**
	 * 原始方法
	 * 
	 * @param arrayA
	 * @param arrayB
	 */
	private static List getCommonObjectsNormally(int[] arrayA, int[] arrayB) {
		List same = new ArrayList<Integer>();
		printArray(arrayA);
		String delimiter = "******************************************************************************";
		System.out.println(delimiter);
		printArray(arrayB);
		System.out.println(delimiter);

		for (int i = 0; i < arrayA.length; i++) {
			for (int j = 0; j < arrayB.length; j++) {
				if (arrayA[i] == arrayB[j]) {
					same.add(arrayA[i]);
					break;
				}
			}
		}
		printArray(same.toArray());
		return same;
	}

	private static int findEndIndex(int[] arrayA, int[] arrayB) {
		if (arrayA.length == 0 || arrayB.length == 0) {
			return -1;
		} else {
			int b = 0;
			for (; b < arrayB.length; b++) {
				if (arrayA[arrayA.length - 1] >= arrayB[b]) {
					continue;
				} else {
					return --b;
				}
			}
			return --b;
		}
	}

	private static int findBeginIndex(int[] arrayA, int[] arrayB) {
		if (arrayA.length == 0 || arrayB.length == 0) {
			return -1;
		} else {
			int a;
			for (a = arrayA.length - 1; a >= 0; a--) {
				if (arrayB[0] <= arrayA[a]) {
					continue;
				} else {
					return ++a;
				}
			}
			return ++a;
		}
	}

	public static void printArray(int[] arrayA) {
		for (int i = 0; i < arrayA.length; i++) {
			System.out.print(arrayA[i] + " ");
			if (i == arrayA.length - 1 || i % 20 == 0)
				System.out.println();
		}
	}

	public static void printArray(Object[] arrayA) {
		for (int i = 0; i < arrayA.length; i++) {
			System.out.print(arrayA[i] + " ");
			if (i == arrayA.length - 1 || i % 20 == 0)
				System.out.println();
		}
	}

}
