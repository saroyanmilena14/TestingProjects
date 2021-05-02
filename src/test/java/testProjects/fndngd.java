package testProjects;

import org.testng.annotations.*;

public class fndngd {

@BeforeSuite
public void beforeSuite() {
        System.out.println("BeforeAfter2.beforeSuite");
        }

@BeforeClass
public void beforeClass() {
        System.out.println("BeforeAfter2.beforeClass");
        }
  @BeforeMethod
  public void beforMethod(){
          System.out.println("before each method");
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
