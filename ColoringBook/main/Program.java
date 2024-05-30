package ColoringBook.main;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import ColoringBook.graphics.DrawingFrame;
import graph.Point;
import ColoringBook.graphics.Drawing;

public class Program {
    
    /**
     * Global static fields for the Drawing object being worked on
     * and the DrawingFrame containing and displaying it.
     */
    private static Drawing _drawing;
    private static DrawingFrame _frame;
    
    /**
     * Demonstrates a simple alteration to the drawing:
     * On a square section of the image, from top-left: (40,30) to bottom-right (140, 130)
     * replace the dark pixels with yellow and the bright pixels with yellow.
     */
    public static void paint() throws InterruptedException {
        for(int x = 40; x < 140; x++) {
            for (int y = 30; y < 130; y++) {
                _frame.step(1);
                if (_drawing.isDarkPixel(x, y)) {
                    _drawing.setPixel(x, y, Color.yellow);
                } else {
                    _drawing.setPixel(x, y, Color.red);
                }
            }
        }
    }
    
    /**
     * Main entry point in the program:
     * Initializes the static Drawing (_drawing) with an image of your choice,
     * then initializes the static DrawingFrame (_frame) loading into it the new drawing.
     * Subsequently the frame is opened on the screen then the drawing is painted upon
     * and displayed as it is being modified before the program terminates. 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Festival!");
        
        // pick a drawing
        _drawing = new Drawing("ColoringBook/drawings/bird.jpg");
        
        // put it in a frame
        _frame = new DrawingFrame(_drawing);

        // put the frame on display and stop to admire it.
        _frame.open();
        

        _frame.step();
        //RandomDrawRecursive(330, 280, Color.RED);
        for (int x = 0, y = 0; x <= _drawing.getWidth() && y <= _drawing.getHeight(); x++) {
            int rand = (int)(Math.random() * 10);
            Color c = Color.RED;
            switch (rand) {
                case 0:
                    c = Color.DARK_GRAY;
                    break;
                case 1:
                    c = Color.RED;
                    break; 
                case 2: 
                    c = Color.BLUE;
                    break;
                case 3:
                    c = Color.YELLOW;
                    break;
                case 4:
                    c = Color.ORANGE;
                    break;
                case 5:
                    c = Color.PINK;
                    break;
                case 6:
                    c = Color.GRAY;
                    break;
                case 7:
                    c = Color.GREEN;
                    break;
                case 8:
                    c = Color.CYAN;
                    break;
                case 9:
                    c = Color.MAGENTA;
                    break;
            }
            int checker = (int)(Math.random() * 2);
            if (checker == 0) {
                RandomDrawQueue(x, y, c);

            } else if (checker == 1) {
                RandomDrawStack(x, y, c);
            }
            if (x == _drawing.getWidth()) {
                y++;
                x = 0;
            }
        }
        // make some change to the drawing, then stop for applause.
        //paint();
        
        _frame.stop();
        
        // the show is over.
        //_frame.close();
        
        System.out.println("Well done, goodbye!");
    }

    public static void RandomDrawStack(int xCord, int yCord, Color Color) throws InterruptedException {
        Stack<Point> s = new Stack<Point>();
        Point p = new Point(xCord, yCord);
        s.push(p);
        while (!s.isEmpty()) {
            _frame.step(2);
            Point pCur = s.pop();
            for (int x = (int)pCur.getX() - 1, y = (int)pCur.getY() - 1; x < (int)pCur.getX() + 2; y++) {
                Point pTemp = new Point(x, y);
                if (_drawing.isValidPixel(x, y) && _drawing.isBrightPixel(x, y)) {
                    _drawing.setPixel(x, y, Color);
                    _frame.step();
                    s.push(pTemp);
                }
                if (y == (int)pCur.getY() + 1) {
                    x++;
                    y = (int)pCur.getY() - 1;
                }
        }
      }
    }
    public static void RandomDrawQueue(int xCord, int yCord, Color Color) throws InterruptedException {
        Queue<Point> q = new LinkedList<Point>();
        Point p = new Point(xCord, yCord);
        q.add(p);
        while (!q.isEmpty()) {
            _frame.step(2);
            Point pCur = q.remove();
            for (int x = (int)pCur.getX() - 1, y = (int)pCur.getY() - 1; x < (int)pCur.getX() + 2; y++) {
                Point pTemp = new Point(x, y);
                if (_drawing.isValidPixel(x, y) && _drawing.isBrightPixel(x, y)) {
                    _drawing.setPixel(x, y, Color);
                    _frame.step();
                    q.add(pTemp);
                }
                if (y == (int)pCur.getY() + 1) {
                    x++;
                    y = (int)pCur.getY() - 1;
                }
        }
      }
    }
    public static void RandomDrawRecursive(int xCord, int yCord, Color Color) throws InterruptedException {
        _frame.step(2);
        Point p = new Point(xCord, yCord);
            for (int x = (int)p.getX() - 1, y = (int)p.getY() - 1; x < (int)p.getX() + 2; y++) {
                Point pTemp = new Point(x, y);
                if (_drawing.isValidPixel(x, y) && _drawing.isBrightPixel(x, y)) {
                    _drawing.setPixel(x, y, Color);
                    _frame.step();
                    RandomDrawRecursive(x, y, Color);
                }
                if (y == (int)p.getY() + 1) {
                    x++;
                    y = (int)p.getY() - 1;
                }
        }
    }
}

    

