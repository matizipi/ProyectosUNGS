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
	public String toString() {
		return this._value;
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null )
			return false;
		if( this.getClass() != obj.getClass() )
			return false;
		
		return this._value.equals( obj.toString() )?true:false;
	}
}