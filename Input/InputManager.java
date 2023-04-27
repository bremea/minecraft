import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.jogamp.newt.event.KeyEvent;

public class InputManager {
	float x;
	float y;
	float z;
	float pitch;
	float yaw;

	JFrame frame;

	float mouseX;
	float mouseY;
	float prevMouseX;
	float prevMouseY;
	float[] lookAt;
	float mouseXDelta;
	float mouseYDelta;

	float angleV;
	float angleH;
	boolean onUpperEdge;
	boolean onLowerEdge;
	boolean onLeftEdge;
	boolean onRightEdge;

	double canvasWidth;
	double canvasHeight;
	ArrayList<Integer> keysPressed;

	boolean windowInFocus;
	boolean movedMouseInit;

	float sensitivity;
	Robot robot;

	public final static String KEY_W = "KEY_W";
	public final static String KEY_S = "KEY_S";
	public final static String KEY_A = "KEY_A";
	public final static String KEY_D = "KEY_D";
	public final static String KEY_SPACE = "KEY_SPACE";
	public final static String KEY_SHIFT = "KEY_SHIFT";
	public final static int MARGIN = 10;
	public final static float EDGE_STEP = 1.0f;

	public InputManager(float sensitivity, JFrame frame) throws AWTException {
		super();
		pitch = 0f;
		yaw = 0f;
		x = 0f;
		y = 0f;
		z = 0f;
		mouseXDelta = 0f;
		mouseYDelta = 0f;
		this.sensitivity = sensitivity;
		robot = new Robot();
		this.frame = frame;
		canvasHeight = frame.getContentPane().getSize().getHeight();
		canvasWidth = frame.getContentPane().getSize().getWidth();
		windowInFocus = true;
		keysPressed = new ArrayList<Integer>();
		movedMouseInit = false;
	}

	public void updateMouse(float x, float y) {
		this.mouseX = x;
		this.mouseY = y;
	}

	public void refresh() {
		mouseYDelta = 0f;
		mouseXDelta = 0f;

		canvasHeight = frame.getContentPane().getSize().getHeight();
		canvasWidth = frame.getContentPane().getSize().getWidth();

		mouseXDelta = (int) (mouseX - prevMouseX);
		mouseYDelta = (int) (mouseY - prevMouseY);

		System.out.println(mouseXDelta + ", " + mouseYDelta);

		pitch += mouseYDelta * sensitivity;
		yaw += mouseXDelta * sensitivity;

		normalize();

		mouseX = (float) canvasWidth / 2;
		mouseY = (float) canvasHeight / 2;
		prevMouseX = mouseX;
		prevMouseY = mouseY;

		if (windowInFocus && ((mouseXDelta != 0f || mouseYDelta != 0f) || (!movedMouseInit && canvasHeight > 0))) {
			movedMouseInit = true;
			robot.mouseMove((int) (frame.getX() + (double) (frame.getWidth() - canvasWidth) + (double) canvasWidth / 2),
					(int) (frame.getY() + (double) (frame.getHeight() - canvasHeight) + (double) canvasHeight / 2));
		}

		for (int key : keysPressed) {
			switch (key) {
				case 87: { // W
					z += .33f * Math.cos(yaw / 57.2958);
					x -= .33f * Math.sin(yaw / 57.2958);
					break;
				}
				case 83: { // S
					z -= .33f * Math.cos(yaw / 57.2958);
					x += .33f * Math.sin(yaw / 57.2958);
					break;
				}
				case 65: { // A
					z -= .33f * Math.cos((yaw + 90) / 57.2958);
					x += .33f * Math.sin((yaw + 90) / 57.2958);
					break;
				}
				case 68: { // D
					z += .33f * Math.cos((yaw + 90) / 57.2958);
					x -= .33f * Math.sin((yaw + 90) / 57.2958);
					break;
				}
				case 32: { // SPACE
					y -= .33f;
					break;
				}
				case 16: { // SHIFT
					y += .33f;
					break;
				}
			}
		}
	}

	public double getSensitivity() {
		return sensitivity;
	}

	public float[] getLookAtVec() {
		return lookAt;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	synchronized public void addKeyPressed(int key) {
		keysPressed.add(key);
	}

	synchronized public void removeKeyPressed(int key) {
		keysPressed.removeAll(Collections.singleton((Integer) key));
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public float updateX(float val) {
		x += val;
		return x;
	}

	public float updateY(float val) {
		y += val;
		return y;
	}

	public float updateZ(float val) {
		z += val;
		return z;
	}

	public void setWindowFocus(boolean focus) {
		windowInFocus = focus;
	}

	public void normalize() {
		if (pitch > 89)
			pitch = 89;
		if (pitch < -89)
			pitch = -89;

		while (yaw < -180)
			yaw += 360;
		while (yaw > 180)
			yaw -= 360;

	}

	public void initKeyboard(JFrame frame) {
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(87, 0, true),
				KEY_W + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(83, 0, true),
				KEY_S + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(65, 0, true),
				KEY_A + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(68, 0, true),
				KEY_D + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(32, 0, true),
				KEY_SPACE + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(16, 0, true),
				KEY_SHIFT + "P");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(87, 0, false),
				KEY_W + "R");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(83, 0, false),
				KEY_S + "R");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(65, 0, false),
				KEY_A + "R");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(68, 0, false),
				KEY_D + "R");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(32, 0, false),
				KEY_SPACE + "R");
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(16, KeyEvent.SHIFT_MASK, false),
				KEY_SHIFT + "R");

		frame.getRootPane().getActionMap().put(KEY_W + "P", new Keyboard(this, false, 87));
		frame.getRootPane().getActionMap().put(KEY_S + "P", new Keyboard(this, false, 83));
		frame.getRootPane().getActionMap().put(KEY_A + "P", new Keyboard(this, false, 65));
		frame.getRootPane().getActionMap().put(KEY_D + "P", new Keyboard(this, false, 68));
		frame.getRootPane().getActionMap().put(KEY_SPACE + "P", new Keyboard(this, false, 32));
		frame.getRootPane().getActionMap().put(KEY_SHIFT + "P", new Keyboard(this, false, 16));
		frame.getRootPane().getActionMap().put(KEY_W + "R", new Keyboard(this, true, 87));
		frame.getRootPane().getActionMap().put(KEY_S + "R", new Keyboard(this, true, 83));
		frame.getRootPane().getActionMap().put(KEY_A + "R", new Keyboard(this, true, 65));
		frame.getRootPane().getActionMap().put(KEY_D + "R", new Keyboard(this, true, 68));
		frame.getRootPane().getActionMap().put(KEY_SPACE + "R", new Keyboard(this, true, 32));
		frame.getRootPane().getActionMap().put(KEY_SHIFT + "R", new Keyboard(this, true, 16));

	}

}