
import java.util.List;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Program {

       public static void main(String[] args) {
              // TODO Auto-generated method stub
              
              final int numRows = 20;
              final int numCols = 30;
              final int cellSize = 25;
              MazeCanvas canvas = new MazeCanvas(numRows, numCols, cellSize);
              Maze maze = new Maze(canvas);
              Generator gen = new Generator(canvas, maze);
              canvas.open();
              maze.initialize();
              gen.run();
              Solver solv = new Solver(canvas, maze);
              solv.run();
              canvas.pause();
              Cell cell = maze.getCell(0, 0);
              List<Side> paths = cell.getPaths();
              canvas.pause();
              canvas.close();
              
              
       }

}
