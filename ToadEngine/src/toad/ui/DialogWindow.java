package toad.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DialogWindow extends GameWindow {
	private TreeMap<Integer, ArrayList<String>> textMap = new TreeMap<>();
	int mapSize;

	public DialogWindow(String title, String body, int w, int h, String buttonName, String textFile) {
		super(title, body, w, h, buttonName);
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(textFile);
			br = new BufferedReader(fr);
			mapSize = br.read();
			ArrayList<String> textList = new ArrayList<>();
			for (int a = 0; a < mapSize; a++) {
				int currIndex = 0;
				String rawText = br.readLine();
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
