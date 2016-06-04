
package jrcengine.StartManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Game;
import jrcengine.Interface.Abstract_Screen;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.NetworkModule.NetworkModule;

public class Game_Start_Manager extends GL_Game {
    boolean firstTimeCreate = true;

    public Abstract_Screen getStartScreen() {
        return new Screen_StartLogo(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            //NetworkModule.startClient();
            Manage_Settings.load(getFileIO());
            Manage_Assets.load(this);
            
            firstTimeCreate = false;
            
            
        } else {
            Manage_Assets.reload();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // if(Manage_Settings.soundEnabled)
        // Manage_Assets.music.pause();
    }

}
