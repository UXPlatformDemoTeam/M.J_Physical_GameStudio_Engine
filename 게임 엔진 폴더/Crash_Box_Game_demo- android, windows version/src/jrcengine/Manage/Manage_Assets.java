package jrcengine.Manage;

import java.util.ArrayList;

import jrcengine.Basic.GL_Game;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Font;
import jrcengine.GL.GL_Texture;
import jrcengine.GL.GL_TextureRegion;

import jrcengine.Interface.IFace_Music;
import jrcengine.Interface.IFace_Sound;

public class Manage_Assets {
	
	/*DEMO_NUMBER_AND_SELECTOR*/
	
	public static final int TEXTURE_0_DEMO_NUMBER = 0;
	
	public static final int TEXTUREREGION_0_DEMO_NUMBER_1 = 0;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_2 = 1;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_3 = 2;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_4 = 3;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_5 = 4;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_6 = 5;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_7 = 6;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_8 = 7;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_9 = 8;
	public static final int TEXTUREREGION_0_DEMO_NUMBER_SELECTOR = 9;
	
	/*DEMO_BACKGROUND*/
	
	public static final int TEXTURE_1_BACKGROUND = 1;
	
	public static final int TEXTUREREGION_1_DEMO_BACKGROUND = 10;
	
	
	/* font */
	public static final int FONT_5_font = 0;

	/* Music */
	public static final int MUSIC = 0;

	public static final int jumpSound = 0;
	
	public static ArrayList<GL_Texture> texture = new ArrayList<GL_Texture>();
	public static ArrayList<GL_TextureRegion> textureRegion = new ArrayList<GL_TextureRegion>();
	public static ArrayList<GL_Animation> animation = new ArrayList<GL_Animation>();
	public static ArrayList<GL_Font> font = new ArrayList<GL_Font>();

	public static ArrayList<IFace_Music> music = new ArrayList<IFace_Music>();
	public static ArrayList<IFace_Sound> sound = new ArrayList<IFace_Sound>();

	public static void load(GL_Game game) {
		// ------------------ key_pad.png 파일에서 정의 ------------------
		// number_demo
		texture.add(new GL_Texture(game, "number.png"));

		// demo_number 0 - 9 , final select
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 0, 0, 155, 165)); // 0 - number 1
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 155, 0, 155, 165));	//1 - number 2
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 310, 0, 155, 165));	//2 - number 3
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 0, 165, 155, 165));	//3 - number 4
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 155, 165, 155, 165));	//4 - number 5
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 310, 165, 155, 165));	//5 - number 6
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 0, 330, 155, 165));	//6 - number 7
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 155, 330, 155, 165));	//7 - number 8
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 310, 330, 155, 165));	//8 - number 9
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_0_DEMO_NUMBER), 465, 0, 155, 165));	//9 - selector
		
		
		
		texture.add(new GL_Texture(game, "background.png"));
		
		textureRegion.add(new GL_TextureRegion(texture
				.get(TEXTURE_1_BACKGROUND), 0, 0, 307, 512));		//10 - background


		// 배경음
//		music.add(game.getAudio().newMusic("music.mp3"));
//		music.get(MUSIC).setLooping(true);
//		music.get(MUSIC).setVolume(0.5f);
//
//		// 효과음
//		sound.add(game.getAudio().newSound("jump.ogg"));
//		sound.add(game.getAudio().newSound("highjump.ogg"));
//		sound.add(game.getAudio().newSound("hit.ogg"));
//		sound.add(game.getAudio().newSound("coin.ogg"));
//		sound.add(game.getAudio().newSound("click.ogg"));

	}

	public static void reload() {
		texture.get(TEXTURE_0_DEMO_NUMBER).reload();
		texture.get(TEXTURE_1_BACKGROUND).reload();
	}

	public static void playSound(IFace_Sound sound) {

	}
}
