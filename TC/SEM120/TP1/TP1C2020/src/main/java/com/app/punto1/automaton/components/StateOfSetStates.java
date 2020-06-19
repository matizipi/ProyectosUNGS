package com.app.punto1.automaton.components;

import java.util.ArrayList;
import java.util.List;

public class StateOfSetStates extends StateA {

	private List< StateA > _lstState;
	
	/** Create state from string. **/
	public StateOfSetStates( String name ) {
		super(name);
		this._lstState = new ArrayList<StateA>();
	}
	
	/** Create state from number **/
	public StateOfSetStates( int number ) {
		super( number );
		this._lstState = new ArrayList< StateA >();
	}

	/** Add new internal states. **/
	public void addState( StateA state ) {
		
		if( state.isStartState() == true ) {
			this.startState( true );
		}
		
		if ( state.isFinalState() == true ) {
			this.FinalState( true );
		}
		
		if( this._lstState.contains( state ) == false ) {
			this._lstState.add( state );
		}
	}
	
	public void addStates( List< StateA > states) {
		
//		boolean exists = false;
//		
//		for ( StateA s01: states ) {
//			exists = false;
//			
//			if ( s01.isFinalState() ) {
//				this.finalState = true;
//			}
//			
//			for ( StateA s02: this._lstState ) {
//				if ( s01.equals( s02 ) )
//					exists = true;
//			}
//			if ( exists == false )
//				this._lstState.add( s01 );
//		}		
		
		for( StateA state: states ) {
			this.addState( state );
		};
	}
	
//	public boolean equals( StateOfSetStates state ) {
//		
//		/* Compare list size. */
//		if ( this._lstState.size() == state.getStates().size() ) {
//			boolean haveState = false;
//			for ( StateA st01 : this._lstState ) {
//				haveState = false;
//				for ( StateA st02 : state.getStates() ) {
//					if ( st01.equals( st02 ) ) {
//						haveState = true;
//					}
//				}
//				
//				if ( haveState == false ) {
//					return haveState;
//				}
//			}
//			
//			return true;
//			
//		}
//		
//		return false;
//	}
	
	@Override
	public boolean equals( Object obj ) {
		
		if( obj == null ) {
			return false;
		}
		
		if( StateA.class.isAssignableFrom( obj.getClass() ) == false ) {
			return false;
		}
		
		StateOfSetStates state = ( StateOfSetStates ) obj;
		if( this.isSameState( state ) == false ) {
			return false;
		}
		
		return true;
	}
	
	/* Verify if the states have the same states. */
	private boolean isSameState( StateOfSetStates state ) {
		
		if( (this.getStates().size()==state.getStates().size() ) == false ) {
			return false;
		}
		
		boolean existState;
		
		for( StateA stt01: this.getStates() ) {
			existState = false;
			for( StateA stt02: state.getStates() ) {
				if( stt01.equals( stt02 ) ) {
					existState = true;
				}
			}
			
			if( existState==false ) {
				return false;
			}
			
		}
		
		return true;
	}
	
	/** Simplify the state to state without internal states. **/
	public StateA toStateA() {
		StateA q = new StateA( this.name.replaceAll(  PREFIX, "" ) );
		
//		for (StateA st: this._lstState) {
//			if ( st.isFinalState() == true ) {
//				q.FinalState( true ); 
//			}
//		}
		
		q.startState( this.isStartState() );
		q.FinalState( this.isFinalState() );
		
		return q;
	}
	
	/* Get a list of internal states. */
	public List< StateA > getStates() {
		return this._lstState;
	}
}