package VSWorkspace;
import java.lang.Math;
public class UnitTestProject {
    private int money;
    private int salary;

    public UnitTestProject(int _money, int _salary) {
        money = _money;
        salary = _salary;
    }

    public int getMoney() {
        return money;
    }

    public int getSalary() {
        return salary;
    }

    public void setMoney(int _money) {
        money = _money;
    }

    public void setSalary(int _salary) {
        salary = _salary;
    }
       
    public boolean isValid(int num) {
        boolean valid = false;
        if (Math.floor(num) > -1) {
            valid = true;
        }
        return valid;
    }


}



