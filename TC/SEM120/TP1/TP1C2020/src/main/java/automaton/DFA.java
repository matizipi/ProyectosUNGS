package automaton;

import java.util.List;

import automaton.components.StateA;
import automaton.components.alphabet.Alphabet;
import automaton.components.alphabet.Input;
import automaton.components.alphabet.Simbol;
import automaton.dfn.components.TransactionFunctionDFA;
import helper.Msg;

public class DFA extends Automaton {
	
	private List< TransactionFunctionDFA > _lstTfs;
	
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
		List< Simbol > smbls = this._coAlphabet.getSimbols(); 
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
	public boolean accept(String input) {
		
		this._lstMsg.clear();
		
		Input inp = new Input( input );
		
		if ( this._coAlphabet.accept( inp ) == false ) {
			this._lstMsg.addAll( this._coAlphabet.getMsgs() );
			return false;
		}
		
		this._lstMsg.add( new Msg( Msg.INFO, this, "Los simbolos del input pertenecen al alfabeto." ) );
		
		return this.acceptInputFrom( this._coStartState, inp );
	}
	
	public boolean acceptInputFrom( StateA state, Input input ) {
		
		boolean tfSucces = false;
		Simbol[] symbolsOfInput = input.getSimbolsOfInput();
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
			this._lstMsg.add( new Msg( Msg.INFO, this, "Input no aceptado." ) );
			return false;
		}
	}
	
////	public DFA(NFA nfa) {
//		super(nfa.getAlphabet());
//		
//		/* States will be create when pass the transaction function. */
//		/* Transaction function when create with the new functions. */		
//		
//		/* Segunda implementaciÃ³n del tranformaciÃ³n. */
//		//StateA sa01 = nfa.getStartState();
//		this._coStartState = nfa.getStartState();
//		this._coStates.add( nfa.getStartState() );
//		
//		StateOfSetStates startState = new StateOfSetStates( "0" );
//		startState.addState( this._coStartState );
//		
//		List< StateOfSetStates > lstStatesAux = new ArrayList< StateOfSetStates >();
//		lstStatesAux.add( startState );
//		boolean stateWithoutTf = true;
//		int lstIterator = 0;
//		StateOfSetStates stateOfList, stateAux;
//		
//		while( stateWithoutTf == true ) {
//			
//			/* Voy obteniendo cada uno de los estado que se van a ir a gregando. */
//			stateOfList = lstStatesAux.get( lstIterator );
//			
//			for ( Simbol smb: this._coAlphabet.getSimbols() ) {
//				
//				/* new state of set of state from tfs */
//				stateAux = this.newStateFromTfsNfa( stateOfList, smb, nfa );
//				
//				/* Comparo contra todos los estados que tengo. si el nuevo estado no existe */
//				boolean existsStateInAutomaton = false;
//				for ( StateOfSetStates soss:  lstStatesAux) {
//					if ( soss.equals( stateAux ) ) {
//						existsStateInAutomaton = true;
//						this._coTfs.add( new TransactionFunctionDFA( stateOfList.toStateA(), smb, soss.toStateA() ) );
//						break;
//					}
//				}
//				
//				if ( existsStateInAutomaton == false ) {
//					lstStatesAux.add( stateAux );
//					this._coStates.add( stateAux.toStateA() );	
//					this._coTfs.add( new TransactionFunctionDFA( stateOfList.toStateA() , smb, stateAux.toStateA() ) );
//				}
//			}
//			
//			lstIterator++;
//			LogWriter.writeLog( this, lstIterator + " | " + lstStatesAux.size());
//			
//			if ( lstIterator >= lstStatesAux.size() ) {
//				stateWithoutTf = false;
//			}
//			
//		}
//		
////		this.generateTransactionFunctionsTable( startState, nfa );
//	}
//	
////	private List< StateOfSetStates > generateTransactionFunctionsTable( StateA state, NFA nfa) {
////		
////		List< StateOfSetStates > lstState = new ArrayList< StateOfSetStates >();
////		
////		/* Al estado le aplico cada uno de los inputs dentro del alfabeto. */
////		for ( Simbol smb: this._coAlphabet.getSimbols() ) {
////				
////			/* Paso estos dos parametros al nfa para que me devuelva un conjunto de estados */
////			List< StateA > lstAux = nfa.doTransactionFunctionTo( state, smb );
////				
////			/* Creo un nuevo estado para el input que es un conjunto de estados resultantes. */
////			StateOfSetStates stateAux = new StateOfSetStates( String.valueOf( this._coStates.size() ) );
////			stateAux.addStates( lstAux );
////			System.out.println( stateAux.getName() );
////			
////			/*  */
////		}
//////		StateOfSetStates aux = new StateOfSetStates("");
//////		lstState.addAll( this.segunda(stt.getStates()) );
////		
////		return lstState;
////	}
//	
////	private StateOfSetStates newStateFromTfsNfa( StateOfSetStates state, Simbol symbol, NFA nfa) {
//		
//		StateOfSetStates stt = new StateOfSetStates( this._coStates.size() );
//		
////		pensar parte de que cuando ya esta el vacio no puede volverse a agregar al estado
//		
//		for ( StateA st: state.getStates() ) {
//			stt.addStates( nfa.doTransactionFunctionTo( st, symbol ) );
//		}
//		
//		return stt;
//	}
}