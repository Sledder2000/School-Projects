import graphics.MazeCanvas.Side;
import graphics.MazeCanvas;
import java.util.ArrayList;
import java.awt.Color;
public class Generator {
	
	public MazeCanvas mazeCanvas = new MazeCanvas();
	public Maze maze = new Maze(mazeCanvas);
	final public Color generatePathColor = Color.BLUE;
	
	public Generator(MazeCanvas mc, Maze maze) {
		this.mazeCanvas = mc;
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
		return Side.Center;
	}
	
	private boolean run(Cell cell, Side side) {
		cell.visited = true;
		mazeCanvas.drawPath(cell.getRow(), cell.getCol(), side, generatePathColor);
		mazeCanvas.drawPath(cell.getRow(), cell.getCol(), Side.Center, generatePathColor);
		mazeCanvas.eraseWall(cell.getRow(), cell.getCol(), side);
		cell.removeWall(side);
		for (Side s : shuffle(cell.getWalls())) {
			Cell temp = maze.getNeighbor(cell, s);
			if(temp == null) {
			    } if (!temp.getVisited()) {
				mazeCanvas.drawPath(cell.getRow(), cell.getCol(), s, generatePathColor);
				cell.removeWall(s);
				mazeCanvas.eraseWall(cell.getRow(), cell.getCol(), s);
				run(temp, getOpposite(s));
				mazeCanvas.erasePath(cell.getRow(), cell.getCol(), s);
			}
		}
		mazeCanvas.erasePath(cell.getRow(), cell.getCol(), side);
		mazeCanvas.erasePath(cell.getRow(), cell.getCol(), Side.Center);
		return false;
	} 
	
	public boolean run() {
		return run(maze.getEntryCell(), Side.Center);
	}
}
