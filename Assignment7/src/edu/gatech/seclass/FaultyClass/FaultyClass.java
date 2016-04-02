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
        while (luckDraw<b){
            a--;
            luckDraw++;
        }
        return luckDraw/a;
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

    public int method4(int x, int y){
        int finalT = 0;
        if (x>y) {
            finalT = x/(y-1);
        } else {
            finalT=y;
        }
        return finalT;
    }

}
