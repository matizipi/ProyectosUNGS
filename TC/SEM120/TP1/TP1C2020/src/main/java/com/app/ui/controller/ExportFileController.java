package com.app.ui.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;

import com.app.helper.thread.ProgressBarThread;
import com.app.ui.dialog.ExportFileDialog;

public class ExportFileController extends Controller {
	
	private ExportFileDialog _dialog;
	private Object _object;
	
	public ExportFileController( JFrame parent, Object object ) {
		this._dialog = new ExportFileDialog( parent );
		
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
			return;
		}
		
		this._dialog.getBtnExport().setEnabled( false );
		this._dialog.getPrgb().setVisible( true );
		
		boolean[] options = new boolean[] { this._dialog.getChbFromFile().isSelected()
				, this._dialog.getChbTable().isSelected()
				, this._dialog.getChbSteps().isSelected() };
		
		Component[] buttons = new Component[] {this._dialog.getBtnExport()};
		
		ProgressBarThread thr = new ProgressBarThread( file, this._object, this._dialog.getPrgb(), options, buttons );
		thr.start();
	}
}