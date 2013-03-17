

public class Popup{
	public static void  main(String args[]){
		int arrs[]={2,3,123,34,89,99,5,12};
		for(int i=arrs.length;i>0;i--){
			for(int j=0;j<i-1;j++){
				int temp=0;
				if(arrs[j]>arrs[j+1])
				{
				temp=arrs[j];
				arrs[j]=arrs[j+1];
				arrs[j+1]=temp;
				 continue;
				}
			}
		}
		int f=0;
		while(f<arrs.length){
		System.out.println(""+arrs[f]+"\t");
		f++;
		}
	}
}