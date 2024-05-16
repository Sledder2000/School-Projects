import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
public class Solver {
	public MazeCanvas mc;
	public Maze maze;
	public Color fwdPathColor = Color.red.darker();
	public Color bwdPathColor = Color.green;
	
	public Solver(MazeCanvas mc, Maze maze) {
		this.mc = mc;
		this.maze = maze;
	}
	
	public ArrayList<Side> shuffle(ArrayList<Side> Sides) {
		for (int i = Sides.size() - 1; i > 0; i--) {
			int j = (int) (Math.random() * i);
			Side temp = Sides.get(j);
			Side temp2 = Sides.get(i);
			Sides.set(i, temp);
			Sides.set(j, temp2);
		}
		return Sides;
	}
	
	public Side getOpposite(Side side) {
		if (side.equals(Side.Right)) {
			return Side.Left;
		} else if (side.equals(Side.Left)) {
			return Side.Right;
		} else if (side.equals(Side.Bottom)) {
			return Side.Top;
		} else if (side.equals(Side.Top)) {
		return Side.Bottom;
		}
		return side;
	}
	
	public boolean run(Cell cell, Side side) {
		cell.setVisited(true);
		mc.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
		mc.drawPath(cell.getRow(), cell.getCol(), side, fwdPathColor);
		if (cell.equals(maze.getExitCell())) {
			return true;
		}
		ArrayList<Side> sides = new ArrayList<Side>();
		sides = cell.getPaths();
		Collections.shuffle(sides);
		for (Side s : sides) {
			Cell temp = maze.getNeighbor(cell, s);
			if (temp.getVisited() == false) {
				mc.drawPath(cell.getRow(), cell.getCol(), s, fwdPathColor);
				mc.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
				boolean solved = run(temp, getOpposite(s));
				if (solved) {
					return true;
				} else {
				mc.drawPath(cell.getRow(), cell.getCol(), s, bwdPathColor);
				}
			}
		}
		mc.drawPath(cell.getRow(), cell.getCol(), side, bwdPathColor);
		mc.drawCenter(cell.getRow(), cell.getCol(), bwdPathColor);
		return false;
	}
	
	public boolean run() {
		for (int r = 0; r < mc.getRows(); r++) {
			for (int c = 0; c < mc.getCols(); c++) {
		
			maze.getCell(r, c).setVisited(false);
			
		}
	}
		return run(maze.getEntryCell(), Side.Center);
	}
}

