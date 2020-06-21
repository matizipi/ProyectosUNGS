package com.app.ui.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.app.helper.thread.ProgressBarThread;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto2.parser.Parser;
import com.app.ui.dialog.ExportFileDialog;

public class ExportFileController extends Controller {

//	private static String NFA_STRING = "NFA";
//	private static String DFA_STRING = "DFA";
//	private static String PARSER_STRING = "PARSER";
	
	private ExportFileDialog _dialog;
//	private String _class;
//	private NFA _nfa;
//	private DFA _dfa;
//	private Parser _parser;
	private Object _object;
	
	public ExportFileController( JFrame parent, Object object ) {
		this._dialog = new ExportFileDialog( parent );
		
//		if( object.getClass().equals( NFA.class ) ) {
//			System.out.println( "NFA" );
//			this._class = NFA_STRING;
//			this._nfa = ( NFA ) object;
//		} else if( object.getClass().equals( DFA.class ) ) {
//			System.out.println( "DFA" );
//			this._class = DFA_STRING;
//			this._dfa = ( DFA ) object;
//		} else if( object.getClass().equals( Parser.class ) ) {
//			System.out.println( "Parser" );
//			this._class = PARSER_STRING;
//			this._parser = ( Parser ) object;
//		}
		
		this._object = object;
		this.addActionListener();
	}
	
	private void addActionListener() {
		this._dialog.getBtnExport().addActionListener( this );
	}
		
	@Override
	public void start() {
		this._dialog.getPrgb().setVisible( false );
		this._dialog.setVisible( true );
	}

	@Override
	public void finish() {
		this._dialog.setVisible( false );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == this._dialog.getBtnExport() ) {
			this.exportFile();
		}
	}
	
	private void exportFile() {
		
		String filepath = this._dialog.getTxtFile().getText();
		File file = new File( filepath );
		
		if( file.exists() == false ){
//			return;
		}
		
		this._dialog.getBtnExport().setEnabled( false );
		this._dialog.getPrgb().setVisible( true );
		
		Component[] buttons = new Component[] {this._dialog.getBtnExport()};
		
		ProgressBarThread thr = new ProgressBarThread( file, this._object, this._dialog.getPrgb(), buttons );
		thr.start();
	}
}