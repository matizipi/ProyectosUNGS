package com.app.ui.controller;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.io.File;

import com.app.common.Msg;
import com.app.punto1.automaton.ObjectConverter;
import com.app.punto2.parser.ParserTopDownNoRecursive;
import com.app.punto2.parser.components.First;
import com.app.punto2.parser.components.Follow;
import com.app.punto2.parser.components.Production;
import com.app.ui.view.AutomatonNFView;
import com.app.ui.view.ParserView;

public class ParserController extends Controller {
	
	private ParserView _frame;
	private ObjectConverter _oc = new ObjectConverter();
	private ParserTopDownNoRecursive _parser;
	
	public ParserController() {
		this._frame = new ParserView();
		
		this.addListeners();
	}
	
	private void addListeners() {
		this._frame.getBtnFile().addActionListener( this );
		this._frame.getBtnReturn().addActionListener( this );
		this._frame.getBtnInput().addActionListener( this );
		this._frame.getBtnParse().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == this._frame.getBtnFile() ) {
			this.importFile();
		} else if( arg0.getSource() == this._frame.getBtnReturn() ) {
			this.returnMainMenu();
		} else if( arg0.getSource() == this._frame.getBtnInput() ) {
			this.testInput();
		} else if( arg0.getSource() == this._frame.getBtnParse() ) {
			this.showValidateTable();
		}
	}

	@Override
	public void start() {
		this._frame.setVisible( true );
		
		if( this._parser == null ) {
			this.showParserProperties( false );
		} else {
			this.showParserProperties( true );
		}
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}
	
	private void showParserProperties( boolean b ) {
		
		this._frame.getLblInput().setVisible( b );
		this._frame.getTxtInput().setVisible( b );
		this._frame.getBtnInput().setVisible( b );
		this._frame.getBtnParse().setVisible( false );
		this._frame.getSpFirst().setVisible( b );
		this._frame.getSpFollow().setVisible( b );
		this._frame.getSpParsingTable().setVisible( b );
		this._frame.getSpLog().setVisible( b );
		
		this._frame.getTblLog().getColumnModel().getColumn( 0 ).setPreferredWidth( 70 );
		this._frame.getTblLog().getColumnModel().getColumn( 1 ).setPreferredWidth( this._frame.getSpLog().getWidth() - 70 );
		
		if( b == true) {
			this.loadFirstTable();
			
			this.loadFollowTable();
			
			this.loadParserTable();	
		} 
	}
	
	public void importFile() {
		/* Get string from field. */
		String filePath = this._frame.getTxtFile().getText();
		String charEmpty = "@";
		
		/* Print file path in log. */
		this.printLog( new Msg( Msg.INFO, this, "Cargarndo archivo: " + filePath ) );
		
		/* Create object file. */
		File file = new File( filePath );
		
		/* Ask if this file exists. */
		if( file.exists() ) {
			/* If exists create a new parser. */
			this._parser = this._oc.parserTopDownNoRecursiveFromFile( file, charEmpty );
			/* Print in log parser message. */
			this.printMessages( this._oc.getMessages() );
		} else {
			/* If file don´t exists write a message in log. */
			this.printLog( new Msg( Msg.ERROR, this, "Archivo no existente." ) );
		}
		
		this.showParserProperties( true );
	}

	/* Load data in table of first. */
	private void loadFirstTable() {
		First[] firsts = this._parser.getArrayFirsts();
		
		DefaultTableModel dtm = this._frame.getDtmFirst();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( ParserView.FIRST_TITLE );
		
		String[] line;
		
		for( int i = 0; i < firsts.length; i++ ) {
			line = new String[]{ firsts[i].toString() };
			dtm.addRow( line );
		}
		
		this._frame.getTblFirst().setModel( dtm );
	}
	
	private void loadFollowTable() {
		Follow[] follows = this._parser.getArrayFollows();
		
		DefaultTableModel dtm = this._frame.getDtmFollow();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( ParserView.FOLLOW_TITLE );
		
		String[] line;
		
		for( int i = 0; i < follows.length; i++ ) {
			line = new String[] { follows[i].toString() };
			dtm.addRow( line );
		}
		
		this._frame.getTblFollow().setModel( dtm );
	}
	
	private void loadParserTable() {
		String[][] parsingTable = this._parser.getParsingTable();
		
		DefaultTableModel dtm = this._frame.getDtmParsingTable();
		
		dtm.setRowCount( 0 );
		dtm.setColumnCount( 0 );
		dtm.setColumnIdentifiers( parsingTable[0] );
		
		for( int i = 1; i < parsingTable.length; i++ ) {
			dtm.addRow( parsingTable[i] );
		}
		
		this._frame.getTblParsingTable().setModel( dtm );
	}
	
	private void returnMainMenu() {
		MainController ctr = new MainController();
		this._parser = null;
		this.finish();
		ctr.start();
	}
	
	private void testInput() {
		
		String input = this._frame.getTxtInput().getText();
		
		if( this._parser.AcceptString( input ) ) {
			this.printMessages( this._parser.getMsgs() );
			this._frame.getBtnParse().setVisible( true );
			this.printMessage( new Msg( Msg.INFO, this, "input aceptado." ) );
		} else {
			this.printMessages( this._parser.getMsgs() );
			this._frame.getBtnParse().setVisible( false );
			this.printMessage( new Msg( Msg.INFO, this, "input no aceptado." ) );
		}
	}
	
	private void showValidateTable() {
		String[][] table = this._parser.getValidationProcessTable();
		parserTableValidationController ctr = new parserTableValidationController( this._frame, table );
		ctr.start();
	}
	
	private void printMessages( List< Msg > msgs ) {
		
		for( Msg msg: msgs ) {
			this.printMessage( msg );
		}
		
	}
	
	private void printMessage( Msg msg ) {
		
		super.printLog( msg );
		
	}
}