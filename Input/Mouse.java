import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;

public class Mouse extends MouseInputAdapter {
	Camera camera;

	Mouse(Camera camera) {
		this.camera = camera;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			camera.setLeftClickDown(true);
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			camera.setRightClickDown(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			camera.setLeftClickDown(false);
			camera.setAlreadyPlaced(false);
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			camera.setRightClickDown(false);
			camera.setAlreadyPlaced(false);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float x = (float) e.getX();
		float y = (float) e.getY();

		camera.updateMouse(x, y);
	}
}
