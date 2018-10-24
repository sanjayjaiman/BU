package intlinkedbinarytree;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

/**
 * IntLinkedBinaryTree
 *
 * @author Vincent P. Van Dintel
 * CS526 Assignment 5
 */
public class IntLinkedBinaryTreeWithDataCopy extends LinkedBinaryTree<Integer>{
	/**
	 * Add a new node with the provided element to the tree rooted at the provided position.
	 * If the element already exists in the tree, return null
	 * If the provided position is null, then add a new node with the element to the root.
	 * Otherwise add a child node
	 * @param position The root of the tree to which new node is added
	 * @param element The element of the new node
	 * @return If a node with the provided element does not exist, a new node with the element is added and
	 *   a reference to the node is returned. If a node with the element exists, null is returned.
	 */
	public Position<Integer> add(Position<Integer> position, Integer element){
		if (find(position, element) != null) {
			return null;
		} else {
			return position == null ? addRoot(element) : addChild(position, element);
		}
	}

	/**
	 * Add a node to the left or right after its parent
	 * @param position The root of the tree to which new node is added
	 * @param element The element of the new node
	 * @return the added element. If it was not added return null
	 */
	private Position<Integer> addChild(Position<Integer> position, Integer element) {
		Position<Integer> parent = getParent(position, element);

		if (parent == null) {
			return null;
		}

		return parent.getElement() > element
				? addLeft(parent, element)
				: addRight(parent, element);
	}

	/**
	 * Get the parent node to which the new node will be added as a child
	 * @param position The root of the tree to which new node is added
	 * @param element The element of the new node
	 * @return the parent node of the provided position and element to be added.
     * If it already exists, return null.
	 */
	private Position<Integer> getParent(Position<Integer> position, Integer element) {
		Position<Integer> child = position;
		Position<Integer>  parent = child;

		while (child != null) {
			if (parent.getElement().equals(element)) {
				return null;
			}
			else if (child.getElement() > element) {
				parent = child;
				child = left(child);
			}
			else {
				parent = child;
				child = right(child);
			}
		}

		return parent;
	}

	/**
	 * Check if a node has zero or one child
	 * @param position String
	 * @return true or false
	 */
	private boolean hasZeroOrOneChild(Position<Integer> position) {
        return !hasTwoChildren(position);
	}

	/**
	 * Check if a node has two children
	 * @param position String
	 * @return true or false
	 */
	private boolean hasTwoChildren(Position<Integer> position) {
	    return left(position) != null && right(position) != null;
	}

	/**
	 * Get the subtree maximum position of the provided position
	 * @param position String
	 * @return Max position
	 */
	private Position<Integer> getMaxPosition(Position<Integer> position) {
		Position<Integer> maxPosition = position;
		maxPosition = left(maxPosition);

		while (right(maxPosition) != null) {
			maxPosition = right(maxPosition);
		}

		return maxPosition;
	}

	/**
	 * Delete the provided position
	 * @param position Position
	 * @param element String
	 * @return the deleted element, or null if no element to delete
	 */
	public Integer delete(Position<Integer> position, Integer element) {
		Position<Integer> foundPosition = find(position, element);

		if (foundPosition == null) {
		    return null;
        }
		else if (hasZeroOrOneChild(foundPosition)) {
			return remove(foundPosition);
		} else {
		    // has two children
			Position<Integer> replacementPosition = getMaxPosition(foundPosition);
			set(foundPosition, replacementPosition.getElement());
			remove(replacementPosition);
			return element;
		}
	}

	/**
	 * Recursively search the tree for a position
	 * @param position Position
	 * @param element String
	 * @return the position if it exists, otherwise return null
	 */
	private Position<Integer> find(Position<Integer> position, Integer element) {
		if (position == null || position.getElement().equals(element)) {
			return position;
		}

		// recursively search through the left or right children
		return position.getElement() > element
				? find(left(position), element)
				: find(right(position), element);
	}

	/**
	 * Overrides the inherited method to display tree contents in ascending order
	 * @return String
	 */
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();

		inorder().forEach(position ->
                stringBuilder.append(
                        position.getElement().toString().concat(" "))
        );

		return stringBuilder.toString();
	}
    public static void main(String[] args) {
        IntLinkedBinaryTreeWithDataCopy tree = new IntLinkedBinaryTreeWithDataCopy();

        /* Uncomment this block to add 10 integers to the tree for testing */

        Integer[] testValues = { 100, 111, 56, 389, 7, 51, 0, 20, 12, 6, 62, 62, 10, -20, 8, 9, 42, 275 };

        // Loop through test value and add one by one, printing the element of the returned node
        // There is a duplicate on purpose, which should return null
        System.out.println("\nAdding 10 test values to tree: ");
        for (Integer testValue : testValues) {
//            System.out.print("Adding " + testValue);
            if (tree.add(tree.root(), testValue) != null) {
//                System.out.println("DONE");
            }
            else {
//                System.out.println("NOT DONE");
            }
        }
        System.out.println("Tree: " + tree.toString());
        
        int[] deleteValues = {20, 100, 111};
        for (Integer testValue : deleteValues) {
            tree.delete(tree.root(), testValue);
            System.out.println(tree.toString());
        }
        
    }

}
