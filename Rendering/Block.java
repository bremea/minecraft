package Rendering;

import java.nio.IntBuffer;

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.texture.Texture;

public class Block {
	private static final float ZERO_F = 0.0f;
	private static final float ONE_F = 0.5f;
	public static final float TILES_Y = 4f;
	public static final float TILES_X = 4f;
	public static final float TILE_W = 1.0f / TILES_X;
	public static final float TILE_H = 1.0f / TILES_Y;

	IntBuffer textureIDs;

	float x;
	float y;
	float z;
	Texture atlas;

	public Block(float x, float y, float z, Texture atlas) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.atlas = atlas;
	}

	public void render(GLAutoDrawable drawable, boolean[] borders) {
		final GL2 gl = drawable.getGL().getGL2();
		atlas.enable(gl);

		drawCube(gl, borders);

		atlas.disable(gl);
		gl.glEnd();
		gl.glFlush();
	}

	void drawCube(GL2 gl, boolean[] borders) {
		gl.glBegin(GL2.GL_QUADS);

		int tileID = 0;

		float MAX = 1f;
		float MIN = -1f;

		gl.glColor3f(1.0f, 1.0f, 1.0f); 
		if (!borders[3]) {
			gl.glTexCoord2f(MAX, MAX);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(MIN, MAX);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(MIN, MIN);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(MAX, MIN);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
		}

		if (!borders[2]) {
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

		if (!borders[4]) {
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
		}

		if (!borders[5]) {
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
		}

		if (!borders[0]) {
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
		}

		if (!borders[1]) {
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

	}
}