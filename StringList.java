
/**
 * Class StringList represents a String List in which each node represents a
 * char and a number of times it appears in a row in a String. For example: The
 * String "aabbbacddd" will displayed by a list:
 * [a,2]->[b,3]->[a,1]->[c,1]->[d,3] 
 * @author (Amit Levi)
 * @version (2017)
 */

public class StringList {
	private CharNode _head;

	// constructors
	public StringList() {
		_head = null; // builds empty list when there is no String
	}

	public StringList(CharNode node) {
		if (node == null) { // empty String
			_head = null;
		} else {
			_head = new CharNode(node.getData(), node.getValue(), null); // set _head to point the first node in list
			for (CharNode ptr = node.getNext(), last = _head; ptr != null; ptr = ptr.getNext()) // create new nodes for
																								// each char in list
			{
				last.setNext(new CharNode(ptr.getData(), ptr.getValue(), ptr.getNext()));
				last = last.getNext();
			}
		}

	}

	/**
	 * Constructor builds a list of chars based on the string it gets.
	 * 
	 * @param s the string that the constructor gets in order to build the list
	 */
	public StringList(String s) {
		if (s != null) {
			for (int curr = s.length() - 1, last = curr + 1, counter = 1; curr >= 0; curr--, last -= 1)// adding new
																										// nodes to the
																										// end of the
																										// list
			{

				if (_head == null) // if the list is empty

					_head = new CharNode(s.charAt(curr), counter, null); // adding new node, and reset head to point
																			// the new first node

				else if (s.charAt(curr) == s.charAt(last))// if current char == last char, only the value needs to be
															// updated
				{
					counter += 1; // update counter and value
					_head.setValue(counter);

				}

				else // if current char != last char, new node will be added to the list
				{
					counter = 1; // counter reseted
					CharNode temp = new CharNode(s.charAt(curr), counter, _head);
					_head = temp;

				}
			}
		}

	}

	/**
	 * @param other the StringList that the constructor needs to copy
	 */
	public StringList(StringList other) {
		if (other == null || other._head == null)
			_head = null;

		else { // if there is at least one node in list
			_head = new CharNode(other._head.getData(), other._head.getValue(), null); // copy the first node in list
			CharNode myCurrentNode = _head;// pointer in this list
			CharNode otherCurrentNode = other._head.getNext();// pointer of the other list that the constructor got
			while (otherCurrentNode != null)// stop the loop when arrived to the end of list
			{
				// adding into this.setNext node a new node with the same details as other node
				myCurrentNode.setNext(new CharNode(otherCurrentNode.getData(), otherCurrentNode.getValue(), null));
				myCurrentNode = myCurrentNode.getNext();
				otherCurrentNode = otherCurrentNode.getNext();
			}
		}
	}

	// methods
	/**
	 * Method gets a number of index in string and returns the char in this index.
	 * 
	 * @param i = the number of the index in string
	 * @return the char that appears in index i
	 */
	public char charAt(int i) {
		int counter = -1; // because string start from 0
		CharNode ptr = _head;// pointer
		char c = ' ';

		if (_head != null) {

			while (counter < i && ptr != null) // when it stops it means that the right char has been reached
			{
				counter += ptr.getValue();
				c = ptr.getData();
				ptr = ptr.getNext();
			}
		}
		return c;
	}

	/**
	 * Method is returning a new list which made of this. list and str list that the
	 * method gets.
	 * 
	 * @param str the other list the method in adding to this. list
	 * @return the new list which include the two lists
	 * 
	 */
	public StringList concat(StringList str) {
		StringList list1 = new StringList(this);// copy list in order not to change the original list
		StringList list2 = new StringList(str);

		CharNode p = (list1._head); // pointer of this list
		while (p.getNext() != null) // getting the last node in this list
		{
			p = p.getNext();
		}
		p.setNext(list2._head);// adding into it the first node of str
		return list1;
	}

	/**
	 * Time complexity = O(n) Space complexity = O(1) Method is getting a ASCII code
	 * of a char and checks the first index in string that this char is appears.
	 * 
	 * @param ch the ASCII code of a char that needs to be found
	 * @return the number of first index that this char is appears
	 * @return -1 if char is not found in list
	 */
	public int indexOf(int ch) {
		CharNode ptr = _head;
		for (int count = 0; ptr != null; ptr = ptr.getNext()) {
			if (ptr.getData() == ch)
				return count;// return this place

			else // continue looking for char
				count += ptr.getValue();

		}

		return -1; // char is not found in list
	}

	/**
	 * Method is getting a number of index and ASCII code of a char and checks the
	 * first index in string that this char is appears from this index number.
	 * 
	 * @param ch        the char
	 * @param fromIndex the number of the index that methods start to search from
	 * @return the number of the first index the char is at from fromIndex
	 * @return -1 if char is not found in list
	 * 
	 */
	public int indexOf(int ch, int fromIndex) {
		CharNode ptr = _head;

		int from = 0;
		int to = 0;

		for (; ptr != null; ptr = ptr.getNext()) {
			if (ptr == _head)// from=0 only at the first time
				from = 0;
			else
				from = to + 1;

			to = from + ptr.getValue() - 1;// -1 because _value will always shows the next following index

			// when ch will be the from : for example (aaabba) (a,4)
			if (ptr.getData() == ch && from > fromIndex)
				return from;
			// when ch is in the middle of from and to : for example (aaaaabba) (a,3)
			if (ptr.getData() == ch && fromIndex >= from && fromIndex <= to)
				return fromIndex;

		}
		return -1;
	}

	/**
	 * Methods checks if this list and other list are equal.
	 * 
	 * @param str the other list
	 * @return true if they are equal
	 */
	public boolean equals(StringList str) {

		if (str == null)
			return false;

		return equals(this._head, str._head);

	}

	/**
	 * This is a private rec equal method
	 */
	private boolean equals(CharNode ptr1, CharNode ptr2) {
		if (ptr1 == null && ptr2 == null)// if the rec is done or they are both null
			return true;

		if (ptr1 == null || ptr2 == null)// means that they are not the same length
			return false;

		if (ptr1.getData() == ptr2.getData() && ptr1.getValue() == ptr2.getValue())// checks the details
			return equals(ptr1.getNext(), ptr2.getNext());

		return false;
	}

	/**
	 * Methods checks which string is bigger than the other lexicography. If they
	 * both equal, 0 will return. If this string is smaller then other string , -1
	 * will returned. If this string is bigger then other string , 1 will returned.
	 * 
	 * @param str the other string
	 * @return -1 If this string is smaller then other string
	 * @return 1 If this string is bigger then other string
	 * @return 0 If they both equal
	 */
	public int compareTo(StringList str) {
		CharNode thisCharNode = _head; // pointer of this Char Node
		CharNode strCharNode = str._head; // // pointer of other Char Node
		while (thisCharNode != null && strCharNode != null) {
			if (thisCharNode.getData() < strCharNode.getData())
				return -1;
			if (thisCharNode.getData() > strCharNode.getData())
				return 1;

			if (thisCharNode.getValue() == strCharNode.getValue()) { // continue to the next node
				thisCharNode = thisCharNode.getNext();
				strCharNode = strCharNode.getNext();
			} else {
				if (thisCharNode.getValue() < strCharNode.getValue()) {
					// checks the next data in this list compare to other current data
					if (thisCharNode.getNext() == null || thisCharNode.getNext().getData() < strCharNode.getValue())
						return -1;
					return 1;
				} else {
					// checks the next data in other list compare to this current data
					if (strCharNode.getNext() == null || strCharNode.getNext().getData() < thisCharNode.getValue())
						return 1;
					return -1;
				}
			}
		}

		if (strCharNode == null && thisCharNode == null)
			return 0; // equals
		if (strCharNode != null)
			return -1; // means that this list is shorter
		return 1;

	}

	/**
	 * Method is 'cutting' the string until int i index, the new edited list is
	 * return.
	 * 
	 * @param i the number of the index that the new list will start from
	 * @return the new list
	 */
	public StringList substring(int i) {
		CharNode ptr = _head; // pointer

		for (int val = 0, newValue = 0; ptr != null; ptr = ptr.getNext()) {
			val += ptr.getValue(); // add the amount of the value of this node
			if (val > i) {
				newValue = val - i; // calculate the new value
				CharNode newNode = new CharNode(ptr.getData(), newValue, ptr.getNext());// create a new node with the
																						// new value
				return new StringList(newNode); // send to the constructor to get a new list
			}
		}
		return new StringList();// when it's an empty string , an empty node will returned
	}

	/**
	 * Method gets int i and int j and return new StringList based on this
	 * StringList, that is cut until index i and from index j.
	 * 
	 * @param i the index that the new list will start from.
	 * @param j the index that the new list will end at.
	 * @return the new list after substring
	 */
	public StringList substring(int i, int j) {
		if (i >= j) // means that the string is empty, then return an empty list
		{
			return new StringList();
		}
		StringList newList = substring(i); // send to the last method with (i) and get a new list after cutting the
											// indexes before index (i)
		CharNode ptr = newList._head; // newList pointer
		int val = i;// now value @param will start from index i and not from the first index in the
					// original list
		int newVal = 0; // the new value of the last node in list will updated

		// from now on going to work on newList - the copy list from the constructor
		for (; ptr != null; ptr = ptr.getNext()) {
			val += ptr.getValue();// val is updated

			if (val == j) // its the right place to end the string
			{
				ptr.setNext(null); // updating that this node will be the lase node in list
				return newList;
			}

			if (val > j)// the value needs to be equal to j
			{
				newVal = ptr.getValue() - (val - j); // (val - j) calculate how many extra chars should be deleted from
														// the original _value in this node
				ptr.setValue(newVal); // reset the value of the last node in list
				ptr.setNext(null);// update that this is the last node in list
				return newList;

			}

			// if ( val < j ) -> continue the loop
		}

		return null; // when _head = null
	}

	/**
	 * Method returns the length of the string
	 * 
	 * @return the numbers of the length of the string
	 */
	public int length() {
		if (_head != null) {
			CharNode ptr = _head;
			int i = 0; // counter
			for (; ptr != null; ptr = ptr.getNext())
				i += ptr.getValue();
			return i;
		}

		return 0; // its an empty list
	}

	/**
	 * Method returns this string.
	 * 
	 * @return string
	 */
	public String toString() {
		String str = "\"";
		CharNode ptr = _head;

		for (int val = 0; ptr != null; ptr = ptr.getNext()) {
			val = ptr.getValue();
			while (val > 0) {
				val -= 1;
				str += ptr.getData();
			}

		}
		return str + "\"";
	}
}
