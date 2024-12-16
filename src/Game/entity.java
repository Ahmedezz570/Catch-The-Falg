package Game;

import java.util.ArrayList;

public class entity {
    Ball b;
    flag f;

    public entity(Ball b, flag f) {
        this.b = b;
        this.f = f;
    }
    public double sqrdDistance(double x, double y, double x1, double y1){
        return Math.pow(x-x1,2)+Math.pow(y-y1,2);
    }

    public boolean areTheyClose(double x, double y, double x1, double y1){
        if(sqrdDistance(x,y,x1,y1)<=20){
            return true;
        }
        return false;
    }
    public void holdingFlag(){

        if(areTheyClose(b.x, b.y,f.x,f.y)) {
            f.x= b.x;
            f.y = b.y;
        }
        if(b.x>50) {
           f.x = 5;
            f.y= 50;
        }
    }
    public void resturnTheFlag(ArrayList<Ball>balls){

        for(Ball b1: balls) {
            if (b1 == b) continue;
            if (areTheyClose(b.x, b.y, b1.x, b1.y)) {
                f.x= 5;
                f.y = 50;
            }
        }
    }
}
