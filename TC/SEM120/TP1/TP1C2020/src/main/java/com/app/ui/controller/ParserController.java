package com.app.ui.controller;

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
		String filePath = this._frame.getTxtFile().getText();
		
		this.printLog( new Msg( Msg.INFO, this, "Cargarndo archivo: " + filePath ) );
		
		File file = new File( filePath );
		if( file.exists() ) {
			this._parser = this._oc.parserTopDownNoRecursiveFromFile( file );
		} else {
			
		}
	}
}