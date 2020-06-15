package com.app.punto2.parser.components;

public class Variable extends ProductionComponent {
	
	private static String initVariable = "X_{1}";
	
	private String _id;
	
	public Variable( String variable ) {
		this._id = variable;
	}
	
	@Override
	public boolean isTerminal() {
		return false;
	}
	
	@Override
	public boolean isInitSymbol() {
		return ( this._id.toString().equals( "X_{1}" ) );
	}
	
	@Override
	public String toString() {
		return this._id;
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null )
			return false;
		if( this.getClass() != obj.getClass() )
			return false;
		
		return this._id.equals( obj.toString() )?true:false;
	}

	public boolean isInitial() {
		if( this._id.equals( initVariable ) ) {
			return true;
		}
		
		return false;
	}

	public static ProductionComponent getInitialComponent() {
		return new Variable( initVariable );
	}
}