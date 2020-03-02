package toad.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import toad.game.Main;

public class DialogueWindow extends GameWindow {

	public ArrayList<GameButton> gameButtons = new ArrayList<>();
	String f1;
	String f2;
	File file;
	BufferedReader fr;
	BufferedReader fr2;
	TextList PlayerOptions;
	static TextList NPCOptions;
	Integer numR;

	static StringManager sm = new StringManager();
	Integer numO;
	public static ArrayList<String> choiceArr = new ArrayList<String>();
	public static ArrayList<String> npcArr = new ArrayList<String>();
	public TextNode Current;
	public TextNode temp;

	public DialogueWindow(String title, String body, int w, int h,
			String buttonName, String f1, String f2) throws IOException {
		super(title, body, w, h, buttonName);
		this.f1 = f1;
		this.f2 = f2;
		File file = new File(f1);
		File file2 = new File(f2);
		this.file = file;
		try {
			fr = new BufferedReader(new FileReader(file));
			fr2 = new BufferedReader(new FileReader(file2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Player_numResponses = fr.readLine();
		numR = Integer.parseInt(Player_numResponses);
		PlayerOptions = new TextList();
		PlayerOptions.front = new TextNode(fr.readLine());
		for (int i = 0; i < numR - 1; i++) {
			PlayerOptions.add(fr.readLine());
		}

		String NPC_numResponses = fr2.readLine();
		numO = Integer.parseInt(NPC_numResponses);
		NPCOptions = new TextList();
		for (int x = 0; x < numO; x++) {
			NPCOptions.add(fr2.readLine());
		}
		choiceSorter(0);
	}

	public void choiceSorter(int a) {

		if (a == 0) {
			choiceArr.clear();
			Current = PlayerOptions.front;
			npcArr.add(NPCOptions.front.text);
			while (!(Current.text.equals("WAIT"))) {
				choiceArr.add(Current.text);
				Current = Current.next;
			}
		} else if (a == 1) {
			if (Current != null) {
				Current = Current.next;
			}
			int c = 0;
			if(Current != null){
			
			while (!(Current.text.equals("WAIT"))) {
				choiceArr.set(c, Current.text);
				c++;
				Current = Current.next;
				if(Current == null){
					break;
				}
			}
			}
			int q = 0; 
			while(q < c){
				if(gameButtons.get(q).textNum < NPCOptions.size())
				gameButtons.get(q).setTextNum(gameButtons.get(q).textNum + c);
				q++;
			}

		} else if (a == -1){
			deactivate();
			choiceSorter(0);
		}
	}

	public void input(int x) {
		int count = 0;
		TextNode npcCurrent = NPCOptions.front;
		while (npcCurrent.next != null) {
			count++;
			if (count == x) {
				String s = npcCurrent.next.text;
				
				choiceSorter(sm.StringChecker(s, npcArr));
				break;
			}
			npcCurrent = npcCurrent.next;
		}

	}

	public void makeButtons(int m, int i, String line) {
		gameButtons.add(new GameButton(x + (w / 2) - (GameButton.stdWidth / 2)
				- 38, y + h - GameButton.stdHeight - m, buttonName, bodyFont,
				line.length() * 2, 5, i) {
			public void onClick() {
				 System.out.println(this.textNum);
				input(this.textNum);
			}
		});

	}

	public void showText(Graphics g) {
		int m = 55;
		int gbNum = 1;
		g.setColor(Color.white);
		g.drawString(title, x + 1, y + InGameUI.standardFont.getSize() + 1);
		g.setFont(bodyFont);
		int tempy = y + InGameUI.standardFont.getSize() + 1;
		g.drawString(npcArr.get(0), x + 1, tempy += g.getFontMetrics()
				.getHeight());
		for (String line : choiceArr) {
			g.drawString(line, x + 1, tempy += g.getFontMetrics().getHeight());
			if (gameButtons.size() < choiceArr.size()) {
				makeButtons(m, gbNum, line);
				m -= 7;
				gbNum++;
			}
		}

		g.setFont(InGameUI.standardFont);
	}

	public void show() {

		Graphics g = Main.g;
		g.setColor(colour);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawRect(x, y, w, h);
		if(gameButtons != null){
		for (GameButton b : gameButtons) {
			b.render(g);
		}
		}
		showText(g);

	}

	public void update() {
		if (active) {
			show();
			if(gameButtons != null){
			for (GameButton b : gameButtons) {
				b.tick();
			}
			}
		}
	}

}
