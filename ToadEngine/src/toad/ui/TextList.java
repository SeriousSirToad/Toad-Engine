package toad.ui;
public class TextList {
	public TextNode front;  // first value in the list

    // post: constructs an empty list
    public TextList() {
        front = null;
    }

    // post: returns the current number of elements in the list
    public int size() {
        int count = 0;
        TextNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

  
    // returns the text at the given index in the list
    public String get(int index) {
        return nodeAt(index).text;
    }

    //creates a comma-separated, bracketed version of the list
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.text;
            TextNode current = front.next;
            while (current != null) {
                result += ", " + current.text;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

   //returns index of Value
    public int indexOf(String value) {
        int index = 0;
        TextNode current = front;
        while (current !=  null) {
            if (current.text.equals(value)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // appends the given value to the end of the list
    public void add(String newText) {
        if (front == null) {
            front = new TextNode(newText);
        } else {
            TextNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new TextNode(newText);
        }
    }

    public void add(int index, String value) {
        if (index == 0) {
            front = new TextNode(value, front);
        } else {
            TextNode current = nodeAt(index - 1);
            current.next = new TextNode(value, current.next);
        }
    }


    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            TextNode current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    private TextNode nodeAt(int index) {
        TextNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}


