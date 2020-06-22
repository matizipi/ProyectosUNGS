package com.app.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class NFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* Image Icon */
	private static String TITLE_ICON_PATH = "src/main/resources/img/Frame-Icon.png";
	protected ImageIcon _titleIcon;
	
	protected int _xOsDifference;
	protected int _yOsDifference;
	
	protected int spX = 7;
	protected int spY = 5;
	protected int fldH = 27;
	
	public NFrame() {
		this.setResizable( false );
		String str = System.getProperty("os.name").toString().toUpperCase();
		
		if ( str.contains("WINDOWS 10")/*( "Windows 10" )*/ ) {
			this._xOsDifference = 6;
			this._yOsDifference = 29;
		} else if( str.contains("WINDOWS 7") ) {
			this._xOsDifference = 7;
			this._yOsDifference = 29;
		} else {
			this._xOsDifference = 8;
			this._yOsDifference = 45;
		}
		
		this._titleIcon = new ImageIcon( TITLE_ICON_PATH );
		this.setIconImage( this._titleIcon.getImage() );
	}
}