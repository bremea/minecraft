import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FocusListener implements WindowListener {
	InputManager inputManager;

	public FocusListener(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		inputManager.setWindowFocus(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		inputManager.setWindowFocus(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		inputManager.setWindowFocus(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		inputManager.setWindowFocus(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		inputManager.setWindowFocus(false);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		inputManager.setWindowFocus(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		inputManager.setWindowFocus(true);
	}

}
