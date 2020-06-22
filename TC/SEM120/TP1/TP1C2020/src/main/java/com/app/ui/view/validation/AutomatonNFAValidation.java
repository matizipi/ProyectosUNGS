package com.app.ui.view.validation;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.app.ui.view.AutomatonNFView;

public class AutomatonNFAValidation extends Validation {
	
	private AutomatonNFView _frame;
	
	public AutomatonNFAValidation( AutomatonNFView view ) {
		this._frame = view;
	}
	
	public boolean isFilePathCorrect() {
		
		JTextField filePath = this._frame.getTxtLoadFile();
		
		if ( filePath.getText().isEmpty() ) {
			filePath.setBackground( Color.RED );
			filePath.requestFocus();
			JOptionPane.showMessageDialog(_frame, "Debe completar la ruta del archivo que desea cargar.", "No hay ruta de archivo", JOptionPane.ERROR_MESSAGE );
			return false;
		}
		
		if ( filePath.getBackground().equals( Color.RED ) ) {
			filePath.setBackground( new Color(255,255,255) );
		}
		
		return true;
		
	}

	public boolean isInputCorrect() {
		
		JTextField inputText = this._frame.getTxtInput();
		
		if ( inputText.getText().contains( " " ) ) {
			inputText.setBackground( Color.YELLOW );
			inputText.requestFocus();
			int option = JOptionPane.showConfirmDialog( _frame , "Debe completar los espacios vacios del campo input.", "Input con espacio vacios.", JOptionPane.WARNING_MESSAGE );
			
			System.out.println( JOptionPane.OK_OPTION );
			
			return option==JOptionPane.OK_OPTION?true:false;
		}
		
		if ( inputText.getBackground().equals( Color.YELLOW ) ) {
			inputText.setBackground( new Color(255,255,255) );
		}
		
		return true;
		
	}
}