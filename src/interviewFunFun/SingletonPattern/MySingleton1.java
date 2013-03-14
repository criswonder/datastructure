package interviewFunFun.SingletonPattern;

public class MySingleton1 {
	private static MySingleton1 mySingleton =  new MySingleton1();
	public static int index=0;
	private MySingleton1(){
		System.out.println("Constructor of MySingleton get called");
		index++;
	}
	/**
	 * 不加这个synchronized 就会由线程安全的问题, 其实就是违反了单例模式 
	 * @return
	 */
	public static synchronized MySingleton1 getInstance(){
		return mySingleton;
	}
	public String toString(){
		return "index="+index+"";
	}
}
