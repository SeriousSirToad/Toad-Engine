package toad.ui;

import java.awt.Color;import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import toad.game.Main;

public class DialogueWindow extends GameWindow {

	public ArrayList<GameButton> gameButtons = new ArrayList<>();
	public int init;
	String f1;
	String f2;
	File file;
	BufferedReader fr;
	BufferedReader fr2;
	TextList PlayerOptions;
	static TextList NPCOptions;
	Integer numR;
	static StringManager sm;
	Integer numO;
	public static ArrayList<String> choiceArr = new ArrayList<String>();
	public static ArrayList<String> npcArr = new ArrayList<String>();
	public static int numButtons = 0;
	public static boolean deactivate = false;
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
		for (int i = 0; i < numR - 2; i++) {
			PlayerOptions.add(fr.readLine());
		}

		String NPC_numResponses = fr2.readLine();
		numO = Integer.parseInt(NPC_numResponses);
		NPCOptions = new TextList();
		for (int x = 0; x < numO; x++) {
			NPCOptions.add(fr2.readLine());
		}
		
		TextNode Current = PlayerOptions.front;
		choiceArr.add(PlayerOptions.front.text);
		while (!(Current.next.text.equals("WAIT") || Current.next.text
				.equals("EXIT"))) {
			choiceArr.add(Current.next.text);
			Current = Current.next;
		}
		npcArr.add(NPCOptions.front.text);
		
	}
		

	public void input(int x) {
		int count = 0;
		TextNode npcCurrent = NPCOptions.front;
		while (npcCurrent.next != null) {
			count++;
			if (count == x) {
				String s = npcCurrent.next.text;
				sm = new StringManager(s, npcArr, deactivate);
				sm.StringChecker();
				if(sm.getDeactivate()){
					deactivate();
				}
			}
			npcCurrent = npcCurrent.next;
		}

	}
	
	public void deactivate() {
		active = false;
		InGameUI.removeFromRendOrder(this);
	}


	public void makeButtons() {
		int mody = 55;
		init = 1;
		for (String line : choiceArr) {
				gameButtons.add(new GameButton(x + (w / 2)
						- (GameButton.stdWidth / 2) - 38, y + h
						- GameButton.stdHeight - mody, buttonName, bodyFont,
						line.length() * 2, 5, init) {
					public void onClick() {
						for (GameButton b : gameButtons) {
							if (b.hasBeenClicked) {
								input(b.textNum);
							}
						}
					}
				});
			
			mody -= 7;
			init++;
		}
	}

	public void showText(Graphics g) {
		g.setColor(Color.white);
		g.drawString(title, x + 1, y + InGameUI.standardFont.getSize() + 1);
		g.setFont(bodyFont);
		int tempy = y + InGameUI.standardFont.getSize() + 1;
		g.drawString(npcArr.get(0), x + 1, tempy += g.getFontMetrics().getHeight());
		for (String line : choiceArr) {
			g.drawString(line, x + 1, tempy += g.getFontMetrics().getHeight());
		}
		makeButtons();
		g.setFont(InGameUI.standardFont);
	}

	public void show() {

		Graphics g = Main.g;
		g.setColor(colour);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawRect(x, y, w, h);
		for (GameButton b : gameButtons) {
			b.render(g);
		}
		showText(g);

	}

	public void update() {
		if (active) {
			show();
			for (GameButton b : gameButtons) {
				b.tick();

			}
		}
	}

}
