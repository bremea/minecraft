import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;

public class Mouse extends MouseInputAdapter {
	InputManager inputManager;

	Mouse(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 0) {
			System.out.println("clicked");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float x = (float) e.getX();
		float y = (float) e.getY();
		
		inputManager.updateMouse(x, y);
	}
}
