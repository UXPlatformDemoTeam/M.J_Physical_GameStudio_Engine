package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Normal;

public class StageSlots extends Object_Normal {
	
	public static final int NOTEXIST = 0;
	
	public static float SLOTWIDTH = 50f;
	public static float SLOTHEIGHT = 40f;
	
	private int nStageNum;

	public StageSlots(float center_x, float center_y) {
		super(center_x, center_y, SLOTWIDTH, SLOTHEIGHT);
		// TODO Auto-generated constructor stub
	}

	public int getnStageNum() {
		return nStageNum;
	}

	public void setnStageNum(int nStageNum) {
		this.nStageNum = nStageNum;
	}

}
