
import java.awt.Color;
import graphics.MazeCanvas;

public class EntryCell extends EdgeCell{
       public static final Color color = Color.red;
    
    public EntryCell(MazeCanvas mazeCanvas, int row, int col) {
           super(mazeCanvas, row, col);
           canvas.drawShade(row, col, color);
           
}
}
