package com.taffaz.ld29.game.utils;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

	public static Audio audio;
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/music.wav"));
	
	public static Sound hit = Gdx.audio.newSound(Gdx.files.internal("data/audio/hit.wav"));
	public static Sound chest = Gdx.audio.newSound(Gdx.files.internal("data/audio/ChestPickup.wav"));
	public static Sound bubble = Gdx.audio.newSound(Gdx.files.internal("data/audio/bubblePop.wav"));
	public static Sound swim = Gdx.audio.newSound(Gdx.files.internal("data/audio/swim.wav"));

}
