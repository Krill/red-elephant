package Main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Engine.GameClient;
import Engine.ServerEngine;
import GUI.GameView;
import GUI.StartScreen;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryo.*;

/**
 * 
 * @author kristoffer & johan
 */
public class Main {

	// fields:
	private static StartScreen startScreen;
	private static GameView gameView;
	private static ServerEngine serverEngine;
	private static GameClient gameClient;
	private static Thread gameClientThread;
	private static Thread serverEngineThread;
	private static Thread viewThread;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {	
		startScreen = new StartScreen();
	}
	
	public static void hostGame(){
		// Start server
		serverEngine = new ServerEngine();
		serverEngineThread = new Thread(serverEngine);
		System.out.println("[MAIN][THREAD] Starting serverEngineThread...");
		serverEngineThread.start();
		// Connect to the server
		//System.out.println("Trying to join Server...");
		//try {joinGame(InetAddress.getLocalHost().getHostAddress());} catch (UnknownHostException e) {e.printStackTrace();}
	}
	
	public static void joinGame(String localAddress){
		
		System.out.println("[MAIN][GAMECLIENT] Creating gameClient");
		gameClient = new GameClient("ClientPlayer", localAddress);
		gameClientThread = new Thread(gameClient);
		
		gameView = new GameView(gameClient);
		viewThread = new Thread(gameView);
		
		System.out.println("[MAIN][THREAD] Starting gameViewThread");
		viewThread.start();
		System.out.println("[MAIN][THREAD] Starting gameClientThread");
		gameClientThread.start();
		
	}
	
	/**
	 * When the Player has loaded a current state of the game old
	 * threads stop and new ones are created. Loads the new state.
	 */
	@SuppressWarnings("deprecation")
	public static void restart(){
		if(inGame){
			engineThread.stop();
			viewThread.stop();
			gameView.dispose();
		}
	
		gameView = new GameView(gameClient);
		
		engineThread = new Thread(gameClient);
		viewThread = new Thread(gameView);
		
		engineThread.start();
		viewThread.start();
	}
}
