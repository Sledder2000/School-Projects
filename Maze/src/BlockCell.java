import graphics.MazeCanvas;

import java.awt.Color;
public class BlockCell extends ShadedCell{

       public static final Color color = Color.LIGHT_GRAY;
       
       public BlockCell (MazeCanvas mazeCanvas, int row, int col) {
              super (mazeCanvas, row, col, color);
       }
       public boolean getVisited() {
    	   return true;
       }
}
