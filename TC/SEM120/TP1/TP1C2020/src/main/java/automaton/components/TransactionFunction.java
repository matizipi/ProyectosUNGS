package automaton.components;

import automaton.components.alphabet.Simbol;

public abstract class TransactionFunction {

	protected StateA _stateParameter;
	protected Simbol _symbol;
	
	private static String SPARAMETERFORMAT = "%s,%s";
	
	public TransactionFunction(StateA initState, Simbol symbol) {
		this._stateParameter = initState;
		this._symbol = symbol;
	}
	
	public TransactionFunction(String parameterLine) {
		
		String functionParameters[] = parameterLine.replaceAll(" ", "").split( "," );
		
		this._stateParameter = new StateA( functionParameters[0] );
		this._symbol = new Simbol( functionParameters[1] );
	}
	
//	@Override
//	public String toString() {
//		return "\u0394(" + this._stateParameter.getName() + "," + this._symbol.getSimbol() + ") = " + this._nextState.getName();
//	}
	
	public boolean hasParameters(StateA state, Simbol simbol) {
		return ( this._stateParameter.equals( state ) && this._symbol.equals( simbol ) )?true:false;
	}
	
	public abstract String toString();
	
	public StateA getStateParameter() {
		return this._stateParameter;
	}
	
	public Simbol getInput() {
		return this._symbol;
	}
	
	public String getParameterString() {
		return String.format( SPARAMETERFORMAT, this._stateParameter.getName(), this._symbol.getSimbol() );
//		return this._stateParameter.getName() + "," + this._symbol.getSimbol() ;
	}

	public static String generateParameterStringFormat(StateA state, Simbol symbol) {
		return String.format( SPARAMETERFORMAT, state.getName(), symbol.getSimbol() );
	}
}