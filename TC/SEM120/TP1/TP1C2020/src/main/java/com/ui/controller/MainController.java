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
		this._frame.getBtnParser().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( arg0.getSource() == this._frame.getBtnAutomatonSolution() ) {
			this.transferAutomatonActions();
		} else if( arg0.getSource() == this._frame.getBtnParser() ) {
			this.transferParsersActions();
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
	
	private void transferAutomatonActions() {
		this.finish();
		AutomatonNFAController aNfa = new AutomatonNFAController();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingresó a menú de manipulación de automatas." ) );
		aNfa.start();
	}
	
	private void transferParsersActions() {
		this.finish();
		ParserController pCtr = new ParserController();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingresó a menú de parsers." ) );
		pCtr.start();
	}
}