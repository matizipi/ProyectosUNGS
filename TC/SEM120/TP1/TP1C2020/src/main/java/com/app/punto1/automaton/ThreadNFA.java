package com.app.punto1.automaton;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Simbol;

public class ThreadNFA extends Thread {
	
//	private Thread _thr;
	private String _threadName;
	
	private NFA _automaton;
	private StateA _coState;
	private Input _coInp;
	private boolean _accept = false;
	private List< StateA > _lstAcceptState = null;
	
	public ThreadNFA( NFA automaton, StateA state, Input inp, String name ) {
		Log.WriteFileLog( new Msg( Msg.INFO, this, name + ": Thread creado." + " Con parametros " + state.getName() ) );
		this._threadName = name;
		this._automaton = automaton;
		this._coState = state;
		/* Copy the input for no modify the pivot for each threads. */
		this._coInp = inp.copy();
		this._lstAcceptState = new ArrayList<StateA>();
	}
	
	public boolean getResult() {
		return this._accept;
	}
	
	public void run() {
				
		Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": Running. Pivot: " + this._coInp.getPivot() ) );
		/* Process the input, if the input has symbols to read. */
		if( this._coInp.hasNextSymbol() ) {
			
			/* Get the symbol to process. */
			Simbol smbl = this._coInp.readSymbol();
			
			Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": Read " + smbl.getSimbol() ) );
			/* Get list of next states for this state and this symbol. */
			List< StateA > lstState = this._automaton.doTransactionFunctionTo( this._coState, smbl );
			
			/* Make a list of new threads. */
			ThreadNFA[] threads = new ThreadNFA[ lstState.size() ];
			
			int i = 0;
			/* Make a new thread and run it, for each state in list of next states. */
			for( StateA stt : lstState ) {
				Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": Crea " + this._threadName + "." + i ) );
				threads[i] = new ThreadNFA( this._automaton, stt, this._coInp, this._threadName + "." + i);
				threads[i].start();
				
				i++;
			}
			
			/* String log. */
			String waitingfor = "";
			boolean waiting = true;
			
			/* Wait for each thread to end the process. */
			while( waiting ) {
				waiting = false;

				waitingfor = "Esperando por: ";
				/* Ask if each thread is finishing. */
				for( i = 0; i < threads.length; i++) {
					if ( threads[i].isAlive() ) {
						waiting = true;
						waitingfor += threads[i].getThNFAName() + ", ";
					}
				}
				Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + " is waiting: " + waitingfor ) );
			}
			
			/* Ask if any thread accept the input. */
			for( i = 0; i < threads.length; i++ ) {
				if( threads[i].accept() ) {
					Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": Acepta por " + threads[i].getThNFAName() ) );
					this._accept = true;
					if ( this._lstAcceptState.isEmpty() ) {
						this._lstAcceptState.add( this._coState );
						this._lstAcceptState.addAll( threads[i].getAcceptStates() );
					}
				}
			}
			
		} else {
			if ( this._coState.isFinalState() ) {
				Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": acepta." ) );
				this._lstAcceptState.add( this._coState );
				this._accept = true;
			}
		}
		
		Log.WriteFileLog( new Msg( Msg.INFO, this, this._threadName + ": thread exiting" ) );
	}
	
//	public void start () {
//		LogWriter.writeLog( this, 0, this._threadName + ": Starting" );
//		if ( this._thr == null) {
//			this._thr = new Thread (this, this._threadName );
//			this._thr.start ();
//		}
//	}
		
	public boolean accept() {
		return this._accept;
	}
	
	public String getThNFAName() {
		return this._threadName;
	}
	
	public List< StateA > getAcceptStates() {
		return this._lstAcceptState;
	}
	
	public String getStringAcceptStates() {
		
		String str = "";
		
		for( StateA state: this._lstAcceptState ) {
			str += state.getName() + ", ";
		}
		
		return str.substring( 0, str.length() - 2 );
	}
}