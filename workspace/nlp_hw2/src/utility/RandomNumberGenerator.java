package utility;

import java.util.Random;

public class RandomNumberGenerator {
	
	/**
	 * return a random integer from 0 (inclusive) to n (exclusive).
	 * use system time as seed
	 * @param to : upper boundary of the random
	 * @return : a random integer
	 */
	public static int randomIntTo(int to){
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Random generator = new Random(System.currentTimeMillis());
		int rand = Math.abs(generator.nextInt());
		return rand % to;
	}
}