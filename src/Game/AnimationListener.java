package Game;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.media.opengl.GLEventListener;

public abstract class AnimationListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

    protected String assetsFolderName = "Assets//";

}
