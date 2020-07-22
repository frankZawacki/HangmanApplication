package hangmanapplication;

import java.io.IOException;
import java.util.Scanner;

public class HangmanApplication {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to hangman. I will pick a word and you will "
				+ "try to guess it letter by letter. If you guess wrong 6 times "
				+ "then I win., if you guess it before then, you win.");

		System.out.println();
		System.out.println("I have picked a word. Keep guessing, with each wrong guess I "
				+ "will keep adding parts until the person is complete, when the "
				+ "person is complete you lose.");
		
		boolean doYouWantToPlay = true;
		while (doYouWantToPlay) {
			System.out.println("Alright let's play.");
			Hangman game = new Hangman();
			do {
				System.out.println();
				System.out.println(game.drawPicture());
				System.out.println();
				System.out.println(game.getFormalCurrentGuess());
				System.out.println(game.mysteryWord);		
				System.out.println();
				
				//get the guess
				System.out.println("What is your guess?");
				char guess = (sc.next().toLowerCase().charAt(0));
				System.out.println();
				
				while(game.isGuessedAlready(guess)) {
					System.out.println("You already guessed that letter, "
							+ "try another letter.");
					guess = (sc.next().toLowerCase().charAt(0));
				}
				if(game.playGuess(guess)) {
					System.out.println("Great guess that character is in the word");
				}else {
					System.out.println("Unfortunately that character4 is not in the word.");
				}
				
				
			}while(!game.gameOver()); 

			System.out.println();
			System.out.println("Do you want to play another game? Enter Y if you do.");
			Character response = (sc.next().toUpperCase()).charAt(0);
			doYouWantToPlay = (response == 'Y');
		}
		
;
	}

}
