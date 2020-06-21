package com.app.helper.thread;

import java.io.File;

import javax.swing.JProgressBar;

import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto2.parser.Parser;

public class ProgressBarThread extends Thread {

	private static String _NFA_STRING = NFA.class.getSimpleName();
	private static String _DFA_STRING = DFA.class.getSimpleName();
	private static String _PARSER_STRING = Parser.class.getSimpleName();
	
	private File _file;
	private String _objectClass;
	private NFA _nfa;
	private DFA _dfa;
	private Parser _parser;
	private JProgressBar _bar;
	
	public ProgressBarThread( File file, NFA nfa, JProgressBar bar ) {
		this._file = file;
		this._objectClass = _NFA_STRING;
		this._nfa = nfa;
		this._bar = bar;
	}
	
	public ProgressBarThread( File file, DFA dfa, JProgressBar bar ) {
		this._file = file;
		this._objectClass = _DFA_STRING;
		this._dfa = dfa;
		this._bar = bar;
	}
	
	public ProgressBarThread( File file, Parser parser, JProgressBar bar ) {
		this._file = file;
		this._objectClass = _PARSER_STRING;
		this._parser = parser;
		this._bar = bar;
	}
	
	@Override
	public void run() {
		
		if( this._objectClass.equals( _NFA_STRING ) ) {
			this.exportNfaSolution();
		} else if( this._objectClass.equals( _DFA_STRING ) ) {
			this.exportDfaSolution();
		} else if( this._objectClass.equals( _PARSER_STRING ) ) {
			this.exportParserSolution();
		}
		
	}
	
	private void exportNfaSolution() {
		for( int i = 0; i <= 100; i++ ) {
			try {
				sleep(100);
			} catch( Exception e ) {
				
			}
			
			this._bar.setValue( i );
		}
	}
	
	private void exportDfaSolution() {
		for( int i = 0; i <= 100; i++ ) {
			try {
				sleep(100);
			} catch( Exception e ) {
				
			}
			
			this._bar.setValue( i );
		}
	}
	
	private void exportParserSolution() {
		for( int i = 0; i <= 100; i++ ) {
			try {
				sleep(100);
			} catch( Exception e ) {
				
			}
			
			this._bar.setValue( i );
		}
	}
}