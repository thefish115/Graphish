package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.function.UnaryOperator;
import javax.swing.JPanel;

import drawing.Clip;
import drawing.PointR2;

public class FunctionPanel extends JPanel {	private static final long serialVersionUID = 1958439464744943417L;

	public FunctionWindow window;
	public Clip clip = new Clip(new PointR2(-2*Math.PI, -1.2), new PointR2(2*Math.PI, 1.2));
	public DrawList drawList = new DrawList(this);
	
	public UnaryOperator<PointR2> deviceSpaceTransform() {
		return p -> new PointR2(
				this.getSize().getWidth() / (clip.max.x - clip.min.x) * (p.x - clip.min.x),
				this.getSize().getHeight() / (clip.min.y - clip.max.y) * (p.y - clip.max.y)
				);
	}
	
	public FunctionPanel(FunctionWindow w) {
		window = w;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle r = this.getBounds();
		g.setColor(new Color(255,255,255));
		g.fillRect(r.x, r.y, r.width, r.height);
		drawList.draw(g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return getParent().getSize();
	}
	public int getResolution() {
		return this.getBounds().width*5;
	}
}
