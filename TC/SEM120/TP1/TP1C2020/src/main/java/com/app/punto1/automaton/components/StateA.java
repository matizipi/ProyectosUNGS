package com.app.punto1.automaton.components;

import java.util.ArrayList;
import java.util.List;

public class StateA {
	
	protected static String PREFIX = "Q_";
	
	protected String name;
	protected boolean startState = false;
	protected boolean finalState = false;
	
	/** State constructor from String. **/
	public StateA(String name) {
		this.name = PREFIX + name;
	}
	
	/** State construction from number. **/
	public StateA(int number) {
		this.name = PREFIX + Integer.toString( number );
	}
	
	/** Set if the initial state. **/
	public void startState(boolean b) {
		this.startState = b;
	}

	/** Set if a final state. **/	
	public void FinalState(boolean b) {
		this.finalState = b;
	}
	
	/** Get name of state. **/
	public String getName() {
		return this.name;
	}
	
	public boolean isStartState() {
		return this.startState;
	}
	
	public boolean isFinalState() {
		return this.finalState;
	}

	/** Auto-generate states from number. **/
	public static List< StateA > generateListStatesFromInt(int qtyOfStates) {
		
		List< StateA > list = new ArrayList< StateA >();

		StateA stateAux;
		
		for ( int i = 1; i <= qtyOfStates; i++ ) {
			
			/* Generate a new state from a number. */
			stateAux = new StateA( i );
			
			/* Set the first state as initial state. */
			if ( i == 1 ) {
				stateAux.startState(true);
			}
			
			/* Add the state to the list. */
			list.add( stateAux );
		}
		
		return list;
	}

	/** Generate a list of states from string line. **/
	public static List<StateA> generateListStatesFromLine(String line) {
		/* create a new list of state to return. */
		List<StateA> list = new ArrayList<StateA>();
		
		/* Get name of states. */
		String[] states = line.replaceAll(" ", "").split(",");
		
		/* Create state and add to list. */	
		for( int i = 0; i < states.length; i++ ) {
			StateA state = new StateFA( states[i] );
			/* If list contain state, not add this state. */
			if( list.contains( state ) == false ) {
				list.add( state );
			}
		}
		
		return list;
	}
	
	
//	public boolean equals(StateA obj) {
//		if ( obj.getName().equals( this.name ) ) {
//			return true;
//		}
//		
//		return false;
//	}

	@Override
	public boolean equals(Object obj) {
				
		if ( obj == null ) {
			System.out.println("2");
			return false;
		}
		
		if ( StateA.class.isAssignableFrom( obj.getClass() ) == false ) {
			System.out.println("3");
			return false;
		}
		
		StateA state = (StateA) obj;
		if( this.haveTheSameNameWhat( state ) == false ) {
			System.out.println("4");
			return false;
		}
		System.out.println("5");
		return true;
	}
	
	/** Verify is have the same name. **/
	private boolean haveTheSameNameWhat( StateA state ) {
		
		if( this.getName().equals( state.getName() ) ) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Object{ Name: " + this.name + ", StartState :" + this.startState + ", FinalState :" + this.finalState + " }";
	}
}