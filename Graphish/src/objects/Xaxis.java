package objects;

import java.awt.Color;
import java.util.stream.Stream;

import drawing.Clip;
import drawing.PointR2;
import drawing.Streamable;
import window.FunctionPanel;

public class Xaxis implements Streamable {
	private FunctionPanel pnl;
	private Color color = Color.DARK_GRAY;
	
	public Xaxis(FunctionPanel pnl) {
		this.pnl = pnl;
	}
	
	@Override
	public Stream<Clip> stream() {
		
		Clip c = new Clip(
				new PointR2(pnl.clip.min.x, 0),
				new PointR2(pnl.clip.max.x, 0),
				color
				);
		
		return c.stream();
	}
}
