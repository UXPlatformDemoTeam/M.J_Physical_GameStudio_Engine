// 실제 게임 화면(플레이어가 실제 즐길 수 있는 화면)

package jrcengine.MainGame;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Screen;
import jrcengine.Basic.Log_TimeCounter;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Vector;
import jrcengine.NetworkModule.NetworkModule;

public class Screen_MainGame extends GL_Screen {
    static final int GAME_READY = 0;

    static final int GAME_RUNNING = 1;

    static final int GAME_PAUSED = 2;

    static final int GAME_LEVEL_END = 3;

    static final int GAME_OVER = 4;

    int state;

    Log_TimeCounter fpsCounter;

    private GL_Camera2D guiCam;

    private GL_SpriteBatcher batcher;

    private MainGame_Manager manage;

    private MainGame_Renderer renderer;

    private Math_Vector touchPoint;

    private Math_Vector move_Point;

    public Screen_MainGame(IFace_Game game) {
        super(game);
        this.state = GAME_READY;
        this.guiCam = new GL_Camera2D(glGraphics, Manage_Settings.GAME_WIDTH,
                Manage_Settings.GAME_HEIGHT);
        this.batcher = new GL_SpriteBatcher(glGraphics, 1000);

        this.fpsCounter = new Log_TimeCounter();
        this.move_Point = new Math_Vector(Manage_Settings.GAME_WIDTH/2, Manage_Settings.GAME_HEIGHT/2);
        this.touchPoint = new Math_Vector();

        this.manage = new MainGame_Manager(Manage_Settings.GAME_WIDTH, Manage_Settings.GAME_HEIGHT);
        this.renderer = new MainGame_Renderer(glGraphics, batcher, manage);

        NetworkModule.sendPacket(1,
                Manage_Assets.NetworkProtocol._REQUEST_MOBILE_MAKE_GAME_UNIVERSE + "");
        NetworkModule.setglMainGame(this);

    }

    @Override
    public void update(float deltaTime) {
        if (deltaTime > 0.1f)
            deltaTime = 0.1f;

        update_Contoroller(state, deltaTime);

        switch (state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_LEVEL_END:
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }
    }

    private void update_Contoroller(int state, float deltaTime) {

        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {

                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if (GAME_READY == state)
                    if (len > 0) {
                        this.state = GAME_RUNNING;

                    }
                
                if(GAME_RUNNING == state){
                    if( len > 0){
                        move_Point = touchPoint;
                    }
                }

            }

            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }

            if (event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if (GAME_OVER == state)
                    if (len > 0) {
                        game.setScreen(new Screen_MainMenu(game));
                        return;
                    }

                if (GAME_LEVEL_END == state)
                    if (len > 0) {

                    }

                if (GAME_RUNNING == state) {

                }

                if (GAME_PAUSED == state) {

                }
            }
        }
    }

    private void updateReady() {

    }

    private void updateRunning(float deltaTime) {

        manage.update(renderer, deltaTime, game.getInput().getAccelX(),
                game.getInput().getAccelY(), move_Point.getX(), move_Point.getY());

    }

    private void updatePaused() {

    }

    private void updateGameOver() {
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);

        renderer.render(deltaTime, state);

        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        switch (state) {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }

        gl.glDisable(GL10.GL_BLEND);
        if (Screen_MainMenu.is_Debug)
            fpsCounter.logFrame();
    }

    private void presentReady() {
    }

    private void presentRunning() {
    }

    private void presentPaused() {

    }

    private void presentLevelEnd() {

    }

    private void presentGameOver() {

    }

    @Override
    public void pause() {
        if (state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    public MainGame_Manager getManage() {
        return manage;
    }

}
