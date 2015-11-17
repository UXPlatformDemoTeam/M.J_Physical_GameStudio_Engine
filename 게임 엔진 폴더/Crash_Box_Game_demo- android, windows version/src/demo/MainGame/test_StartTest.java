package demo.MainGame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Game;

import jrcengine.Interface.Abstract_Screen;
import jrcengine.Manage.*;

public class test_StartTest extends GL_Game
{
	boolean firstTimeCreate = true;
	private Manage_Settings manage_Settings = new Manage_Settings();

	public Abstract_Screen getStartScreen()
	{
		start();
		return new Screen_MainGame(this, manage_Settings);
	}
	
	
	private void start(/*int flag*/)
	{
		//manage_Settings.loadStage(getFileIO(), "test.txt");
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate)
		{
			
			Manage_Assets.load(this);
			firstTimeCreate = false;
		}
		else
		{
			Manage_Assets.reload();
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
	//	if (true)
			//Manage_Assets.music.get(Manage_Assets.MUSIC).pause();
	}
}