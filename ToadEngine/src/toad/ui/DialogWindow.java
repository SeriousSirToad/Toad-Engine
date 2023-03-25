package toad.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class DialogWindow extends GameWindow {
	private TreeMap<Integer, ArrayList<String>> textMap = new TreeMap<>();

	public DialogWindow(String title, String body, int w, int h, String buttonName) {
		super(title, body, w, h, buttonName);
	}
}
