package intlinkedbinarytree;

import java.util.*;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer>{

	       
        public IntLinkedBinaryTree () {
            root = null;
        }
 
        public Position<Integer> add(Position<Integer> p, Integer e){

            if (p == null) {    
                System.out.println("Add root Value = " + e);
                return addRoot(e);
            }
            else {
                Position<Integer> x = p;
                
                privBool addleft = new privBool(true);
                while (true) {
                    Integer xEle = x.getElement();
                    
                    if (Objects.equals(xEle, e) )
                        return null;
                    
                    addleft.bool = true;
                    Position<Integer> y = x;
                    x = advance(x, e, addleft);
                    
                    if (x == null) {
                        if (addleft.bool) {
                            return addLeft(y,e);
                        }
                        return addRight(y,e);
                    }
                }
            }
	}

        public static void print_recurse(int level, Node<Integer> p) {
            if (p == null) {
                return;
            }
            Node<Integer> l = p.getLeft();
            Node<Integer> r = p.getRight();            
            print_recurse(level+1, l);
            Integer pnt = null;
            if (p.getParent() != null) {
                pnt = p.getParent().getElement();
            }
            System.out.print(p.getElement() + "(" + level + "/" + (pnt == null ? "ROOT" : pnt) + ") ");
            print_recurse(level+1, r);
        }        
        public static void print_priv(IntLinkedBinaryTree t) {
            if (t.root == null) {
                System.out.println("\tTree empty");
                return;
            }
            print_recurse(1, t.root);
            System.out.println();
            System.out.println();
        }        
        public static void print(IntLinkedBinaryTree t) {
            if (t.root == null) {
                System.out.println("\tTree empty");
                return;
            }
            System.out.println();
            System.out.print("\tTree (root = " + t.root.getElement().toString() + ") = ");
            Iterator<Position<Integer>> it = t.inorder().iterator();	
            
            while (it.hasNext()){
                System.out.print(it.next().getElement() + " ");
            }
            System.out.println();
            System.out.println();
        }
    
        public void add(Position<Integer> p, Position<Integer> a) {
            Integer e = a.getElement();
            
            if (p == null) {    
                System.out.println("Add root Value = " + e);
                addRoot(e);
            }
            else {
                Position<Integer> x = p;
                
                privBool addleft = new privBool(true);
                while (true) {
                    Integer xEle = x.getElement();
                    
                    if (Objects.equals(xEle, e) )
                        return;
                    
                    addleft.bool = true;
                    Position<Integer> y = x;
                    x = advance(x, e, addleft);
                    
                    if (x == null) {
                        Node<Integer> n = (Node<Integer>)y;
                        Node<Integer> b = (Node<Integer>)a;
                        if (addleft.bool) {
                            n.setLeft(b);
                        }
                        else {
                            n.setRight(b);
                        }
                        b.setParent(n);
                        return;
                    }
                }
            }
	}

        // Created a wrapper class so a boolean could be passed by reference
        private static class privBool {
             protected Boolean bool;
             protected privBool(boolean b) {
                 bool = b;
             }
        }
        
	private Position<Integer> advance(Position<Integer> x, Integer e, privBool addleft)
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

        private Position<Integer> find(Position<Integer> p, Integer e, privBool left) {

            if (root == null) {    
                return null;
            }
            else {
                Position<Integer> x = p;
                
                Integer xEle = x.getElement();

                if (xEle.equals(e) )
                    return x;

                left.bool = true;
                Position<Integer> y = x;
                x = advance(x, e, left);

                if (x != null) {
                    return find(x,e, left);
                }
                return null;
            }        
        }


        private Node<Integer> findRightmost(Node<Integer> p){
            if (p == null) {    
                return null;
            }
            else {
                Node<Integer> x = p;
                Node<Integer> rt = (Node<Integer>)right(x);
                if (rt == null) {
                    return x;
                }
                return findRightmost(rt);
            }        
        }

        
        private void makeroot(Node<Integer> n) {
            if (n != null) {
                n.setParent(null);
            }
            root = n;
//            System.out.println("New root = " + (root == null ? "NULL" : root.getElement().toString()));
        }
        
        private Node<Integer> moveUpOne(Node<Integer> n, Node<Integer> with) {
            if (n == null) {
                System.out.println("Something wrong");
                return null;
            }
            Node<Integer> parent = n.getParent();
            Node<Integer> right = n.getRight();
            Node<Integer> left = n.getLeft();
            
            if (parent == null)  {  //root
                makeroot(with);
            }
            else {
                boolean wasLeftChild = Objects.equals(parent.getLeft(), n);
                boolean wasRightChild = Objects.equals(parent.getRight(), n);
                if (wasLeftChild) {
                    parent.setLeft(with);
                }
                if (wasRightChild) {
                    parent.setRight(with);
                }
            }
            if (with != null) {
                boolean isRight = Objects.equals(right, with);
                boolean isLeft = Objects.equals(left, with);
                Node<Integer> x = (isRight) ? left : ((isLeft) ? right : null);
                with.setParent(parent);
                if (x != null) {
                    x.setParent(with);
                }
                if (isRight) {
                    with.setLeft(left);                             
                }
                else if (isLeft) {
                    with.setRight(right);
                }
//
//                String newName = (null != left) ? left.getElement().toString() :
//                                    (null != right) ? right.getElement().toString() : "NULL";
//                String origName = (with.getLeft() != null) ? with.getLeft().getElement().toString() :
//                                     (with.getRight() != null) ? with.getRight().getElement().toString() : "NULL";
//                String whichPtr = (left != null) ? "left" : (right != null) ? "right" : "none";
//                String s = whichPtr + " pointer To " + newName + " was " + origName;
//                System.out.println("*** Setting " + with.getElement() + " pointers: " + s);
            }
            return n;
        }

        // "n" is the rightmost node from the first leftchild
        // "found" is node to be replaced
        private void replace(Node<Integer> found, Node<Integer> n) {
            
            Node<Integer> l_child = found.getLeft();
            Node<Integer> r_child = found.getRight();
            Node<Integer> parent  = found.getParent();
            if (Objects.equals(l_child, n)) {   
                moveUpOne(found, l_child);            
                return;
            }
            // Bring it's left child up  (right child should ne null)
            Node<Integer> lc = n.getLeft();
//            if (lc != null) {
//                System.out.println("*** lc(" + lc.getElement() + ") is not null; n = " + n.getElement());
//            }
            moveUpOne(n, lc);       // no right child to worry about       

            // (9) Adjust on the new node "n" and the previous node's children
            n.setLeft(l_child);
            n.setRight(r_child);
            n.setParent(parent);
            if (l_child != null) {
                l_child.setParent(n);                    
            }            
            if (r_child != null) {
                r_child.setParent(n);
            }
            if (parent == null) {
                makeroot(n);
            }
            else {    
                boolean isLeft = Objects.equals(found, parent.getLeft());
                boolean isRight = Objects.equals(found, parent.getRight());
                if (isLeft) {
                    parent.setLeft(n);
                }
                if (isRight) {
                    parent.setRight(n);
                }
            }
        }
        
        public Position<Integer> delete(Integer e){
            Position<Integer> x = root;
            
            privBool prevLeft = new privBool(true);
            // (1)
            Node<Integer> found = (Node<Integer>)find(x, e, prevLeft);
            
            if (found == null) {
                return null;
            }
            // (2)
            Node<Integer> biggest = findRightmost(found);
            Node<Integer> f_lc = found.getLeft();
            Node<Integer> f_rc = found.getRight();
            
//            System.out.println("Found node = " + found.getElement());
            
            // (3)
            if (Objects.equals(found, biggest))  {  // no right child
                return moveUpOne(found, f_lc);
            }
            // (4)
            if (f_lc == null) {  // first left is null
                return moveUpOne(found, f_rc);  // There has to be one becsue no right child covered above
            }
//            System.out.println("lc = " + f_lc.getElement());
//            System.out.println("rc = " + f_rc.getElement());
            // (5)
            Node<Integer> bigButLessThanF = findRightmost(f_lc);
//            System.out.println("bigButLessThanF = " + bigButLessThanF.getElement());
            
            // (6) Make bigButLessThanF left child's parent & right child's parent (Could move a couple of levels up);  Also move it 
            //     up one level;
            replace(found, bigButLessThanF);
            
            found.setParent(null);
            found.setLeft(null);
            found.setRight(null);
            return found;
        }
        
        public static void main(String[] args) {
		
		// create a new binary tree instance
		IntLinkedBinaryTree t =   new IntLinkedBinaryTree();
		
		// add some integers
		t.add(t.root, 100);
		t.add(t.root, 50);
		t.add(t.root, 150);
                t.add(t.root, 70);
                t.add(t.root, 175);
                t.add(t.root, 200);
                t.add(t.root, 80);
                t.add(t.root, 125);
                t.add(t.root, 20);
                t.add(t.root, 15);
                t.add(t.root, 2);
                t.add(t.root, 25);
                t.add(t.root, 120);
                t.add(t.root, 75);
                
		// test with more integers 
		
		// print all integers in the tree in increasing order
		// after adding above four integers, the following should be printed
		// 50 70 100 150
		
		print_priv(t);
                print(t);
                Integer [] y = {50, 100, 80, 175, 2, 70, 125, 15, 150, 20, 120, 25, 200, 75};
//                Integer [] y = {100, 80};
                for (Integer x: y) {
//                    System.out.println("Integer to delete = " + x);
                    if (t.delete(x) != null) {
                        System.out.println("Integer " + x + " deleted");
                    }
                    else {
                        System.out.println("Integer " + x + " not found");
                    }
//                    print_priv(t);
                    print(t);
                }
	}
}
