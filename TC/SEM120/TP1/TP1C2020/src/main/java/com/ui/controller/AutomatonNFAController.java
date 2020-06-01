package com.ui.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.table.DefaultTableModel;

import com.ui.view.NonDeterministicAutomatonView;

import automaton.AutomatonConverter;
import automaton.NFA;
import automaton.components.StateA;
import automaton.components.alphabet.Simbol;

public class AutomatonNFAController implements ControllerImpl {

	private NonDeterministicAutomatonView _frame;
	private AutomatonConverter _ac = new AutomatonConverter();
	private NFA _a = null;
	
	public AutomatonNFAController() {
		this._frame = new NonDeterministicAutomatonView();
		this.addListener();
	}
	
	private void addListener() {
		this._frame.getBtnLoadFile().addActionListener( this );
		this._frame.getBtnConvertToDFA().addActionListener( this );
		this._frame.getBtnInput().addActionListener( this );
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( arg0.getSource() == this._frame.getBtnLoadFile() ) {
			this.loadFile();
		} else if ( arg0.getSource() == this._frame.getBtnConvertToDFA() ) {
			this.convertToDFA();
		} else if ( arg0.getSource() == this._frame.getBtnInput() ) {
			this.processInput();
		}
	}

	@Override
	public void start() {
		this._frame.setVisible( true );
		if ( this._a != null ) {
			this.loadAutomtonIntoView();
		}
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}
	
	private void loadFile() {
		String sFile = this._frame.getTxtLoadFile().getText();
		File file = new File( sFile );
		this._a = _ac.newNFAFromFile( file );
		
		this.loadAutomtonIntoView();
	}
	
	private void loadAutomtonIntoView() {
		
		this.loadTableAlphabet();
		
		this.loadTableState();
		
		this.loadTableTf();
	}
	
	private void loadTableAlphabet() {
		
		DefaultTableModel dtm = this._frame.getDtmAlphabet();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( NonDeterministicAutomatonView.ALPHABET_TITLE );
		
		for( Simbol smb: this._a.getAlphabet().getSimbols() ) {
			Object[] fila = { smb.getSimbol() };
			dtm.addRow( fila );
		}
		
		this._frame.getTblAlphabet().setModel( dtm );
	}
	
	private void loadTableState() {
		
		DefaultTableModel dtm = this._frame.getDtmStates();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( NonDeterministicAutomatonView.STATE_TITLE );
		
		for( StateA stt: this._a.getStates() ) {
			Object[] fila = { stt.getName(), stt.isStartState()?"Yes":"No", stt.isFinalState()?"Yes":"No" };
			dtm.addRow( fila );
		}
		
		this._frame.getTblStates().setModel( dtm );
		
	}
	
	private void loadTableTf() {
		
		DefaultTableModel dtm = this._frame.getDtmTransactionFunction();
		
		String[][] tfTable = this._a.getTfTable();
		String[] title = this._a.getTfTable()[0];
		title[0] = NonDeterministicAutomatonView.TF_TITLE[0];
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( title );
		
		String[] fila;
		for( int i = 1; i < tfTable.length; i++ ) {
			fila = new String[ tfTable[ i ].length ];
			for( int j = 0; j < tfTable[ i ].length ; j++ ) {
				fila[ j ] = tfTable[ i ][ j ];
			}
			
			dtm.addRow( fila );
		}
		
		this._frame.getTblTransactionFunction().setModel( dtm );		
	}
	
	private void convertToDFA() {
		AutomatonDFAController ctr = new AutomatonDFAController();
		ctr.setDFA( this._ac.DFAFromNFA( this._a ) );
		this.finish();
		ctr.start();
	}
	
	private void processInput() {
		
		DefaultTableModel dtm = this._frame.getDtmLog();
		
		dtm.addRow( new String[] { "[INFO ]", "Correcto." } );
	}
}