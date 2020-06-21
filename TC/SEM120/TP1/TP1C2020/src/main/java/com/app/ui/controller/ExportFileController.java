package com.app.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.app.helper.thread.ProgressBarThread;
import com.app.punto1.automaton.DFA;
import com.app.punto1.automaton.NFA;
import com.app.punto2.parser.Parser;
import com.app.ui.dialog.ExportFileDialog;

public class ExportFileController extends Controller {

	private ExportFileDialog _dialog;
	private String _filePath;
	private NFA _nfa;
	
	public ExportFileController( JFrame parent, Object object ) {
		this._dialog = new ExportFileDialog( parent );
		
		if( object.getClass().equals( NFA.class ) ) {
			System.out.println( "NFA" );
			this._nfa = ( NFA ) object;
		} else if( object.getClass().equals( DFA.class ) ) {
			System.out.println( "DFA" );
		} else if( object.getClass().equals( Parser.class ) ) {
			System.out.println( "Parser" );			
		}
		
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
		this._dialog.getBtnExport().setEnabled( false );
		this._dialog.getPrgb().setVisible( true );
		
		ProgressBarThread thr = new ProgressBarThread( this._filePath, this._nfa, this._dialog.getPrgb() );
		thr.start();
		
		this._dialog.getBtnExport().setEnabled( true );
	}
}