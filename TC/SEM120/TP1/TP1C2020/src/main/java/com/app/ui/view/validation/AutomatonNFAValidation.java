package com.app.ui.view.validation;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.app.ui.view.AutomatonNFView;

public class AutomatonNFAValidation {
	
	private AutomatonNFView _view;
	
	public AutomatonNFAValidation( AutomatonNFView view ) {
		this._view = view;
	}
	
	public boolean isInputCorrect() {
		
		JTextField input = this._view.getTxtInput();
		
		if ( input.getText().isEmpty() ) {
			input.setBackground( Color.RED );
			input.requestFocus();
			JOptionPane.showMessageDialog(_view, "Debe completar el campo input." );
			return false;
		}
		
		if ( input.getBackground().equals( Color.RED ) ) {
			input.setBackground( new JButton().getBackground() );
		}
		
		return true;
		
	}
}