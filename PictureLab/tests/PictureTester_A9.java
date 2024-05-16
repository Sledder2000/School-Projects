import java.util.Scanner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("resource")
class PictureTester_A9 {
    
    private void AssertVisual(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message + "? ");
        System.out.print("Yes/No> ");
        String answer = input.nextLine();
        Assert.assertTrue("Test failed!", answer.equalsIgnoreCase("Yes"));
    }
    
    /** Method to test edgeDetection*/
    @Test
    public void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.explore();
        swan.edgeDetection(10);
        swan.explore();
        AssertVisual("Edge detected correctly");
    }
}
