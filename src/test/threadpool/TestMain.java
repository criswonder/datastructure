package test.threadpool;

import java.util.Random;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			MyThreadPool.getInstance().execute(new MThread(i));
		}
		MyThreadPool.getInstance().shutDown();
	}
	
	
	static class MThread extends Thread{
		int num;
		public MThread(int i){
			num = i;
		}
		@Override
		public void run() {
			super.run();
			System.out.println(num);
			try {
				Thread.sleep(new Random().nextInt(1300));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
