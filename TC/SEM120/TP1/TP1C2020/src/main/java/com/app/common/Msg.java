package com.app.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author NFERNANDEZ
 **/
public class Msg {
	
	public static int INFO = 0;
	public static int ERROR = 1;
	
	private String _type;
	private String _object;
	private String _date;
	private String _msg;

	private SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyy HH:mm:ss" );

	/**
	 * <b>Description:</b> Message contructor
	 * @param Integer - Message type
	 * @param Object - Object request
	 * @param String - Message
	 */
	public Msg( int msgType, Object object, String msg ) {
		
		/* Get date to print in the message. */
		Date date = new Date();
		this._date = "[" + this.formatter.format( date ) + "]";
		
		/* Select type of message. */
		switch (msgType) {
			case 0: this._type = "[INFO ]";
				break;
			case 1: this._type = "[ERROR]";
				break;
			default: this._type = "";
		}
		
		/* If the object is null, no print object from. */
		if ( object == null ) {
			this._object = "";
		} else {
			this._object = "[" + object.getClass().getSimpleName() + "]";
		}
		
		/* Body of message. */
		this._msg = msg;
	}
	
	/* Print message. */
	public String printMsg() {
		return this._date + this._type + this._object + " -> " + this._msg;
	}
	
	/* Print message without object from. */
	public String printMsgWithOutClass() {
		return this._date + this._type + this._object + " -> " + this._msg;
	}

	/* Get date of message. */
	public String getDate() {
		return this._date;
	}
	
	/* Get type of message. */
	public String getType() {
		return this._type;
	}

	/* Get object name. */
	public String getObject() {
		return this._object;
	}
	
	/* Get body of message. */
	public String getMsg() {
		return this._msg;
	}

	/* Return string type from integer type. */
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

	/* Get body message from exception. */
	public static String getMessage( Exception exc ) {
		
		String msg = exc.toString() + "\n";
		
		for( int i = 0; i < exc.getStackTrace().length; i++ ) {
			msg += exc.getStackTrace()[i].toString() + "\n";
		}
		
		return msg;
	}
}