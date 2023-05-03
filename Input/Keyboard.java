import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Keyboard extends AbstractAction {
	Camera camera;
	boolean pressed;
	int keyCode;

	Keyboard(Camera camera, boolean pressed, int keyCode) {
		this.camera = camera;
		this.pressed = pressed;
		this.keyCode = keyCode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (pressed) {
			camera.addKeyPressed(keyCode);
		} else {
			if (keyCode == 27) {
				camera.toggleCursorLock();
			} else {
				camera.removeKeyPressed(keyCode);
			}
		}
	}
}
