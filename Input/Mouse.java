import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;

public class Mouse extends MouseInputAdapter {
	Camera camera;

	Mouse(Camera camera) {
		this.camera = camera;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("clicked");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float x = (float) e.getX();
		float y = (float) e.getY();
		
		camera.updateMouse(x, y);
	}
}
