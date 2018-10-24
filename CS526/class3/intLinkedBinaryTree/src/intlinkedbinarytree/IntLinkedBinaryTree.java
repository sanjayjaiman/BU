package intlinkedbinarytree;

import java.util.*;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer>{

	       
        public IntLinkedBinaryTree () {
            root = null;
        }
        
	/**
	 * Add a new node with e to the tree rooted at position p
	 * @param p The root of the tree to which new node is added
	 * @param e The element of the new node
	 * @return If a node with e does not exist, a new node with e is added and 
	 *   reference to the node is returned. If a node with e exists, null is returned.
	 */
	public Position<Integer> add(Position<Integer> p, Integer e){

            if (root == null) {    
                System.out.println("Add root Value = " + e);
                return addRoot(e);
            }
            else {
                Position<Integer> x = p;
                Position<Integer> y = x;
                
                while (x != null) {
                    Position<Integer> left = left(x);
                    Position<Integer> right = right(x);
                    Integer xEle = x.getElement();
                    if (xEle == e )
                        return null;
                    else if (xEle > e) {
                        y = x;
                        x = left;
                    }
                    else {
                        y = x;
                        x = right;
                    }
                }
                Integer yEle = y.getElement();
                if (yEle > e) {
                    addLeft(y,e);
                }
                else {
                    addRight(y,e);
                }
            }
            return null;		
	}

        // Created a wrapper class so a boolean could be passed by reference
        private class addLeftBool {
             protected Boolean bool;
             protected addLeftBool(boolean b) {
                 bool = b;
             }
        }
        
	private Position<Integer> advance(Position<Integer> x, Integer e, addLeftBool addleft)
        {
            Position<Integer> left = left(x);
            Position<Integer> right = right(x);
            Integer xEle = x.getElement();
            if (xEle > e) {
                return left;
            }
            addleft.bool = false;
            return right;
        }
        
        // Does not work as addleft is passed by value and switches back
	private Position<Integer> advance(Position<Integer> x, Integer e, Boolean addleft)
        {
            Position<Integer> left = left(x);
            Position<Integer> right = right(x);
            Integer xEle = x.getElement();
            if (xEle > e) {
                return left;
            }
            addleft = false;
            return right;
        }

        public Position<Integer> addSanjay(Position<Integer> p, Integer e){

            if (p == null) {    
                System.out.println("Add root Value = " + e);
                return addRoot(e);
            }
            else {
                Position<Integer> x = p;
                
                addLeftBool addleft = new addLeftBool(true);
//                Boolean addleft = new Boolean(true);
                while (true) {
                    Integer xEle = x.getElement();
                    
                    if (Objects.equals(xEle, e) )
                        return null;
                    
                    addleft.bool = true;
//                    addleft = true;
                    Position<Integer> y = x;
                    x = advance(x, e, addleft);
                    
                    if (x == null) {
                        if (addleft.bool) {
//                        if (addleft) {
                            return addLeft(y,e);
                        }
                        return addRight(y,e);
                    }
                }
            }
	}

        public static void main(String[] args) {
		
		// create a new binary tree instance
		IntLinkedBinaryTree t =   new IntLinkedBinaryTree();
		
		// add some integers
		t.addSanjay(t.root, 100);
		t.addSanjay(t.root, 50);
		t.addSanjay(t.root, 150);
		t.addSanjay(t.root, 70);
                t.addSanjay(t.root, 75);
                t.addSanjay(t.root, 200);
		// test with more integers 
		
		// print all integers in the tree in increasing order
		// after adding above four integers, the following should be printed
		// 50 70 100 150
		
		Iterator<Position<Integer>> it = t.inorder().iterator();	
		System.out.println();
		while (it.hasNext()){
                    System.out.print(it.next().getElement() + " ");
		}
		System.out.println();
	}
}
