package interviewFunFun.SingletonPattern;

import interviewFunFun.SingletonPattern.MySingleton;
import interviewFunFun.SingletonPattern.MySingleton1;

public class RunHere {

	/**
	 * @param args
	 */
	static long duration = 2000;
	public static void main(String[] args) {
		//testThreadSafe();
		testHungryMode2LazyMode();
	}
	
	/**
	 * 比较单例的: 饥饿加载方式, 延迟加载方式. 
	 * 可以发现,饥饿的加载方式, 在这个类加载进来的时候, singleton就已经初始化了.
	 */
	private static void testHungryMode2LazyMode() {
		try {
			Class.forName("interviewFunFun.SingletonPattern.MySingleton");
			Class.forName("interviewFunFun.SingletonPattern.MySingleton1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 线程安全的测试. 看在多线程的情况下 是否会违反单例模式.
	 */
	private static void testThreadSafe() {
		for(int i=0;i<20;i++){
			WorkThread thread = new WorkThread();
			thread.start();
		}
		try {
			Thread.sleep(duration+2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------------------------------");
	}
	
	static class WorkThread extends Thread{
		@Override
		public void run() {
			super.run();
			
			long start = System.currentTimeMillis();
			while(!(System.currentTimeMillis()-start>duration)){
				MySingleton s = MySingleton.getInstance();
//				System.out.println(s);
				if(s.index>1)
					throw new RuntimeException(""+s);
			}
		}
	}
}
