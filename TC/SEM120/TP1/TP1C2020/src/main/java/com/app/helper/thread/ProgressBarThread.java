package com.app.helper.thread;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JProgressBar;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Symbol;
import com.app.punto1.automaton.nfa.components.TransactionFunctionNFA;
import com.app.punto2.parser.Parser;
import com.app.punto2.parser.ParserTopDownNoRecursive;
import com.app.ui.controller.ExportFileController;

public class ProgressBarThread extends Thread {

	private static String _NFA_STRING = NFA.class.getSimpleName();
	private static String _DFA_STRING = DFA.class.getSimpleName();
	private static String _PARSER_STRING = Parser.class.getSimpleName();
	
	private File _file;
	private String _objectClass;
	private NFA _nfa;
	private DFA _dfa;
	private ParserTopDownNoRecursive _parser;
	private JProgressBar _bar;
	private Component[] _components;
	
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
	
	public ProgressBarThread( File file, ParserTopDownNoRecursive parser, JProgressBar bar ) {
		this._file = file;
		this._objectClass = _PARSER_STRING;
		this._parser = parser;
		this._bar = bar;
	}
	
	public ProgressBarThread( File file, Object object, JProgressBar bar, Component[] components ) {
		this._file = file;
		
		if( object.getClass().equals( NFA.class ) ) {
			System.out.println( "-" );
			this._objectClass = _NFA_STRING;
			this._nfa = ( NFA ) object;
		} else if( object.getClass().equals( DFA.class ) ) {
			System.out.println( "--" );
			this._objectClass = _DFA_STRING;
			this._dfa = ( DFA ) object;
		} else if( object.getClass().equals( ParserTopDownNoRecursive.class ) ) {
			System.out.println( "---" );
			this._objectClass = _PARSER_STRING;
			this._parser = ( ParserTopDownNoRecursive ) object;
		}
		
		this._bar = bar;
		this._components = components;
	}
	
	@Override
	public void run() {
		
		this.enabledAll( false );
		
		if( this._objectClass.equals( _NFA_STRING ) ) {
			this.exportNfaSolution();
		} else if( this._objectClass.equals( _DFA_STRING ) ) {
			this.exportDfaSolution();
		} else if( this._objectClass.equals( _PARSER_STRING ) ) {
			this.exportParserSolution();
		}
		
		this.enabledAll( true );
	}
	
	private void enabledAll( boolean b ) {
		
		for( int i = 0; i < this._components.length; i++ ) {
			this._components[ i ].setEnabled( b );
		}
		
	}
	
	private void exportNfaSolution() {
		System.out.println( "nfa" );
		
		try {
			File exportFile = new File( this._file.getAbsolutePath()
					.replaceAll( this._file.getName(), this._file.getName().substring( 0, this._file.getName().length() - 4 ) + "export.txt" ) );
			BufferedWriter bw = new BufferedWriter( new FileWriter( exportFile ) );
			
			/* println from file. */
			if( true ) {
				bw.write( "Alfabeto." );
				String[] alphabet = this._nfa.getAlphabet().getArrayStringOfSimbols();
				String writeLine = alphabet[0];
				for( int i = 1; i < alphabet.length; i++ ) {
					writeLine += ", " + alphabet[i];
				}
				
				bw.write( writeLine );
				
				bw.newLine();
				
				bw.write( "Estados." );
				bw.newLine();
				writeLine = "";
				List< StateA > states = this._nfa.getStates();
				for( StateA state: states ) {
					writeLine += state.toString() + ", ";
				}
				writeLine.substring( writeLine.length() - 2 , writeLine.length() );
				
				bw.write( writeLine );
				
				bw.newLine();
				
				bw.write( "Estados Finales." );
				bw.newLine();
				writeLine = "";
				List< StateA > fnlStates = this._nfa.getFinalStates();
				for( StateA state: fnlStates ) {
					writeLine += state.toString() + ", ";
				}
				writeLine.substring( writeLine.length() - 2, writeLine.length() );
				
				bw.newLine();
				
				for( TransactionFunctionNFA tf: this._nfa.getTfs() ) {
					bw.write( tf.toString() );
				}
			}
			
			/* tables in view. */
			if( true ) {
				bw.newLine();
				
				String[][] table = this._nfa.getTfTable();
				for( int i = 0; i < table.length; i++ ) {
					bw.write( table[i].toString() );
					bw.newLine();
				}
			}
			
			BufferedReader br = new BufferedReader( new FileReader( this._file ) );
	
			int total = 0, lineNumber = 0;
			while( br.readLine() != null ) {
				total++;
			}
			
			br.close();
			
			
			br = new BufferedReader( new FileReader( this._file ) );
			
			String readLine;
			
			while( ( readLine = br.readLine() ) != null ) {
				
				bw.write( readLine );
				bw.newLine();
				bw.write( "Evaluando" );
				bw.newLine();
				if( this._nfa.accept( new Input( readLine ) ) ) {
					for( Msg message: this._nfa.getMsgs() ) {
						bw.write( message.getDate() + message.getMsg() );
					}
				} else {
					bw.write( "Input no aceptado" );
				}
				
				lineNumber++;
				this._bar.setValue( lineNumber * 100 / total );
			}
			
			bw.close();
			br.close();
			
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
	}
	
	private void exportDfaSolution() {
		System.out.println( "dfa" );
		for( int i = 0; i <= 100; i++ ) {
			try {
				sleep(100);
			} catch( Exception e ) {
				
			}
			
			this._bar.setValue( i );
		}
	}
	
	private void exportParserSolution() {
		System.out.println( "parser" );
		for( int i = 0; i <= 100; i++ ) {
			try {
				sleep(100);
			} catch( Exception e ) {
				
			}
			
			this._bar.setValue( i );
		}
	}
}