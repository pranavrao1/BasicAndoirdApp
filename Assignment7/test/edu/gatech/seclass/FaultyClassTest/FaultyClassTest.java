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

    //Task 1
    @Test
    public void method1SC(){
        assertEquals(1, faultyClass.method1(true, false));
        assertEquals(1,faultyClass.method1(false,true));
    }

    @Test
    public void method1BC(){
        assertEquals(1, faultyClass.method1(true, false));
        assertEquals(1,faultyClass.method1(false,true));
    }

    @Test
    public void method1PC(){
        assertEquals(1, faultyClass.method1(true, false));
        assertEquals(1,faultyClass.method1(false,true));
        assertEquals(1, faultyClass.method1(true, true));
        assertEquals(0,faultyClass.method1(false,false));
    }

    //Task 2
    @Test
    public void method2PC(){
        faultyClass.method2();
    }

    @Test
    public void method2SC(){
        faultyClass.method2();
    }

    @Test
    public void method2BC(){
        while(true){
            faultyClass.method2();
        }
    }


}
