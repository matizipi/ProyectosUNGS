package com.app.punto1.automaton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.helper.LogWriter;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.StateOfSetStates;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Simbol;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;
import com.app.punto2.parser.ParserTopDownNoRecursive;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Variable;

public class ObjectConverter {
	
//	private ConverterSummary _cs;
	List< Msg > _lstMsg;
	
	public ObjectConverter() {
		this._lstMsg = new ArrayList< Msg >();
	}
	
	public NFA newNFAFromFile( File file ) {
		
		/* Start variables to new no deterministic finite automaton  */
		Alphabet alphabet = null;
		List< StateA > lstState = null;
		StateA startState = null;
		List< StateA > lstFnlState = null;
		List< TransactionFunctionDFA > lstTfs = new ArrayList< TransactionFunctionDFA >();
		
		NFA a;
		
		//this._mapTf = new HashMap<String, TransactionFunctionNFA>();
		//this._lstTf = new ArrayList<TransactionFunctionDFA>();
		BufferedReader br;
		
		try {
			
			this._lstMsg.add( new Msg( Msg.INFO, this, "Comenzando a leer automata desde archivo." ) ) ;
			br = new BufferedReader( new FileReader( file ) );
			
			String line = br.readLine();
			int numberOfLine = 1;
			
			while( line != null ) {
			
				this._lstMsg.add( new Msg( Msg.INFO, this, "Leyendo linea: " + numberOfLine ) );
				
				/* Read the alphabet. */
				if ( numberOfLine == 1 ) {
					alphabet = Alphabet.generateFrom(line);
					/*LogWriter.writeLog( this, LogWriter.INFO, "Proceso alphabet finish [OK]");*/
					this._lstMsg.add( new Msg( Msg.INFO, this, "Alfabeto leido [OK]." ) );
				}
				
				/* Read quantity of states. */
				if ( numberOfLine == 2 ) {
					int qtyOfStates = Integer.parseInt( line.replaceAll( " ", "" ) );
					
					lstState = StateA.generateListStatesFromInt( qtyOfStates );
					
					/* LogWriter.writeLog( this, LogWriter.INFO, "Proceso generate NFA states finish [OK]" ); */
					this._lstMsg.add( new Msg( Msg.INFO, this, "Estado generados [OK]." ) );
					
					for ( StateA s: lstState ) {
						if ( s.isStartState() ) {
							startState = s;
							break;
						}
					}
					this._lstMsg.add( new Msg( Msg.INFO, this, "Estado inicial: " + startState.getName() ) );
				}
				
				/* Read final states. */
				if ( numberOfLine == 3 ) {
					lstFnlState = StateA.generateListStatesFromLine(line);
					
					/* LogWriter.writeLog( this, LogWriter.INFO, "Carga lista stados finales [OK]" ); */
					this._lstMsg.add( new Msg( Msg.INFO, this, "Carga estados finales [OK]." ) );
				}
				
				/* Read transaction functions. */
				if ( numberOfLine > 3 ) {
					TransactionFunctionDFA tfAux = new TransactionFunctionDFA( line );
					lstTfs.add(tfAux);
					this._lstMsg.add( new Msg( Msg.INFO, this, "Creada la funcion de transacción: " + tfAux.toString() ) );
				}				
				
				/* Continue with next line */
				line = br.readLine();
				numberOfLine++;
			}
			
			br.close();
			this._lstMsg.add( new Msg( Msg.INFO, this, "Archivo leido." ) );
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		this._lstMsg.add( new Msg( Msg.INFO, this, "Creando automata." ) );
		a = new NFA(alphabet, lstState, startState, lstFnlState, lstTfs);
		this._lstMsg.addAll( a.getMsgs() );
		a.clearMessages();
		
		return a;
	}
	
	public DFA DFAFromNFA(NFA nfa) {
		
		Alphabet alphabet = nfa.getAlphabet();
		List< StateA > states = new ArrayList<StateA>();
		List< TransactionFunctionDFA > tfs = new ArrayList<TransactionFunctionDFA>();
		StateA startState;
		List< StateA > fnlStates = new ArrayList<StateA>();
		
		/* States will be create when pass the transaction function. */
		/* Transaction function when create with the new functions. */		
		
		/* Set Start State. */
		startState = nfa.getStartState();
		
		/* Add start state to list of states. */
		states.add( nfa.getStartState() );
		
		/* Create a new state that contain a set of automaton, in this case the start state only. */
		StateOfSetStates initState = new StateOfSetStates( "1" );
		initState.addState( startState );
		
		/* Create a list of States to know which state are created. */
		List< StateOfSetStates > lstSetStates = new ArrayList< StateOfSetStates >();
		lstSetStates.add( initState );
		
		/* Change variable name. */
		boolean stateWithoutTf = true;
		
		/* The states from NFD exists in DFA. */
		boolean existsStateInAutomaton = false;
		
		/* Iterator of lsSetState because the list will increase the number of states. */
		int lstIterator = 0;
		
		/* State to add in the list and state to compare with each state in the list. */
		StateOfSetStates stateInDFA, stateFromNFA;
		

		while( stateWithoutTf == true ) {
			
			/* Get each of state in the list. */
			stateInDFA = lstSetStates.get( lstIterator );
			LogWriter.writeLog( this, 0, stateInDFA.getName() + ":" + stateInDFA.isFinalState() );
			
			/* Get each symbol into the alphabet. */
			for ( Simbol smb: alphabet.getSimbols() ) {
				
				/* Get state of set of state from tfs in NFA. */
				stateFromNFA = new StateOfSetStates( states.size() + 1 );
				stateFromNFA.addStates( this.stateFromTfsNfa( stateInDFA, smb, nfa ) );
				
				LogWriter.writeLog( this, 0, "state: " + stateFromNFA.getName() );
				for( StateA test: stateFromNFA.getStates() ) {
					LogWriter.writeLog( this, 0, "\t State: " + test.getName() + " is final: " + test.isFinalState() );
				}
				
				/* Compare if stateFromNFA exists in DFA states. */
				existsStateInAutomaton = false;
				
				/* Compare stateFromNFA with all state in DFA. */
				for ( StateOfSetStates soss:  lstSetStates ) {
					/* if exists replace stateFromNFA to the exists state in DFA. */
					if ( soss.equals( stateFromNFA ) ) {
						existsStateInAutomaton = true;
						stateFromNFA = soss;
						break;
					}
				}
				
				/* If the state not exists in DFA, this process will add the state. */
				if ( existsStateInAutomaton == false ) {
					lstSetStates.add( stateFromNFA );					
					states.add( stateFromNFA.toStateA() );
					/* Verify of the state is final. */
					if ( stateFromNFA.isFinalState() ) {
						fnlStates.add( stateFromNFA.toStateA() );
					}
				}
				
				/* Add the new transaction function. */
				tfs.add( new TransactionFunctionDFA( stateInDFA.toStateA(), smb, stateFromNFA.toStateA() ) );
			}
			
			lstIterator++;
			
			/* change stateWithoutTf value, if get a transaction function for each state an symbol. */
			if ( lstIterator >= lstSetStates.size() ) {
				stateWithoutTf = false;
			}
			
		}
		
		/* Create the new deterministic finite automaton. */
		DFA a = new DFA(alphabet, states, tfs, startState, fnlStates);
		
		return a;
	}
	
	/**
	 * <b> Return set of states from transaction function in no deterministic finite automata. </b>**/
	private List< StateA > stateFromTfsNfa(StateOfSetStates state, Simbol symbol, NFA nfa) {
		
		List< StateA > lstState = new ArrayList< StateA >();
		
		/* Add to states each next state in each transaction function to state and symbol. */
		for ( StateA st: state.getStates() ) {
			lstState.addAll( nfa.doTransactionFunctionTo( st, symbol ) );
		}
		
		return lstState;
	}

	public ParserTopDownNoRecursive parserTopDownNoRecursiveFromFile(File file) {
		
		List< Production > lstProd = new ArrayList< Production >();
			
		BufferedReader br;
		
		try {
			
			this._lstMsg.add( new Msg( Msg.INFO, this, "Comienza la lectura del parser." ) );
			br = new BufferedReader( new FileReader( file ) );
			
			String line;
			
			Variable leftVar;
			ProductionComponent[] rigth;
			Production prd;
			
			while( ( line = br.readLine() ) != null ) {
				
				this._lstMsg.add( new Msg( Msg.INFO, this, "Leyendo linea: " + line ) );
				String[] production = line.replaceAll( " ", "").split( "->" ); 
				
				leftVar = new Variable( production[0] );
				
				rigth = ProductionComponent.arrayPrdCompFromString( production[1] );
				
				prd = new Production( leftVar, rigth );
			}
			
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, "Problemas con la lectura del parser." ) );
		}
		
		ParserTopDownNoRecursive parse = new ParserTopDownNoRecursive( lstProd );
		
		return parse;
	}
	
	public List<Msg> getMessages() {
		List<Msg> lst = new ArrayList< Msg >();
		lst.addAll( this._lstMsg );
		
		this._lstMsg.clear();
				
		return lst;
	}
}