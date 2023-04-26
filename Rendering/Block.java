package Rendering;

import com.jogamp.opengl.*;

public class Block {
	private static final float ZERO_F = 0.0f;
	private static final float ONE_F = 1.0f;

	public void render(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		drawCube(gl);

		gl.glEnd();
		gl.glFlush();
	}

	void drawCube(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor3f(ONE_F, ZERO_F, ZERO_F); // red color
		gl.glVertex3f(ONE_F, ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, ONE_F, ONE_F);
		gl.glVertex3f(ONE_F, ONE_F, ONE_F);

		gl.glColor3f(ZERO_F, ONE_F, ZERO_F); // green color
		gl.glVertex3f(ONE_F, -ONE_F, ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, -ONE_F);
		gl.glVertex3f(ONE_F, -ONE_F, -ONE_F);

		gl.glColor3f(ZERO_F, ZERO_F, ONE_F); // blue color
		gl.glVertex3f(ONE_F, ONE_F, ONE_F);
		gl.glVertex3f(-ONE_F, ONE_F, ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, ONE_F);
		gl.glVertex3f(ONE_F, -ONE_F, ONE_F);

		gl.glColor3f(ONE_F, ONE_F, ZERO_F); // yellow (red + green)
		gl.glVertex3f(ONE_F, -ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, ONE_F, -ONE_F);
		gl.glVertex3f(ONE_F, ONE_F, -ONE_F);

		gl.glColor3f(ONE_F, ZERO_F, ONE_F); // purple (red + green)
		gl.glVertex3f(-ONE_F, ONE_F, ONE_F);
		gl.glVertex3f(-ONE_F, ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, -ONE_F);
		gl.glVertex3f(-ONE_F, -ONE_F, ONE_F);

		gl.glColor3f(ZERO_F, ONE_F, ONE_F); // sky blue (blue +green)
		gl.glVertex3f(ONE_F, ONE_F, -ONE_F);
		gl.glVertex3f(ONE_F, ONE_F, ONE_F);
		gl.glVertex3f(ONE_F, -ONE_F, ONE_F);
		gl.glVertex3f(ONE_F, -ONE_F, -ONE_F);

	}
}
