package com.app.punto1.automaton.dfn.components;

import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.TransactionFunction;
import com.app.punto1.automaton.components.alphabet.Simbol;

public class TransactionFunctionDFA extends TransactionFunction {
	
	protected StateA _nextState;
	
	public TransactionFunctionDFA(StateA initState, Simbol symbol, StateA nextState) {
		super( initState, symbol );
		this._nextState = nextState;
	}
	
	public TransactionFunctionDFA(String line) {
		super( line.split("->")[0] );
		String functionBody[] = line.replaceAll(" ", "").split( "->" );
		
		this._nextState = new StateA( functionBody[1] );
	}
	
	public boolean hasNextState(StateA sa, Simbol s) {
		if ( this._stateParameter.equals(sa) && this._symbol.equals(s) ) {
			return true;
		}
		
		return false;
	}
	
//	public boolean hasNextState(StateDFAFromNFA state, Simbol s) {
//		
//		boolean hasNext = false;
//		
//		for ( StateA stta: state.getStatesA() ) {
//			if ( this.hasNextState(stta, s) ) {
//				hasNext = true;
//			}
//		}
//		
//		return hasNext;
//	}
	
	public StateA nextState() {
		return this._nextState;
	}
	
	@Override
	public String toString() {
		return "\u0394(" + this._stateParameter.getName() + "," + this._symbol.getSimbol() + ") = " + this._nextState.getName();
	}
}