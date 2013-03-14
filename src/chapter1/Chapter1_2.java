package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chapter1_2 {
	static int findMax(int[] a){
		int x=a[0];
		for(int i=1;i<a.length;i++){
			if(a[i]>x)x=a[i];
		}
		return x;
	}
	
	public static void main(String args[]) throws IOException{
		int a[] = new int[5];
		for(int i=0;i<5;i++){
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader rdr = new BufferedReader(in);
			String line = rdr.readLine();
			a[i] = Integer.parseInt(line);
		}
		int max = findMax(a);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println(max);
	}
}
