import java.awt.Color;
import graphics.MazeCanvas;

public class ExitCell extends EdgeCell{
       public static final Color color = Color.green.darker();
    
    public ExitCell(MazeCanvas mazeCanvas, int row, int col) {
           super(mazeCanvas, row, col);
           canvas.drawShade(row, col, color);
}
}
