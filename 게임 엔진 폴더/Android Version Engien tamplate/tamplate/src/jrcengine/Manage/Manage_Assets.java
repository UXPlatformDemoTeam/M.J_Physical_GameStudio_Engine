
package jrcengine.Manage;

import jrcengine.Basic.GL_Game;
import jrcengine.GL.GL_Texture;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.IFace_Sound;

public class Manage_Assets {

    // Resource Sheet


    public static GL_Texture gtJerryCoLogo;

    // Resource Particle Logo
    public static GL_TextureRegion gsJerrycoLogo;

    public class NetworkProtocol {
        }

    public static void load(GL_Game game) {

       Manage_Assets.gtJerryCoLogo = new GL_Texture(game, "jerryco_logo.png");

            Manage_Assets.gsJerrycoLogo = new GL_TextureRegion(Manage_Assets.gtJerryCoLogo, 0, 0, 866,
                488);
    }

    public static void reload() {
     }

    public static void playSound(IFace_Sound sound) {

    }
}
