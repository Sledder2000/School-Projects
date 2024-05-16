import java.util.Scanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("resource")
class PictureTester_A7 {
    
    private void AssertVisual(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message + "? ");
        System.out.print("Yes/No> ");
        String answer = input.nextLine();
        Assert.assertTrue("Test failed!", answer.equalsIgnoreCase("Yes"));
    }
    
    /** Method to test mirrorTemple */
    @Test
    public void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
        AssertVisual("Roof of the temple is repaired");
    }
    
    /** Method to test mirrorTemple count calculation */
    @Test
    public void testMirrorTempleCount() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        int count = temple.mirrorTemple();
        temple.explore();
        Assert.assertEquals(18410, count);
    }
    
    /** Method to test mirrorArms */
    @Test
    public void testMirrorArms() {
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
        snowman.mirrorArms();
        snowman.explore();
        AssertVisual("Arms of the snowman are mirrored");
    }
    
    /** Method to test mirrorGull */
    @Test
    public void testMirrorGull() {
        Picture snowman = new Picture("seagull.jpg");
        snowman.explore();
        snowman.mirrorGull();
        snowman.explore();
        AssertVisual("Two seagulls on the beach, mirrored");
    }
}
