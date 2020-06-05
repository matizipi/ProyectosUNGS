package com.common.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import helper.Msg;

public class Log {

	private static Log instance = null;
	
	private Log() {
		
	}

	public static Log getInstance() {
		if ( instance == null ) {
			instance = new Log();
		}
		return instance;
	}

	public static void WriteFileLog( Msg msg ) {
		
		File f = new File("tp.log");
		FileWriter fw;
		
		try {
			if ( f.exists() ) {
				fw = new FileWriter( f, true );
			} else {
				fw = new FileWriter( f );
			}
			
			BufferedWriter bf = new BufferedWriter( fw );
			
			bf.write( msg.printMsg() );
			bf.newLine();
			
			bf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void WriteFileLog(List<Msg> msgs) {
		
		for( Msg message: msgs ) {
			Log.WriteFileLog( message );
		}
	}
}