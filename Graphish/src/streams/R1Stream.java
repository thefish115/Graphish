package streams;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import drawing.Clip;
import drawing.PointR2;

public class R1Stream {
	public static Stream<PointR2> xStream(Clip c, int resolution) {
		return DoubleStream.iterate(c.min.x, d -> d + (c.max.x - c.min.x)/resolution)
				.mapToObj(e -> new PointR2(e, e + (c.max.x - c.min.x)/resolution));
	}
}
