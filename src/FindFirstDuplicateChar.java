
public class FindFirstDuplicateChar {
	public static void main(String args[]) {
		String str = "maohongyun";

		for (int i = 0; i < str.length(); i++) {
			//*int java.lang.String.indexOf(int ch, int fromIndex)
			 //*@ch 从上面可以看出第一参数是int, 但是我下面是传的一个char。但是char和int是可以相互转换的，所以问题
			 //*@fromIndex 第二个参数是包含在内的。也就是说从第二个参数开始找起
			 //*/
			if (str.indexOf(str.charAt(i),i+1) != -1) {
				System.out.println("" + str.charAt(i));
				break;
			}
		}
	}
}