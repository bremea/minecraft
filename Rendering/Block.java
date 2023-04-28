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
	public static final int[][] BLOCK_TEXS = {
			{ 0, 2, 1, 1, 1, 1 }, // grass
			{ 2, 2, 2, 2, 2, 2 }, // dirt
			{ 3, 3, 3, 3, 3, 3 }, // stone
			{ 4, 4, 4, 4, 4, 4 }, // wood
	};

	IntBuffer textureIDs;

	float x;
	float y;
	float z;
	int blockId;
	Texture atlas;

	float minY;
	float maxY;
	float minX;
	float maxX;

	public Block(float x, float y, float z, Texture atlas, int blockId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blockId = blockId;
		this.atlas = atlas;
	}

	public void render(GLAutoDrawable drawable, boolean[] borders, boolean isactive) {
		final GL2 gl = drawable.getGL().getGL2();
		atlas.enable(gl);
		atlas.bind(gl);

		drawCube(gl, borders, isactive);

		gl.glEnd();
		gl.glFlush();
		atlas.disable(gl);
	}

	void drawCube(GL2 gl, boolean[] borders, boolean isactive) {
		gl.glBegin(GL2.GL_QUADS);

		int tileID = 0;

		recalcTexBounds(0);
		float colorvex = isactive ? .25f : 1f;
		gl.glColor3f(colorvex, colorvex, colorvex);
		if (!borders[3]) {
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
		}

		recalcTexBounds(1);
		if (!borders[2]) {
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

		recalcTexBounds(2);
		if (!borders[4]) {
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
		}

		recalcTexBounds(3);
		if (!borders[5]) {
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
		}

		recalcTexBounds(4);
		if (!borders[0]) {
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + -ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + -ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + -ONE_F, y + -ONE_F, z + ONE_F);
		}

		recalcTexBounds(5);
		if (!borders[1]) {
			gl.glTexCoord2f(maxX, maxY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + -ONE_F);
			gl.glTexCoord2f(minX, maxY);
			gl.glVertex3f(x + ONE_F, y + ONE_F, z + ONE_F);
			gl.glTexCoord2f(minX, minY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + ONE_F);
			gl.glTexCoord2f(maxX, minY);
			gl.glVertex3f(x + ONE_F, y + -ONE_F, z + -ONE_F);
		}

	}

	void recalcTexBounds(int face) {
		minY = 1 - (TILE_H * ((int) (BLOCK_TEXS[blockId - 1][face] / TILES_Y)) + TILE_H);
		maxY = 1 - (TILE_H * ((int) (BLOCK_TEXS[blockId - 1][face] / TILES_Y)));
		minX = (TILE_W * ((int) (BLOCK_TEXS[blockId - 1][face] % TILES_X)) + TILE_W);
		maxX = (TILE_W * ((int) (BLOCK_TEXS[blockId - 1][face] % TILES_X)));
	}
}