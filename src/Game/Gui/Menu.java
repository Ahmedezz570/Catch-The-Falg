package Game.Gui;

import javax.media.opengl.GL;
import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;
import Game.AnimEventListener;

public class Menu {
    public int mute = 6;


int x = MAX_WIDTH/2, y = MAX_WIDTH/2-10;
int []indexMenu  = {0,1,2,3,4,5,6,7,8,9};


    public  void drawMenu(GL gl ) {

        AnimEventListener animEventListener = new AnimEventListener();
        animEventListener.drawBackground(gl);

        animEventListener.drawSprite(gl, MAX_WIDTH-5,  5,12,10 , 10);        
        animEventListener.drawSprite(gl, x, y+30, indexMenu[4],20 , 10);
        animEventListener.drawSprite(gl, x, y+18, indexMenu[5],20 , 10);
        animEventListener.drawSprite(gl, x, y+6, indexMenu[2],20 , 10);
        animEventListener.drawSprite(gl, x, y-6, indexMenu[3],20 , 10);
        animEventListener.drawSprite(gl, MAX_WIDTH-5, MAX_WIDTH-5, indexMenu[mute],5 , 5);

    }
    public void drawHowToPlay(GL gl ,int index){
        AnimEventListener animEventListener = new AnimEventListener();
        animEventListener.drawSprite(gl, MAX_WIDTH/2, MAX_HEIGHT/2, index,100 , 100);
        animEventListener.drawSprite(gl ,MAX_WIDTH-10 , 5 ,8,12,6 );
    }

}
