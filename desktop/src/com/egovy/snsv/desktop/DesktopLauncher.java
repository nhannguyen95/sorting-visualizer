package com.egovy.snsv.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.egovy.snsv.SnsVisualizer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1300;
		config.height = 750;
		new LwjglApplication(new SnsVisualizer(), config);
	}
}
