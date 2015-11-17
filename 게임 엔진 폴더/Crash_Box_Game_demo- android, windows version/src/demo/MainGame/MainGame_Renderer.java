package demo.MainGame;


import jrcengine.Basic.GL_Graphics;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Manage.*;

public class MainGame_Renderer {

	private GL_Graphics glGraphics;
	private MainGame_Manager manage;
	private GL_Camera2D game_cam;
	private GL_SpriteBatcher batcher;
	private Manage_Settings setting;

	public MainGame_Renderer(GL_Graphics glGraphics, GL_SpriteBatcher batcher,
			MainGame_Manager manage, Manage_Settings settings) {

		this.glGraphics = glGraphics;
		this.manage = manage;
		this.setting = settings;
		this.game_cam = new GL_Camera2D(glGraphics, manage.get_Box_World_Width(),
				manage.get_Box_World_Height());
		this.batcher = batcher;



	}

	public void render() {
		game_cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	private void renderBackground() {
		batcher.beginBatch(Manage_Assets.texture
				.get(Manage_Assets.TEXTURE_1_BACKGROUND));
		batcher.drawSprite(game_cam.position.x, game_cam.position.y, manage
				.get_Box_World_Width(), manage.get_Box_World_Height(),
				Manage_Assets.textureRegion
						.get(Manage_Assets.TEXTUREREGION_1_DEMO_BACKGROUND));
		batcher.endBatch();
	}

	private void renderObjects() {
		
	}

	private void render_Number_Box() {

	
	}



	public GL_Camera2D get_Game_Cam() {
		return this.game_cam;
	}

}
