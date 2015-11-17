package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public class HpBar extends Object_Dynamic {

	public static final int FULLHP = 4;
	public static final int THREEHP = 3;
	public static final int TWOHP = 2;
	public static final int ONEHP = 1;
	public static final int ZEROHP = 0;

	private final float FULLHPPERSENTAGE = 1f;
	private final float HPTHREEPERSENTAGE = 0.8f;
	private final float HPTWOPERSENTAGE = 0.6f;
	private final float HPONEPERSENTAGE = 0.4f;

	public static final float HPBARWIDTH = 50f;
	public static final float HPBARHEGIHT = 5f;

	private final float LIMITVISIBLETIME = 3f;

	public boolean isVisible = false;

	private int nHp;
	private int nFullHp;

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public HpBar(float center_x, float center_y) {
		super(center_x, center_y, HPBARWIDTH, HPBARHEGIHT);
		this.setVisible(false);
		// TODO Auto-generated constructor stub
	}

	public void update(float _x, float _y, float _height, int HP, int fullHP,
			float deltaTime) {

		this.setnHp(HP);
		this.setnFullHp(fullHP);

		position.x = _x;
		position.y = _y + _height;

		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		if (this.isVisible() == true)
			stateTime += deltaTime;

		if (stateTime >= LIMITVISIBLETIME) {
			this.setVisible(false);
			stateTime = 0f;
		}
	}

	public int getnHp() {
		return nHp;
	}

	private void setnHp(int nHp) {
		this.nHp = nHp;
	}

	private int getnFullHp() {
		return nFullHp;
	}

	private void setnFullHp(int nFullHp) {
		this.nFullHp = nFullHp;
	}

	public int getCurrentStateBar() {
		int _returnValue;
		float _result = (float)this.getnHp() / (float)this.getnFullHp();

		if (_result == FULLHPPERSENTAGE)
			_returnValue = FULLHP;
		else if (_result >= HPTHREEPERSENTAGE)
			_returnValue = THREEHP;
		else if (_result >= HPTWOPERSENTAGE)
			_returnValue = TWOHP;
		else if (_result >= HPONEPERSENTAGE)
			_returnValue = ONEHP;
		else
			_returnValue = ZEROHP;

		return _returnValue;
	}

}
