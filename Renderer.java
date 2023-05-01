import org.joml.Vector3f;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;

import Rendering.Block;
import Shaders.Shader;

public class Renderer {
	public void renderWorld(Camera camera, GLAutoDrawable drawable, World world, Texture atlas, Shader shader,
			Vector3f lookingAt, Vector3f rLa) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		int[][][] blockData = world.getBlockData();

		Vector3f tt = new Vector3f();

		for (int x = 0; x < blockData.length; x++) {
			for (int y = 0; y < blockData[x].length; y++) {
				for (int z = 0; z < blockData[x][y].length; z++) {
					if (blockData[x][y][z] != 0) {
						boolean[] borders = new boolean[6];
						if (x > 0) {
							borders[0] = blockData[x - 1][y][z] != 0 && blockData[x - 1][y][z] != 5;
						}
						if (x < 15) {
							borders[1] = blockData[x + 1][y][z] != 0 && blockData[x + 1][y][z] != 5;
						}
						if (y > 0) {
							borders[2] = blockData[x][y - 1][z] != 0 && blockData[x][y - 1][z] != 5;
						}
						if (y < 15) {
							borders[3] = blockData[x][y + 1][z] != 0 && blockData[x][y + 1][z] != 5;
						}
						if (z > 0) {
							borders[5] = blockData[x][y][z - 1] != 0 &&  blockData[x][y][z - 1] != 5;
						}
						if (z < 15) {
							borders[4] = blockData[x][y][z + 1] != 0 &&  blockData[x][y][z + 1] != 5;
						}

						boolean isactive = x == (int) Math.round(lookingAt.x) && y == (int) Math.round(lookingAt.y)
								&& z == (int) Math.round(lookingAt.z);
						if (isactive) {
							Vector3f pp = new Vector3f(x, y + 0.5f, z);
							// System.out.println(lineIntersection(pp, new Vector3f(0f, 1f, 0f), lookingAt,
							// rLa.normalize()));
						}
						new Block(x, y, z, atlas, blockData[x][y][z]).render(drawable, borders, isactive);
					}
				}
			}
		}
		/*
		 * gl.glDisable(GL2.GL_DEPTH_TEST);
		 * gl.glLineWidth(50f);
		 * gl.glBegin(GL2.GL_LINE);
		 * gl.glVertex3f(tt.x, tt.y, tt.z);
		 * gl.glVertex3f(tt.x, tt.y + .5f, tt.z);
		 * gl.glEnd();
		 */
	}

	public static boolean lineIntersection(Vector3f planePoint, Vector3f planeNormal, Vector3f linePoint,
			Vector3f lineDirection) {
		float denom = planeNormal.dot(lineDirection.normalize());
		if (Math.abs(denom) > 0.0001f) {
			float t = (planePoint.sub(linePoint)).dot(planeNormal) / denom;
			if (-t >= 0)
				return true; // you might want to allow an epsilon here too
		}
		return false;
	}

}