package functions;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.DoubleUnaryOperator;

import drawing.Clip;
import drawing.PointR2;

public class CubicSpline implements DoubleUnaryOperator {
	private HashMap<Clip, DoubleUnaryOperator> core;
	
	public CubicSpline(PointR2...points) {
		core = new HashMap<>(0);
		
		for(int pIndex = 0; pIndex < points.length - 1; pIndex++) {
			Clip clip = new Clip(
					points[pIndex],
					points[pIndex+1],
					null
					);
			double x0 = clip.min.x;
			double x1 = clip.max.x;
			double c = (
				Math.pow(x0,2)*clip.max.y*(x0 - 3*x1)/3	-
				Math.pow(x1,2)*clip.min.y*(x1 - 3*x0)/3
				) / (2*(clip.max.y - clip.min.y));
			double k =
					clip.min.y / (
						c			+
						Math.pow(x0,2)*x1/2	-
						Math.pow(x0,3)/6
					);
			DoubleUnaryOperator dUO = x -> k * (Math.pow(x, 3)/3 - (x0 + x1)*Math.pow(x, 2)/2 + x0*x1*x + c);
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
