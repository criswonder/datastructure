package interviewFunFun;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("")));
		
//		Scanner s = new Scanner(new File(""));
//		s.useDelimiter("");
//		s.hasNext();
//		s.next()
		int i=1,j=2,k=3;
		System.out.format("%d + %d=%d\n", i,j,k);
		
		System.getProperties().get("");
		System.getProperty("sdf");
		
		 String input =
			     "A Matcher examines the results of applying a pattern.";
			    String regex = "\\ba\\w*\\b";
			    Pattern p = Pattern.compile(regex);
			    Matcher m = p.matcher(input);
			    String val = null;
			    System.out.println("INPUT: " + input);
			    System.out.println("REGEX: " + regex +"\r\n");
			    while (m.find()){
			      val = m.group();
			      System.out.println("MATCH: " + val);
			    }
			    if (val == null) {
			      System.out.println("NO MATCHES: ");
			    }	}

}

