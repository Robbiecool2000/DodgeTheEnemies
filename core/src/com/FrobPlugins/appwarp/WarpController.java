package com.FrobPlugins.appwarp;


import java.nio.charset.Charset;
import java.util.HashMap;

import com.FrobPlugins.me.Level1;
import com.FrobPlugins.me.Main;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.server.domain.User;
import com.sun.deploy.uitoolkit.impl.fx.ui.resources.Deployment;

public class WarpController 
{
	private static WarpController instance = new WarpController();
	public static WarpController getInstance()
	{
		return instance;
	}
	
	private WarpClient warpClient;
	
	private String localUser = "bobber";
	private String roomId;
	
	public boolean isOnline = false;
	public boolean isConnected = false;
	boolean isUDPEnabled = false;
	
	String userName = "Nick";    
	String pwd = "********";    
	String emailId = "nick@shephertz.com";
	
	private WarpListener multiplayerScreenListener ;
	private WarpListener waitingRoomListener ;
	private WarpListener gameListener ;
	
	private int STATE;
	
	// Game state constants
	public static final int WAITING = 1;
	public static final int STARTED = 2;
	public static final int COMPLETED = 3;
	public static final int FINISHED = 4;
	
	// Game completed constants
	public static final int GAME_WIN = 5;
	public static final int GAME_LOOSE = 6;
	public static final int ENEMY_LEFT = 7;
	
	public static final String apiKey = "ba6ea5de6d9248268d09c9af50da00665eb2193831ebaa962740689a2c3426e9";
	public static final String secretKey = "c1d75dd6ec63123196bba526a9942428e8f5b2bf055196ae46c799310adc87c9";
	
	public WarpController() 
	{
		initAppwarp();
		warpClient.addConnectionRequestListener(new ConnectionListener(this));
		warpClient.addChatRequestListener(new ChatListener(this));
		warpClient.addZoneRequestListener(new ZoneListener(this));
		warpClient.addRoomRequestListener(new RoomListener(this));
		warpClient.addNotificationListener(new NotificationListener(this));
		warpClient.addLobbyRequestListener(new LobbyListener(this));
	}
	
	public void setMultiplayerScreenListener(WarpListener listener)
	{
		this.multiplayerScreenListener = listener;
	}
	
	public void setWaitingRoomListener(WarpListener listener)
	{
		this.waitingRoomListener = listener;
	}
	
	public void setGameListener(WarpListener listener)
	{
		this.waitingRoomListener = null;
		this.multiplayerScreenListener = null;
		this.gameListener = listener;
	}
	
	private void initAppwarp()
	{
		try 
		{
			WarpClient.initialize(apiKey,secretKey);
			warpClient = WarpClient.getInstance();
			WarpClient.enableTrace(true);
			System.out.println(Main.Message_INFO + "Initialized WarpClient class");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void startApp(String localUser)
	{
		this.localUser = localUser;
		warpClient.connectWithUserName(localUser);
	}
	
	public void stopApp()
	{
		if(isConnected)
		{
			warpClient.unsubscribeRoom(roomId);
			warpClient.leaveRoom(roomId);
			//warpClient.unsubscribeLobby();
			//warpClient.leaveLobby();
		}
		
		warpClient.disconnect();
	}
	
	public void startGame()
	{
		STATE = STARTED;
		waitingRoomListener.onGameStarted("Start the Game");
	}
	
	public void sendGameUpdate(String msg)
	{
		if(isConnected)
		{
			if(isUDPEnabled)
			{
				warpClient.sendUDPUpdatePeers((localUser+"#@"+msg).getBytes(Charset.forName("utf-8")));
			}
			else
			{
				warpClient.sendUpdatePeers((localUser+"#@"+msg).getBytes(Charset.forName("utf-8")));
			}
		}
	}
	
	public void updateResult(int code, String msg)
	{
		if(isConnected)
		{
			STATE = COMPLETED;
			HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("result", code);
			warpClient.lockProperties(properties);
		}
	}
	
	public int getState()
	{
		return this.STATE;
	}
		
	private void waitForOtherUser()
	{
		STATE = WAITING;
		waitingRoomListener.onWaitingStarted("Waiting for other user");
	}
	
	private void handleError()
	{
		if( roomId!=null && roomId.length() > 0 )
		{
			warpClient.deleteRoom(roomId);
		}
		disconnect();
	}
	
	public void handleLeave()
	{
		if(isConnected)
		{
			
			warpClient.unsubscribeRoom(roomId);
			warpClient.leaveRoom(roomId);
			
			if( STATE != STARTED )
			{
				warpClient.deleteRoom(roomId);
			}
			
			warpClient.disconnect();
		}
	}
	
	private void disconnect()
	{
		warpClient.removeConnectionRequestListener(new ConnectionListener(this));
		warpClient.removeChatRequestListener(new ChatListener(this));
		warpClient.removeZoneRequestListener(new ZoneListener(this));
		warpClient.removeRoomRequestListener(new RoomListener(this));
		warpClient.removeNotificationListener(new NotificationListener(this));
		warpClient.disconnect();
		
		isOnline = false;
	}
	
	public void joinRandomRoom()
	{
		warpClient.joinRoomInRange(4, 4, false);
	}
	
	//---CHAT LISTENER
	public void onSendChatDone(boolean status)
	{
	}
	//---
	
	//---CONNECTION LISTENER
	
    public void onConnectDone(boolean status){  
        if(status){  
            warpClient.initUDP();  
            warpClient.joinRoomInRange(1, 1, false);  
            System.out.println(Main.Message_INFO + "Joined lobby");
        }else{  
            isConnected = false;  
            handleError();
            System.out.println(Main.Message_ERROR + "Unable to connect.");
        }  
    }  
	public void onDisconnectDone(boolean status)
	{
	}
	public void setIsUDPEnabled(boolean isEnabled)
	{
		this.isUDPEnabled = isEnabled;
	}
	//---
	
	//---NOTIFICATION LISTENER
	public void onUserJoinedRoom(String roomId, String userName)
	{		
		//warpClient.getLiveRoomInfo(roomId);
		
		
		
		/*
		 * if room id is same and username is different then start the game
		 */
		//if( localUser.equals(userName) == false )
		//{
		//	startGame();
		//}
	}
	public void onUserLeftRoom(String roomId, String userName)
	{
		if(STATE==STARTED && !localUser.equals(userName))
		{// Game Started and other user left the room
			gameListener.onGameFinished(ENEMY_LEFT, true);
			System.out.println(Main.Message_INFO + "User left room");
		}
	}
	public void onUpdatePeersReceived(String message)
	{
		
		String userName = message.substring(0, message.indexOf("#@"));
		String data = message.substring(message.indexOf("#@")+2, message.length());
		
		if( !localUser.equals(userName))
		{
			if(waitingRoomListener != null)
				waitingRoomListener.onGameUpdateReceived(data);
			else if(gameListener != null)
				gameListener.onGameUpdateReceived(data);
		}
	}
	public void onUserChangeRoomProperty(String userName, int code)
	{		
		if( localUser.equals(userName) == false )
		{
			STATE = FINISHED;
			multiplayerScreenListener.onGameFinished(code, true);
		}
		else
		{
			multiplayerScreenListener.onGameFinished(code, false);
		}
	}
	public void onUserJoinedLobby(LobbyData data, String userName)
	{
	}
	//---
	
	//---ROOM LISTENER
	public void onJoinRoomDone(RoomEvent event)
	{		
		if( event.getResult() == WarpResponseResultCode.SUCCESS )
		{// success case
			this.roomId = event.getData().getId();
			//warpClient.joinRoom(roomId);
			warpClient.subscribeRoom(roomId);
		}
		else if( event.getResult() == WarpResponseResultCode.RESOURCE_NOT_FOUND )
		{// no such room found
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("result", "");
			warpClient.createRoom("superjumper", "shephertz", 4, data);
		}
		else
		{
			warpClient.disconnect();
			handleError();
		}
	}
	public void onSubscribeRoomDone(String roomId)
	{
		if( roomId != null )
		{
			isConnected = true;
			warpClient.getLiveRoomInfo(roomId);
		}
		else
		{
			warpClient.disconnect();
			handleError();
		}
	}
	public void onGetLiveRoomInfoDone(String[] liveUsers)
	{
		if( liveUsers.length == 3 )
		{
			startGame();	
		}
		else
		{
			waitForOtherUser();
		}
	}
	//---
	
	//---ZONE LISTENER
	public void onCreateRoomDone(String roomId)
	{		
		if( roomId != null )
		{
			warpClient.joinRoom(roomId);
			warpClient.subscribeRoom(roomId);
		}
		else
		{
			handleError();
		}
	}
	//--
	public void  sendMove ( String  moveData ){
		
	}
	
	public void onMoveCompleted(MoveEvent moveEvent) {
            if (moveEvent.getMoveData() != null) {
            	System.out.println("Move Completed");
        }
	}
	//---LOBBY LISTENER
	public void onJoinLobbyDone(LobbyData data)
	{
		
	}
	
	public void onSubscribeLobbyDone(LobbyData data)
	{
		
	}
	//---
}