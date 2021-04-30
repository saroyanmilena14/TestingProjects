package testProjects;

import org.testng.Assert;
import org.testng.annotations.Test;

import static Calculatorr.Calculator.*;

public class testingCalculator  {


    @Test
    public void testMul () {
       double actualresult= mult(3.4,5.6);
       Assert.assertEquals(actualresult, 19.04);
               
    }

    @Test
    public void testMulTrue() {
        double actualresult= pow(5, 6);
        double expectedresult= 15625;
        Assert.assertTrue(checkEquality(actualresult,expectedresult));

    }
    public boolean checkEquality(double i, double j) {
        return i==j;
    }

    @Test
    public void testDiv() {
        double actualresult= div(9,2);
        Assert.assertEquals(actualresult, 4.5);

    }

    @Test
    public void testDiv1() {
        long actualresult= (long) div(7, 0.0000000001);
        Assert.assertEquals(actualresult, 70000000000L );

    }

    @Test
    public void testDiv2() {
        double actualresult= div(56.7, 0);
        Assert.assertEquals(actualresult, Double.POSITIVE_INFINITY);
    }

    @Test
    public void testDiv3() {
        double actualresult= div(-7, 0);
        Assert.assertEquals(actualresult, Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testPow() {
        double actualresult = pow(8.9,3);
        Assert.assertEquals(actualresult, 704.969);
    }

    @Test
    public void testPow1() {
        double actualresult= pow(56,0);
        Assert.assertEquals(actualresult, 1);

    }

    @Test
    public void testPow2() {
        double actualresult= pow(3,-2);
        Assert.assertEquals(actualresult, 0.1111111111111111);
    }

    @Test
    public void testPow3() {
        double actualresult= pow(4, -0.5);
        Assert.assertEquals(actualresult, 0.5);
    }

    @Test
    public void testPow4() {
        long actualresult= (long) pow(67, 765);
        Assert.assertEquals(actualresult, 9223372036854775807L);
    }
}
