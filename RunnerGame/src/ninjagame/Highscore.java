package ninjagame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Highscore {

	private int score;
	private String name;
	
	static String pathName = "src/highscore/highscore.txt";

	public Highscore(String name, int score) {
		this.score = score;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " - " + score;
	}

	static List<Highscore> sortList(List<Highscore> hsl){ 

		Collections.sort(hsl, new Comparator<Highscore>() {
		@Override
		public int compare(Highscore hsobj1, Highscore hsobj2) {
				return hsobj2.score - hsobj1.score;
			}
		});
		return hsl;
		}
		
	static List<Highscore> readFile(){
		
		List<Highscore> highscoreList = new LinkedList<>();
		String oneRowOfText = "";

		try (BufferedReader instream = new BufferedReader(new FileReader(pathName))) { 
			
			int counter = 1;
			
			while ((oneRowOfText = instream.readLine()) != null && counter <=10) {
				String[] rowArray = oneRowOfText.split(" ");
				int rowScore = Integer.parseInt(rowArray[0]);
				String rowName = rowArray[1];
				highscoreList.add(new Highscore(rowName, rowScore));
				counter ++;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Fil hittades inte");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fel med I/O");
			e.printStackTrace();
		}
		return highscoreList;

}
	
	static void writeFile(List<Highscore> highscoreList) {
		
		String writeToFile = "";
		
		try (BufferedWriter outstream = new BufferedWriter(new FileWriter(pathName))){
			for(int i = 0; i <=9; i++){
				writeToFile += highscoreList.get(i).score + " " + highscoreList.get(i).name + "\n";
			}
			outstream.write(writeToFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
