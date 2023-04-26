import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.AWTException;

import javax.swing.JFrame;

public class Main {
	static GLCanvas canvas;
	static GLCapabilities capabilities;
	static GLProfile profile;
	static FPSAnimator animator;
	static JFrame frame;
	static InputManager inputManager;

	public final static int width = 1000;
	public final static int height = 1000;

	public static void main(String[] args) throws AWTException {
		frame = new JFrame("not minecraft");

		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
		canvas = new GLCanvas(capabilities);

		inputManager = new InputManager(0.1f, frame);

		Game game = new Game(inputManager);
		canvas.addGLEventListener(game);
		canvas.setSize(width, height);

		frame.getContentPane().add(canvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setLocation(0, 0);
		frame.setVisible(true);
		canvas.addMouseMotionListener(new Mouse(inputManager));
		frame.addWindowListener(new FocusListener(inputManager));

		inputManager.initKeyboard(frame);
		
		animator = new FPSAnimator(60);
		animator.add(canvas);
		animator.start();
	}
}
