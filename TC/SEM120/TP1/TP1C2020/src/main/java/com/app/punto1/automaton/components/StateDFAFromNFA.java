package com.app.punto1.automaton.components;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StateDFAFromNFA extends StateA {

	private List< StateA > _lstStates;

	/** Constructor of state. **/
	public StateDFAFromNFA(String name) {
		super(name);
		this._lstStates = new ArrayList<StateA>();
	}

	/** Add new states and set start or final state. **/
	public void addState( StateA state ) {
		this.name += state.getName();

		if( state.isStartState() ) {
			this.startState( true );
		}

		if( state.isFinalState() ) {
			this.finalState = true;
		}

		if( this._lstStates.contains( state ) == false ) {
			this._lstStates.add(state);
		}
	}

//	@Override
//	public boolean equals( StateA sa ) {
//
//		boolean exist = false;
//		String[] sIds = sa.getName().split( "Q_" );
//
//		if ( sIds.length > 0 ) {
//			for ( StateA _sa: this._lstStates ) {
//				exist = false;
//				for ( int i = 0; i < sIds.length; i++ ) {
//					if ( _sa.equals( sIds[i]) ) {
//						exist = true;
//						break;
//					}
//				}
//				if ( !exist )
//					return exist;
//			}
//		}
//
//		return exist;
//	}

	@Override
	public boolean equals( Object obj ) {

		if( obj == null ) {
			System.out.println("a");
			return false;
		}

		if( StateA.class.isAssignableFrom( obj.getClass() ) == false ) {
			System.out.println("aa");
			return false;
		}

		StateA state = ( StateA ) obj;
		if( this.contain( state ) == false ) {
			System.out.println("aaa");
			return false;
		}

		return true;
	}

	/** Verify if the state contain the state. **/
	private boolean contain( StateA state ) {

		for ( StateA _sa: this._lstStates ) {
			if ( _sa.equals( state ) ) {
				return true;
			}
		}

		return false;
	}

	/** Return the list of states that conform the automaton. **/
	public List<StateA> getStatesA() {
		return this._lstStates;
	}

	/** Return new stateA without list of internal states. **/
	@Deprecated
	public StateA getStateDFA() {

		for ( StateA sa: this._lstStates ) {
			this.name += sa.getName();
		}

		return null;
	}
}