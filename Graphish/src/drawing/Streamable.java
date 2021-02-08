package drawing;

import java.util.stream.Stream;

public interface Streamable {
	public Stream<Clip> stream();
}
