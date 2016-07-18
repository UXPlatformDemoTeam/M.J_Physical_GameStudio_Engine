// 각 객체의 여러 인스턴스를 추적하고
// 매 프레임마다 객체를 업데이트 하며 객체와 Bob 사이의 충돌을 검사하고 반응을 수행

package jrcengine.MainGame;

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import jrcengine.Interface.Screen_Manager;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Vector;

public class MainGame_Manager extends Screen_Manager {

    private final int world_Width;

    private final int world_Height;

    private ArrayList<Asteroid> arAsteroids;

    private Player playAsteroid;

    public MainGame_Manager(int world_Width, int world_Height) {

        this.world_Width = world_Width;
        this.world_Height = world_Height;
        this.arAsteroids = new ArrayList<Asteroid>();
        this.playAsteroid = new Player(Manage_Settings.GAME_WIDTH / 2,
                Manage_Settings.GAME_HEIGHT / 2);

        generate();

    }

    // 게임 세계의 생성
    @Override
    protected void generate() {

    }

    public void addAsteroid(float x, float y) {
        arAsteroids.add(new Asteroid(x, y));
    }

    public void update(MainGame_Renderer rander, float deltaTime, float accel_X, float accel_Y,
            float click_X, float click_Y) {

        update_Objections(deltaTime, accel_X, accel_Y, click_X, click_Y);

    }

    private void update_Objections(float deltaTime, float accel_X, float accel_Y, float click_X,
            float click_Y) {
        playAsteroid.setVcMovePoint(click_X, click_Y);
        playAsteroid.update(deltaTime);

        for (int i = 0; i < arAsteroids.size(); i++) {
            Asteroid ast = arAsteroids.get(i);
            ast.setVcMovePoint(playAsteroid.getPosition().getX(), playAsteroid.getPosition().getY());
            ast.update(deltaTime);
        }
    }

    @Override
    protected void update_checkCollisions(float deltaTime) {

    }

    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub

    }

    public int get_World_Width() {
        return this.world_Width;
    }

    public int get_World_Height() {
        return this.world_Height;
    }

    public ArrayList<Asteroid> getArAsteroids() {
        return arAsteroids;
    }

    public Player getPlayAsteroid() {
        return playAsteroid;
    }

}
