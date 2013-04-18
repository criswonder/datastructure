package test.random;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class GetRandomLongitudeAndLatitude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// double lon = 120.23433;
		// double lat = 30.25544;
		// Random random = new Random();
		// double val= random.nextDouble();
		// int intval = random.nextInt(10);
		for (int i = 0; i < 10; i++) {
			System.out.println(getRandomCoord());
		}
		// System.out.println(val);
		// System.out.println(intval);
//		String time="2012-12-09 12:00:09";
//		System.out.println(time.substring(5,16));
	}

	private static String getRandomCoord() {
		double lon = 120.23433;
		double lat = 30.25544;
		Random random = new Random();
		double val = random.nextDouble();
		int intval = random.nextInt(10);
		if (intval % 2 == 0) {
			lon = lon - intval + val;
			lat = lat - intval + val;
		} else {
			lon = lon + intval - val;
			lat = lat + intval - val;
		}
		BigDecimal blon = new BigDecimal(lon);
		blon = blon.setScale(5, RoundingMode.HALF_UP);
		BigDecimal blat = new BigDecimal(lat);
		blat = blat.setScale(5, RoundingMode.HALF_UP);

		String string = blat.doubleValue() + " " + blon.doubleValue();
		return string;
	}

}
