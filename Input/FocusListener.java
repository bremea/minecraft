import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FocusListener implements WindowListener {
	Camera camera;

	public FocusListener(Camera camera) {
		this.camera = camera;
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		camera.setWindowFocus(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		camera.setWindowFocus(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		camera.setWindowFocus(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		camera.setWindowFocus(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		camera.setWindowFocus(false);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		camera.setWindowFocus(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		camera.setWindowFocus(true);
	}

}
