package com.app.punto1.automaton.nfa.components;

import java.util.ArrayList;
import java.util.List;

import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.TransactionFunction;
import com.app.punto1.automaton.components.alphabet.Symbol;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;

public class TransactionFunctionNFA extends TransactionFunction {
	
	private List<StateA> _lstNextState;
	
	public TransactionFunctionNFA(StateA initState, Symbol symbol, StateA nextState) {
		super( initState, symbol );
				
		this._lstNextState = new ArrayList<StateA>();
		this._lstNextState.add( nextState );
	}
	
	public TransactionFunctionNFA( TransactionFunctionDFA tf ) {
		super( tf.getStateParameter(), tf.getInput() );

		this._lstNextState = new ArrayList<StateA>();
		this._lstNextState.add( tf.nextState() );
	}
	
//	public TransactionFunctionNFA(String line) {
//		super( line );
//	}

	public void addTf(TransactionFunctionDFA tf) {
		this._lstNextState.add( tf.nextState() );
	}

//	public boolean hasNextState(StateA sa, Simbol s) {
////		if ( this._stateParameter.equals(sa) && this._symbol.equals(s) ) {
////			return true;
////		}
////		
////		return false;
//		return true;
//	}
	
	public boolean hasNextStateFor( Symbol s ) {
		return ( this._symbol.equals( s ) && this._lstNextState.isEmpty() == false )?true:false;
	}
	
	public List<StateA> nextStates() {
		
		return this._lstNextState;
	}
	
	@Override
	public String toString() {
		
		String states = "";
		
		for (StateA stt: this._lstNextState) {
			states += stt.getName() + ", ";
		}
		
		return "\u0394(" + this._stateParameter.getName() + "," + this._symbol.getSimbol() + ") = {" + states.substring( 0, states.length() - 2 ) + "}";
	}

	public String nextStringStates() {
		
		String nextStates = "{";
		
		for( StateA stt: this._lstNextState ) {
			nextStates += stt.getName() + ",";
		}
		
		nextStates = nextStates.substring( 0, nextStates.length() - 1 ) + "}";
		
		return nextStates;
	}
}