package com.app.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.StateOfSetStates;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Symbol;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;
import com.app.punto2.parser.ParserTopDownNoRecursive;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Variable;

public class ObjectConverter {
	
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
		
		/* New no deterministic finite automaton. */
		NFA a;
		
		/* Reader of file. */
		BufferedReader br;
		
		try {
			
			/* Set the file to read. */
			this._lstMsg.add( new Msg( Msg.INFO, this, "Comenzando a leer automata desde archivo." ) ) ;
			br = new BufferedReader( new FileReader( file ) );
			
			//String line = br.readLine();
			/* index of line to read the information. */
			String line;
			int numberOfLine = 1;
			
			while( ( line = br.readLine() ) != null ) {
			
				this._lstMsg.add( new Msg( Msg.INFO, this, "Leyendo linea: " + numberOfLine ) );
				
				/* Read the alphabet. */
				if ( numberOfLine == 1 ) {
					/* The alphabet read the information and convert the line in object alphabet. */
					alphabet = Alphabet.generateFrom(line);
					this._lstMsg.add( new Msg( Msg.INFO, this, "Alfabeto leido [OK]." ) );
				}
				
				/* Read quantity of states. */
				if ( numberOfLine == 2 ) {
					/* Parse the string to number of states (State "Q_0" is always the initial state). */
					int qtyOfStates = Integer.parseInt( line.replaceAll( " ", "" ) );
					
					/* Generate the states from number. */
					lstState = StateA.generateListStatesFromInt( qtyOfStates );
					
					/* LogWriter.writeLog( this, LogWriter.INFO, "Proceso generate NFA states finish [OK]" ); */
					this._lstMsg.add( new Msg( Msg.INFO, this, "Estado generados [OK]." ) );
					
					/* Search the start state and obtain this state. */
					for ( StateA s: lstState ) {
						if ( s.isStartState() ) {
							this._lstMsg.add( new Msg( Msg.INFO, this, "Estado inicial: " + s.getName() ) );
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
//				line = br.readLine();
				numberOfLine++;
			}
			
			br.close();
			this._lstMsg.add( new Msg( Msg.INFO, this, "Archivo leido." ) );
			
		} catch( Exception e ) {
			this._lstMsg.add( new Msg( Msg.ERROR, this, "Error al intenar generar el automata finito no deterministico" + Msg.getMessage( e ) ) );
			e.printStackTrace();
		}
		
		this._lstMsg.add( new Msg( Msg.INFO, this, "Creando automata." ) );
		/* Get a new no deterministic finite automaton. */
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
		
		boolean haveStateWithoutTf = true;
		
		/* The states from NFD exists in DFA. */
		boolean existsStateInAutomaton = false;
		
		/* Iterator of lsSetState because the list will increase the number of states. */
		int lstIterator = 0;
		
		/* State to add in the list and state to compare with each state in the list. */
		StateOfSetStates stateInDFA, stateFromNFA;

		while( haveStateWithoutTf == true ) {
			
			/* Get each of state in the list. */
			stateInDFA = lstSetStates.get( lstIterator );
			LogWriter.writeLog( this, 0, stateInDFA.getName() + ":" + stateInDFA.isFinalState() );
			
			/* Get each symbol into the alphabet. */
			for ( Symbol smb: alphabet.getSimbols() ) {
				
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
				haveStateWithoutTf = false;
			}
			
		}
		
		/* Create the new deterministic finite automaton. */
		DFA a = new DFA(alphabet, states, tfs, startState, fnlStates);
		
		/* Create the table of equivalences. */
		String[][] table = new String[ lstSetStates.size() ][2];
		
		for( int i = 0; i < lstSetStates.size(); i++  ) {
			table[i] = new String[] { lstSetStates.get( i ).getName(), lstSetStates.get( i ).getNamesStates() };
		}
		
		a.setEquivalenceTable( table );
		
		return a;
	}
	
	/**
	 * <b> Return set of states from transaction function in no deterministic finite automata. </b>**/
	private List< StateA > stateFromTfsNfa(StateOfSetStates state, Symbol symbol, NFA nfa) {
		
		List< StateA > lstState = new ArrayList< StateA >();
		
		/* Add to states each next state in each transaction function to state and symbol. */
		for ( StateA st: state.getStates() ) {
			lstState.addAll( nfa.doTransactionFunctionTo( st, symbol ) );
		}
		
		return lstState;
	}

	public ParserTopDownNoRecursive parserTopDownNoRecursiveFromFile( File file, String charEmpty ) {
		
		/* Create list to productions */
		List< Production > lstProd = new ArrayList< Production >();
		
		BufferedReader br;
		
		try {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Comienza la lectura del parser." ) );
			/* Read File. */
			br = new BufferedReader( new FileReader( file ) );
			
			String line;
			Variable leftVar;
			ProductionComponent[] right;
			Production prd;
			
			/* Read file line to line. */
			while( ( line = br.readLine() ) != null ) {
				
				this._lstMsg.add( new Msg( Msg.INFO, this, "Leyendo linea: " + line ) );
				/* Split the production in left and right part. */
				String[] production = line.replaceAll( " ", "").split( "->" ); 
				
				/* Left part(variable). */
				leftVar = new Variable( production[0] );
				Log.WriteFileLog( new Msg( Msg.INFO, this, leftVar.toString()));
				
				/* Right part(production). */
				right = ProductionComponent.arrayPrdCompFromString( production[1] );
				
				prd = new Production( leftVar, right );
				
				lstProd.add( prd );
			}
			
		} catch( Exception e ) {
			this._lstMsg.add( new Msg( Msg.ERROR, this, "Problemas con la lectura del parser." ) );
			this._lstMsg.add( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
		
		ParserTopDownNoRecursive parse = new ParserTopDownNoRecursive( lstProd, charEmpty );
		
		this._lstMsg.addAll( parse.getMsgs() );
		
		return parse;
	}
	
	public List<Msg> getMessages() {
		List<Msg> lst = new ArrayList< Msg >();
		lst.addAll( this._lstMsg );
		
		this._lstMsg.clear();
				
		return lst;
	}
}