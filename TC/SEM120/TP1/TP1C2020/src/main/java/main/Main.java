package main;

import javax.swing.UIManager;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.ui.controller.MainController;

public class Main {
	public static void main(String[] arg01) {
		
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, "Arranca apición TP. " ) );
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, "Crea control principal " ) );
		MainController mc = new MainController();
		
		Log.WriteFileLog( new Msg( Msg.INFO, null, "Arranca aplicación" ) );
		mc.start();
	}
}