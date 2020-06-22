package com.app.ui.controller;

import java.awt.event.ActionEvent;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.ui.view.MainFrame;

public class MainController implements ControllerImpl {

	private MainFrame _frame;
	
	public MainController() {
		this._frame = new MainFrame();
		this.addListeners();
	}
	
	private void addListeners() {
		this._frame.getBtnAutomatonSolution().addActionListener( this );
		this._frame.getBtnParser().addActionListener( this );
		this._frame.getBtnConfiguration().addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( arg0.getSource() == this._frame.getBtnAutomatonSolution() ) {
			this.transferAutomatonActions();
		} else if( arg0.getSource() == this._frame.getBtnParser() ) {
			this.transferParsersActions();
		} else if( arg0.getSource() == this._frame.getBtnConfiguration() ) {
			this.transferConfiguration();
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
		AutomatonNFAController aNfa = new AutomatonNFAController();
		this.finish();
		aNfa.start();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingresó a menú de automatas." ) );
	}
	
	private void transferParsersActions() {
		ParserController pCtr = new ParserController();
		this.finish();
		pCtr.start();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingresó a menú de parsers." ) );
	}
	
	private void transferConfiguration() {
		ConfigurationController ctr = new ConfigurationController();
		this.finish();
		ctr.start();
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Ingresó a configuraciones." ) );
	}
}