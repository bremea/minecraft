import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class Keyboard extends AbstractAction {
	InputManager inputManager;
	boolean pressed;
	int keyCode;

	Keyboard(InputManager inputManager, boolean pressed, int keyCode) {
		this.inputManager = inputManager;
		this.pressed = pressed;
		this.keyCode = keyCode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (pressed) {
			inputManager.addKeyPressed(keyCode);
		} else {
			inputManager.removeKeyPressed(keyCode);
		}
	}
}
