package com.app.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.ObjectConverter;
import com.app.punto1.automaton.components.StateA;
import com.app.punto1.automaton.components.alphabet.Input;
import com.app.punto1.automaton.components.alphabet.Simbol;
import com.app.ui.view.AutomatonNFView;
import com.app.ui.view.validation.AutomatonNFAValidation;

public class AutomatonNFAController extends Controller implements ControllerImpl {

	private AutomatonNFView _frame;
	private AutomatonNFAValidation _validation;
	private ObjectConverter _ac = new ObjectConverter();
	private NFA _a = null;
	
	public AutomatonNFAController() {
		this._frame = new AutomatonNFView();
		this.addListener();
		this._validation = new AutomatonNFAValidation( this._frame );
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
		this.showFirstComponent();
		
		this._frame.setVisible( true );
		if ( this._a != null ) {
			this.loadAutomtonIntoView();
		}
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}
	
	private void showFirstComponent() {
		this._frame.getBtnConvertToDFA().setVisible( false );
		this._frame.getLblInput().setVisible( false );
		this._frame.getTxtInput().setVisible( false );
		this._frame.getBtnInput().setVisible( false );
		this._frame.getSpAlphabet().setVisible( false );
		this._frame.getSpStates().setVisible( false );
		this._frame.getSpTransactionFunction().setVisible( false );
		this._frame.getSpLog().setVisible( false );
	}
	
	private void loadFile() {		
		String sFile = this._frame.getTxtLoadFile().getText();
		
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Cargado archivo: " + sFile ) );
		
		File file = new File( sFile );
		
		if ( file.exists() ) {
			this._a = this._ac.newNFAFromFile( file );
			this.printMessages( this._ac.getMessages() );
		} else {
			this.printMessage( new Msg( Msg.ERROR, this, "El archivo especificado no existe." ) );
		}
		
		this.loadAutomtonIntoView();
	}
	
	private void loadAutomtonIntoView() {
		
		this.printMessage( new Msg( Msg.INFO, this, "Comienza carga de alfabeto." ) );
		this.loadTableAlphabet();
		
		this.printMessage( new Msg( Msg.INFO, this, "Comienza carga de estados."));
		this.loadTableState();
		
		this.printMessage( new Msg( Msg.INFO, this, "Comienza carga de funciones de transacción." ) );
		this.loadTableTf();
		
		
		this.showAllComponents();
	}
	
	private void loadTableAlphabet() {
		
		DefaultTableModel dtm = this._frame.getDtmAlphabet();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( AutomatonNFView.ALPHABET_TITLE );
		
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
		dtm.setColumnIdentifiers( AutomatonNFView.STATE_TITLE );
		
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
		title[0] = AutomatonNFView.TF_TITLE[0];
		
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
	
	private void showAllComponents() {
		this._frame.getBtnConvertToDFA().setVisible( true );
		this._frame.getLblInput().setVisible( true );
		this._frame.getTxtInput().setVisible( true );
		this._frame.getBtnInput().setVisible( true );
		this._frame.getSpAlphabet().setVisible( true );
		this._frame.getSpStates().setVisible( true );
		this._frame.getSpTransactionFunction().setVisible( true );
		this._frame.getSpLog().setVisible( true );
	}
	
	private void convertToDFA() {
		AutomatonDFAController ctr = new AutomatonDFAController();
		ctr.setDFA( this._ac.DFAFromNFA( this._a ) );
		this.finish();
		ctr.start();
	}
	
	private void processInput() {
		
		if( this._validation.isInputCorrect() == false ) {
			this.printMessage( new Msg( Msg.ERROR, this, "El input no cumple con formato que se espera recibir." ) );
			return;
		}
		
		Input input = new Input( this._frame.getTxtInput().getText() );
		
		if( this._a.accept( input ) ) {
			this.printMessages( this._a.getMsgs() );
			this.printMessage( new Msg( Msg.INFO, this, "input: " + input.toString() + " aceptado [OK].") );
		} else {
			this.printMessages( this._a.getMsgs() );
			this.printMessage( new Msg( Msg.INFO, this, "input: " + input.toString() + " no aceptado [FAIL].") );
		}
	}
	
	private void printMessages( List< Msg > msgs ) {
		
		for( Msg m: msgs ) {
			this.printMessage( m );
		}
		
	}
	
	private void printMessage( Msg msg ) {
		this.printLog( msg );
		
		DefaultTableModel dtm = this._frame.getDtmLog();
		
		String[] row = new String[ 2 ];
		
		row[ 0 ] = msg.getType();
		row[ 1 ] = msg.getDate() + msg.getObject() + " - " + msg.getMsg();
		
		dtm.addRow( row );
		
		this._frame.getTblLog().setModel( dtm );

		this._frame.getTblLog().getColumnModel().getColumn( 0 ).setPreferredWidth( 70 );
		this._frame.getTblLog().getColumnModel().getColumn( 1 ).setPreferredWidth( this._frame.getWidth() - 130 );
		
		JScrollBar jsb = this._frame.getSpLog().getVerticalScrollBar();
		jsb.setValue( jsb.getMaximum() );
	}
}