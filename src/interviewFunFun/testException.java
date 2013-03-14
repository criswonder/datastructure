package interviewFunFun;

import javax.management.RuntimeErrorException;

public class testException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			if(true)
				throw new RuntimeErrorException(new Error());
		}catch(RuntimeException ex){
			System.out.println(1);
			return;
		}catch(Exception ex){
			System.out.println("2");
		}finally{
			System.out.println(3);
		}
	}

}
