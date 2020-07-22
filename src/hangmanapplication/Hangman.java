package hangmanapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	
	String mysteryWord;
	StringBuilder currentGuess;
	ArrayList<Character> previousGuesses = new ArrayList<>();
	
	int maxTries = 6;
	int currentTry = 0;
	ArrayList<String> dictionary = new ArrayList<>();
	private static FileReader fileReader;
	private static BufferedReader bufferedFileReader;
	
	public Hangman() throws IOException{
		intializeStreams();
		mysteryWord = pickWord();
		currentGuess = initializeCurrentGuess();
		
	}
	
	public void intializeStreams() throws IOException {
		try {
			File inFile = new File("Dictionary.txt");
			fileReader = new FileReader(inFile);
			bufferedFileReader = new BufferedReader(fileReader);
			String currentLine = bufferedFileReader.readLine();
			while (currentLine != null) {
				dictionary.add(currentLine);
				currentLine = bufferedFileReader.readLine();
			}
			bufferedFileReader.close();
			fileReader.close();
		}catch (IOException e) {
			System.out.println("Could not init streams.");
		}
		
	}
	
	public String pickWord() {
		Random rand = new Random();
		int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
		return dictionary.get(wordIndex);
	}
	
	public StringBuilder initializeCurrentGuess() {
		StringBuilder current = new StringBuilder();
		for (int i = 0; i < mysteryWord.length() * 2; i++) {
			if(i % 2 == 0) {
				current.append("_");
			}else {
				current.append(" ");
			}
		}
		return current;		
	}
	public String getFormalCurrentGuess() {
		return "Current guess: " + currentGuess.toString();
	}
	
	public boolean gameOver() {
		if(didWeWin()) {
			System.out.println();
			System.out.println("Congrats. You win.");
			return true;
		}else if(didWeLose()) {
			System.out.println();
			System.out.println("Oh no, you lost.");
			return true;
		}
		return false;
	}
	
	public boolean didWeWin() {
		String guess = getCurrentCondensedGuess();
		return guess.equals(mysteryWord);
	}
	
	public String getCurrentCondensedGuess() {
		String guess = currentGuess.toString();
		return guess.replace(" ", "");				
	}
	
	public boolean didWeLose() {
		return currentTry >= maxTries;
	}
	
	public boolean isGuessedAlready(char guess) {
		return previousGuesses.contains(guess);
	}
	
	public boolean playGuess(char guess) {
		boolean isItAGoodGuess = false;
		for(int i = 0; i < mysteryWord.length(); i++) {
			if(mysteryWord.charAt(i) == guess) {
				currentGuess.setCharAt(i *2, guess);
				isItAGoodGuess = true;
				previousGuesses.add(guess);				
			}
		}
		if(!isItAGoodGuess) {
			currentTry++;
		}
		return isItAGoodGuess;
	}
	
//	" - - - - -\n"+
//	"|        |\n"+
//	"|        O\n" +
//	"|      / | \\ \n"+
//	"|        |\n" +
//	"|       / \\ \n" +
//	"|\n" +
//	"|\n";
	
	public String drawPicture() {
		switch(currentTry) {
			case 0: return noPersonDraw();
			case 1: return addHeadDraw();
			case 2: return addBodyDraw();
			case 3: return addFirstArmDraw();
			case 4: return addSecondArmDraw();
			case 5: return addLegsDraw();
			default: return fullPersonDraw();
										
		}
		
	}

	private String noPersonDraw() {
		return " - - - - -\n"+
		"|        |\n"+
		"|        \n" +
		"|       \n"+
		"|        \n" +
		"|        \n" +
		"|\n" +
		"|\n";
		
	}
	
	private String addHeadDraw() {
		return 	" - - - - -\n"+
		"|        |\n"+
		"|        O\n" +
		"|      \n"+
		"|        \n" +
		"|       \n" +
		"|\n" +
		"|\n";

	}

	private String addBodyDraw() {
		// TODO Auto-generated method stub
		return 		" - - - - -\n"+
				"|        |\n"+
				"|        O\n" +
				"|        | \n"+
				"|        |\n" +
				"|       \n" +
				"|\n" +
				"|\n";

	}

	private String addFirstArmDraw() {
		// TODO Auto-generated method stub
		return 		" - - - - -\n"+
		"|        |\n"+
		"|        O\n" +
		"|      / | \n"+
		"|        |\n" +
		"|      \n" +
		"|\n" +
		"|\n";

	}

	private String addSecondArmDraw() {
	// TODO Auto-generated method stub
	return 		" - - - - -\n"+
	"|        |\n"+
	"|        O\n" +
	"|      / | \\ \n"+
	"|        |\n" +
	"|        \n" +
	"|\n" +
	"|\n";

	}
	
	private String addLegsDraw() {
		// TODO Auto-generated method stub
		return 		" - - - - -\n"+
		"|        |\n"+
		"|        O\n" +
		"|      / | \\ \n"+
		"|        |\n" +
		"|       / \n" +
		"|\n" +
		"|\n";

	}
	
	private String fullPersonDraw() {
		// TODO Auto-generated method stub
		return 		" - - - - -\n"+
		"|        |\n"+
		"|        O\n" +
		"|      / | \\ \n"+
		"|        |\n" +
		"|       / \\ \n" +
		"|\n" +
		"|\n";

	}

}
