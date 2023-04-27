import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main {
	static GLCanvas canvas;
	static GLCapabilities capabilities;
	static GLProfile profile;
	static FPSAnimator animator;
	static JFrame frame;
	static Camera camera;

	public final static int width = 1000;
	public final static int height = 1000;

	public static void main(String[] args) throws AWTException {
		frame = new JFrame("not minecraft");

		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
		canvas = new GLCanvas(capabilities);

		camera = new Camera(0.1f, frame);

		Game game = new Game(camera, profile);
		canvas.addGLEventListener(game);
		canvas.setSize(width, height);

		frame.getContentPane().add(canvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setLocation(0, 0);
		frame.setVisible(true);
		canvas.addMouseMotionListener(new Mouse(camera));
		frame.addWindowListener(new FocusListener(camera));
		frame.setCursor(frame.getToolkit().createCustomCursor(
				new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
				"null"));

		camera.initKeyboard(frame);

		animator = new FPSAnimator(60);
		animator.add(canvas);
		animator.start();
	}
}
