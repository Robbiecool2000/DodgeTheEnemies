package com.FrobPlugins.appwarp;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;

public class RoomListener implements RoomRequestListener
{	
	private WarpController callBack;
	
	public RoomListener(WarpController callBack) 
	{
		this.callBack = callBack;
	}
	
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) 
	{
		if( event.getResult() == WarpResponseResultCode.SUCCESS )
		{
			callBack.onGetLiveRoomInfoDone( event.getJoinedUsers() );
		}
		else
		{
			callBack.onGetLiveRoomInfoDone(null);
		}
	}

	public void onJoinRoomDone(RoomEvent event) 
	{
		callBack.onJoinRoomDone(event);
	}

	public void onLeaveRoomDone(RoomEvent arg0) 
	{
		
	}

	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) 
	{
		
	}

	public void onSubscribeRoomDone(RoomEvent event) 
	{
		if( event.getResult() == WarpResponseResultCode.SUCCESS )
		{
			callBack.onSubscribeRoomDone( event.getData().getId() );
		}
		else
		{
			callBack.onSubscribeRoomDone(null);
		}
	}

	public void onUnSubscribeRoomDone(RoomEvent arg0) 
	{
		
	}

	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) 
	{
		
	}

	
	public void onLockPropertiesDone (byte result) 
	{
		
	}

	
	public void onUnlockPropertiesDone (byte arg0) 
	{
		
	}
}