package test;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Test extends JFrame {	private static final long serialVersionUID = -982798754720311902L;

	private JLabel lbl;
	private JButton btn;
	
    private Test() {
    	super();
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setTitle("Test");
    	
    	Container pane = getContentPane();
    	pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	
    	lbl = new JLabel("0");
    	btn = new JButton("+1");
    	btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String lastNum = Test.this.lbl.getText();
				int newNum = 1 + Integer.parseInt(lastNum);
				Test.this.lbl.setText("" + newNum);
			}
		});
    	pane.add(lbl);
    	pane.add(btn);
    	pack();
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Test t = new Test();
			t.setVisible(true);
		});
	}
}
