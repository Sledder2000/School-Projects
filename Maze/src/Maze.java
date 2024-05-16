import java.awt.Color;
import java.util.Random;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Maze {
       	  
          public Cell enter;
          public Cell exit;
          Random rand = new Random();
       private MazeCanvas _mc;
       public Cell[][] gridOfCells;

    public Maze(MazeCanvas _mc) {
     this._mc = _mc;    
    Cell[][] grid = new Cell[_mc.getRows()][_mc.getCols()];
    gridOfCells = grid;
    }
    
    public void initialize() {
       double count = 0.0;
       int perims = 2 * _mc.getRows() + 2 * _mc.getCols() - 4;
       int iEntry = (int) (Math.random() * perims);
       int iExit = (int) (Math.random() * perims);
       int edgeCount = 0;
       for (int r = 0; r < gridOfCells.length; r++) {
            for (int c = 0; c < gridOfCells[r].length; c++) {
            count += 0.05;
            if (r == 0 || r == gridOfCells.length - 1 || c == 0 || c == gridOfCells[r].length - 1) {
            if (edgeCount == iEntry) {
                     gridOfCells[r][c] = new EntryCell(_mc, r, c);
                     enter = gridOfCells[r][c];
            } else if (edgeCount == iExit) {
                    gridOfCells[r][c] = new ExitCell(_mc, r, c);
                    exit = gridOfCells[r][c];
            } else {
                    gridOfCells[r][c] = new EdgeCell(_mc, r, c);
            } 
              edgeCount++;
            }
            if (r != 0 && r != gridOfCells.length - 1 && c != 0 && c != gridOfCells[r].length - 1 && Math.random() <= 0.05 && count > 0) {
                    gridOfCells[r][c] = new BlockCell(_mc, r, c);
                    count -= 1.0;
            } else if (r != 0 && r != gridOfCells.length - 1 && c != 0 && c != gridOfCells[r].length - 1){
                    gridOfCells[r][c] = new Cell(_mc, r, c);
            }
             }
          }
       }
    
    public Cell getEntryCell() {
       return (Cell) enter;
    }
    public Cell getExitCell() {
       return (Cell) exit;
    }
    
    
    public Cell getCell(int row, int col) {
       return gridOfCells[row][col];
    }
    
   
    public Cell getNeighbor(Cell cell, Side side) {
    	int row = cell.getRow();
    	int col = cell.getCol();
    	if (side == Side.Right) {
    		col++;
    	} else if(side == Side.Bottom) {
    		row++;
    	} else if(side == Side.Top) {
    		row--;
    	} else if(side == Side.Left) {
    		col--;
    	} else if (side == Side.Center) {
    		return cell;
    	}
    	if (col < 0 || row < 0 || col >= _mc.getCols() || row >= _mc.getRows()) {
    	return null;
    	}
    	return gridOfCells[row][col];
    }
    
    public void genSnake() {
          for (int r = 0; r < _mc.getRows(); r++) {
          for (int c = 0; c < _mc.getCols(); c++) {
                    _mc.drawCell(r, c);
                    if (r == 0) {
                           if (c % 2 == 1) {
                                  _mc.eraseWall(r, c, Side.Right);
                            _mc.eraseWall(r, c, Side.Bottom);
                            _mc.drawCenter(r, c, Color.red);
                            _mc.drawPath(r, c, Side.Right, Color.RED);
                            _mc.drawPath(r, c, Side.Bottom, Color.RED);
                           }
                           if (c % 2 == 0) {
                           _mc.eraseWall(r, c, Side.Left);
                           _mc.eraseWall(r, c, Side.Bottom);
                           _mc.drawPath(r, c, Side.Left, Color.RED);
                           _mc.drawPath(r, c, Side.Bottom, Color.RED);
                           _mc.drawCenter(r, c, Color.red);
                                   }
                            }
                    if (r == _mc.getRows() - 1) {
                           if (c % 2 == 1) {
                                  _mc.eraseWall(r, c, Side.Left);
                            _mc.eraseWall(r, c, Side.Top);
                            _mc.drawPath(r, c, Side.Left, Color.RED);
                            _mc.drawPath(r, c, Side.Top, Color.RED);
                            _mc.drawCenter(r, c, Color.red);
                           }
                           if (c % 2 == 0) {
                           _mc.eraseWall(r, c, Side.Right);
                           _mc.eraseWall(r, c, Side.Top); 
                           _mc.drawPath(r, c, Side.Right, Color.RED);
                           _mc.drawPath(r, c, Side.Top, Color.RED);
                           _mc.drawCenter(r, c, Color.red);
                    }
                  }
                    if (r > 0 && r < _mc.getRows() - 1) {
                           _mc.eraseWall(r, c, Side.Bottom);
                        _mc.eraseWall(r, c, Side.Top); 
                        _mc.drawCenter(r, c, Color.RED);
                        _mc.drawPath(r, c, Side.Top, Color.RED);
                        _mc.drawPath(r, c, Side.Bottom, Color.RED);
                    }
              }
       }
}
}
