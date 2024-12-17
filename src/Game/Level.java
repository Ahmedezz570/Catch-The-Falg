package Game;

import javax.media.opengl.GL;
import java.util.ArrayList;

public class Level {

    ArrayList<Ball>balls;

    ArrayList<flag>flags;
    entity e;
    entity e2;

    int level;

    public Level(ArrayList<Ball> balls, ArrayList<flag> flags, entity e, entity e2, int level) {
        this.balls = balls;
        this.flags = flags;
        this.e = e;
        this.e2 = e2;
        this.level = level;
    }

    public Level(ArrayList<Ball> balls, ArrayList<flag> flags, entity e, int level) {
        this.balls = balls;
        this.flags = flags;
        this.e = e;
        this.level=level;
    }

    public Level(ArrayList<Ball> balls, int level) {
        this.balls = balls;
        this.level=level;
    }

    public void init(GL gl, int [] textures,int whatdraw){
        if(level == 1 ) {
            for (int i = 0; i < balls.size(); i++) {
                Ball b = balls.get(i);
                b.drawSprite(gl, b.x, b.y, textures.length - 5, 4, 4);
                if (i == 1 || i == 5)
                    continue;
                if (i % 2 == 0)
                    b.animation();
                else
                    b.Hori_animation();
            }
            for (flag f : flags) {
                f.drawSprite(gl, f.x, f.y, f.textureNumber, 6, 6);
            }
            e.holdingFlag();
            e.resturnTheFlag(balls);
        }
        if(level == 2){
            for (int i = 0; i < balls.size(); i++) {
                Ball b = balls.get(i);
                b.drawSprite(gl, b.x, b.y, textures.length - 5, 4, 4);
                if (i == 1 || i == 5)
                    continue;
                if (i % 2 == 0 && i !=4)
                    b.animation();
                else
                    b.Hori_animation();
            }
            for (flag f : flags) {
                f.drawSprite(gl, f.x, f.y, f.textureNumber, 6, 6);
            }
            e.holdingFlag();
            e.resturnTheFlag(balls);

//            e.resturnTheFlag1(balls);
//            e.holdingFlag1();
        }
        if(whatdraw == 30 || whatdraw==31){
            System.out.println("here");
            e2.holdingFlag1();
         //   e2.scoreIncrement();
            e2.resturnTheFlag1(balls);
        }

    }
    public void create (int [] textures){
        if(level == 1) {
            for (int i = 0; i < 8; i++) {
                if (i < 4) {
                    if (i % 2 == 0) {
                        Ball b = new Ball(true, 21, 20, 80, (i == 2) ? 80 : 25, false,
                                false, 80, (i == 2) ? 80 : 25, textures.length - 5, "red");
                        balls.add(b);
                    } else {
//                    Ball b=new Ball((i==1)?55 :75,50,textures.length-5);
                        Ball b = new Ball(true, 21, 20, (i == 1) ? 55 : 75, 50, false,
                                false, (i == 1) ? 55 : 75, 50, textures.length - 5, "red");
                        balls.add(b);
                    }
                } else {
                    if (i % 2 == 0) {
//                    Ball b=new Ball(20,(i==6)? 80:25,textures.length-6);
                        Ball b = new Ball(true, 21, 20, 20, (i == 6) ? 80 : 25, false,
                                false, 20, (i == 6) ? 80 : 25, textures.length - 6, "blue");
                        balls.add(b);
                    } else {
//                    Ball b=new Ball((i==5)?45 : 25,50,textures.length-6);
                        Ball b = new Ball(true, 21, 20, (i == 5) ? 45 : 25, 50, false,
                                false, (i == 5) ? 45 : 25, 50, textures.length - 6, "blue");
                        balls.add(b);
                    }
                }
            }
        }
        if(level == 2) {
            for (int i = 0; i < 10; i++) {
                if (i < 5) {
                    if (i==2 || i==0) { //0 ,2

                        Ball b = new Ball(true, 21, 20, 80, (i == 2) ? 80 : 25, false,
                                false, 80, (i == 2) ? 80 : 25, textures.length - 5, "red");
                        balls.add(b);
                    }
                    else if (i == 3 || i == 4) {
                        Ball b = new Ball(true, 21, 20, 85, (i == 3) ? 40 : 60, false,
                                false, 85, (i == 3) ? 40 : 60, textures.length - 5, "red");
                        balls.add(b);
                    } else {
                        Ball b = new Ball(true, 21, 20, 55, 50, false,
                                false, 55, 50, textures.length - 5, "red");
                        balls.add(b);
                    }
                }
                else {
                    if (i % 2 == 0) {//6 , 8
//                    Ball b=new Ball(20,(i==6)? 80:25,textures.length-6);
                        Ball b = new Ball(true, 21, 20, 25, (i == 6) ? 80 : 25, false,
                                false, 20, (i == 6) ? 80 : 25, textures.length - 6, "blue");
                        balls.add(b);
                    } else { // 5, 7, 9
                        if (i == 7 || i == 9) {
                            Ball b = new Ball(true, 21, 20, 15, (i == 7) ? 40 : 60, false,
                                    false, 20, (i == 7) ? 40 : 60, textures.length - 6, "blue");
                            balls.add(b);
                        } else {
                            Ball b = new Ball(true, 21, 20, 45, 50, false,
                                    false, 45, 50, textures.length - 6, "blue");
                            balls.add(b);
                        }
                    }
                }
            }
        }
    }

    }

