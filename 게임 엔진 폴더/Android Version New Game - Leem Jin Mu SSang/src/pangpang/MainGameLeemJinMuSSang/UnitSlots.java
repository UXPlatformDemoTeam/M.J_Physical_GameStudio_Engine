package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Normal;

public class UnitSlots extends Object_Normal {
	
	public static final int NOTEXIST = 0;
	
	public static float SLOTWIDTH = 30f;
	public static float SLOTHEIGHT = 30f;
	
	private int nUnitNum;

	public UnitSlots(float center_x, float center_y) {
		super(center_x, center_y, SLOTWIDTH, SLOTHEIGHT);
		// TODO Auto-generated constructor stub
	}

	public int getnUnitNum() {
		return nUnitNum;
	}

	public void setnUnitNum(int nUnitNum) {
		this.nUnitNum = nUnitNum;
	}

}
