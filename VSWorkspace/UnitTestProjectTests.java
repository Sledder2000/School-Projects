package VSWorkspace;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestProjectTests {

    private UnitTestProject moneyTest;

    @Before
    public void setupTests() {
        System.out.println("Before");
        moneyTest = new UnitTestProject(1000, 23);
    }


    @Test
    public void testConstructor() {
        System.out.println(moneyTest.getMoney());
        assertEquals(1000, moneyTest.getMoney());
        assertEquals(23, moneyTest.getSalary());
    }

    @Test
    public void testMoneySetter() {
        System.out.println("testMoneySetterConstructor");
        moneyTest.setMoney(2000);
        assertEquals(2000, moneyTest.getMoney());
    }

    @Test
    public void testSalarySetter() {
        System.out.println("testSalarySetterConstructor");
        moneyTest.setSalary(30);
        assertEquals(30, moneyTest.getSalary());
    }

    @Test
    public void isValidTest() {
        assertEquals(true, moneyTest.isValid(moneyTest.getMoney()));
        assertEquals(true, moneyTest.isValid(moneyTest.getSalary()));
    }

    @After
    public void cleanupTests() {
        System.out.println("cleanupTests");
    }
}
