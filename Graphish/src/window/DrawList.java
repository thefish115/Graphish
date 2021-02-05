package window;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import drawing.Drawable;
import drawing.FunctionR2X;

public class DrawList extends ArrayList<Drawable> implements Drawable {	private static final long serialVersionUID = 441869252152408099L;

	public DrawList(FunctionPanel pnl) {
		super(0);
		this.add(sin(pnl));
	}
	
	public static FunctionR2X sin(FunctionPanel pnl) {
		return new FunctionR2X(pnl, Math::sin, Color.BLACK);
	}

	@Override
	public void draw(Graphics g) {
		for(Drawable d : this) {
			d.draw(g);
		}
	}
}
