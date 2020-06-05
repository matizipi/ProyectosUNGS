package helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg {
	
	public static int INFO = 0;
	public static int ERROR = 1;
	
	private String _type;
	private String _object;
	private String _date;
	private String _msg;
	
	public Msg( int msgType, Object object, String msg ) {
		
		SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyy HH:mm:ss" );
		Date date = new Date();
		this._date = "[" + formatter.format( date ) + "]";
		
		switch (msgType) {
			case 0: this._type = "[INFO ]";
				break;
			case 1: this._type = "[ERROR]";
				break;
			default: this._type = "";
		}
		
		if ( object == null ) {
			this._object = "";
		} else {
			this._object = "[" + object.getClass().getSimpleName() + "]";
		}
		this._msg = msg;
	}
	
//	public String[] getMsg() {
//		return new String[] { this._date, this._type, this._object, this._msg };
//	}
	
	public String printMsg() {
		return this._date + this._type + this._object + " -> " + this._msg;
	}

	public String getDate() {
		return this._date;
	}
	
	public String getType() {
		return this._type;
	}

	public String getObject() {
		return this._object;
	}
	
	public String getMsg() {
		return this._msg;
	}

	public static String getType( int type ) {
		
		String t = "";
		
		switch ( type ) {
			case 0: t = "[INFO ]";
				break;
			case 1: t = "[ERROR]";
				break;
			default: t = "";
		}
		
		return t;
	}
}