import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Collections;

public class InputManager {
	float x;
	float y;
	float z;
	float pitch;
	float yaw;

	boolean isaf = true;

	float mouseX;
	float mouseY;
	float prevMouseX;
	float prevMouseY;

	int canvasWidth;
	int canvasHeight;
	ArrayList<Integer> keysPressed;

	boolean windowInFocus;

	float sensitivity;
	Robot robot;

	public InputManager(float sensitivity, int w, int h) throws AWTException {
		super();
		x = 0f;
		y = 0f;
		z = 0f;
		pitch = 0f;
		yaw = 0f;
		this.sensitivity = sensitivity;
		robot = new Robot();
		canvasWidth = w;
		canvasHeight = h;
		windowInFocus = true;
		keysPressed = new ArrayList<Integer>();
	}

	public void updateMouse(float x, float y) {
		this.mouseX = x;
		this.mouseY = y;
	}

	public void refresh() {
		float mouseXDiff = mouseX - prevMouseX;
		float mouseYDiff = mouseY - prevMouseY;
		prevMouseX = mouseX;
		prevMouseY = mouseY;

		yaw += mouseXDiff * sensitivity;
		pitch += mouseYDiff * sensitivity;

		//normalize();

		if (isaf) {
			robot.mouseMove(canvasWidth / 2, canvasHeight / 2);
			isaf = false;
		}

		System.out.println(x + ", " + z);

		for (int key : keysPressed) {
			switch (key) {
				case 87: { // W
					z += .33f;
					break;
				}
				case 83: { // S
					z -= .33f;
					break;
				}
				case 65: { // A
					x += .33f;
					break;
				}
				case 68: { // D
					x -= .33f;
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

	public void normalize() {
		if (pitch > 89) pitch = 89;
		if (pitch < -89) pitch = 89;

		while (yaw < -180) yaw += 360;
		while (yaw > 180) yaw -= 360;
	}

	public double getSensitivity() {
		return sensitivity;
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

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	synchronized public void addKeyPressed(int key) {
		keysPressed.add(key);
	}

	synchronized public void removeKeyPressed(int key) {
		keysPressed.removeAll(Collections.singleton((Integer) key));
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

	public float updatePitch(float val) {
		pitch += val;
		return pitch;
	}

	public float updateYaw(float val) {
		yaw += val;
		return yaw;
	}

	public void setWindowFocus(boolean focus) {
		windowInFocus = focus;
	}

}