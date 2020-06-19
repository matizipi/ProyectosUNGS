package com.app.punto1.automaton.components;

import com.app.punto1.automaton.components.alphabet.Symbol;

/** Transaction function. **/
public abstract class TransactionFunction {

	protected StateA _stateParameter;
	protected Symbol _symbol;
	
	private static String SPARAMETERFORMAT = "%s,%s";
	
	/** Construction from parameters. **/
	public TransactionFunction(StateA initState, Symbol symbol) {
		this._stateParameter = initState;
		this._symbol = symbol;
	}
	
	/** Construction from line text. **/
	public TransactionFunction(String parameterLine) {
		
		String functionParameters[] = parameterLine.replaceAll(" ", "").split( "," );
		
		this._stateParameter = new StateA( functionParameters[0] );
		this._symbol = new Symbol( functionParameters[1] );
	}
	
	/** Return true is the tansaction function has the same parameters. **/
	public boolean hasParameters(StateA state, Symbol symbol) {
		return ( this._stateParameter.equals( state ) && this._symbol.equals( symbol ) )?true:false;
	}
	
	public abstract String toString();
	
	/** Get a state parameter. **/
	public StateA getStateParameter() {
		return this._stateParameter;
	}
	
	/** Get symbol parameter. **/
	public Symbol getInput() {
		return this._symbol;
	}
	
	/** Get parameter in string format. **/
	public String getParameterString() {
		return String.format( SPARAMETERFORMAT, this._stateParameter.getName(), this._symbol.getSimbol() );
	}
	
	/** Generate parameter in string format from parameters. **/
	public static String generateParameterStringFormat(StateA state, Symbol symbol) {
		return String.format( SPARAMETERFORMAT, state.getName(), symbol.getSimbol() );
	}
}