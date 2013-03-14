package chapter8;

public class SortType implements Comparable{

	int num;	  //关键字域
	int stn;	  //排序码
	String rest;  //其它域
	
	public SortType(int nu, int st, String re) {
		num = nu;
		stn = st;
		rest = re;
	}
	@Override
	public int compareTo(Object arg0) {
		return stn - ((SortType)arg0).stn;
	}

}
