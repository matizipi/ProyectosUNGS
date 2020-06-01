package com.ui.controller;

import java.awt.event.ActionEvent;

import com.ui.view.MainFrame;

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
			this.location();
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
	
	private void location() {
		this.finish();
		AutomatonNFAController aNfa = new AutomatonNFAController();
		aNfa.start();
	}
}