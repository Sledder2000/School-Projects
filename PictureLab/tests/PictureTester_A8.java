import java.util.Scanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("resource")
class PictureTester_A8 {
    
    private void AssertVisual(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message + "? ");
        System.out.print("Yes/No> ");
        String answer = input.nextLine();
        Assert.assertTrue("Test failed!", answer.equalsIgnoreCase("Yes"));
    }
    
    /** Method to test mirrorCollage*/
    @Test
    public void testCollage() {
        Picture canvas = new Picture("640x480.jpg");
        canvas.createCollage();
        canvas.explore();
        AssertVisual("Looks like a default collage");
    }
    
    /** Method to test copy overload */
    @Test
    public void testCopy() {
        Picture canvas = new Picture("640x480.jpg");
        Picture seagull = new Picture("seagull.jpg");
        seagull.explore();
        canvas.copy(seagull, 226, 226, 331, 357, 200, 20);
        canvas.copy(seagull, 226, 226, 331, 357, 20, 200);
        canvas.explore();
        AssertVisual("Looks like two seagulls copied into canvas");
    }
    
    /** Method to test myCollage */
    @Test
    public void testMyCollage() {
        Picture canvas = new Picture("640x480.jpg");
        canvas.myCollage();
        canvas.explore();
        AssertVisual("Looks like your own collage");
    }
}
