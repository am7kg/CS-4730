package edu.virginia.engine.sound;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	HashMap<String, Clip> mySoundManager = new HashMap<String, Clip>();
	//MediaPlayer mp = new MediaPlayer();
	
	public SoundManager(){
	}
	
	public void LoadSoundEffect(String id, String fileName){
		Clip sfx = null;
		
		try 
		{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
			sfx = AudioSystem.getClip();
			sfx.open( audioIn );
		}
		catch ( Exception e ){
			e.printStackTrace();
		}
		
		if (sfx != null)
			if (!mySoundManager.containsKey(id))
				mySoundManager.put(id, sfx);
		
	}
	
	public void PlaySoundEffect(String id) {
		 // sound effects are short and removed once complete
		if ( mySoundManager.get(id).isRunning() )
			mySoundManager.get(id).stop();
		mySoundManager.get(id).setFramePosition(0);
		mySoundManager.get(id).start();
	}

	public void LoadMusic(String id, String fileName){
		Clip music = null;
		
		try 
		{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
			music = AudioSystem.getClip();
			music.open( audioIn );
		}
		catch ( Exception e ){
			e.printStackTrace();
		}
		
		if (music != null) 
			if (!mySoundManager.containsKey(id))
				mySoundManager.put(id, music);
		
		
	}
	
	public void PlayMusic(String id , int loop){
		//loops finitely
		if ( mySoundManager.get(id).isRunning() )
			mySoundManager.get(id).stop();
		mySoundManager.get(id).setFramePosition(0);
		mySoundManager.get(id).loop(loop);
	}
	
	@SuppressWarnings("static-access")
	public void PlayMusic(String id){
		//plays forever
		if ( mySoundManager.get(id).isRunning() )
			mySoundManager.get(id).stop();
		mySoundManager.get(id).setFramePosition(0);
		mySoundManager.get(id).loop(mySoundManager.get(id).LOOP_CONTINUOUSLY);
	}
	
	public void StopMusic(String id){
		mySoundManager.get(id).stop();
	}

	/**
	 * @return the mySoundManager
	 */
	public HashMap<String, Clip> getMySoundManager() {
		return mySoundManager;
	}

	/**
	 * @param mySoundManager the mySoundManager to set
	 */
	public void setMySoundManager(HashMap<String, Clip> mySoundManager) {
		this.mySoundManager = mySoundManager;
	}
	
}
