package jrcengine.Math;

public class Math_Overlap_Rectangle {
	private final Math_Vector lowerLeft;
	private float width, height;

	public Math_Overlap_Rectangle(float lowerLeft_x, float lowerLeft_y,
			float width, float height) {
		this.lowerLeft = new Math_Vector(lowerLeft_x, lowerLeft_y);
		this.width = width;
		this.height = height;
	}

	public void setRectangle(Math_Vector position) {
		this.lowerLeft.set(position.getX() - width / 2, position.getY() - height / 2);
	}

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Math_Vector getLowerLeft() {
        return lowerLeft;
    }
	
	
	
}
