package demo.MainGame;


import jrcengine.Basic.GL_Graphics;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap_Rectangle;

public class Screen_Game_Window {

	private GL_Camera2D cam;
	private GL_SpriteBatcher batcher;
	private GL_Graphics glGraphics;
	private Screen_MainGame mainGame;
	private Game_Window_Manager manager;
	private Game_Window_Renderer renderer;
	private Manage_Settings settings;

	private int particle_World_Width;
	private int particle_World_Height;

	public Screen_Game_Window(Manage_Settings settings, GL_Graphics glGraphics,
			GL_SpriteBatcher batcher, Screen_MainGame main_game,
			int center_x_set, int center_y_set, int width_set, int hight_set,
			int frustum_width, int frustum_height) {

		this.particle_World_Width = frustum_width;
		this.particle_World_Height = frustum_height;

		this.settings = settings;
		this.glGraphics = glGraphics;
		this.cam = new GL_Camera2D(glGraphics, center_x_set, center_y_set,
				width_set, hight_set, particle_World_Width,
				particle_World_Height);
		this.batcher = batcher;
		this.mainGame = main_game;

		this.manager = new Game_Window_Manager(settings, particle_World_Width,
				particle_World_Height);
		this.renderer = new Game_Window_Renderer(glGraphics, batcher,
				center_x_set, center_y_set, width_set, hight_set,this.manager ,this.settings);

	}

	public void update(float deltaTime) {
		manager.update(deltaTime);
	}

	public void render() {
		cam.setParticleViewportAndMatrices(mainGame);
		renderer.render();
	}

	public Math_Overlap_Rectangle getBuildBouns() {
		return cam.get_Math_Overlap_Rectangle();
	}

	public GL_Camera2D getGL_cam() {
		return this.cam;
	}
	
	public Game_Window_Manager getManager()
	{
		return this.manager;
	}

}
