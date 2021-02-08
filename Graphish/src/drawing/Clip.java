package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.Stream;

public class Clip {
	public final PointR2 min, max;
	public Color color;
	
	public Clip(PointR2 min, PointR2 max, Color c) {
		this.min = min;
		this.max = max;
		this.color = c;
	}

	public Stream<Clip> stream() {
		return Stream.of(this);
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine((int)(min.x), (int)(min.y), (int)(max.x), (int)(max.y));
	}
	
	public boolean containsX(double x) {
		if(min.x < max.x) {
			return (x > min.x && x < max.x) ? true : false;
		} else if(max.x < min.x) {
			return (x < min.x && x > max.x) ? true : false;
		}
		return false;
	}
}
