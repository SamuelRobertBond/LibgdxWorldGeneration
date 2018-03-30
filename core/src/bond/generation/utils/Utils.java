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
	
	public static Quadrant[] createQuadrants(int q_count, int width, int height){
		
		Quadrant[] points = new Quadrant[q_count];
		int div = (int)Math.sqrt(q_count);
		
		int count = 0;
		
		for(int x = 0; x < div; ++x){
			for(int y = 0; y < div; ++y){
				points[count] = new Quadrant( (int)(width * ((float)x / (float)div)), (int)(height * ((float)y / (float)div)), width/div, height/div);
				++count;
			}
		}
		
		return points;
		
	}
	
}
