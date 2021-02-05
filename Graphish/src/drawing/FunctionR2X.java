package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import streams.R1Stream;
import window.FunctionPanel;

public class FunctionR2X implements Function<PointR2, Clip>, Drawable {
	
	private FunctionPanel pnl;
	private DoubleUnaryOperator f;
	private Color color = Color.BLACK;
	
	public FunctionR2X(FunctionPanel pnl, DoubleUnaryOperator dUO, Color c) {
		this.pnl = pnl;
		this.f = dUO;
		this.color = c;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(color);
		UnaryOperator<PointR2> dST = pnl.deviceSpaceTransform();
		
		R1Stream.xStream(pnl.clip, pnl.getResolution())
			.map(this::apply)
			.map(c ->
				new Clip(
					dST.apply(c.min),
					dST.apply(c.max)
					)
				)
			.limit(pnl.getResolution() + 1)
			.forEach(f -> g.drawLine((int)(f.min.x), (int)(f.min.y), (int)(f.max.x), (int)(f.max.y)));
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
				)
			);
	}
}
