import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Project1 {
	public static void main(String[] args) {

		HashMap<Character, Integer> letterMap = new HashMap<>();
		HashMap<String, Integer> wordMap = new HashMap<>();
		HashMap<String, Integer> stopMap = new HashMap<>();
		HashMap<Character, Integer> firstLetterMap = new HashMap<>();

		String fileName = "tom-sawyer.txt";
		String stopTextFile = "stop-list.txt";

		FileReader fin = null;
		FileReader stopFile = null;

		try {
			fin = new FileReader(fileName);
			stopFile = new FileReader(stopTextFile);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) not found.");
		}

		Scanner bookInfo = new Scanner(fin);
		Scanner stopInfo = new Scanner(stopFile);

		ArrayList<String> stopListWords = new ArrayList<String>();
		while (stopInfo.hasNext()) {
			stopListWords.add(stopInfo.next());
		}

		ArrayList<String> fileLines = new ArrayList<>();
		while (bookInfo.hasNext()) {
			fileLines.add(bookInfo.nextLine());
		}

		for (String line : fileLines) {
			Scanner scan = new Scanner(line);

			while (scan.hasNext()) {
				// this will read the line and separate out each word
				scan.useDelimiter("[^a-zA-Z']");
				String word = scan.next();
				word = word.toLowerCase();
				// replace all leading apostrophes
				word = word.replaceAll("^'+", ""); // replace all trailing apostrophes
				word = word.replaceAll("'+$", "");

				if (!word.equals("")) {
					// add words to WORD MAP
					if (wordMap.get(word) == null) {
						wordMap.put(word, 1);
					} else {
						int value = wordMap.get(word);
						wordMap.put(word, value + 1);
					}

					// adds words to STOP LIST WORD MAP
					if (!stopListWords.contains(word)) {
						if (stopMap.get(word) == null) {
							stopMap.put(word, 1);
						} else {
							int value = stopMap.get(word);
							stopMap.put(word, value + 1);
						}
					}

					// adds letters of word to LETTER MAP
					for (int i = 0; i < word.length(); i++) {
						char letter = word.charAt(i);

						if (letter >= 'a' && letter <= 'z') {
							if (letterMap.get(letter) == null) {
								letterMap.put(letter, 1);
							} else {
								int value = letterMap.get(letter);
								letterMap.put(letter, value + 1);
							}
						}
					}

					// adds first letter of word to FIRST LETTER MAP
					char firstLetter = ' ';
					if (!stopListWords.contains(word))
						firstLetter = word.charAt(0);

					if (firstLetter >= 'a' && firstLetter <= 'z') {
						if (firstLetterMap.get(firstLetter) == null) {
							firstLetterMap.put(firstLetter, 1);
						} else {
							int value = firstLetterMap.get(firstLetter);
							firstLetterMap.put(firstLetter, value + 1);
						}
					}
				}
			}
		}

		/***** PRINT LETTER MAP *****/
		System.out.println("Top 10 Letter Frequencies (all converted to lower case)");
		for (int i = 0; i < 10; i++) {
			int max = -1;
			char holdKey = ' ';
			for (char temp = 'a'; temp <= 'z'; temp++) {
				if (letterMap.containsKey(temp) && max < letterMap.get(temp)) {
					max = letterMap.get(temp);
					holdKey = temp;
				}
			}

			System.out.println("Letter: " + holdKey + " frequency " + letterMap.get(holdKey));
			letterMap.remove(holdKey);
		}

		/***** PRINT WORD MAP *****/
		System.out.println("\nTop 10 Word Frequencies (all converted to lower case)");
		for(int i = 0; i<10; i++)
		{
			int max = -1;
			String holdKey = "";
			for(String s : wordMap.keySet())
			{
				if(wordMap.containsKey(s) && max < wordMap.get(s))
				{
					max = wordMap.get(s);
					holdKey = s;
				}
			}
			
			System.out.println("Word: " + holdKey + " frequency " + max);
			wordMap.remove(holdKey);
		}

		/***** PRINT STOP LIST WORD MAP *****/
		System.out.println("\nTop 10 Word Frequencies using Stop List (all converted to lower case)");
		for(int i = 0; i<10; i++)
		{
			int max = -1;
			String holdKey = "";
			for(String s : stopMap.keySet())
			{
				if(stopMap.containsKey(s) && max < stopMap.get(s))
				{
					max = stopMap.get(s);
					holdKey = s;
				}
			}
			
			System.out.println("Word: " + holdKey + " frequency " + max);
			stopMap.remove(holdKey);
		}

		/***** PRINT FIRST LETTERS MAP *****/
		System.out.println("\nTop 10 Frequencies of the First Letter of each Word (Using Stop List) (all converted to lower case)");
		for (int i = 0; i < 10; i++) {
			int max = -1;
			char holdKey = ' ';
			for (char temp = 'a'; temp <= 'z'; temp++) {
				if (firstLetterMap.containsKey(temp) && max < firstLetterMap.get(temp)) {
					max = firstLetterMap.get(temp);
					holdKey = temp;
				}
			}

			System.out.println("Letter: " + holdKey + " frequency " + max);
			firstLetterMap.remove(holdKey);
		}
	}
}
