package testProjects;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class beforeafter  {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeAfter2.beforeSuite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeAfter2.beforeClass");
    }

    @Test
    public void test1() {
        System.out.println("BeforeAfter2.test1");
    }

    @Test
    public void test2() {
        System.out.println("BeforeAfter2.test2");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("BeforeAfter2.afterClass");
    }
}


