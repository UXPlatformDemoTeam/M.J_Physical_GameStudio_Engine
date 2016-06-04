
package jrcengine.GL;

/*
 * loading 해온 texture을 partition하는 것
 */

public class GL_TextureRegion {
    private final float text_l_u_p_x, text_l_u_p_y; // texture_left_up_persantage_x
                                                    // ,texture_left_up_persantage_y

    private final float text_r_d_p_x, text_r_d_p_y;// texture_right_down_persantage_x,texture_right_down_persantage_y

    private final GL_Texture texture;

    // graphic를 texture의 크기에 사상 시키기 위해서 있는 것이다.
    public GL_TextureRegion(GL_Texture texture, float texture_left_up_x, float texture_left_up_y,
            float width, float height) {
        this.text_l_u_p_x = texture_left_up_x / texture.getWidth();
        this.text_l_u_p_y = texture_left_up_y / texture.getHeight();
        this.text_r_d_p_x = this.text_l_u_p_x + width / texture.getWidth();
        this.text_r_d_p_y = this.text_l_u_p_y + height / texture.getHeight();
        this.texture = texture;
    }

    public float getText_l_u_p_x() {
        return text_l_u_p_x;
    }

    public float getText_l_u_p_y() {
        return text_l_u_p_y;
    }

    public float getText_r_d_p_x() {
        return text_r_d_p_x;
    }

    public float getText_r_d_p_y() {
        return text_r_d_p_y;
    }

    public GL_Texture getTexture() {
        return texture;
    }

}
