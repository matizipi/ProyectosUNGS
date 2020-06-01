package automaton.components.alphabet;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

	private List<Simbol> _sSimbols;
	
	public Alphabet(Simbol... simbols) {
		
		this._sSimbols = new ArrayList<Simbol>();
		
		for ( int i = 0; i < simbols.length; i++) {
			this._sSimbols.add(simbols[i]);
		}
	}
	
	public void addSimbols(Simbol... simbols) {
		for( int i = 0; i < simbols.length; i++ ) {
			this._sSimbols.add( simbols[i] );
		}
	}

	public static Alphabet generateFrom(String line) {
		
		String[] stringSimbols = line.replaceAll(" ", "").split(",");
		Simbol[] smb = new Simbol[stringSimbols.length];
		
		for( int i = 0; i < smb.length; i++ ) {
			smb[i] = new Simbol(stringSimbols[i]);
		}
		
		return new Alphabet(smb);
	}

	public List<Simbol> getSimbols() {
		return this._sSimbols;
	}
	
	public String[] getArrayStringOfSimbols() {
		
		String[] array = new String[ this._sSimbols.size() ];
		
		int i = 0;
		for ( Simbol smb: this._sSimbols ) {
			array[i] = smb.getSimbol();
			i++;
		}
		
		return array;
	}
}