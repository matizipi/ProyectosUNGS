package com.app.punto1.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.TransactionFunction;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Simbol;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;
import com.app.punto1.automaton.nfa.components.TransactionFunctionNFA;

public class NFA extends Automaton {

	private Map< String, TransactionFunctionNFA > _mapTf = new HashMap< String, TransactionFunctionNFA >();
	private List< TransactionFunctionNFA > _lstTf = new ArrayList< TransactionFunctionNFA >();
	
	public NFA(Alphabet alphabet, List<StateA> states, StateA startState, List<StateA> fnlStates, List< TransactionFunctionDFA > tfs) {
		super(alphabet, states, startState, fnlStates);
		this.makeTfNFA( tfs );
	}
	
	private void makeTfNFA( List<TransactionFunctionDFA> tfs ) {
		
		for ( TransactionFunctionDFA tf: tfs ) {
			
			/* If next state if final, set it as final. */
			this.verifyFinalStatesFor( tf );
			
			TransactionFunctionNFA tfnfa = this._mapTf.get( tf.getParameterString() );
			
			if ( tfnfa == null ) {
				this._mapTf.put( tf.getParameterString(), new TransactionFunctionNFA( tf ) );
			} else {
				tfnfa.addTf( tf );
			}
		}
		
		/* In case use list */
		boolean b;
		
		for ( TransactionFunctionDFA tfdfa: tfs) {
			b = false;
			
			/* If next state if final, set it as final. */
			this.verifyFinalStatesFor( tfdfa );
			
			for( TransactionFunctionNFA tfnfa: this._lstTf ) {
				if ( tfdfa.getParameterString().equals( tfnfa.getParameterString() ) ) {
					b= true;
					tfnfa.addTf( tfdfa );
				}
			}
			if ( b == false ) {
				this._lstTf.add( new TransactionFunctionNFA( tfdfa ) );
			}
		}
	}
	
	private void verifyFinalStatesFor( TransactionFunctionDFA tf ) {
		
		for ( StateA fstt: this._coLstFnlState ) {
			if( fstt.equals( tf.nextState() ) ) {
				tf.nextState().FinalState( true );
			}
		}
		
	}
	
	public List<StateA> doTransactionFunctionTo(StateA state, Simbol symbol) {
		
		String parametersFormat = TransactionFunction.generateParameterStringFormat(state, symbol);
		
		if ( this._mapTf.get(parametersFormat) == null ) {
			
			/* Return the same state of parameters because the input don´t change the state. */
			List< StateA > lst = new ArrayList< StateA >();
			//lst.add( new StateA( "" ) );
			for( StateA stt: this._coLstFnlState ) {
				if ( state.equals(stt) ) {
					state.FinalState( true );
				}
			}
			lst.add( state );
			return lst;
			
		} else {
			return this._mapTf.get(parametersFormat).nextStates();
		}
	}
	
	@Override
	public boolean acceptInputFrom(StateA state, Input input) {
		
		Msg msg = new Msg( Msg.INFO, this, "Comienza el proceso [accepInputFrom]" );
		Log.WriteFileLog( msg );
		this._lstMsg.add( msg );
		
		ThreadNFA thNFA = new ThreadNFA( this, state, input, "Thread_1" );
		thNFA.start();
		
		while( thNFA.isAlive() ) {
			Log.WriteFileLog( new Msg( Msg.INFO, this, "Automata esperando repuesta." ) );
			try {
				Thread.sleep( 100 );
			} catch (Exception e) {
				Log.WriteFileLog( new Msg( Msg.ERROR, this, "Error en sleep." ) );
			}
		}
		
		if( thNFA.getResult() == true ) {
			this._lstMsg.add( new Msg( Msg.INFO, this, "El automata acepto la siguiente secuencia: " + thNFA.getStringAcceptStates() ) );
			return true;
		} else {
			return false;
		}
	}

	public List< TransactionFunctionNFA > getTfs() {
		return this._lstTf;
	}
	
	public String[][] getTfTable() {
		
		String[][] table = new String[ this._coLstState.size() + 1 ][ this._coAlphabet.getSimbols().size() + 1 ];
		
		for ( int i = 0; i < this.getAlphabet().getSimbols().size(); i++ ) {
			table[0][ i +1 ] = this.getAlphabet().getSimbols().get( i ).getSimbol();
		}
		
		for ( int i = 0; i < this._coLstState.size(); i++ ) {
			table[ i + 1 ][ 0 ] = this._coLstState.get( i ).getName();
			for( int j = 0; j < this._coAlphabet.getSimbols().size() ; j++ ) {
				boolean hasNext = false;
				for( TransactionFunctionNFA tfs: this._lstTf ) {
					if ( tfs.hasParameters( this._coLstState.get( i ), this._coAlphabet.getSimbols().get( j ) ) ) {
						table[ i + 1 ][ j + 1 ] = tfs.nextStringStates();
						hasNext = true;
					}
				}
				if ( hasNext == false ) {
					table[ i + 1 ][ j + 1 ] = "-";
				}
			}
		}
		
		return table;
	}
}