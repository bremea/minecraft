import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.joml.Matrix4f;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import Shaders.Shader;

public class Game implements GLEventListener {
	Camera camera;
	GLU glu = new GLU();
	Renderer render;
	World world;
	Matrix4f cam;
	TextureData textureData;
	GLProfile profile;
	int atlasId;
	Shader shader;
	Texture atlas;

	private static final float ZERO_F = 0.0f;
	private static final float ONE_F = 1.0f;
	public static final int TILE_W = 16;
	public static final int TILE_H = 16;
	public static final int CHANNELS = 4;
	public static final int TILES_X = 4;
	public static final int TILES_Y = 4;
	public static final int IMAGE_COUNT = TILES_X * TILES_Y;

	public Game(Camera camera, GLProfile profile) {
		this.camera = camera;
		this.profile = profile;
		render = new Renderer();
		world = new World();
		cam = new Matrix4f();
		// world.genRand();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// ! game loop, update then render
		final GL2 gl = drawable.getGL().getGL2();
		// shader.use(gl);

		camera.refresh();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glEnable(GL2.GL_TEXTURE_2D); // ! no more colors

		cam.setRotationXYZ((float) Math.toRadians(camera.getPitch()), (float) Math.toRadians(camera.getYaw()), 0f);
		float[] matrix = new float[16];
		cam.get(matrix);
		gl.glMultMatrixf(matrix, 0);
		gl.glTranslatef(camera.getX(), camera.getY(), camera.getZ());
		render.renderWorld(camera, drawable, world, atlas, shader);
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// ...
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glClearColor(ZERO_F, ZERO_F, ZERO_F, ZERO_F);
		gl.glClearDepth(ONE_F);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		gl.glEnable(GL.GL_CULL_FACE);

		try {
			textureData = TextureIO.newTextureData(profile,
					new File("Textures/atlas.png"), false, TextureIO.PNG);
			atlas = TextureIO.newTexture(textureData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// shader = new Shader("Shaders/basic_texture.vs", "Shaders/basic_texture.fs",
		// gl);

		atlasId = atlas.getTextureObject();
		atlas.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
		atlas.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
		atlas.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP_TO_EDGE);
		atlas.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP_TO_EDGE);
		atlas.setTexParameteri(gl, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
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
