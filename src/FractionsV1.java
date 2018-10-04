import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Ian Bryan
 *
 * I tried to set the size of the listOfFractions to the lineCounter using the following code:
 * 
 * I found this on StackOverflow and read more on what constitutes a "new line" 
 * and this did end up producing the correct line number
 * but when I compiled the code and ran it, it produced 31 "nulls". 
 * 
 *  public void openTheFile(){
 * 		BufferedReader readFile = new BufferedReader(new FileReader("fractions.txt"));
 *  	int numOfFracts = 0;
 *		while (readFile.readLine() != null) {numOfFracts++;}
 *		readFile.close();
 *
 *		Scanner dataFile = new Scanner("fractions.txt");
 *		String[] listOfFractions = new String[numOfFracts];
 *		while(dataFile.hasNextLine()){
 *			listOfFractions[i] = dataFile.nextLine();
 *		}
 *	}
 */

public class FractionsV1 {

	/*
	 * Data Types [Arrays]
	 * 
	 * String array is declared and will be resized to store the fraction values
	 * Another String array of length 2 used to for containing the numerator/denominator
	 * int arrays for the numerators and denominators once they're split
	 * The boonlean array is used to check if that "card" in the "deck" already exists
	 * */
	String[] listOfFractions = new String[31];
	private String[] splitFractions = new String[2];
	private int[] theNumerators = new int[31];
	private int[] theDenoms = new int[31];
	private boolean[] fractionIndexed = new boolean[31];

	/*
	 * Data Types (primitive)
	 * 
	 * */
	private int lineCounter = 0;
	private int dupeCounter = 0;
	private int checkDenom = 0;
	private int checkNumerator = 0;

	Scanner dataFile = null;

	/*Begin Main*/
	public static void main(String[] args) {

		FractionsV1 fv1 = new FractionsV1();
		fv1.openTheFile();
		fv1.countRowsInFile();
		fv1.splitListOfFractions();
		fv1.compareND();
		fv1.closeTheFile();
	}

	/*
	 * try-catch is required for when declaring a new Scanner object to open a file
	 * The `try` will attempt to location and open a new File object containing the fractions
	 * `catch` grabs and returns some dialogue for if the file is missing.
	 * */
	public void openTheFile(){

		try{
			dataFile = new Scanner(new File("fractions.txt"));
		}catch(FileNotFoundException e){
			System.out.println("Program unable to locate file. Check directory and try again");
			System.exit(0);
		}
	}

	/*
	 * Counting the ## of rows (indices) there are in the file
	 * Set the number returned to the size of the array to store fractions
	 * */
	public void countRowsInFile(){
		for(int a=0;dataFile.hasNextLine();a++){
			listOfFractions[a] = dataFile.nextLine();
			lineCounter++;
		}
	}

	/*
	 * Using the other arrays for split fractions and numerator/denominator storage
	 * The fractions are split and placed into appropriate arrays of equal size
	 * */
	public void splitListOfFractions(){
		for(int b=0;b<lineCounter;b++){
			splitFractions = listOfFractions[b].split("/");
			theNumerators[b] = Integer.parseInt(splitFractions[0]);
			theDenoms[b] = Integer.parseInt(splitFractions[1]);
		}
	}

	/*
	 * The left hand side is found first, then the right hand side is dealt with.
	 * First check to see if the number on the top matches another on the list of numerators
	 * If it does, then check the denominator too to find if it's the same fraction
	 * If a fraction is already logged, the numerator array keeps moving until 
	 * there are not more indices to iterate over.
	 * */
	public void compareND(){
		for(int i=0;i<lineCounter;i++){
			checkNumerator = theNumerators[i];
			checkDenom = theDenoms[i];

			//nested for-loop
			for(int c=0;c<lineCounter;c++){
				if(!fractionIndexed[c]){
					if(checkDenom == theDenoms[c]){
						if(checkNumerator == theNumerators[c]){
							dupeCounter++;
							fractionIndexed[c] = true; //the fraction already exists in the listOfFractions array
						}//end if block
					}//end if block
				}//end last nested if
			}//end for loop

			if(dupeCounter > 0){
				System.out.println(checkNumerator + "/" + checkDenom + " has a count of " + dupeCounter);
			}
			dupeCounter = 0;
		}
	}

	//close the file for the end of the program
	public void closeTheFile(){dataFile.close();
	}
}