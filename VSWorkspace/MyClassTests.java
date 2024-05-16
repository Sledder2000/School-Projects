package VSWorkspace;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyClassTests {

    private MyClass myObj;

    @Before
    public void setupTests() {
        System.out.println("Before"); 
        myObj = new MyClass(30);
    }

    @Test
    public void testContructor() {
        System.out.println("testConstructor");

        System.out.println(myObj.getValue());
        assertEquals(30, myObj.getValue());
    }

    @Test
    public void testSetter() {
        System.out.println("testSetterConstructor");
        myObj.setValue(33);
        assertEquals(33, myObj.getValue());
    }

    @After
    public void cleanupTests() {
        System.out.println("cleanupTests");
    }
}
