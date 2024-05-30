import java.util.ArrayList;
import java.awt.Color;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Cell {
       
       public int row;
       public int col;
       public MazeCanvas canvas;
       public ArrayList<Side> listOfWalls = new ArrayList<Side>();
       boolean visited = false;
       
       public String toString() {
    	   if (this == null) {
    	   return "null";
    	   }
    	   return "cell";
       }
       
       public Cell(MazeCanvas mazeCanvas, int row, int col) {
              this.canvas = mazeCanvas;
              this.row = row;
              this.col = col;
              listOfWalls.add(Side.Top);
              listOfWalls.add(Side.Bottom);
              listOfWalls.add(Side.Left);
              listOfWalls.add(Side.Right);
              canvas.drawCell(row, col);
              
       }
       
       public ArrayList<Side> getPaths() {
    	   ArrayList<Side> listOfPaths = new ArrayList<Side>();
    	   listOfPaths.add(Side.Top);
    	   listOfPaths.add(Side.Bottom);
    	   listOfPaths.add(Side.Left);
    	   listOfPaths.add(Side.Right);
    	   for (Side s : listOfWalls) { 
    				   listOfPaths.remove(s);
    		   }
    	   
    	   return listOfPaths;
       }
       
       public int getRow() {
              return row;
       }
       
       public int getCol() {
              return col;
       }
       
       public ArrayList<Side> getWalls() {
              ArrayList<Side> temp = new ArrayList<Side>();
              for (Side s : listOfWalls) {
                     temp.add(s);
              }

              return temp;
       }
       
       public void removeWall(Side side) {
              canvas.eraseWall(row, col, side);
              for (int r = 0; r < listOfWalls.size(); r++) {
                     if (listOfWalls.get(r).equals(side)) {
                           listOfWalls.remove(r);
                     }
              }
       }
       
       public boolean getVisited() {
    	   return visited;
       }
       
       public void setVisited(boolean yes) {
    	   this.visited = yes;
       }
}
