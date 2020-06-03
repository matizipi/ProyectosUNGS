package automaton.components.alphabet;

public class Input {
	
	Simbol[] _coInput;
	
	public Input( String input ) {
		
		this._coInput = new Simbol[ input.length() ];
		
		for( int i = 0; i < input.length(); i++ ) {
			this._coInput[ i ] = new Simbol( input.charAt( i ) );
		}
	}
	
	public Input(Simbol[] in) {
		this._coInput = in;
	}

	public Simbol[] getSimbolsOfInput() {
		return this._coInput;
	}
}