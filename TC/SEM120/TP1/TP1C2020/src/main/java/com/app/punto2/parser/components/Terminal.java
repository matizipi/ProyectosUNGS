package com.app.punto2.parser.components;

public class Terminal extends ProductionComponent {

	public String _value;
	
	public Terminal( String value ) {
		this._value = value;
	}

	@Override
	public boolean isTerminal() {
		return true;
	}

	@Override
	public boolean isInitSymbol() {
		return false;
	}
	
	@Override
	public String toString() {
		return this._value;
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null )
			return false;
		
		if( Terminal.class.isAssignableFrom( obj.getClass() ) == false )
			return false;
		
		Terminal terminal = ( Terminal ) obj;
		if( this.sameValue( terminal ) == false ) {
			return false;
		}
		
		//return this._value.equals( obj.toString() )?true:false;
		return true;
	}
	
	private boolean sameValue( Terminal terminal ) {
		
		if( this.getValue().equals( terminal.getValue() ) ) {
			return true;
		}
		
		return false;
	}
	
	public String getValue() {
		return this._value;
	}
}