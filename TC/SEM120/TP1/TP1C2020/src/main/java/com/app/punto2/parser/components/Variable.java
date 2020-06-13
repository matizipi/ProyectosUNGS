package com.app.punto2.parser.components;

public class Variable extends ProductionComponent {
	
	private String _id;
	
	public Variable( String variable ) {
		this._id = variable;
	}
	
	@Override
	public String toString() {
		return this._id;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null )
			return false;
		if( this.getClass() != obj.getClass() )
			return false;
		
		return this._id.equals( obj.toString() )?true:false;
	}
}