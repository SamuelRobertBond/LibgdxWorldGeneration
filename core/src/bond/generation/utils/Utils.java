package bond.generation.utils;

import java.util.Random;

public class Utils {

	public static final Random rand = new Random();
	
	public static void setSeed(long seed){
		rand.setSeed(seed);
	}
	
	/**
	 * Gets a random integer
	 * @param range - Exclusive
	 * @return
	 */
	public static int getRandomInt(int range){
		return rand.nextInt(range);
	}
	
	public static float getRandomFloat(){
		return rand.nextFloat();
	}
	
}
