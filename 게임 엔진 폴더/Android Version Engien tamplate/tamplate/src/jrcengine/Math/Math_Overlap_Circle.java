package jrcengine.Math;

public class Math_Overlap_Circle {
	private final Math_Vector center = new Math_Vector();
	private float radius;

	public Math_Overlap_Circle(float x, float y, float radius) {
		this.center.set(x, y);
		this.radius = radius;
	}

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Math_Vector getCenter() {
        return center;
    }
	
	
}
