package interviewFunFun.SingletonPattern;

public class MySingleton {
	private static MySingleton mySingleton;
	public static int index=0;
	private MySingleton(){
		System.out.println("Constructor of MySingleton get called");
		index++;
	}
	/**
	 * 不加这个synchronized 就会由线程安全的问题, 其实就是违反了单例模式 
	 * @return
	 */
	public static synchronized MySingleton getInstance(){
		if(mySingleton == null){
			mySingleton = new MySingleton();
			return mySingleton;
		}else
			return mySingleton;
		
	}
	public String toString(){
		return "index="+index+"";
	}
}
