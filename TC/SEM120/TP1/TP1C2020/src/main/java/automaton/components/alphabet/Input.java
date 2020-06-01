package automaton.components.alphabet;

public class Input {
	
	Simbol[] _coInput;
	
	public Input(Simbol[] in) {
		this._coInput = in;
	}

	public Simbol[] getSimbolsOfInput() {
		return this._coInput;
	}
}