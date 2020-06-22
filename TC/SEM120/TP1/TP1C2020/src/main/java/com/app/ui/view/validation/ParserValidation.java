package com.app.ui.view.validation;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.app.ui.view.ParserView;

public class ParserValidation extends Validation {

	private ParserView _frame;
	
	public ParserValidation( ParserView frame ) {
		this._frame = frame;
	}
	
	public boolean fileToImportOK() {
		
		JTextField filePath = this._frame.getTxtFile();
		JTextField emptySymbol = this._frame.getTxtEmptySymbol();
		
		if( filePath.getText().isEmpty() ) {
			filePath.setBackground( Color.RED );
			filePath.requestFocus();
			JOptionPane.showMessageDialog( this._frame, "No se ingresó una ruta de archivo.", "Falta ruta del archivo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if( emptySymbol.getText().isEmpty() ) {
			emptySymbol.setBackground( Color.YELLOW );
			int confirm = JOptionPane.showConfirmDialog( this._frame, "Si no ingresa un simbolo para identificar el vacio por defecto será \"@\".\n¿Desea continuar?", "Sin simbolo vacio", JOptionPane.INFORMATION_MESSAGE );
			if( ( confirm == JOptionPane.NO_OPTION ) == true || ( confirm == JOptionPane.CANCEL_OPTION ) == true) {
				return false;
			}
		}
		
		filePath.setBackground( new Color( 255, 255, 255 ) );
		emptySymbol.setBackground( new Color( 255, 255, 255 ) );
		emptySymbol.setText( "@" );
		
		return true;
	}
	
	
}