package objects;

import java.awt.Color;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import drawing.Clip;
import drawing.PointR2;
import drawing.Streamable;
import streams.R1Stream;
import window.FunctionPanel;

public class FunctionR2X implements Function<PointR2, Clip>, Streamable {
	
	private FunctionPanel pnl;
	private DoubleUnaryOperator f;
	private Color color = Color.BLACK;
	
	public FunctionR2X(FunctionPanel pnl, DoubleUnaryOperator dUO, Color c) {
		this.pnl = pnl;
		this.f = dUO;
		this.color = c;
	}
	
	@Override
	public Stream<Clip> stream() {
		
		return R1Stream.xStream(pnl.clip, pnl.getResolution())
			.map(this::apply)
			.limit(pnl.getResolution() + 1);
	}

	@Override
	public Clip apply(PointR2 p) {
		return new Clip(
			new PointR2(
				p.x,
				f.applyAsDouble(p.x)
				),
			new PointR2(
				p.y,
				f.applyAsDouble(p.y)
				),
			this.color
			);
	}
	public Color getColor() {
		return color;
	}
}
