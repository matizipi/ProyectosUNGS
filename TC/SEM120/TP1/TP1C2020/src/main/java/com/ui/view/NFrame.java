package com.ui.view;

import javax.swing.JFrame;

public abstract class NFrame extends JFrame {

	protected int _xOsDifference;
	protected int _yOsDifference;
	
	public NFrame() {
		String str = System.getProperty("os.name").toString();
		
		if ( str.equals( "Windows 10" ) ) {
			this._xOsDifference = 22;
			this._yOsDifference = 45;
		} else {
			this._xOsDifference = 8;
			this._yOsDifference = 45;
		}
	}
}