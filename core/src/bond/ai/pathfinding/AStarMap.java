package bond.ai.pathfinding;

import bond.generation.terraingen.TerrainType;

public class AStarMap {

    private Node[][] map;

    private final int width;
    private final int height;

    public AStarMap(TerrainType terrain[][]) {
        this.width = terrain.length;
        this.height = terrain[0].length;

        map = new Node[width][height];
        for (int x = 0; x < width; ++x) {
           for (int y = 0; y < height; ++y) {
        	   boolean isWall = false;
          	  if(terrain[x][y] == TerrainType.WATER) { //Collision is set here
          		 isWall = true;
          	  }
          	  map[y][x] = new Node(this, x, y, isWall);
           }
       }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Node getNodeAt(int x, int y) {
        return map[y][x];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
               stringBuilder.append(map[y][x].isWall ? "#" : " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}