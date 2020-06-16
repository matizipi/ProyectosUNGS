package com.app.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.app.ui.dialog.TableValidationDialog;

public class parserTableValidationController extends Controller {

	private TableValidationDialog _dialog;
	private JFrame _frame;
	private String[][] _table;
	
	public parserTableValidationController( JFrame frame, String[][] table ) {
		this._frame = frame;
		this._table = table;
		this._dialog = new TableValidationDialog( this._frame );
		
		this.addActionListener();
	}
	
	private void addActionListener() {
		this._dialog.getBtnClose().addActionListener( this );
	}
	
	@Override
	public void start() {
		this.loadTable();
		this._dialog.setVisible( true );
	}

	@Override
	public void finish() {
		this._dialog.setVisible( false );
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == this._dialog.getBtnClose() ) {
			this.finish();
		}
	}
	
	private void loadTable() {
		
		DefaultTableModel dtm = this._dialog.getDtmValidation();
		
		dtm.setColumnCount( 0 );
		dtm.setRowCount( 0 );
		dtm.setColumnIdentifiers( TableValidationDialog.TABLE_TITLE );
		
		for( int i = 0; i < this._table.length; i++ ) {
			dtm.addRow( this._table[i] );
		}
		
		this._dialog.getTblValidation().setModel( dtm );
	}
}