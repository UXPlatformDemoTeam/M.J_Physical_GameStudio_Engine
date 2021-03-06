package jrcengine.GL;


/*
 * 폰트에 관해 이미지 처리부.
 */

public class GL_Font {
	public final GL_Texture texture;
	public final int glyphWidth;
	public final int glyphHeight;
	public final GL_TextureRegion[] glyphs = new GL_TextureRegion[10];

	public GL_Font(GL_Texture texture, int offsetX, int offsetY,
			int glyphsPerRow, int glyphWidth, int glyphHeight) {
		this.texture = texture;
		this.glyphWidth = glyphWidth;
		this.glyphHeight = glyphHeight;
		int x = offsetX;
		int y = offsetY;
		for (int i = 0; i < 10; i++) {
			glyphs[i] = new GL_TextureRegion(texture, x, y, glyphWidth,
					glyphHeight);
			x += glyphWidth;
			if (x == offsetX + glyphsPerRow * glyphWidth) {
				x = offsetX;
				y += glyphHeight;
			}
		}
	}

	public void drawText(GL_SpriteBatcher batcher, String text, float x, float y) {
		int len = text.length();
		for (int i = 0; i < len; i++) {
			int c = text.charAt(i) - ' ' - 16;
			if (c < 0 || c > glyphs.length - 1)
				continue;
			if (0 == c)
				c = 9;
			else
				c--;
			GL_TextureRegion glyph = glyphs[c];
			batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
			x += glyphWidth;
		}
	}

	public void drawText(GL_SpriteBatcher batcher, String text, float x,
			float y, float size_x, float size_y, float gap) {
		int len = text.length();
		for (int i = 0; i < len; i++) {
			int c = text.charAt(i) - ' ' - 16;

			if (c < 0 || c > glyphs.length - 1)
				continue;

			if (0 == c)
				c = 9;
			else
				c--;

			GL_TextureRegion glyph = glyphs[c];
			batcher.drawSprite(x, y, size_x, size_y, glyph);
			x += gap;
		}
	}
}
