import java.time.Clock;

public class Timer {
	
	private static long begin = 0L;

	static void start() {
		begin = Clock.systemDefaultZone().millis();
	}

	static long stop() {
		if(begin == 0) return 0L;
		return Clock.systemDefaultZone().millis() - begin;
	}
}