package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Test extends JFrame {	private static final long serialVersionUID = -982798754720311902L;
	
	private final Dimension INITIAL_D = new Dimension(600,600);
	private JPanel pnl;
	private JLabel lbl;
	private JButton btn;
	
    private Test() {
    	super();
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setTitle("Test");
    	
    	Container pane = getContentPane();
    	pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	pane.setSize(INITIAL_D);
    	
    	pnl = new JPanel() {	private static final long serialVersionUID = 2629717016682715818L;
    		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Rectangle r = this.getBounds();
				g.setColor(new Color(0,0,0));
				g.fillRect(r.x, r.y, r.width, r.height);
			}
    		@Override
			public
    		Dimension getPreferredSize() {
				return getParent().getSize();
    		}};
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
    	pane.add(pnl);
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
