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
	int mapSize;

	public DialogWindow(String title, String body, int w, int h, String buttonName, String textFile) {
		super(title, body, w, h, buttonName);
		FileInputStream fr;
		Scanner sc;
		try {
			fr = new FileInputStream(new File(textFile));
			sc = new Scanner(fr);
			mapSize = sc.nextInt();
			ArrayList<String> textList = new ArrayList<>();
			for (int a = 0; a < mapSize; a++) {
				int currIndex = 0;
				String rawText = sc.nextLine();
				while (rawText.contains("|")) {
					String temp = rawText.substring(currIndex, rawText.indexOf("|"));
					currIndex = rawText.indexOf("|");
					textList.add(temp);
					rawText.replaceFirst("|", "");
				}
				System.out.println(rawText);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
