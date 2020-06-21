package com.app.ui.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.app.common.Msg;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Symbol;
import com.app.ui.view.AutomatonDFView;

public class AutomatonDFAController implements ControllerImpl {

	private AutomatonDFView _frame;
	private DFA _a;
	
	public AutomatonDFAController() {
		this._frame = new AutomatonDFView();
		
		this.addListener();
	}
	
	private void addListener() {
		this._frame.getBtnInput().addActionListener( this );
		this._frame.getBtnInputFromFile().addActionListener( this );
		this._frame.getBtnEquivalenceTable().addActionListener( this );
		this._frame.getBtnReturn().addActionListener( this );
		this._frame.getBtnReturnToMainMenu().addActionListener( this );
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( arg0.getSource() == this._frame.getBtnInput() ) {
			this.processInput();
		} else if( arg0.getSource() == this._frame.getBtnInputFromFile() ) {
			this.exportFile();
		} else if( arg0.getSource() == this._frame.getBtnEquivalenceTable() ) {
			this.showEquivalenceTable();
		} else if( arg0.getSource() == this._frame.getBtnReturn() ) {
			AutomatonNFAController ctr = new AutomatonNFAController();
			this.finish();
			ctr.start();
		} else if( arg0.getSource() == this._frame.getBtnReturnToMainMenu() ) {
			MainController ctr = new MainController();
			this.finish();
			ctr.start();
		}
	}

	@Override
	public void start() {
		this._frame.setVisible(true);
		
		if ( this._a != null ) {
			this.loadAutomatonInView();
		}
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}

	private void processInput() {
		
		Input input = new Input( this._frame.getTxtInput().getText().toString() );
		
		if ( this._a.accept( input ) == true ) {
			this.printMessages( this._a.getMsgs() );
			this.printMessage( new Msg( Msg.INFO, this, "input: " + input + " aceptado.") );
		} else {
			this.printMessages( this._a.getMsgs() );
			this.printMessage( new Msg( Msg.ERROR, this, "input: " + input + " no aceptado.") );
		}
		
	}
	
	private void exportFile() {
		ExportFileController ctr = new ExportFileController( this._frame, this._a );
		ctr.start();
	}
	
	private void showEquivalenceTable() {
		EquivalenceTableController ctr = new EquivalenceTableController( this._frame, this._a.getEquivalenceTable() );
		ctr.start();
	}
	
	private void loadAutomatonInView() {
		
		if ( this._a.isCorrectly() == true ) {
			this.loadAlphabet();
			
			this.loadStates();
			
			this.loadTransactionFunctions();
			
		} else {
			this.printMessages( this._a.getMsgs() );
		}
		
	}
	
	private void loadAlphabet() {
		
		DefaultTableModel dtm = this._frame.getDtmAlphabet();
		Alphabet alp = this._a.getAlphabet();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( AutomatonDFView.ALPHABET_TITLE );
		
		String[] line;
		for( Symbol smb: alp.getSimbols() ) {
			line = new String[] { smb.getSimbol() };
			dtm.addRow( line );
		}
		
		this._frame.getTblAlphabet().setModel( dtm );
	}
	
	private void loadStates() {
		
		DefaultTableModel dtm = this._frame.getDtmState();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( AutomatonDFView.STATE_TITLE );
		
		String[] line;
		for( StateA stt: this._a.getStates() ) {
			line = new String[] { stt.getName()
					, stt.isStartState()?"Yes":"No"
					, stt.isFinalState()?"Yes":"No" };
			dtm.addRow( line );
		}
		
		this._frame.getTblState().setModel( dtm );
	}
	
	private void loadTransactionFunctions() {
		
		DefaultTableModel dtm = this._frame.getDtmTf();
		
		String[][] tableTf = this._a.getTfTable();
		String[] titles = tableTf[0];
		titles[ 0 ] = AutomatonDFView.TF_TITLE[ 0 ];
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( titles );
		
		String[] row;
		for( int i = 1; i < tableTf.length; i++ ) {
			row = new String[ tableTf[ i ].length ];
			for( int j = 0; j < tableTf[ i ].length; j++ ) {
				row[ j ] = tableTf[ i ][ j ];
			}
			dtm.addRow( row );
		}
		
		this._frame.getTblTf().setModel( dtm );
	}
	
	private void printMessages( List< Msg > msgs ) {
		
		for( Msg m: msgs ) {
			this.printMessage( m );
		}
		
	}
	
	private void printMessage( Msg msg ) {
		
		DefaultTableModel dtm = this._frame.getDtmLog();
		
		String[] row = new String[ 2 ];
		
		row[ 0 ] = msg.getType();
		row[ 1 ] = msg.getDate() /*+ msg.getObject()*/ + " - " + msg.getMsg();
		
		dtm.addRow( row );
		
		this._frame.getTblLog().setModel( dtm );
	}
	
	public void setDFA(DFA dfaFromNFA) {
		this._a = dfaFromNFA;
	}
}