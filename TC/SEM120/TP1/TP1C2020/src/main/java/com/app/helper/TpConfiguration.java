package com.app.helper;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class TpConfiguration {

	private static TpConfiguration instance;
	
	public static String COMMON_PAR_ROW = "commmon_par_row";
	public static String COMMON_INPAR_ROW = "commmon_impar_row";
	public static String COMMON_SELECTED = "commmon_selected";
	public static String LOG_PAR_ROW = "log_par_row";
	public static String LOG_INPAR_ROW = "log_impar_row";
	public static String LOG_SELECTED = "log_selected";
	
	private static String R = ".R";
	private static String G = ".G";
	private static String B = ".B";
	
	private static String ACTIVE = ".ACTIVE";
	
	private Properties _prop;
	
	private TpConfiguration() {
		this._prop = new Properties();
		
		try {
			FileInputStream in = new FileInputStream( "TpConfiguration.properties" );
			this._prop.load( in );
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		if( this._prop.get( COMMON_PAR_ROW + "." + R ) == null ) {
			this.loadDefault();
		}
	}
	
	public static TpConfiguration getInstance() {
		if( instance == null ) {
			instance = new TpConfiguration();
		}
		
		return instance;
	}
	
	private void loadDefault() {
		this._prop.put(COMMON_PAR_ROW + R, "255");
		this._prop.put(COMMON_PAR_ROW + G, "255");
		this._prop.put(COMMON_PAR_ROW + B, "255");
		
		this._prop.put(COMMON_INPAR_ROW + R, "210");
		this._prop.put(COMMON_INPAR_ROW + G, "239");
		this._prop.put(COMMON_INPAR_ROW + B, "255");
		
		this._prop.put(COMMON_SELECTED + R, "190");
		this._prop.put(COMMON_SELECTED + G, "190");
		this._prop.put(COMMON_SELECTED + B, "190");
		
		this._prop.put(LOG_PAR_ROW + R, "255");
		this._prop.put(LOG_PAR_ROW + G, "255");
		this._prop.put(LOG_PAR_ROW + B, "255");
		
		this._prop.put(LOG_INPAR_ROW + R, "220");
		this._prop.put(LOG_INPAR_ROW + G, "220");
		this._prop.put(LOG_INPAR_ROW + B, "220");
		
		this._prop.put(LOG_SELECTED + ACTIVE, "false");
		this._prop.put(LOG_SELECTED + R, "190");
		this._prop.put(LOG_SELECTED + G, "190");
		this._prop.put(LOG_SELECTED + B, "190");
		
		this.saveConfiguration();
	}
	
	private void saveConfiguration() {
		
		try {
			FileOutputStream out = new FileOutputStream( "TpConfiguration.properties" );
			this._prop.store( out , null);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getColorTo( String string ) {
		
		try {
			int r = Integer.parseInt( (String) this._prop.get( string + R ) );
			int g = Integer.parseInt( (String) this._prop.get( string + G ) );
			int b = Integer.parseInt( (String) this._prop.get( string + B ) );
		
			return new Color( r, g, b );
		} catch( Exception e ) {
			e.printStackTrace();
			return new Color( 255, 255, 255 );
		}
		
	}
	
	public void setColorTo( String string, Color color ) {
		
		this._prop.setProperty(string + R, String.valueOf( color.getRed() ) );
		this._prop.setProperty(string + G, String.valueOf( color.getGreen() ) );
		this._prop.setProperty(string + B, String.valueOf( color.getBlue() ) );
		
		this.saveConfiguration();
	}

	public boolean isActiva( String propertie ) {
		
		if( this._prop.get( propertie + ACTIVE ) == null ) {
			return false;
		} else if( this._prop.get( propertie + ACTIVE ).equals( "true" ) ) {
			return true;
		}
		
		return false;
	}
}
