package automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import automaton.components.StateA;
import automaton.components.TransactionFunction;
import automaton.components.alphabet.Alphabet;
import automaton.components.alphabet.Simbol;
import automaton.dfn.components.TransactionFunctionDFA;
import automaton.nfa.components.TransactionFunctionNFA;

public class NFA extends Automaton {

	private Map< String, TransactionFunctionNFA > _mapTf = new HashMap< String, TransactionFunctionNFA >();
	private List< TransactionFunctionNFA > _lstTf = new ArrayList< TransactionFunctionNFA >();
	
	public NFA(Alphabet alphabet, List<StateA> states, StateA startState, List<StateA> fnlStates, List< TransactionFunctionDFA > tfs) {
		super(alphabet, states, startState, fnlStates);
		this.makeTfNFA( tfs );
	}
	
	private void makeTfNFA( List<TransactionFunctionDFA> tfs ) {
		
		for ( TransactionFunctionDFA tf: tfs ) {
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
	
//	public NFA(Alphabet a, List<StateOfNFA> states, List<StateOfNFA> fss, List<TransactionFunctionNFA> tfs) {
//		this._coAlphabet = a;
//		this._coStates = states;
//		this._coFnlStates = fss;
//		this._coTfs = tfs;
//		
//		this.makeFNA();
//		
//		System.out.println(this._sMsg);
//	}
	
//	public NFA(File file) {
//		
//		this._mapTf = new HashMap<String, TransactionFunctionNFA>();
//		this._lstTf = new ArrayList<TransactionFunctionDFA>();
//		BufferedReader br;
//		
//		try {
//			/* para sacar este advertice pensar de la forma que me de el stream<string> con el metodo
//			 * getlines(). cerrar el archivo y recorrer ese stream con un for */
//			br = new BufferedReader( new FileReader(file) );
//			
//			String line = br.readLine();
//			int numberOfLine = 1;
//			
//			while( line != null ) {
//				
//				/* Read the alphabet. */
//				if ( numberOfLine == 1 ) {
//					this._alphabet = Alphabet.generateFrom(line);
//					LogWriter.writeLog( this, "Proceso alphabet finish [OK]");
//				}
//				
//				/* Read quantity of states. */
//				if ( numberOfLine == 2 ) {
//					int qtyOfStates = Integer.parseInt( line.replaceAll( " ", "" ) );
//					
//					this._listState = StateA.generateListStatesFromInt( qtyOfStates );
//					
//					LogWriter.writeLog( this, "Proceso generate NFA states finish [OK]");
//					
//					for (StateA snfa: this._listState) {
//						if (snfa.isStartState()) {
//							this._coStartState = snfa;
//							break;
//						}
//					}
//				}
//				
//				/* Read final states. */
//				if ( numberOfLine == 3 ) {
//					this._lstFnlState = StateA.generateListStatesFromLine(line);					
//					
//					LogWriter.writeLog( this, "Carga lista stados finales [OK]" );
//					if ( this.setFinalStates() ) {
//						LogWriter.writeLog( this, "Proceso set stados finales [OK]" );
//					} else {
//						LogWriter.writeLog( this, "Proceso set stados finales [Failed]" );
//						LogWriter.writeLog( this, "\t" + this._sMsg);
//						return;
//					}
//				}
//				
//				/* Read transaction functions. */
////				if ( numberOfLine > 3 ) {
////					TransactionFunctionNFA tfAux = new TransactionFunctionNFA( line );
////					this._lstTf.add(tfAux);
////				}				
//				if ( numberOfLine >3 ) {
//					TransactionFunctionDFA tfAux = new TransactionFunctionDFA( line );
//					
//					/* Preguntando si devuelve un estado final. */
//					for ( StateA stts: this._listState ) {
//						if ( stts.equals( tfAux.nextState() ) ) {
//							tfAux.nextState().FinalState(true);
//							LogWriter.writeLog( this, tfAux.toString() + " Devuelve estado final.");
//						}
//					}
//					
//					String sParameters = tfAux.getParameterString();
//					
//					if ( this._mapTf.get( sParameters ) == null ) {
//						this._mapTf.put( sParameters , new TransactionFunctionNFA( tfAux ) );
//					} else {
//						this._mapTf.get( sParameters ).addTf( tfAux );
//					}
//				}
//				
//				/* Continue with next line */
//				line = br.readLine();
//				numberOfLine++;
//			}
//			
//			br.close();
//			
//		} catch( Exception e ) {
//			e.printStackTrace();
//		}
//	}

//	private void makeFNA() {
//		
//		if ( !this.setStartState() ) {
//			return;
//		}
//		
//		// verify and set final states
//		if ( !this.setFinalStates() ) {
//			return;
//		}
//		
//		// set transaction functions
////		this.setTransactionsFunctions();
//		
//	}
	
//	private boolean setStartState() {
//		this._listState.forEach(stt -> {
//			if ( stt.isStartState() ) {
//				this._coStart = stt;
//			}
//		});
//		
//		return (this._coStart == null)?false:true;
//	}
	
	
	public List<StateA> doTransactionFunctionTo(StateA state, Simbol symbol) {
		
		String parametersFormat = TransactionFunction.generateParameterStringFormat(state, symbol);
		
		if ( this._mapTf.get(parametersFormat) == null ) {
			
			/* return state "Q_", that represent no state. */
			List< StateA > lst = new ArrayList< StateA >();
			lst.add( new StateA( "" ) );
			return lst;
			
		} else {
			return this._mapTf.get(parametersFormat).nextStates();
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
	
//	private void setTransactionsFunctions() {
//		
//		/* think the validates of states. */
//		
//		this._coTfs.forEach(tf -> {
//			for ( StateAutomaton state: this._coStates ) {
//				if ( tf.getInitialState().equals( state ) ) {
//					state.addTransactionFunction(tf);
//					break;
//				}
//			}
//		});
//		
//	}
	
//	public boolean acceptInput(Input input) {
//		boolean accept = false;
//		Set<StateA> states = new HashSet<StateA>();
//		Set<StateA> statesAux;
//		
//		Simbol[] s = input.getSimbolsOfInput();
//		
//		states.add(this._coStart);
//		
//		for ( int i = 0; i < s.length; i++ ) {
//			statesAux = new HashSet<StateA>();
//			/*
//			Iterator<AutomatonState> it = states.iterator();
//			while( it.hasNext() ) {
//				statesAux.add(it.next().transactionFunction(s[i]));
//			}
//			*/
//			states.addAll(statesAux);
//		}
//		
//		return accept;
//	}

//	public DFA convertToDFA() {
//		
////		/* nuevos estadode del automata. */
////		List<StateAutomaton> statesDFA = null;
////		
////		/* Nuevas transacciones del automata */
////		List<TransactionFunction> lTf = null;
////		
////		/* Nuevos estado finales */
////		List<StateAutomaton> fnlStatesDFA = null;
////		
////		this.lerolero(statesDFA, lTf, fnlStatesDFA);
////		
////		/* (alfabeto, estados, funciones de transicion, estado incial, estado final) */
////		DFA a =  new DFA(this._coAlphabet, statesDFA, lTf, this._coStartState, fnlStatesDFA);
//		
//		FA a = new DFA(this._coAlphabet, this._coStartState);
//		
//		this.lerolero2(a); 
//		
//		return a;
//	}
	
//	private void lerolero(Object a, Object b, Object c) {
//		
//		for ( TransactionFunction tf: this._coTfs ) {
//			if ( tf.getStateParameter().equals(this._coStartState) ) {
//				
//			}
//		}	
//	}

//	public List<TransactionFunctionDFA> getTransactionsFunctions() {
//		return this._lstTf;
//	}
}