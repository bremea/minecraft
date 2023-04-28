import java.nio.IntBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;

import Rendering.Block;
import Shaders.Shader;

public class Renderer {
	public void renderWorld(Camera camera, GLAutoDrawable drawable, World world, Texture atlas, Shader shader) {
		int[][][] blockData = world.getBlockData();

		
		for (int x = 0; x < blockData.length; x++) {
			for (int y = 0; y < blockData[x].length; y++) {
				for (int z = 0; z < blockData[x][y].length; z++) {
					if (blockData[x][y][z] != 0) {
						boolean[] borders = new boolean[6];
						if (x > 0) {
							borders[0] = blockData[x - 1][y][z] != 0;
						}
						if (x < 15) {
							borders[1] = blockData[x + 1][y][z] != 0;
						}
						if (y > 0) {
							borders[2] = blockData[x][y - 1][z] != 0;
						}
						if (y < 15) {
							borders[3] = blockData[x][y + 1][z] != 0;
						}
						if (z > 0) {
							borders[5] = blockData[x][y][z - 1] != 0;
						}
						if (z < 15) {
							borders[4] = blockData[x][y][z + 1] != 0;
						}
						new Block(x, y, z, atlas).render(drawable, borders);
					}
				}
			}
		}
	}
}