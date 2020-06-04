package automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import automaton.components.StateA;
import automaton.components.TransactionFunction;
import automaton.components.alphabet.Alphabet;
import automaton.components.alphabet.Input;
import automaton.components.alphabet.Simbol;
import automaton.dfn.components.TransactionFunctionDFA;
import automaton.nfa.components.TransactionFunctionNFA;
import helper.LogWriter;

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
			
			///* return state "Q_", that represent no state. */
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
		
		LogWriter.writeLog(this, 0, "Start acceptInputFrom process");
		
		ThreadNFA thNFA = new ThreadNFA( this, state, input, "Thread_1" );
		thNFA.start();
		
		while( thNFA.isAlive() ) {
			LogWriter.writeLog(this, 0, "Automaton esperando repuesta." );
		}
		
		LogWriter.writeLog( this, 0, "Automaton ya tiene respuesta.");
		
		return thNFA.getResult();
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