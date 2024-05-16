
import graphics.MazeCanvas;

import java.awt.Color;

public class ShadedCell extends Cell{
       
       public ShadedCell(MazeCanvas mazeCanvas, int row, int col, Color shade) {
              super(mazeCanvas, row, col);
              mazeCanvas.drawCell(row, col, shade);
       }

}
