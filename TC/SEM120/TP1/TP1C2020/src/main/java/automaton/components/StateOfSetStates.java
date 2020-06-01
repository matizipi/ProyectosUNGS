package automaton.components;

import java.util.ArrayList;
import java.util.List;

public class StateOfSetStates extends StateA {

	private List< StateA > _lstState;
	
	public StateOfSetStates( String name ) {
		super(name);
		this._lstState = new ArrayList<StateA>();
	}
	
	public StateOfSetStates( int number ) {
		super( number );
		this._lstState = new ArrayList<StateA>();
	}

	public void addState( StateA state ) {
		this._lstState.add( state );
	}
	
	public void addStates(List<StateA> states) {
		
		boolean exists = false;
		
		for ( StateA s01: states ) {
			exists = false;
			for ( StateA s02: this._lstState ) {
				if ( s01.equals( s02 ) )
					exists = true;
			}
			if ( exists == false )
				this._lstState.add( s01 );
		}		
	}
	
	public boolean equals( StateOfSetStates state ) {
		
		/* Compare list size. */
		if ( this._lstState.size() == state.getStates().size() ) {
			boolean haveState = false;
			for ( StateA st01 : this._lstState ) {
				haveState = false;
				for ( StateA st02 : state.getStates() ) {
					if ( st01.equals( st02 ) ) {
						haveState = true;
					}
				}
				
				if ( haveState == false ) {
					return haveState;
				}
			}
			
			return true;
			
		}
		
		return false;
	}
	
	public StateA toStateA() {
		StateA q = new StateA( this.name.replaceAll(  PREFIX, "" ) );
		
		for (StateA st: this._lstState) {
			if ( st.isFinalState() == true ) {
				q.FinalState( true ); 
			}
		}
		
		return q;
	}
	
	public List< StateA > getStates() {
		return this._lstState;
	}
}