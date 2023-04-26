import com.jogamp.opengl.GLAutoDrawable;

import Rendering.Block;

public class Renderer {
	Block block = new Block();

	public void renderWorld(InputManager inputManager, GLAutoDrawable drawable) {
		block.render(drawable);
	}
}