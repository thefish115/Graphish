package window;

import java.awt.Color;
import java.util.ArrayList;
import java.util.stream.Stream;

import drawing.Streamable;
import objects.FunctionR2X;
import objects.Xaxis;
import objects.Yaxis;
import drawing.Clip;

public class DrawList extends ArrayList<Streamable> {	private static final long serialVersionUID = 441869252152408099L;

	public DrawList(FunctionPanel pnl) {
		super(0);
		
//===============================================================================================================================================
		add(new Yaxis(pnl));
		add(new Xaxis(pnl));
	//	Add the things you want to draw here
		add(new FunctionR2X(pnl, d -> Math.sin(d) + 0, Color.BLACK));
		add(new FunctionR2X(pnl, d -> Math.sin(d) + 1, Color.BLACK));
		
		
		
		
		
		
//===============================================================================================================================================	
	}
	public Stream<Clip> getClipStream() {
		if(this.size() < 1) return Stream.empty();
		return stream(0, this);
	}
	private Stream<Clip> stream(int index, DrawList dl) {
		if(this.size() - index == 1) {
			return this.get(index).stream();
		}
		return Stream.concat(this.get(index).stream(), stream(index + 1, this));
	}
}
