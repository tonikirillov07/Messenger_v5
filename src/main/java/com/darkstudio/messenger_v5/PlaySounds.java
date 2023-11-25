package com.darkstudio.messenger_v5;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

class PlaySounds{
	File soundFile;
	float volume;

	public PlaySounds(File soundFile, float volume) {
		this.soundFile = soundFile;
		this.volume = volume;
	}

	public void play() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

			Clip clip = AudioSystem.getClip();
			clip.open(ais);

			FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			floatControl.setValue(volume);

			clip.setFramePosition(0);
			clip.start();
		}catch (Exception e){
			new ShowExceptions().showException(e);
		}
	}

	public static class Constants{
		public static final File CLICK_SOUND = new File("sounds/click.wav");
		public static final File CONNECT_SOUND = new File("");
		public static final File CHECK_SOUND = new File("");
		public static final File ENTER_SOUND = new File("");
		public static final File ERROR_SOUND = new File("sounds/error.wav");

	}
}