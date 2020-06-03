package automaton;

import java.util.ArrayList;
import java.util.List;

import automaton.components.StateA;
import automaton.components.alphabet.Alphabet;
import helper.LogWriter;
import helper.Msg;

public abstract class Automaton {
	
	protected Alphabet _coAlphabet;
	protected List<StateA> _coLstState;
	protected StateA _coStartState;
	protected List<StateA> _coLstFnlState;
	
	protected boolean _bStartState = false;
	protected boolean _bFnlStates = false;
	protected List< Msg > _lstMsg = new ArrayList<Msg>();
	
	public Automaton(Alphabet alphabet, List<StateA> states, StateA startState, List<StateA> fnlStates) {
		this._lstMsg.add( new Msg( Msg.INFO, this, "Comienza creación del automata.") );
		this._coAlphabet = alphabet;
		this._coLstState = states;
		this._coStartState = startState;
		this._coLstFnlState = fnlStates;
		
		this._lstMsg.add( new Msg( Msg.INFO, this, "Comienza seteo del estado inicial." ) );
		this.setInitState();
		
		this._lstMsg.add( new Msg( Msg.INFO, this, "Comienza seteo de estados finales.") );
		this.setFinalStates();
		
		if ( this._bStartState == true && this._bFnlStates == true ) {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Automaton [OK]." ) );
			LogWriter.writeLog( this,  LogWriter.INFO, "Automaton [OK]." );
		} else {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Creación del Automata [FAIL]." ) );
			LogWriter.writeLog( this,  LogWriter.ERROR, "ERRORES." );
		}
	}
	
	private void setInitState() {
		
		this._bStartState = false;
		
		this._coLstState.forEach( a -> {
			if ( this._coStartState.equals( a ) ) {
				this._bStartState=true;
			}
		});
		
		if( this._bStartState == false ) {
			this._lstMsg.add( new Msg( Msg.INFO, this, "No existe el estado " + this._coStartState.getName() + "." ) );
			this._lstMsg.add( new Msg( Msg.INFO, this, "Estado Inicial [FAID]." ) );
		} else {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Estado Inicial [OK]." ) );
		}
	}
	
	private void setFinalStates() {
		
		this._bFnlStates = true;
		
		this._coLstFnlState.forEach(fnl -> {
			boolean existState = false;
			
			for( StateA state: this._coLstState ) {
				if ( fnl.equals( state ) ) {
					state.FinalState(true);
					existState = true;
					break;
				}
			}
			if ( existState == false ) {
				this._bStartState = false;
				this._lstMsg.add( new Msg( Msg.ERROR, this, fnl.getName() + " no es un estado en el automata" ) ) ;
				this._bFnlStates = false;
			}
		});
		
		if( this._bFnlStates ) {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Estados Finales [OK]." ) );
		} else {
			this._lstMsg.add( new Msg( Msg.INFO, this, "Estados Finales [FAIL]." ) );
		}
	}
	
	public abstract String[][] getTfTable();
	
	abstract public boolean accept(String input);
	
	public boolean isCorrectly() {
		return this._bStartState&&this._bFnlStates;
	}
	
	public Alphabet getAlphabet() {
		return this._coAlphabet;
	}

	public List< StateA > getStates() {
		return this._coLstState;
	}
	
	public StateA getStartState() {
		return this._coStartState;
	}
	
	public List< Msg > getMsgs() {
		return this._lstMsg;
	}
}