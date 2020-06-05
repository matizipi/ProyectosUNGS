package main;

import javax.swing.UIManager;

import com.common.log.Log;
import com.ui.controller.MainController;

import helper.Msg;

public class Main {
	public static void main(String[] arg01) {
		
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, " Arranca apición TP. " ) );
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, " Crea control principal " ) );
		MainController mc = new MainController();
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, "Arranca aplicación" ) );
		mc.start();
	}
}