package com.ui.controller;

import java.awt.event.ActionEvent;

import com.common.log.Log;
import com.ui.view.MainFrame;

import helper.Msg;

public class MainController implements ControllerImpl {

	private MainFrame _frame;
	
	public MainController() {
		this._frame = new MainFrame();
		this.addListeners();
	}
	
	private void addListeners() {
		this._frame.getBtnAutomatonSolution().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( arg0.getSource() == this._frame.getBtnAutomatonSolution() ) {
			this.transferAutomatonAccions();
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
	
	private void transferAutomatonAccions() {
		this.finish();
		AutomatonNFAController aNfa = new AutomatonNFAController();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingreso a menú de manipulación de automatas." ) );
		aNfa.start();
	}
}