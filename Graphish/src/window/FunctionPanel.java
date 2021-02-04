package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import javax.swing.JPanel;

import drawing.Clip;
import drawing.PointR2;

public class FunctionPanel extends JPanel {	private static final long serialVersionUID = 1958439464744943417L;

	private FunctionWindow window;
	
	private UnaryOperator<PointR2> deviceSpaceTransform(Dimension device, Clip view) {
		
		return p -> new PointR2(
				device.getWidth() / (view.max.x - view.min.x) * (p.x - view.min.x),
				device.getHeight() / (view.min.y - view.max.y) * (p.y - view.max.y)
				);
	}
	
	public FunctionPanel(FunctionWindow w) {
		window = w;
	}
	
	private void paintFunction(Graphics g) {
		g.setColor(Color.BLACK);
		int resolution = getResolution();
		UnaryOperator<PointR2> dST = deviceSpaceTransform(this.getSize(), this.window.clip);
		
		DoubleStream.iterate(window.clip.min.x, d -> d + (window.clip.max.x - window.clip.min.x)/resolution)
			.mapToObj(e -> new PointR2(e, e + (window.clip.max.x - window.clip.min.x)/resolution))
			.map(p ->
				new Clip(
					new PointR2(
						p.x,
						window.function.applyAsDouble(p.x)
						),
					new PointR2(
						p.y,
						window.function.applyAsDouble(p.y)
						)
					)
				)
			.map(c ->
				new Clip(
					dST.apply(c.min),
					dST.apply(c.max)
					)
				)
			.limit(resolution + 1)
			.forEach(f -> g.drawLine((int)(f.min.x), (int)(f.min.y), (int)(f.max.x), (int)(f.max.y)));
			
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle r = this.getBounds();
		g.setColor(new Color(255,255,255));
		g.fillRect(r.x, r.y, r.width, r.height);
		paintFunction(g);
		
	}
	@Override
	public Dimension getPreferredSize() {
		return getParent().getSize();
	}
	private int getResolution() {
		return this.getBounds().width*5;
	}
}
