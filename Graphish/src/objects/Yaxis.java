package objects;

import java.awt.Color;
import java.util.stream.Stream;

import drawing.Clip;
import drawing.PointR2;
import drawing.Streamable;
import window.FunctionPanel;

public class Yaxis implements Streamable {
	private FunctionPanel pnl;
	private Color color = Color.DARK_GRAY;
	
	public Yaxis(FunctionPanel pnl) {
		this.pnl = pnl;
	}
	
	@Override
	public Stream<Clip> stream() {
		
		Clip c = new Clip(
				new PointR2(0, pnl.clip.min.y),
				new PointR2(0, pnl.clip.max.y),
				color
				);
		
		return c.stream();
	}
}
