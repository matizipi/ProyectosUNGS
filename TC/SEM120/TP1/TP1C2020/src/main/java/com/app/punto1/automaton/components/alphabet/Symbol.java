package com.app.punto1.automaton.components.alphabet;

public class Symbol {

	private String _sSimbol;
	
	public Symbol(char arg0) {
		this._sSimbol = String.valueOf( arg0 );
	}
	
	public Symbol(String simbol) {
		this._sSimbol = simbol;
	}

	public String getSimbol() {
		return this._sSimbol;
	}
	
//	public boolean equals(Simbol s) {
//		if ( this._sSimbol.equals( s.getSimbol() ) ) {
//			return true;
//		}
//		
//		return false;
//	}
	
	@Override
	public boolean equals( Object obj ) {
		
		/* Verify if the object is null. */
		if( obj == null ) {
			System.out.println("ss");
			return false;
		}
		
		/* Verify that the objects are not the same. */
		if( Symbol.class.isAssignableFrom( obj.getClass() ) == false ) {
			System.out.println("sss");
			return false;
		}
		
		/* Compare internal object variables. */
		Symbol smb = ( Symbol ) obj;
		if( this.haveSameSymbolThat( smb ) == false) {
			return false;
		}
		return true;
	}
	
	/* Compare if the symbols have the same string. */
	private boolean haveSameSymbolThat( Symbol smb ) {
		
		if( this._sSimbol.equals( smb.getSimbol() ) ) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return this._sSimbol;
	}
}