package window;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FunctionWindow extends JFrame {	private static final long serialVersionUID = -982798754720311902L;
	
	private Dimension dim = new Dimension(600,600);
	private FunctionPanel pnl;
	
    private FunctionWindow() {
    	super();
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setTitle("Function Viewer");
    	
    	Container pane = getContentPane();
    	pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	pane.setSize(dim);
    	
    	pnl = new FunctionPanel(this);
    	
    	pane.add(pnl);
    	pack();
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			FunctionWindow t = new FunctionWindow();
			t.setVisible(true);
		});
	}
}
