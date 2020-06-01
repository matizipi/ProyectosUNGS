package automaton.components.alphabet;

public class Simbol {

	private String _sSimbol;
	
	public Simbol(String simbol) {
		this._sSimbol = simbol;
	}
	
	public String getSimbol() {
		return this._sSimbol;
	}
	
	public boolean equals(Simbol s) {
		if ( this._sSimbol.equals( s.getSimbol() ) ) {
			return true;
		}
		
		return false;
	}
}