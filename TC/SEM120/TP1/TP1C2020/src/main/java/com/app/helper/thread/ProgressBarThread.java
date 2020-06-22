package com.app.helper.thread;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.swing.JProgressBar;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.dfn.components.TransactionFunctionDFA;
import com.app.punto1.automaton.nfa.components.TransactionFunctionNFA;
import com.app.punto2.parser.Parser;
import com.app.punto2.parser.ParserTopDownNoRecursive;
import com.app.punto2.parser.components.First;
import com.app.punto2.parser.components.Follow;
import com.app.punto2.parser.components.Production;

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
	private boolean[] _options;
	private Component[] _components;
	
//	public ProgressBarThread( File file, NFA nfa, JProgressBar bar ) {
//		this._file = file;
//		this._objectClass = _NFA_STRING;
//		this._nfa = nfa;
//		this._bar = bar;
//	}
//	
//	public ProgressBarThread( File file, DFA dfa, JProgressBar bar ) {
//		this._file = file;
//		this._objectClass = _DFA_STRING;
//		this._dfa = dfa;
//		this._bar = bar;
//	}
//	
//	public ProgressBarThread( File file, ParserTopDownNoRecursive parser, JProgressBar bar ) {
//		this._file = file;
//		this._objectClass = _PARSER_STRING;
//		this._parser = parser;
//		this._bar = bar;
//	}
	
	public ProgressBarThread( File file, Object object, JProgressBar bar, boolean[] options, Component[] components ) {
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
		this._options = options;
		this._components = components;
	}
	
	@Override
	public void run() {
		
		this.enabledAll( false );
		
		if( this._objectClass.equals( _NFA_STRING ) ) {
			System.out.println( "fffff" );
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
			File exportFile = this.exportFile( "AutomataFinitoNoDeterministico" );
			
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( exportFile ) , StandardCharsets.UTF_16 ) );
			String writeLine;
			
			if( this._options[0] == true ) {
				
				bw.write( "Alfabeto" );
				bw.newLine();
				
				String[] alphabet = this._nfa.getAlphabet().getArrayStringOfSimbols();
				
				writeLine = alphabet[0];
				for( int i = 1; i < alphabet.length; i++ ) {
					writeLine += ", " + alphabet[i];
				}
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				bw.write( "Estados" );
				bw.newLine();
				
				writeLine = "";
				List< StateA > states = this._nfa.getStates();
				for( StateA state: states ) {
					writeLine += state.getName() + ", ";
				}
				writeLine = writeLine.substring( 0, writeLine.length() - 2);
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				bw.write( "Estados Finales" );
				bw.newLine();
				
				writeLine = "";
				List< StateA > fnlStates = this._nfa.getFinalStates();
				for( StateA state: fnlStates ) {
					writeLine += state.getName() + ", ";
				}
				writeLine = writeLine.substring( 0, writeLine.length() - 2 );
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				for( TransactionFunctionNFA tf: this._nfa.getTfs() ) {
					bw.write( tf.toString() );
					bw.newLine();
				}
				
				bw.newLine();
				bw.newLine();
			}
		
			if( this._options[1] == true ) {
				
				bw.write( "Tabla de funciones de transición" );
				bw.newLine();
				
				String[] tfTableRows = this.getStringRowsOfTable( this._nfa.getTfTable() );
				for( int i = 0; i < tfTableRows.length; i++ ) {
					bw.write( tfTableRows[i] );
					bw.newLine();
				}
				
				bw.newLine();
			}
			
			int lines = this.countLineFrom( this._file );
			
			BufferedReader br = new BufferedReader( new FileReader( this._file ) );

			bw.write( "Proceso de evaluación de inputs" );
			bw.newLine();
			
			int lineNumber = 0;
			String readLine;
			
			while( ( readLine = br.readLine() ) != null ) {
				
				bw.write( "Evaluando input: " + readLine );
				bw.newLine();
				
				if( this._nfa.accept( new Input( readLine ) ) ) {
					if( this._options[2] == true ) {
						for( Msg message: this._nfa.getMsgs() ) {
							bw.write( message.getDate() + message.getMsg() );
							bw.newLine();
						}	
					} else {
						bw.write( "Input correcto!!" );
						bw.newLine();
					}
				} else {
					bw.write( "Input no aceptado" );
					bw.newLine();
				}
				
				bw.newLine();
				
				lineNumber++;
				this._bar.setValue( lineNumber * 100 / lines );
			}
			
			bw.close();
			br.close();
				
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
	}
	
	/** Export DFA information. **/
	private void exportDfaSolution() {
		
		try {
			File exportFile = this.exportFile( "AutomataFinitoDeterministico" );
			
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( exportFile ) , StandardCharsets.UTF_16 ) );
			String writeLine;
			
			if( this._options[0] == true ) {
				
				bw.write( "Alfabeto" );
				bw.newLine();
				
				String[] alphabet = this._dfa.getAlphabet().getArrayStringOfSimbols();
				
				writeLine = alphabet[0];
				for( int i = 1; i < alphabet.length; i++ ) {
					writeLine += ", " + alphabet[i];
				}
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				bw.write( "Estados" );
				bw.newLine();
				
				writeLine = "";
				List< StateA > states = this._dfa.getStates();
				for( StateA state: states ) {
					writeLine += state.getName() + ", ";
				}
				writeLine = writeLine.substring( 0, writeLine.length() - 2);
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				bw.write( "Estados Finales" );
				bw.newLine();
				
				writeLine = "";
				List< StateA > fnlStates = this._dfa.getFinalStates();
				for( StateA state: fnlStates ) {
					writeLine += state.getName() + ", ";
				}
				writeLine = writeLine.substring( 0, writeLine.length() - 2 );
				bw.write( writeLine );
				
				bw.newLine();
				bw.newLine();
				
				for( TransactionFunctionDFA tf: this._dfa.getTfs() ) {
					bw.write( tf.toString() );
					bw.newLine();
				}
				
				bw.newLine();
				bw.newLine();
			}
		
			if( this._options[1] == true ) {
				
				bw.write( "Tabla de funciones de transición" );
				bw.newLine();
				
				String[] tfTableRows = this.getStringRowsOfTable( this._dfa.getTfTable() );
				for( int i = 0; i < tfTableRows.length; i++ ) {
					bw.write( tfTableRows[i] );
					bw.newLine();
				}
				
				bw.newLine();
			}
			
			int lines = this.countLineFrom( this._file );
			
			BufferedReader br = new BufferedReader( new FileReader( this._file ) );

			bw.write( "Proceso de evaluación de inputs" );
			bw.newLine();
			
			int lineNumber = 0;
			String readLine;
			
			while( ( readLine = br.readLine() ) != null ) {
				
				bw.write( "Evaluando input: " + readLine );
				bw.newLine();
				
				if( this._dfa.accept( new Input( readLine ) ) ) {
					if( this._options[2] == true ) {
						for( Msg message: this._dfa.getMsgs() ) {
							bw.write( message.getDate() + message.getMsg() );
							bw.newLine();
						}	
					} else {
						bw.write( "Input correcto!!!" );
						bw.newLine();
					}
				} else {
					bw.write( "Input no aceptado" );
					bw.newLine();
				}
				
				bw.newLine();
				
				lineNumber++;
				this._bar.setValue( lineNumber * 100 / lines );
			}
			
			bw.close();
			br.close();
				
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
	}
	
	private void exportParserSolution() {
		System.out.println( "parser" );
		
		try {
			File exportFile = this.exportFile( "ParserNoRecursivo" );
			
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( exportFile ) , StandardCharsets.UTF_16 ) );
			
			if( this._options[0] == true ) {
				
				bw.write( "Producciones" );
				bw.newLine();
				
				for( Production prd: this._parser.getProductions() ) {
					bw.write( prd.toString() );
					bw.newLine();
				}
				
				bw.newLine();
			}
		
			if( this._options[1] == true ) {
				
				bw.write( "Tabla de first" );
				bw.newLine();
				
				/* First table. */
				First[] firsts = this._parser.getArrayFirsts();
				for( int i = 0; i < firsts.length; i++ ) {
					bw.write( firsts[i].toString() );
					bw.newLine();
				}
				
				bw.newLine();
				bw.write( "Tabla de follow" );
				bw.newLine();
				
				/* Follow Table */
				Follow[] follows = this._parser.getArrayFollows();
				for( int i = 0; i < follows.length; i++ ) {
					bw.write( follows[i].toString() );
					bw.newLine();
				}
				
				bw.newLine();
				bw.write( "Tabla de parsing." );
				bw.newLine();
				
				/* Parsing table. */
				String[] tableRows = this.getStringRowsOfTable( this._parser.getParsingTable() );
				for( int i = 0; i < tableRows.length; i++ ) {
					System.out.println( tableRows[i] );
					bw.write( tableRows[i] );
					bw.newLine();
				}
				
				bw.newLine();
			}
		
			/* Input evaluation. */
			int lines = this.countLineFrom( this._file );
			
			BufferedReader br = new BufferedReader( new FileReader( this._file ) );

			bw.write( "Proceso de evaluación de inputs." );
			bw.newLine();
			
			int lineNumber = 0;
			String readLine;
			
			while( ( readLine = br.readLine() ) != null ) {
				
				bw.write( "Evaluando input: " + readLine );
				bw.newLine();

				if( this._parser.acceptString( readLine ) ) {
					if( this._options[2] == true ) {
						String[] validationTableRows = this.getStringRowsOfTable( this._parser.getValidationProcessTable() );
						for( int i = 0; i < validationTableRows.length; i++ ) {
							bw.write( validationTableRows[i] );
							bw.newLine();
						}
						for( Msg message: this._parser.getMsgs() ) {
							bw.write( message.getMsg() );
							bw.newLine();
						}
					} else {
						bw.write( "Input aceptado!!!" );
						bw.newLine();
					}
				} else {
					if( this._options[2] == true ) {
						String[] validationTableRows = this.getStringRowsOfTable( this._parser.getValidationProcessTable() );
						for( int i = 0; i < validationTableRows.length; i++ ) {
							bw.write( validationTableRows[i] );
							bw.newLine();
						}
						for( Msg message: this._parser.getMsgs() ) {
							bw.write( message.getMsg() );
							bw.newLine();
						}
					}
					bw.write( "Input no aceptado" );
					bw.newLine();
				}
				
				bw.newLine();
				
				lineNumber++;
				this._bar.setValue( lineNumber * 100 / lines );
			}
			
			br.close();
			bw.close();
			
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
		
	}
	
	private File exportFile( String objectName ) {
		
		String importFileName = this._file.getName();
		String exportFileName = this._file.getName().substring( 0, this._file.getName().length() - 4 ) + objectName + "export.txt";
		String exportFilePath = this._file.getAbsolutePath().replaceAll( importFileName, exportFileName );
		return new File( exportFilePath );
		
	}
	
	private int countLineFrom( File file ) {
		
		int total = 0;
		
		try {
			BufferedReader br = new BufferedReader( new FileReader( file ) );
			
			while( br.readLine() != null ) {
				total++;
			}
			br.close();
		} catch( Exception e ) {
			Log.WriteFileLog( new Msg( Msg.ERROR, this, Msg.getMessage( e ) ) );
		}
		
		return total;
	}
	
	private String[] getStringRowsOfTable( String[][] table ) {
		
		String[] tableRows = new String[ ( table.length * 2 ) + 1 ];
		String row = "";
		
		int length = 0;
		
		for( int i = 0; i < table.length; i++ ) {
			for( int j = 0; j < table[i].length; j++ ) {
				if( ( table[i][j].length() > length ) == true ) {
					length = table[i][j].length();
				}
			}
		}
		
		row = "-";
		for( int i = 0; i < ( table[0].length ) * ( length + 1 ); i++ ) {
			row += "-";
		}
		tableRows[0] = row;
		
		for( int i = 0; i < table.length; i++ ) {
			row = "|";
			for( int j = 0; j < table[i].length; j++ ) {
				row += table[i][j].toString();
				for( int k = table[i][j].toString().length(); k <= length - 1; k++ ) {
					row += " ";
				};
				row += "|";
			}
			tableRows[ ( i * 2 ) + 1 ] = row;
			
			row = "-";
			for( int j = 0; j < ( table[i].length ) * ( length + 1 ); j++ ) {
				row += "-";
			}
		
			tableRows[ ( i * 2 ) + 2 ] = row;
		}
		
		return tableRows;
	}
}