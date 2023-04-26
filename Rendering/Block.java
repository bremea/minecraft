package Rendering;

import com.jogamp.opengl.*;

public class Block {
	private static final float ZERO_F = 0.0f;
	private static final float ONE_F = 1.0f;

	float x;
	float y;
	float z;

	public Block(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void render(GLAutoDrawable drawable, boolean[] borders) {
		final GL2 gl = drawable.getGL().getGL2();

		drawCube(gl, borders);

		gl.glEnd();
		gl.glFlush();
	}

	void drawCube(GL2 gl, boolean[] borders) {
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor3f(ONE_F, ZERO_F, ZERO_F); // red color
		if (!borders[3]) {
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
		}

		gl.glColor3f(ZERO_F, ONE_F, ZERO_F); // green color
		if (!borders[2]) {
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

		gl.glColor3f(ZERO_F, ZERO_F, ONE_F); // blue color
		if (!borders[4]) {
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
		}

		gl.glColor3f(ONE_F, ONE_F, ZERO_F); // yellow (red + green)
		if (!borders[5]) {
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
		}

		gl.glColor3f(ONE_F, ZERO_F, ONE_F); // purple (red + green)
		if (!borders[0]) {
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
		}

		gl.glColor3f(ZERO_F, ONE_F, ONE_F); // sky blue (blue +green)
		if (!borders[1]) {
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

	}
}
