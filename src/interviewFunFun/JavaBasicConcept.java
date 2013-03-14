package interviewFunFun;

import java.io.File;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author MS
 * 1 变量不能以数字开头,一般以字母,字符开头. 特别说明的是可以使用$ _
 * 2 重写override 父类方法的时候, 只能扩大其可见性,不能缩小其可见性; 返回者也要完全匹配.
 * 3  HttpServletRequest http://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletRequest.html
 *    HttpServletResponse http://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletResponse.html#method_detail
 */
public class JavaBasicConcept {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String s = new String();
//		StringBuilder sb = new StringBuilder();
//		Formatter fm = new Formatter(sb, Locale.JAPAN);
//		fm.format("sdfsd", s);
		JavaBasicConcept jbc = new JavaBasicConcept();
		while(true){
			jbc.test();
		}
	}
	public void test(){
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static class  ClassB extends ClassA{
		//2
		/*private int method1(int a, int b){
			return 0;
		}*/
		/*public String method1(int a, int b){
			return "3434";
		}*/
		/** 这个属于方法的 overloading
		 */
		static protected int method1(int a, int b, int c){
			return 1;
		}
	}
}
