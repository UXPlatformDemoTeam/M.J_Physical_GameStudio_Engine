// 각 객체의 여러 인스턴스를 추적하고
// 매 프레임마다 객체를 업데이트 하며 객체와 Bob 사이의 충돌을 검사하고 반응을 수행
package demo.MainGame;


import jrcengine.Interface.Screen_Manager;
import jrcengine.Manage.Manage_Settings;

public class MainGame_Manager extends Screen_Manager {


	private final Manage_Settings setting;
	private final int windows_Width;
	private final int windows_Height;

	private int state;
	private int money_Score;

	public MainGame_Manager(Manage_Settings settings ,int windows_Width, int windows_Height) {

		this.windows_Width = windows_Width;
		this.windows_Height = windows_Height;
		this.setting = settings;
		this.state = Screen_MainGame.GAME_RUNNING;

		this.money_Score = 0;


		generate();

	}

	// 게임 세계의 생성
	@Override
	protected void generate() {

	}

	public void update(MainGame_Renderer rander, float deltaTime, float accelX,
			float accelY) {

		update_checkCollisions(deltaTime);
		update_Objections(deltaTime);

	}


	private void update_Objections(float deltaTime) {

		update_Number_Box(deltaTime);
		update_Move_Box(deltaTime);

	}
	
	private void update_Move_Box(float deltaTime){

	}

	private void update_Number_Box(float deltaTime) {
	
	}

	// 충돌 검사 메서드를 모두 호출
	@Override
	protected void update_checkCollisions(float deltaTime) {


	}

	

	public int getState() {
		return this.state;
	}

	public int getMoney_Score() {
		return this.money_Score;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public int get_Box_World_Width() {
		return this.windows_Width;
	}

	public int get_Box_World_Height() {
		return this.windows_Height;
	}

}