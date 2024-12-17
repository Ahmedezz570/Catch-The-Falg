    package Game;

    import com.sun.opengl.util.j2d.TextRenderer;

    import java.awt.*;
    import java.util.ArrayList;

    public class  entity {

        Ball b;
        flag f;
        TextRenderer textRenderer = new TextRenderer(Font.decode("PLAIN 100"));
        int maxScore = 3;
        int temp = 0;
        boolean score3 =false;




        public entity(Ball b, flag f) {
            this.b = b;
            this.f = f;
        }


        public double sqrdDistance(double x, double y, double x1, double y1) {
            return Math.pow(x - x1, 2) + Math.pow(y - y1, 2);
        }

        public boolean areTheyClose(double x, double y, double x1, double y1) {
            if (sqrdDistance(x, y, x1, y1) <= 20) {
                return true;
            }
            return false;
        }
        int score = 0;
        int score2 =0;
        public void holdingFlag() {
            if (areTheyClose(b.x, b.y, f.x, f.y) ) {
                f.x = b.x;
                f.y = b.y;
            }
            if (b.x > 50 ) {
                if (b.x > 50 && score < maxScore) {
                    if (f.x > 50) {
                        score++;
                    }
                    f.x = 5;
                    f.y = 50;
                }

                TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));
                textRenderer.beginRendering(700, 700);
                textRenderer.draw("" + score, (int) 102, (int) 642);
                textRenderer.endRendering();
            }
        }

            public void holdingFlag1 () {
                if (areTheyClose(b.x, b.y, f.x, f.y)) {
                    f.x = b.x;
                    f.y = b.y;
                }
                if (b.x < 50)
                {
                    if (f.x < 50){
                    f.x = 95;
                    f.y = 50;
                    score3 =true;
                    score2++;
                    }
                }
                TextRenderer textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));
                textRenderer.beginRendering(700, 700);
                textRenderer.draw("" + score2, (int) 640, (int) 642);
                textRenderer.endRendering();
                System.out.println(f.x);
            }
//        public void scoreIncrement() {
//            if (score3) {
//                score2++;
//                score3 = false; // Reset after increment
//                System.out.println("Score2 incremented to: " + score2);
//            } else {
//                System.out.println("Score3 is not set. No increment.");
//            }
//        }

        public void resturnTheFlag1(ArrayList<Ball> balls){
            for(Ball b1: balls) {
                if (b1 == b) continue;
                if (areTheyClose(b.x, b.y, b1.x, b1.y)) {
                    f.x = 95;
                    f.y = 50;
                }
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
