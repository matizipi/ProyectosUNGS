package main;

import javax.swing.UIManager;

import com.ui.controller.MainController;

public class Main {
	public static void main(String[] arg01) {
		
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		MainController mc = new MainController();
		
		mc.start();
	}
}