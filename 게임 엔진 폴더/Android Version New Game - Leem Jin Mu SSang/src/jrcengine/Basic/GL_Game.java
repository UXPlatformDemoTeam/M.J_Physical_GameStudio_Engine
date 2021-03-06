package jrcengine.Basic;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jrcengine.Interface.Abstract_Screen;
import jrcengine.Interface.IFace_Audio;
import jrcengine.Interface.IFace_FileIO;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Graphics;
import jrcengine.Interface.IFace_Input;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class GL_Game extends Activity implements IFace_Game, Renderer {
	enum GLGameState {
		Initialized, Running, Paused, Finished, Idle
	}

	protected GLSurfaceView glView;
	protected GL_Graphics glGraphics;
	protected IFace_Audio audio;
	protected IFace_Input input;
	protected IFace_FileIO fileIO;
	protected Abstract_Screen screen;
	protected GLGameState state = GLGameState.Initialized;
	protected Object stateChanged = new Object();
	protected long startTime = System.nanoTime();
	protected WakeLock wakeLock;
	boolean firstTimeCreate = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glView = new GLSurfaceView(this);
		glView.setRenderer(this);
		setContentView(glView);

		glGraphics = new GL_Graphics(glView);
		fileIO = new File_FileIO(getAssets());
		audio = new Sound_Audio(this);
		input = new Control_Input(this, glView, 1, 1);
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"GLGame");
	}

	public void onResume() {
		super.onResume();
		glView.onResume();
		wakeLock.acquire();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glGraphics.setGL(gl);

		synchronized (stateChanged) {
			if (state == GLGameState.Initialized)
				screen = getStartScreen();
			state = GLGameState.Running;
			screen.resume();
			startTime = System.nanoTime();
		}
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
	}

	public void onDrawFrame(GL10 gl) {
		GLGameState state = null;
		synchronized (stateChanged) {
			state = this.state;
		}

		if (state == GLGameState.Running) {
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			screen.update(deltaTime);
			screen.present(deltaTime);
		}

		if (state == GLGameState.Paused) {
			screen.pause();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
		}

		if (state == GLGameState.Finished) {
			screen.pause();
			screen.dispose();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
		}
	}
	
	@Override
	public void exit(){
			moveTaskToBack(true);
			finish();
	}

	@Override
	public void onPause() {
		synchronized (stateChanged) {
			if (isFinishing())
				state = GLGameState.Finished;
			else
				state = GLGameState.Paused;
			while (true) {
				try {
					stateChanged.wait();
					break;
				} catch (InterruptedException e) {
					Log_Exception.logEvent("Error Code 22", "Unknown Error");
				}
			}
		}
		wakeLock.release();
		glView.onPause();
		super.onPause();
	}

	public GL_Graphics getGLGraphics() {
		return glGraphics;
	}

	public IFace_Input getInput() {
		return input;
	}

	public IFace_FileIO getFileIO() {
		return fileIO;
	}

	public IFace_Graphics getGraphics() {
		Log_Exception.logEvent("Error Code 23",
				"OpenGL is not Pixmap 2D and Graphics");
		throw new IllegalStateException(
				"Error code 23 : OpenGl is not Pixmap and Graphic" + "2D");
	}

	public IFace_Audio getAudio() {
		return audio;
	}

	public void setScreen(Abstract_Screen screen) {
		if (screen == null) {
			Log_Exception.logEvent("Error Code 13", "Screen must not be Null");
			throw new IllegalArgumentException("Error Code 13 : "
					+ "Screen must not be null");
		}

		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	public Abstract_Screen getCurrentScreen() {
		return screen;
	}
}
