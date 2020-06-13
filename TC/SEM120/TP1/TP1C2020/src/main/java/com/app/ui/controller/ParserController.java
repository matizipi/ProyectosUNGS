package com.app.ui.controller;

import java.util.List;
import java.awt.event.ActionEvent;
import java.io.File;

import com.app.common.Msg;
import com.app.punto1.automaton.ObjectConverter;
import com.app.punto2.parser.ParserTopDownNoRecursive;
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
		this._frame.getBtnInput().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == this._frame.getBtnFile() ) {
			this.importFile();
		}
	}

	@Override
	public void start() {
		this._frame.setVisible( true );
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
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
		
		this.loadFirstTable();
		
		this.loadFollowTable();
		
		this.loadParserTable();
	}

	private void loadFirstTable() {
		
	}
	
	private void loadFollowTable() {
		
	}
	
	private void loadParserTable() {
		
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