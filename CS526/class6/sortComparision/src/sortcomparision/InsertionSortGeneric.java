/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sortcomparision;
import java.util.Comparator;

// Generic class which takes a parameter T which is comparable
public class InsertionSortGeneric<T extends Comparable<T>> {
  
  private static final InsertionSortGeneric instance = new InsertionSortGeneric();
  
  public static InsertionSortGeneric getinstance() {
      return instance;
  }
    
  /** Insertion-sort of an array of characters into nondecreasing order
     * @param data */
  public void insertionSort(T[] data) {
    int n = data.length;
    for (int k = 1; k < n; k++) {            // begin with second character
        T cur = data[k];                    // time to insert cur=data[k]
        int j = k;                             // find correct index j for cur
        Comparator<T> comp = dataElementComparator.getInstance();
        
        while (j > 0 && comp.compare(data[j-1], cur) > 0) {     // thus, data[j-1] must go after cur
            data[j] = data[j-1];                 // slide data[j-1] rightward
            j--;                                 // and consider previous j for cur
        }
        data[j] = cur;                         // this is the proper place for cur
    }
  }
}

  