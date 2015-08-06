package com.FrobPlugins.appwarp;

import java.util.HashMap;

import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

public class NotificationListener implements NotifyListener
{	
	private WarpController callBack;
	
	public NotificationListener(WarpController callBack) 
	{
		this.callBack = callBack;
	}
	
	public void onChatReceived(ChatEvent event) 
	{
		
	}

	public void onRoomCreated(RoomData arg0) 
	{
		
	}

	public void onRoomDestroyed(RoomData arg0) 
	{
		
	}

	public void onUpdatePeersReceived(UpdateEvent event) 
	{
		callBack.onUpdatePeersReceived(new String(event.getUpdate()));
	}

	public void onUserJoinedLobby(LobbyData e, String userName) 
	{
		callBack.onUserJoinedLobby(e, userName);
	}

	public void onUserJoinedRoom(RoomData data, String username) 
	{
		callBack.onUserJoinedRoom(data.getId(), username);
	}

	public void onUserLeftLobby(LobbyData arg0, String arg1) 
	{
		
	}

	public void onUserLeftRoom(RoomData roomData, String userName) 
	{
		callBack.onUserLeftRoom(roomData.getId(), userName);
	}

	
	public void onGameStarted (String arg0, String arg1, String arg2) 
	{
		
	}
	
	
	public void onGameStopped (String arg0, String arg1) 
	{
		
	}

	
	public void onMoveCompleted (MoveEvent me) 
	{
		
	}

	
	public void onPrivateChatReceived (String arg0, String arg1) 
	{
		
	}

	
	public void onUserChangeRoomProperty (RoomData roomData, String userName, HashMap<String, Object> properties, HashMap<String, String> lockProperties) 
	{
		int code = Integer.parseInt( properties.get("result").toString() );
		callBack.onUserChangeRoomProperty(userName, code);
	}

	
	public void onUserPaused (String arg0, boolean arg1, String arg2) 
	{
		
	}

	
	public void onUserResumed (String arg0, boolean arg1, String arg2) 
	{
		
	}
}
