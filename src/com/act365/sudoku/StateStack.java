/*
 * Su Doku Solver
 * 
 * Copyright (C) act365.com August 2005
 * 
 * Web site: http://act365.com/sudoku
 * E-mail: developers@act365.com
 * 
 * The Su Doku Solver solves Su Doku problems - see http://www.sudoku.com.
 * 
 * This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package com.act365.sudoku ;
/**
 * &MF Application Modification
 * Replaced the Vector class with the Java
 * Collections Framework Arraylist class
 */
import java.util.ArrayList;
//import java.util.Vector ;
/*
 * Robert: how do we know this is an integer and how do we ensure it is an integer, use call heirarchy
 *  
 */


/**
 * StateStack stores state grids in a dynamically-expanding vector.
 * The class should be used for memory-intensive state grids that
 * are rarely references (so that performance isn't critical), such
 * as LinearSystemState.
 */

public class StateStack extends ArrayList<Integer> {

    int nMovesStored ;
    
    int[] moves ;
    
    /**
     * Creates a StateStack to store at most maxMoves moves.
     * 
     * &MF modification of constructor to replace the Vector class setSize() 
     * with the ArrayList equivalent ensureCapacity(). Increases
     * the capacity of moves integer array to have minimum size of
     * the maxMoves value.
     *  @param maxMoves maximum number of moved depending on the gameplay layout
     */

    public StateStack( int maxMoves ){
        nMovesStored = 0 ;
        moves = new int[maxMoves];
//        setSize( maxMoves );				// edited by &MF
        ensureCapacity(maxMoves);			// edited by &MF
        
    }
    
    /**
     * @see com.act365.sudoku.IState#pushState(int)
     * 
     * &MF modification of pushState(), while the available moves
     * value stored in the nMovesStored and moves array is not 
     * equal to the total number of moves "nMoves", either:
     * -if- current move number is less that the moves stored, add
     * this move to the array at that move number location
     * -else- add that move to the end of the ArrayList
     * @param nMoves number of possible moves
     * @param obj Grid position object
     */
     
    public void pushState( Object obj , int nMoves ) {
        int i = 0 ;
        while( i < nMovesStored && moves[i] != nMoves ){
            ++ i ;   
        }
        if( i < nMovesStored ){
            // setElementAt( obj , i );   	// edited by &MF
        	set(i, (Integer) obj);
        } else {
            // addElement( obj );			// edited by &MF
        	add( (Integer) obj );
            moves[nMovesStored++] = nMoves ;
        }
    }    

    /**
     * @see com.act365.sudoku.IState#popState(int)
     * 
     * &MF modification of popState() from the Vector class to the 
     * Java Collections Framework ArrayList. While the available moves
     * value stored in the nMovesStored and moves array is not 
     * equal to the total number of moves "nMoves", increase
     * index number count by one. Then,
     * -if- move index is equal to the number of moves stored
     * in nMoveStored, return the element at the specified location 
     * in the array list, or -else- if the move index is not less
     * than the maximum number of moves, return null.
     * @param nMoves total number of moves to be played depending on game grid size
     * @return element at the grid object location.
     */
        
    public Object popState( int nMoves ) {
        int i = 0 ;
        while( i < nMovesStored && moves[i] != nMoves ){
            ++ i ;   
        }
        if( i < nMovesStored ){
            //  return elementAt( i );   // edited by &MF
        	return get(i);
        } else {
            return null ;   
        }
    }
}
