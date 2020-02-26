package toad.ui;
public class TextNode {
	public String text;
	public TextNode next;

	public TextNode(String text, TextNode next) {
		this.text = text;
		this.next = next;
	}

	public TextNode(String text) {
		this(text, null);
	}

	public TextNode() {
		this("", null);
	}
}
