
package jrcengine.Manage;

import java.util.ArrayList;

import android.util.Log;
import jrcengine.Basic.GL_Game;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Font;
import jrcengine.GL.GL_Texture;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.IFace_Music;
import jrcengine.Interface.IFace_Sound;

public class Manage_Assets {

    // Resource Sheet
    public static GL_Texture gtBackGround;

    public static GL_Texture gtLandGreen;

    public static GL_Texture gtLandWater;

    public static GL_Texture gtJerryCoLogo;

    // Box Cycle Sheet
    public static GL_Texture gtBoxCycle;

    // Resource Particle
    public static GL_TextureRegion gsBackGround;

    public static GL_TextureRegion gsLandGreen;

    public static GL_TextureRegion gsLandWater;

    // Box Cycle Particle
    public static GL_TextureRegion gsBox;

    public static GL_TextureRegion gsCycle;

    public static GL_TextureRegion gsCycleBlue;

    public static GL_TextureRegion gsCraft;

    public static GL_TextureRegion gsYellowCraft;

    public static GL_TextureRegion gsBlueBox;

    // Resource Particle Logo
    public static GL_TextureRegion gsJerrycoLogo;

    public class NetworkProtocol {
        public static final int _ACCESS_THE_SERVER = 0x00000001;

        public static final int _ANSWER_LOGIN = 0x0000000e;

        public static final int _REQUEST_MOBILE_LOGIN = 0x00000037;

        /**
         * Request Mobile login to server 56
         */
        public static final int _REQUEST_MOBILE_MAKE_GAME_UNIVERSE = 0x00000038;

        /**
         * Request Mobile login to server 57
         */
        public static final int _ANSWER_MOBILE_MAKE_GAME_UNIVERSE = 0x00000039;
        
        public static final String sEncryptKey = "fe8025947de7cd71";
    }

    public static void load(GL_Game game) {

        Manage_Assets.gtBackGround = new GL_Texture(game, "background.png");
        Manage_Assets.gtLandGreen = new GL_Texture(game, "land_green.png");
        Manage_Assets.gtLandWater = new GL_Texture(game, "land_water.png");
        Manage_Assets.gtJerryCoLogo = new GL_Texture(game, "jerryco_logo.png");

        Manage_Assets.gsBackGround = new GL_TextureRegion(Manage_Assets.gtBackGround, 0, 0, 320,
                480);
        Manage_Assets.gsLandGreen = new GL_TextureRegion(Manage_Assets.gtLandGreen, 0, 0, 118, 81);
        Manage_Assets.gsLandWater = new GL_TextureRegion(Manage_Assets.gtLandWater, 0, 0, 118, 81);
        Manage_Assets.gsJerrycoLogo = new GL_TextureRegion(Manage_Assets.gtJerryCoLogo, 0, 0, 866,
                488);

        Manage_Assets.gtBoxCycle = new GL_Texture(game, "box.png");
        Manage_Assets.gsCycle = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 0, 0, 100, 100);
        Manage_Assets.gsBox = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 100, 0, 100, 100);
        Manage_Assets.gsBlueBox = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 100, 100, 100, 100);
        Manage_Assets.gsCycleBlue = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 0, 100, 100, 100);
        Manage_Assets.gsCraft = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 0, 199, 151, 102);
        Manage_Assets.gsYellowCraft = new GL_TextureRegion(Manage_Assets.gtBoxCycle, 200, 0, 152,
                102);
    }

    public static void reload() {
        Manage_Assets.gtBackGround.reload();
        Manage_Assets.gtLandGreen.reload();
        Manage_Assets.gtLandWater.reload();
    }

    public static void playSound(IFace_Sound sound) {

    }
}
