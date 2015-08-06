package com.FrobPlugins.appwarp;

import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.LobbyRequestListener;

public class LobbyListener implements LobbyRequestListener
{
	private WarpController callBack;
	
	public LobbyListener(WarpController callBack) 
	{
		this.callBack = callBack;
	}

	public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onJoinLobbyDone(LobbyEvent e) 
	{
		callBack.onJoinLobbyDone( e.getInfo() );
	}

	public void onLeaveLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onSubscribeLobbyDone(LobbyEvent e) 
	{
		callBack.onSubscribeLobbyDone( e.getInfo() );	
	}

	public void onUnSubscribeLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
