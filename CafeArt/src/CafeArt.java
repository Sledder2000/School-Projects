import graphics.Graphics;

//Cafe art draws a cafe wall illusion
//Period 7
//10-18-21
//Benji Lelivelt
public class CafeArt {
	
	public static int Morter = 2;

	public static void main(String[] args) {
		Graphics.cafeWall.open();
		
		//start collecting the area
		double drawnArea = 0.0;
		//draw 1st row
		drawnArea += printRows(0, 0, 4, 20);
		//draw 2nd row
		drawnArea += printRows(50, 70, 5, 30);
		//draw 1st grid
		drawnArea += gridRows(10, 150, 4, 25, 0);
		//find area of entire canvas
		drawnArea += gridRows(250, 200, 3, 25, 10);
		drawnArea += gridRows(425, 180, 5, 20, 10);
		drawnArea += gridRows(400, 20, 2, 35, 35);
		//print total area covered
		double wallArea = (650 * 400);
		double percentage = drawnArea / wallArea;
		percentage = Math.round(percentage * 10000);
		percentage = (percentage / 100);
		System.out.println("Percentage of drawn area: " + (percentage  + "%"));
		Graphics.cafeWall.pause();
		Graphics.cafeWall.close();

	}
	/** Method that prints rows
	 * 
	 * @param x starting position
	 * @param y starting position
	 * @param numPairs number of pairs to draw
	 * @param boxSize each boxes width and height
	 * @return the total area drawn as a double
	 */
	public static double printRows(int x, int y, int numPairs, int boxSize) {
		for (int i = 0; i < numPairs; i++) {
		Graphics.cafeWall.drawDarkSquare(x, y, boxSize);
		Graphics.cafeWall.drawBrightSquare(x + boxSize, y , boxSize);
		x += (boxSize * 2);
		}
		//return the area print
		return ((boxSize * boxSize) * 2  * numPairs) ; 
	}
	//Method that prints grid
	public static double gridRows(int x, int y, int numPair, int boxSize, int offSet) {
		double rowsArea = 0.0;
		for (int i = 0; i < numPair; i++) {
			rowsArea += printRows(x, y, numPair, boxSize);
			y += boxSize + Morter;
			rowsArea += printRows(x + offSet, y, numPair, boxSize);
			y += (boxSize + Morter); 
		}
		return rowsArea;
	}
}
