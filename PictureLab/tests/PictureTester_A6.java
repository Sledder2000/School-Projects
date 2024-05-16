import java.util.Scanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("resource")
class PictureTester_A6 {
    
    private void AssertVisual(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message + "? ");
        System.out.print("Yes/No> ");
        String answer = input.nextLine();
        Assert.assertTrue("Test failed!", answer.equalsIgnoreCase("Yes"));
    }
    
    /** Method to test mirrorVertical */
    @Test
    public void testMirrorVertical() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
        AssertVisual("Image is mirrored vertically, left to right");
    }
    
    /** Method to test mirrorVerticalRightToLeft */
    @Test
    public void testMirrorVerticalRightToLeft() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVerticalRightToLeft();
        caterpillar.explore();
        AssertVisual("Image is mirrored vertically, right to left");
    }
    
    /** Method to test mirrorHorizontal */
    @Test
    public void testMirrorHorizontal() {
        Picture motorcycle = new Picture("redMotorcycle.jpg");
        motorcycle.explore();
        motorcycle.mirrorHorizontal();
        motorcycle.explore();
        AssertVisual("Image is mirrored horizontally, top to bottom");
    }

    /** Method to test mirrorHorizontalBotToTop */
    @Test
    public void testMirrorHorizontalBotToTop() {
        Picture motorcycle = new Picture("redMotorcycle.jpg");
        motorcycle.explore();
        motorcycle.mirrorHorizontalBotToTop();
        motorcycle.explore();
        AssertVisual("Image is mirrored horizontally, bottom to top");
    }
    
    /** Method to test mirrorDiagonal */
    @Test
    public void testMirrorDiagonal() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.mirrorDiagonal();
        beach.explore();
        AssertVisual("Image is mirrored diagonally, bottom left to top right");
    }
}
