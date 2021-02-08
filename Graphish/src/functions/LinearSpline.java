package functions;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.DoubleUnaryOperator;

import drawing.Clip;
import drawing.PointR2;

public class LinearSpline implements DoubleUnaryOperator {
	private HashMap<Clip, DoubleUnaryOperator> core;
	
	public LinearSpline(PointR2...points) {
		core = new HashMap<>(0);
		
		for(int pIndex = 0; pIndex < points.length - 1; pIndex++) {
			Clip clip = new Clip(
					points[pIndex],
					points[pIndex+1],
					null
					);
			double x0 = clip.min.x;
			double x1 = clip.max.x;
			double y0 = clip.min.y;
			double y1 = clip.max.y;
			double slope =
					(y1-y0)	/
					(x1-x0);
			DoubleUnaryOperator dUO = x -> slope*(x - x1) + y1;
			core.put(clip, dUO);
		}
	}

	@Override
	public double applyAsDouble(double x) {
		for(Entry<Clip, DoubleUnaryOperator> entry : core.entrySet()) {
			Clip c = entry.getKey();
			DoubleUnaryOperator dUO = entry.getValue();
			if(c.containsX(x)) {
				return dUO.applyAsDouble(x);
			} else if(x == c.min.x) {
				return c.min.y;
			} else if(x == c.max.x) {
				return c.max.y;
			}
		}
		return 0;
	}
}
