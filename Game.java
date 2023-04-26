import org.joml.Matrix4f;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

public class Game implements GLEventListener {
	InputManager inputManager;
	GLU glu = new GLU();
	Renderer render;
	World world;
	Matrix4f cam;

	private static final float ZERO_F = 0.0f;
	private static final float ONE_F = 1.0f;

	public Game(InputManager inputManager) {
		this.inputManager = inputManager;
		render = new Renderer();
		world = new World();
		cam = new Matrix4f();
		world.genRand();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// ! game loop, update then render
		final GL2 gl = drawable.getGL().getGL2();

		inputManager.refresh();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		cam.setRotationXYZ((float) Math.toRadians(inputManager.getPitch()), (float) Math.toRadians(inputManager.getYaw()), 0f);

		float[] matrix = new float[16];
		cam.get(matrix);
		gl.glMultMatrixf(matrix, 0);
		gl.glTranslatef(inputManager.getX(), inputManager.getY(), inputManager.getZ());
		render.renderWorld(inputManager, drawable, world);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// ...
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(ZERO_F, ZERO_F, ZERO_F, ZERO_F);
		gl.glClearDepth(ONE_F);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();

		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0f, h, 1.0, 2000.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
