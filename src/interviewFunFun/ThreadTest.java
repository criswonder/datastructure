package interviewFunFun;

import javax.management.RuntimeErrorException;

public class ThreadTest extends Thread{
	@Override
	public void run() {
		synchronized (this) {
			
			try {
				System.out.println("1!");
				wait();
				System.out.println("notified!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}
	public void restart(){
		synchronized (ThreadTest.class) {
			notifyAll();
			System.out.println("notify all");
		}
	}
	public static void main(String[] args) {
		ThreadTest t = new ThreadTest();
		t.start();
		t.restart();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
