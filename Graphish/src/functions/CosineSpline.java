package functions;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.DoubleUnaryOperator;

import drawing.Clip;
import drawing.PointR2;

public class CosineSpline implements DoubleUnaryOperator {
	private HashMap<Clip, DoubleUnaryOperator> core;
	
	public CosineSpline(PointR2...points) {
		core = new HashMap<>(0);
		
		for(int pIndex = 0; pIndex < points.length - 1; pIndex++) {
			Clip clip = new Clip(
					points[pIndex],
					points[pIndex+1],
					null
					);
			double a = (clip.min.y - clip.max.y) / 2;
			double b = Math.PI / (clip.max.x - clip.min.x);
			double c = clip.min.x;
			double d = (clip.min.y + clip.max.y) / 2;
			DoubleUnaryOperator dUO = x -> a * Math.cos(b * (x - c)) + d;
			core.put(clip, dUO);
		}
	}

	@Override
	public double applyAsDouble(double x) {
		for(Entry<Clip, DoubleUnaryOperator> entry : core.entrySet()) {
			if(entry.getKey().containsX(x)) return entry.getValue().applyAsDouble(x);
		}
		return 0;
	}
}
