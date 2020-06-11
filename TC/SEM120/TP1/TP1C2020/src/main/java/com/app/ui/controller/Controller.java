package com.app.ui.controller;

import com.app.common.Msg;
import com.app.common.log.Log;

public abstract class Controller implements ControllerImpl {
	
	public void printLog( Msg msg ) {
	Log.WriteFileLog( msg );
	}
}