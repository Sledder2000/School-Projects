import java.awt.Color;
import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell{

       public static final Color edgeColor = Color.YELLOW;
       public ArrayList<Side> listOfEdges = new ArrayList<Side>();
       
       public EdgeCell(MazeCanvas mazeCanvas, int row, int col) {
              super(mazeCanvas, row, col, edgeColor);
              if (row == 0) {
  				listOfEdges.add(Side.Top);
  			} 
              if (row == mazeCanvas.getRows()  - 1){
  				listOfEdges.add(Side.Bottom);
  			}
  			
  			if (col == 0) {
  				listOfEdges.add(Side.Left);
  			} 
  			if (col == mazeCanvas.getCols() - 1){
  				listOfEdges.add(Side.Right);
  			}  
   
           }
       
       public ArrayList<Side> getWalls() {
              ArrayList<Side> walls = super.getWalls();
                      for (int s = 0; s < listOfEdges.size(); s++) {
                           walls.remove(listOfEdges.get(s));
                     }
                     return walls;
                     
       }
       
       public ArrayList<Side> getPaths() {
    	   ArrayList<Side> listOfPaths = super.getPaths();
    	   for (Side s : listOfEdges) {
    		   for (Side i : listOfPaths) {
    			   if (s.equals(i)) {
    				   listOfPaths.remove(i);
    			   }
    		   }
    	   }
    	   return listOfPaths;
       }
}