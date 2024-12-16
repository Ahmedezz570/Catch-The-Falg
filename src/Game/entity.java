    package Game;

    import com.sun.opengl.util.j2d.TextRenderer;

    import java.awt.*;
    import java.util.ArrayList;

    public class    entity {
        Ball b;
        flag f;
        TextRenderer textRenderer = new TextRenderer(Font.decode("PLAIN 100"));
        int maxScore = 3 ;
    int temp = 0 ;
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
        int score = 0;

        public void holdingFlag(){


            if(areTheyClose(b.x, b.y,f.x,f.y)) {

                f.x= b.x;
                f.y = b.y;
            }

            if(b.x>50 && score < maxScore) {
                if (f.x > 50){
                    score++;
                }
               f.x = 5;
                f.y= 50;
            }
            TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 20));
            textRenderer.beginRendering(700, 700);
            textRenderer.draw( "" +score,(int)  100,(int) 630);
            textRenderer.endRendering();
        }





//        public void holdingFlag1(){
//            if(areTheyClose(b.x,b.y,f.x,f.y)) {
//                f.x = b.x;
//                f.y = b.y;
//            }
//            if(b.x<50) {
//                f.x = 95;
//                f.y = 50;
//
//            }
//
//        }
//        public void resturnTheFlag1(ArrayList<Ball> balls){
//            Ball ball = balls.get(5);
//            for(Ball b: balls) {
//                if (b == ball) continue;
//                if (areTheyClose(b.x, b.y, ball.x, ball.y)) {
//                    f.x = 95;
//                    f.y = 50;
//                }
//            }
//        }
        public void resturnTheFlag(ArrayList<Ball>balls){

            for(Ball b1: balls) {
                if (b1 == b) continue;
                if (areTheyClose(b.x, b.y, b1.x, b1.y)) {
                    f.x= 5;
                    f.y = 50;
                }
            }
        }
        public int getScore() {
            return score;
        }
    }
