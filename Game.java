import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Vector;

import org.joml.Matrix4f;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class Game implements GLEventListener {
	Camera camera;
	GLU glu = new GLU();
	Renderer render;
	World world;
	Matrix4f cam;
	TextureData textures;
	GLProfile profile;

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
		world.genRand();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// ! game loop, update then render
		final GL2 gl = drawable.getGL().getGL2();

		camera.refresh();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);

		cam.setRotationXYZ((float) Math.toRadians(camera.getPitch()), (float) Math.toRadians(camera.getYaw()), 0f);
		float[] matrix = new float[16];
		cam.get(matrix);
		gl.glMultMatrixf(matrix, 0);
		gl.glTranslatef(camera.getX(), camera.getY(), camera.getZ());
		render.renderWorld(camera, drawable, world, textures);
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
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL2.GL_TEXTURE_3D);

		try {
			textures = TextureIO.newTextureData(profile,
					new File("Textures/atlas.png"), false, TextureIO.PNG);
		} catch (IOException e) {
			e.printStackTrace();
		}

		gl.glTexImage3D(GL2.GL_TEXTURE_2D_ARRAY, 0, GL2.GL_RGBA,
				TILE_W, TILE_H, IMAGE_COUNT, 0,
				GL2.GL_RGBA, GL2.GL_BYTE, textures.getBuffer());

				Vector<Byte> tile = new Vector<Byte>(TILE_W * TILE_H * CHANNELS);
int tileSizeX = TILE_W * CHANNELS;
int rowLen    = TILES_X * tileSizeX;

for (int iy = 0; iy < tilesY; ++ iy)
{
    for (int ix = 0; ix < tilesX; ++ ix)
    {

		BufferedImage tex = 

        glTexSubImage3D(GL_TEXTURE_2D_ARRAY, 0,
            0, 0, i,
            tileW, tileH, 1,
            GL2.GL_RGBA, GL_UNSIGNED_BYTE, tile.data());
    }
}

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
