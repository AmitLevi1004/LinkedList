
/**
 * Class CharNode represents a Char Node in linked list. 
 * @author (Amit Levi)
 * @version (2017)
 * 
 */

public class CharNode {
	private char _data; // current char
	private int _value; // Count how many times this char appears in String
	private CharNode _next; // point to the next char in String

	public CharNode(char c, int val, CharNode n) {
		_data = c;
		_value = val;
		_next = n;
	}

	public CharNode getNext() {
		return _next;
	}

	public void setNext(CharNode node) {
		_next = node;
	}

	public int getValue() {
		return _value;
	}

	public void setValue(int v) {
		_value = v;
	}

	public char getData() {
		return _data;
	}

	public void setData(char c) {
		_data = c;
	}

}
