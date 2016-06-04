
package jrcengine.MainGame;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Graphics;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;

public class MainGame_Renderer {

    private GL_Graphics glGraphics;

    private MainGame_Manager manage;

    private GL_Camera2D game_cam;

    private GL_SpriteBatcher batcher;

    public MainGame_Renderer(GL_Graphics glGraphics, GL_SpriteBatcher batcher,
            MainGame_Manager manage) {

        this.glGraphics = glGraphics;
        this.manage = manage;
        this.game_cam = new GL_Camera2D(glGraphics, manage.get_World_Width(),
                manage.get_World_Height());
        this.batcher = batcher;

    }

    public void render(float deltaTime, int state) {
        game_cam.setViewportAndMatrices();
        renderBackground();
        renderObjects(deltaTime);
    }

    private void renderBackground() {
        }

    private void renderObjects(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
   
        gl.glDisable(GL10.GL_BLEND);

    }

    public GL_Camera2D get_Game_Cam() {
        return this.game_cam;
    }

}
