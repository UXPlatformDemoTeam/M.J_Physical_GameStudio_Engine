
package jrcengine.GL;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Graphics;

import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;

public class GL_Camera2D {

    private float zoom;

    private final Math_Vector position;

    private final float frustum_Width;

    private final float frustum_Height;

    private final GL_Graphics glGraphics;

    private final Math_Overlap_Rectangle bounds;

    public GL_Camera2D(GL_Graphics glGraphics, float frustum_Width, float frustum_Height) {
        this.glGraphics = glGraphics;
        this.frustum_Width = frustum_Width;
        this.frustum_Height = frustum_Height;
        this.bounds = null;
        this.position = new Math_Vector(frustum_Width / 2, frustum_Height / 2);
        this.setZoom(1.0f);
    }

    public GL_Camera2D(GL_Graphics glGraphics, int center_x_set, int center_y_set, int width_set,
            int hight_set, float frustumWidth, float frustumHeight) {
        this.glGraphics = glGraphics;
        this.frustum_Width = frustumWidth;
        this.frustum_Height = frustumHeight;
        this.position = new Math_Vector(frustumWidth / 2, frustumHeight / 2);
        this.bounds = new Math_Overlap_Rectangle(center_x_set - width_set / 2, center_y_set
                - hight_set / 2, width_set, hight_set);
        this.setZoom(1.0f);

    }

    public void setViewportAndMatrices() {
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(position.getX() - frustum_Width * getZoom() / 2, position.getX()
                + frustum_Width * getZoom() / 2, position.getY() - frustum_Height * getZoom() / 2,
                position.getY() + frustum_Height * getZoom() / 2, 1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void touchToWorld(Math_Vector touch) {
        touch.setX((touch.getX() / (float)glGraphics.getWidth()) * frustum_Width * getZoom());
        touch.setY((1 - touch.getY() / (float)glGraphics.getHeight()) * frustum_Height * getZoom());
        touch.add(position).sub(frustum_Width * getZoom() / 2, frustum_Height * getZoom() / 2);
    }

    public void touchToWorld_partition(Math_Vector touch, GL_Camera2D old_cam) {

        touch.setX(((touch.getX() - bounds.getLowerLeft().getX())) / (float)bounds.getWidth()
                * frustum_Width * getZoom());
        touch.setY(((touch.getY() - bounds.getLowerLeft().getY()) / (float)bounds.getHeight())
                * frustum_Height * getZoom());
        touch.add(position).sub(frustum_Width * getZoom() / 2, frustum_Height * getZoom() / 2);

    }

    public Math_Overlap_Rectangle get_Math_Overlap_Rectangle() {
        return bounds;
    }

    public GL_Graphics getGlGraphics() {
        return glGraphics;
    }

    public float getFrustum_Width() {
        return frustum_Width;
    }

    public float getFrustum_Height() {
        return frustum_Height;
    }

    public Math_Overlap_Rectangle getBounds() {
        return bounds;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public Math_Vector getPosition() {
        return position;
    }

}
