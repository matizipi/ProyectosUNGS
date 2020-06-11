package com.app.punto1.automaton.components.alphabet;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;

public class Alphabet {

	private List< Simbol > _lstSimbols;
	private List< Msg > _lstMsg;
	
	public Alphabet(Simbol... symbols) {
		
		this._lstSimbols = new ArrayList< Simbol >();
		this._lstMsg = new ArrayList< Msg >();
		
		for ( int i = 0; i < symbols.length; i++ ) {
			this._lstSimbols.add( symbols[i] );
			this._lstMsg.add( new Msg( Msg.INFO, this, "Simbolo agregado alfabeto: " + symbols[ i ].getSimbol() + "." ) );
		}
	}
	
	public void addSimbols( Simbol... symbols ) {
		this._lstMsg.clear();
		
		for( int i = 0; i < symbols.length; i++ ) {
			this._lstSimbols.add( symbols[i] );
			this._lstMsg.add( new Msg( Msg.INFO, this, "Simbolo agregado alfabeto: " + symbols[ i ].getSimbol() + "." ) );
		}
	}

	public boolean accept(Input input) {
		
		this._lstMsg.clear();
		
		boolean inputExists = false;
		Simbol[] symbolsOfInput = input.getSimbolsOfInput();
		
		/* ecorro los simbolos delinput. */
		for( int i = 0; i < symbolsOfInput.length; i++ ) {
			
			inputExists = false;
			/* los busco en el alfabeto. */
			for( int j = 0; j < this._lstSimbols.size(); j++ ) {
				if( symbolsOfInput[ i ].equals( this._lstSimbols.get( j ) ) ) {
					inputExists = true;
					break;
				}
			}
			/* Si el simbolo no esta en el alfabeto ERROR. */
			if ( inputExists == false ) {
				this._lstMsg.add( new Msg( Msg.ERROR, this, "El simbolo: " + symbolsOfInput[i].getSimbol() + " no pertenece al alfabeto." ) );
				return false;
			}
		}
		
		return true;
	}
	
	public static Alphabet generateFrom( String line ) {
		
		String[] stringSimbols = line.replaceAll(" ", "").split(",");
		Simbol[] smb = new Simbol[ stringSimbols.length ];
		
		for( int i = 0; i < smb.length; i++ ) {
			smb[i] = new Simbol( stringSimbols[i] );
		}
		
		return new Alphabet(smb);
	}
	
	public List<Simbol> getSimbols() {
		return this._lstSimbols;
	}
	
	public String[] getArrayStringOfSimbols() {
		
		String[] array = new String[ this._lstSimbols.size() ];
		
		int i = 0;
		for ( Simbol smb: this._lstSimbols ) {
			array[i] = smb.getSimbol();
			i++;
		}
		
		return array;
	}
	
	public List< Msg > getMsgs() {
		return this._lstMsg;
	}
}