package edu.gatech.seclass.FaultyClassTest;

import edu.gatech.seclass.FaultyClass.FaultyClass;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/1/16
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaultyClassTest {

    private FaultyClass faultyClass = new FaultyClass();

    @Test
    public void method1SC(){
        assertEquals(3, faultyClass.method1(6, 4));
        assertEquals(4,faultyClass.method1(4,8));
    }

    @Test
    public void method1BC(){
        assertEquals(3, faultyClass.method1(6, 4));
        assertEquals(4,faultyClass.method1(4,8));
        assertEquals(0,faultyClass.method1(1,1));
    }

    @Test
    public void method1PC(){
        assertEquals(0,faultyClass.method1(4,2));
        assertEquals(0,faultyClass.method1(2,4));
    }

    //Task 2
    @Test
    public void method2PC(){
        assertEquals(1,faultyClass.method2(1,1));
        assertEquals(1,faultyClass.method2(-1,1));
        assertEquals(1,faultyClass.method2(-1,-11));
    }

    @Test
    public void method2SC(){
        assertEquals(1,faultyClass.method2(1,1));
        assertEquals(1,faultyClass.method2(-1,-11));
    }

    @Test
    public void method2BC(){
        assertEquals(1,faultyClass.method2(-1,-1));
        assertEquals(0,faultyClass.method2(-1,11));
        assertEquals(1,faultyClass.method2(11,0));
    }


}
