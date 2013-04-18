
public class TestSortFun {
	public static void main(String args[]){
		int[] a = new int[]{1,6,3,2,9,10,100,22,33,1,33,44};
		insertSort2(a, a.length);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
		a = new int[]{1,6,3,2,9,10,100,22,33,1,33,44};
		selectSort2(a, a.length);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
	}
	
	public static void insertSort(int[] a,int len){
		int i,j;
		for(i=1;i<len;i++){
			int obj = a[i];
			for(j=i-1;j>=0;j--){
				if(obj<a[j])
				{
					a[j+1]=a[j];
				}else{
					break;
				}
			}
			a[j+1] = obj;
		}
	}
	
	public static void selectSort(int a[], int len){
		int i,j,k;
		for(i=0;i<len-1;i++){
			k = i;
			for(j=i+1;j<len;j++){
				if(a[j]<a[k]){
					k = j;
				}
			}
			if(k!=i){
				int temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			}
		}
	}
	
	
	public static void selectSort2(int a[],int len){
		int i,j,k;
		for(i=0;i<len-1;i++){
			k = i;
			for(j=i+1;j<len;j++){
				if(a[j]<a[k]){
					k = j;
				}
			}
			if(k!=i){
				int temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			}
		}
	}
	
	public static void insertSort2(int a[],int len){
		int i,j;
		for(i=1;i<len;i++){
			int temp = a[i];
			for(j=i-1;j>=0;j--){
				if(temp>=a[j]){
					break;
				}else{
					a[j+1] = a[j];
				}
			}
			a[j+1] = temp;
		}
	}
}
