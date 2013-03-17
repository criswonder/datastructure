import java.util.*;
import java.lang.*;

public class FunDigit {
	public static void main(String args[]) {
		int arr[][] = { { 7 }, { 4, 6 }, { 6, 9, 3 }, { 6, 3, 7, 1 },
				{ 2, 5, 3, 2, 8 }, { 5, 9, 4, 7, 3, 2 },
				{ 6, 4, 1, 8, 5, 6, 3 }, { 3, 9, 7, 6, 8, 4, 1, 5 },
				{ 2, 5, 7, 3, 5, 7, 8, 4, 2 } };

		TreeSet<String> routeSet = new TreeSet<String>();
		synchronized (routeSet) {
			Router rter = new Router(routeSet);
			Router rter1 = new Router(routeSet);
			Router rter2 = new Router(routeSet);
			Router rter3 = new Router(routeSet);
			Router rter4 = new Router(routeSet);
			rter.start();
			rter1.start();
			rter2.start();
			rter3.start();
			rter4.start();
		}
//		routeSet.add("000000112");
//		routeSet.add("000000122");
		while(routeSet.size()!=256){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Iterator<String> rt = routeSet.iterator();
		int rtSize=0;
		while (rt.hasNext()) {
			
			int sum = 0;
			String route = rt.next();
			for (int i = 0; i < 9; i++) {
				char c = route.charAt(i);
				int index = Integer.parseInt("" + c);
				sum += arr[i][index];
			}
			rtSize++;
			
			if (sum == 60)
				System.out.println("time "+(rtSize+1)+",sum" + sum + ",route is:" + route);
			
		}
	}
	//这个就是返回参数 或者参数加一
	static int randomNumber(int i) {
		Random rd = new Random();
		if ((System.currentTimeMillis() + rd.nextInt(100)) % 2 > 0) {
			return i;
		} else {
			return i + 1;
		}
	}
}

class Router extends Thread {
	private TreeSet<String> routeSet;

	public Router(TreeSet<String> rs) {
		routeSet = rs;
	}

	public void run() {
		while (routeSet.size() < 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2) {
			StringBuilder route = new StringBuilder("0");
			for (int i = 0; i < 8; i++) {

				route.append(FunDigit.randomNumber(Integer.parseInt(route
						.toString().substring(route.length() - 1))));
				Random rd = new Random();

				try {
					Thread.sleep(rd.nextInt(100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("thread: " + getName() + ", " + route);
			this.addToSet(route.toString());
		}
	}
	//添加到Tree set因为这个是treeset 又排序又唯一，所以同样的不会添加进来
	public void addToSet(String rt) {
		routeSet.add(rt);
		System.out.println("size:" + routeSet.size() + routeSet);
	}
}
