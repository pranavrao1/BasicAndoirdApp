package edu.gatech.seclass.FaultyClass;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 4/1/16
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaultyClass {

    public int method1(int a, int b){
        int result = 0;
        if (a>b){
            result= a/(b-2);
        } else if (b>a) {
            result= b/(a-2);
        }
        return result;
    }

    public int method2(int a, int b){
        int luckDraw = 0;
        if(a>0 || b>0){
            luckDraw = 1/b;
        } else {
           luckDraw++;
        }
        return luckDraw;
    }

    public void method3(int random){

        boolean evader = true;
        if(evader){
            return;
        }
        // Dead code that is not reachable!
        // Will cause zero fault error
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
