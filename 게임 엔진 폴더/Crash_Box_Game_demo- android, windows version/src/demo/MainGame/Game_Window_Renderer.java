package demo.MainGame;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;
import jrcengine.Basic.GL_Graphics;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Interface.Object_Normal;
import jrcengine.Manage.*;

public class Game_Window_Renderer {

	private GL_Graphics glGraphics;
	private Game_Window_Manager manage;
	private GL_Camera2D game_cam;
	private GL_SpriteBatcher batcher;
	private Manage_Settings setting;
	private Random rand;

	private Obj_D_NumberBox box;

	public Game_Window_Renderer(GL_Graphics glGraphics, GL_SpriteBatcher batcher,
		 int center_x_set, int center_y_set,
			int width_set, int hight_set,
			Game_Window_Manager manager,Manage_Settings settings) {

		this.rand = new Random();
		this.glGraphics = glGraphics;
		this.manage = manager;
		this.setting = settings;
		
		this.game_cam = new GL_Camera2D(glGraphics, center_x_set, center_y_set,
				width_set, hight_set, this.manage.get_Box_World_Width(),
				this.manage.get_Box_World_Height());
		this.batcher = batcher;

	}

	public void render() {
	//	game_cam.setViewportAndMatrices();
		renderObjects();
	}

	private void renderObjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		render_Number_Box();

		gl.glDisable(GL10.GL_BLEND);
	}

	private void render_Number_Box() {

		int len = manage.get_Number_Box().size();

		if (len > 0) {
			batcher.beginBatch(Manage_Assets.texture
					.get(Manage_Assets.TEXTURE_0_DEMO_NUMBER));

			for (int i = 0; i < len; i++) {
				this.box = manage.get_Number_Box().get(i);

				if (box.getIsClicked()||box.getIsDragged()) {

					render_Box_Vibration(box);

				} else {
				batcher.drawSprite(box.position.x, box.position.y,
						box.bounds.width, box.bounds.height,
						Manage_Assets.textureRegion.get(box.getImage_flags(0)));
				}
//				if (box.getIsDragged()) {
//					batcher.drawSprite(
//							box.position.x,
//							box.position.y,
//							box.bounds.width,
//							box.bounds.height,
//							Manage_Assets.textureRegion
//									.get(Manage_Assets.TEXTUREREGION_0_DEMO_NUMBER_SELECTOR));
//				}

			}
			batcher.endBatch();
		}

	}

	private void render_Box_Vibration(Object_Normal input) {
		boolean vib_X_Di = rand.nextBoolean();
		boolean vib_Y_Di = rand.nextBoolean();
		float vib_X;
		float vib_Y;
		if (vib_X_Di)
			vib_X = -0.01f;
		else
			vib_X = 0.01f;
		if (vib_Y_Di)
			vib_Y = -0.01f;
		else
			vib_Y = 0.01f;

		batcher.drawSprite(box.position.x + vib_X, box.position.y + vib_Y,
				box.bounds.width, box.bounds.height,
				Manage_Assets.textureRegion.get(box.getImage_flags(0)));
	}


	public GL_Camera2D get_Game_Cam() {
		return this.game_cam;
	}

}