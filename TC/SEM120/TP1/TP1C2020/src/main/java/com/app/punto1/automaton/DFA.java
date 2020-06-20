package com.app.punto1.automaton;

import java.util.List;

import com.app.common.Msg;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Symbol;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;

public class DFA extends Automaton {
	
	private List< TransactionFunctionDFA > _lstTfs;
	private String[][] _equivalenceTable = new String[][] {{}};
	
	public DFA(Alphabet alphabet, List< StateA > states, List< TransactionFunctionDFA > transactionFunctionList,
			StateA startState, List< StateA > fnlStates) {
		super(alphabet, states, startState, fnlStates);
		this._lstTfs = transactionFunctionList;
	}
	
	public List< TransactionFunctionDFA > getTfs() {
		return this._lstTfs;
	}

	@Override
	public String[][] getTfTable() {
		
		
		List< StateA > stts = this._coLstState;
		List< Symbol > smbls = this._coAlphabet.getSimbols(); 
		String[][] table = new String[ stts.size() + 1 ]
				[ smbls.size() + 1 ];
		
		for( int i = 0; i < smbls.size(); i++ ) {
			table[0][ i +1 ] = smbls.get( i ).getSimbol();
		}
		
		for( int i = 0; i < stts.size(); i++ ) {
			table[ i + 1 ][ 0 ] = stts.get( i ).getName();
			for( int j = 0; j < smbls.size(); j++ ) {
				for( TransactionFunctionDFA tf: this._lstTfs ) {
					if( tf.hasParameters( stts.get( i ), smbls.get( j ) ) ) {
						table[ i + 1 ][ j + 1 ] = tf.nextState().getName();
					}
				}
			}
		}
		
		return table;
	}
	
	@Override
	public boolean acceptInputFrom( StateA state, Input input ) {
		
		boolean tfSucces = false;
		Symbol[] symbolsOfInput = input.getSimbolsOfInput();
		StateA stt = state;
		
		/* For each symbol of input. */
		for( int i = 0; i < symbolsOfInput.length; i++ ) {
			tfSucces = false;
			/* For each transaction function. */
			for( TransactionFunctionDFA tf: this._lstTfs ) {
				/* Ask for next state. */
				if( tf.hasParameters( stt, symbolsOfInput[i] ) ) {
					tfSucces = true;
					stt = tf.nextState();
					this._lstMsg.add( new Msg( Msg.INFO, this, "Realiza: " + tf.toString() ) );
					break;
				}
			}
			/* Ask if did some transaction function. */
			if( tfSucces == false ) {
				this._lstMsg.add( new Msg( Msg.ERROR, this, "No existe función de transacción"
						+ " para el estado: " + stt.getName()
						+ " y el simbolo: " + symbolsOfInput[i].getSimbol() ) );
				return false;
			}
		}
		/* Ask if the final state is final. */
		if( stt.isFinalState() == true ) {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Input aceptado." ) );
			return true;
		} else {
			this._lstMsg.add( new Msg( Msg.ERROR, this, "Input no aceptado." ) );
			return false;
		}
	}
	
	public void setEquivalenceTable( String[][] equivalenceTable ) {
		this._equivalenceTable = equivalenceTable;
	}
	
	public String[][] getEquivalenceTable(){
		return this._equivalenceTable;		
	}
}