package com.app.punto1.automaton.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateA {
	
	protected String name;
	protected boolean startState = false;
	protected boolean finalState = false;
	
	protected static String PREFIX = "Q_";
	
	public StateA(String name) {
		this.name = PREFIX + name;
	}
	
	public StateA(int number) {
		this.name = PREFIX + Integer.toString( number );
	}
	
	public void startState(boolean b) {
		this.startState = b;
	}

	public void FinalState(boolean b) {
		this.finalState = b;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isStartState() {
		return this.startState;
	}
	
	public boolean isFinalState() {
		return this.finalState;
	}

	public static List<StateA> generateListStatesFromInt(int qtyOfStates) {
		
		List<StateA> list = new ArrayList<StateA>();

		StateA stateAux;
		
		for ( int i = 1; i <= qtyOfStates; i++ ) {
			
			stateAux = new StateFA( Integer.toString(i) );
			
			if ( i == 1 ) {
				stateAux.startState(true);
			}
			
			list.add( stateAux );
		}
		
		return list;
	}

	public static List<StateA> generateListStatesFromLine(String line) {
		/* create a new list of state to return. */
		List<StateA> list = new ArrayList<StateA>();
		
		/* Get name of states. */
		String[] states = line.replaceAll(" ", "").split(",");
		
		/* Create state and add to list. */	
		for( int i = 0; i < states.length; i++ ) {
			list.add( new StateFA( states[i] ) );
		}
		
		return list;
	}
	
	public boolean equals(StateA obj) {
		if ( obj.getName().equals( this.name ) ) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		
		if ( this == obj ) {
			System.out.println("1");
			return true;
		}
		
		if ( obj == null ) {
			System.out.println("2");
			return false;
		}
		
		if ( getClass() != obj.getClass() ) {
			System.out.println("3");
			return false;
		}
		
		StateA stateObj = (StateA) obj;
		System.out.println("4");
		return Objects.equals( this.name, stateObj.getName() );
	}
}