import java.util.Scanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("resource")
class PictureTester_A5 {
    
    private void AssertVisual(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message + "? ");
        System.out.print("Yes/No> ");
        String answer = input.nextLine();
        Assert.assertTrue("Test failed!", answer.equalsIgnoreCase("Yes"));
    }
    
    /** Method to test zeroBlue */
    @Test
    public void testZeroBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
        AssertVisual("Blue color eliminated from the image");
    }
    
    /** Method to test keepOnlyBlue */
    @Test
    public void testKeepOnlyBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
        AssertVisual("Image has only blue color");
    }
    
    /** Method to test negate */
    @Test
    public void testNegate() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.negate();
        beach.explore();
        AssertVisual("Image looks like on negative film");
    }
    
    /** Method to test grayscale */
    @Test
    public void testGrayscale() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.grayscale();
        beach.explore();
        AssertVisual("Image has no color (is grayscale)");
    }

    /** Method to test fixUnderwater */
    @Test
    public void testFixUnderwater() {
        Picture water = new Picture("water.jpg");
        water.explore();
        water.fixUnderwater();
        water.explore();
        AssertVisual("Fish are visible under water");
    }
}
