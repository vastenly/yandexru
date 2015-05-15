package com.firstutility.taf.core.ui.controls.devices;

import java.awt.AWTException;
import java.awt.Robot;

public class Keyboard {
	static Robot robot;

	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void press(Keys keys) {
		robot.keyPress(keys.getKeyCode());
		robot.keyRelease(keys.getKeyCode());
	}

	public static void press(Keys... keys) {
		for (Keys key : keys) {
			robot.keyPress(key.getKeyCode());
		}

		for (Keys key : keys) {
			robot.keyRelease(key.getKeyCode());
		}
	}

	public static class KeysCombs {
		public KeysCombs() {

		}

		public static Keys[] put(Keys... keys) {
			return new Keys[] { keys[0], keys[1] };
		}

	}
}
