package demo.MainGame;

import java.util.List;
import java.util.Random;

public class Box_Manage {

	public final int num_01 = 0;
	public final int num_02 = 1;
	public final int num_03 = 2;
	public final int num_04 = 3;
	public final int num_05 = 4;
	public final int num_06 = 5;
	public final int num_07 = 6;
	public final int num_08 = 7;
	public final int num_09 = 8;
	private final int num_max = 9;

	public final int cal_Plus = 0;
	public final int cal_Minus = 1;
	public final int cal_Mult = 2;
	public final int cal_Div = 3;
	private final int cal_max = 4;
    private final Random rand;
	
	private MainGame_Manager manager;
	private Obj_D_NumberBox[] create_Box;

	int[] num;
	int[] cal;

	public Box_Manage( MainGame_Manager manager) {
		this.rand = new Random();
		this.manager = manager;
		this.num = new int[10];
		this.cal = new int[4];
		this.create_Box = new Obj_D_NumberBox[manager.get_Box_World_Width()];
	}

	public void update(List<Obj_D_NumberBox> number_Box) {
		int len = number_Box.size();

		for (int i = 0; i < num_max; i++) {
			num[i] = 0;
		}

		for (int i = 0; i < cal_max; i++) {
			cal[i] = 0;
		}

		if (len > 0) {
			for (int i = 0; i < len; i++) {
				Obj_D_NumberBox box = number_Box.get(i);
				num[box.getImage_flags(0)] += 1;
			}
		}
	}
	
	public Obj_D_NumberBox[] create_Box()
	{
		int start_create = 0;
		int createNumber = -1;
		
		while(start_create < manager.get_Box_World_Width())
		{
			if(0<createNumber&&createNumber <10)
				start_create++;
		}
		
		return create_Box;
	}

}
