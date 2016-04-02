package edu.gatech.seclass.FaultyClass;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/1/16
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaultyClass {

    public int method1(boolean a, boolean b){
        int value = 0;
        if(!a){
            value++;
        }

        if(!b){
            value--;
        }

        return value/value;
    }

    public void method2(){
        // This method cannot exist. If you have a 100% path coverage test, this would mean every path in the code has been covered.
        // This should result in finding the fault. If it does not and

        Long time = System.currentTimeMillis();
        String value = time.toString();
        String end = value.substring(9,12);
        Integer integer = Integer.parseInt(end);
        int luckDraw = 1/(integer-50);
    }

    public void method3(int random){

        boolean evader = true;
        if(evader){
            return;
        }
        // Dead code that is not reachable!
        int c = 5/0;
    }

    public void method4(){
        /**
        This method cannot exist. If you have a 100% branch test coverage, this would mean every branch in the
         code has been tested. Branch coverage is a super set of statement coverage, this means every statement
         has been covered by these tests.
         Hence it is impossible for statement coverage to reveal a fault and branch coverage to fail.
         **/
    }

}
