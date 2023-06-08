package de.duengung.bw;

public class Sleeper {

	public static void sleepALittleBit() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException("Thread was interrupted");
		}
	}
	
	public static void sleepALittleBitLonger() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException("Thread was interrupted");
		}
	}

}
