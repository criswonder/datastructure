package interviewFunFun;

public class HuaWei {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str="123";
//		String str1=null;
		String str1="WEIBO";
		
		str1 = changeString(str);
		System.out.println(str);
		System.out.println(str1);
		
		//看看如果对 对象操作会怎么样
		Parcel p1 = new Parcel("mao");
		p1.setId("hong");
		Parcel p2;
		p2 = changeObject(p1);
		System.out.println(p1);
		System.out.println(p2);
		
	}
	
	private static Parcel changeObject(Parcel p1) {
		//~~~~~=====================================================================
		p1.setId("hongyun"); //这里改他的属性是可以的.
		
		
		
		//~~~~~=====================================================================
		p1 = new Parcel("gao"); //这里想把p1给改掉,结果没改掉, 开来这个和数组时一样的
		
		return p1;
	}

	public static String changeString(String str){
		//~~~~~=====================================================================
		str = "hello";
		return str;
	}
}
