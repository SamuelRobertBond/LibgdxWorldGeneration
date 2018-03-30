package bond.generation.utils;

public class Quadrant {

	public int x;
	public int y;
	
	public int centerX;
	public int centerY;
	
	public int width;
	public int height;
	
	public Quadrant(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.centerX = (width + x) / 2;
		this.centerY = (height + y) / 2;
		
	}
	
}
