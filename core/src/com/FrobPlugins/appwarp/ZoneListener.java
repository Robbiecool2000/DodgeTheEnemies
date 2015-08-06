package com.FrobPlugins.appwarp;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class ZoneListener implements ZoneRequestListener
{
	private WarpController callBack;
	
	public ZoneListener(WarpController callBack) 
	{
		this.callBack = callBack;
	}

	
	public void onCreateRoomDone(RoomEvent e) 
	{
		if( e.getResult() == WarpResponseResultCode.SUCCESS )
		{
			callBack.onCreateRoomDone(e.getData().getId());
		}
		else
		{
			callBack.onCreateRoomDone(null);
		}
	}

	
	public void onDeleteRoomDone(RoomEvent arg0) 
	{		
		
	}

	
	public void onGetAllRoomsDone (AllRoomsEvent arg0) 
	{
			
	}

	
	public void onGetLiveUserInfoDone (LiveUserInfoEvent arg0) 
	{	
		
	}

	
	public void onGetMatchedRoomsDone (MatchedRoomsEvent me) 
	{
		
	}

	
	public void onGetOnlineUsersDone (AllUsersEvent arg0) 
	{
			
	}

	
	public void onSetCustomUserDataDone (LiveUserInfoEvent arg0) 
	{	
		
	}
}
