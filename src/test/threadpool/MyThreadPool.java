package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
	private static ExecutorService executorService = Executors.newFixedThreadPool(5);
	private static MyThreadPool pool;
	private MyThreadPool(){}

	public static synchronized MyThreadPool getInstance(){
		if(pool==null)
			return new MyThreadPool();
		else 
			return pool;
	}
	public void execute(Runnable run){
		executorService.execute(run);
	}
	
	public void shutDown(){
		executorService.shutdown();
		executorService =null;
	}

}
