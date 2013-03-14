package chapter8;

import java.io.Serializable;

public class RecordType implements Serializable , Comparable{
	
	String key;
	int stn;
	String rest;
	
	public RecordType(String ke, int st, String re){
		key =ke;
		stn =st;
		rest = re;
	}
	
	@Override
	public int compareTo(Object o) {
		return stn -((RecordType)o).stn;
	}
	
	public String toString(){
		return String.valueOf(stn);
	}
}
