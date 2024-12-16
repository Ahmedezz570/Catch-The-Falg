package Game;

import javax.media.opengl.GL;
import java.util.ArrayList;

class Ball{
    boolean onetime;
    int lower;
    int upper;
    double X_oldPosi;
    double Y_oldPosi;
    boolean increaseX;
    boolean increaseY;

    String color;

    public Ball(boolean onetime, int lower, int upper, double x_oldPosi, double y_oldPosi,
                boolean increaseX, boolean increaseY, double x, double y, int textureNumber,String color) {
        this.onetime = onetime;
        this.lower = lower;
        this.upper = upper;
        X_oldPosi = x_oldPosi;
        Y_oldPosi = y_oldPosi;
        this.increaseX = increaseX;
        this.increaseY = increaseY;
        this.x = x;
        this.y = y;
        this.textureNumber = textureNumber;
        this.color=color;
    }

//    public Ball(double x, double y, int textureNumber) {
//        this.x = x;
//        this.y = y;
//        this.textureNumber = textureNumber;
//
//    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale) {
        int MAX_WIDTH=100;
        int MAX_HEIGHT=100;

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[textureNumber]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
//        System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }


    public void ifblue( ){
        if(color == "blue"&& onetime){
            upper=-21;
            lower=-20;
            increaseX=true;
            onetime=false;
        }
    }
    public void animation() {
        double step=0.5;
        ifblue();
        if (!increaseX) {
            x-=step;
            if (!increaseY) {
                y-=step;
                if (y <= Y_oldPosi - 8 && y >=Y_oldPosi -9) {
                    increaseY = true;
//                    System.out.println(y+" "+ (Y_oldPosi - 8 )+" "+(Y_oldPosi -9));
                }
            }
            else {
                y+=step;
                if (y <= Y_oldPosi+0.75 && y >= Y_oldPosi-0.75  ) {
                    increaseY = false;
                }
            }

        }
        else {
            x+=step;
            if (!increaseY) {
                y-=step;
                if (y <= Y_oldPosi - 7 && y >=Y_oldPosi -8) {
                    increaseY = true;
                }
            }
            else {
                y+=step;
                if (y <= Y_oldPosi+0.75 && y >= Y_oldPosi-0.75  ) {
                    increaseY = false;
                }
            }

        }
        if (x<= X_oldPosi-upper &&x>= X_oldPosi-lower ){
            increaseX=(color=="blue")? false :true;
            System.out.println("hello");
        }

        if (x>= X_oldPosi-0.75 &&x<= X_oldPosi+0.75){
            increaseX=(color=="blue")? true :false;;
        }
    }
    public void Hori_animation(){
       
        double step=0.4;
        ifblue();

        if(!increaseX)
            x-=step;

        if(x<=X_oldPosi-upper && x >= X_oldPosi-lower ) {
            increaseX= (color == "blue")? false:true;
        }

        if(increaseX)
            x+=step;

        if(x<=X_oldPosi+0.5 && x >= X_oldPosi-0.5 )
            increaseX= (color == "blue")? true:false;
        System.out.println(upper+" "+ lower);

    }
    public void Vert_animation(){
     
        double step=0.6;
        if(!increaseY)
            y-=step;

        if(y<=Y_oldPosi-10 && y >= Y_oldPosi-11 )
            increaseY=true;

        if(increaseY)
            y+=step;

        if(y<=Y_oldPosi+0.5 && y >= Y_oldPosi-0.5 )
            increaseY=false;

    }
    double x,y;

    int textureNumber;
}