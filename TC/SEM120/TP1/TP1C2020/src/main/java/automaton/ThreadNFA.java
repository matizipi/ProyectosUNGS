package automaton;

import java.util.List;

import automaton.components.StateA;
import automaton.components.alphabet.Input;
import automaton.components.alphabet.Simbol;
import helper.LogWriter;

public class ThreadNFA extends Thread {
	
//	private Thread _thr;
	private String _threadName;
	
	private NFA _automaton;
	private StateA _coState;
	private Input _coInp;
	private boolean _accept = false;
	
	public ThreadNFA( NFA automaton, StateA state, Input inp, String name ) {
		LogWriter.writeLog( this, 0, name + ": Thread creado." + " Con parametros " + state.getName());
		this._threadName = name;
		this._automaton = automaton;
		this._coState = state;
		/* Copy the input for no modify the pivot for each threads. */
		this._coInp = inp.copy();
	}
	
	public boolean getResult() {
		return this._accept;
	}
	
	public void run() {
				
		LogWriter.writeLog( this, 0, this._threadName + ": Running. Pivot: " + this._coInp.getPivot() );
		/* Process the input, if the input has symbols to read. */
		if( this._coInp.hasNextSymbol() ) {
			
			/* Get the symbol to process. */
			Simbol smbl = this._coInp.readSymbol();
			
			LogWriter.writeLog( this, 0, this._threadName + ": Read " + smbl.getSimbol() );
			/* Get list of next states for this state and this symbol. */
			List< StateA > lstState = this._automaton.doTransactionFunctionTo( this._coState, smbl );
			
			/* Make a list of new threads. */
			ThreadNFA[] threads = new ThreadNFA[ lstState.size() ];
			
			int i = 0;
			/* Make a new thread and run it, for each state in list of next states. */
			for( StateA stt : lstState ) {
				LogWriter.writeLog( this, 0, this._threadName + ": Crea " + this._threadName + "." + i);
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
				LogWriter.writeLog( this, 0, this._threadName + " is waiting: " + waitingfor);
			}
			
			/* Ask if any thread accept the input. */
			for( i = 0; i < threads.length; i++ ) {
				if( threads[i].accept() ) {
					LogWriter.writeLog(this, 0, this._threadName + ": Acepta por " + threads[i].getThNFAName());
					this._accept = true;
				}
			}
			
		} else {
			if ( this._coState.isFinalState() ) {
				LogWriter.writeLog( this, 0, this._threadName + ": acepta.");
				this._accept = true;
			}
		}
		
		LogWriter.writeLog( this, 0, this._threadName + ": thread exiting" );
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
}