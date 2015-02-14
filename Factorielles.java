/**
	Title:	The "Factorielles" class.
	Date Written: March 2014
	Written By: Sam Dindyal
	Description: A self-testing class which calculates the factorials of values between 0 to 16 in avoidance of an overflow.

*/

import java.util.Scanner;
import java.io.IOException;

public class Factorielles
{
	public static void main (String[] args) throws IOException
	{
		String usrIn = "";
		Scanner scanner = new Scanner(System.in);

		while (!usrIn.equalsIgnoreCase("x") || !usrIn.toLowerCase().startsWith("n"))
		{

			System.out.println("Veuillez entrer un chiffre entre 0 et 16.");
			System.out.println(calculateurFactorielle(scanner.nextInt()));
			System.out.println("Voudriez-vous continuer?");
			usrIn = scanner.next();
		}
	}//Self-testing main

/**
	Recursively calculates a value of a factorial between 0 and 16 inclusive.

	@param	valeur 		The value of the factorial to calculate
	@return 	 		The calculated factorial.
*/
	public static int calculateurFactorielle(int valeur) throws IOException
	{
		if (valeur==1 || valeur==0)
			return 1;
		else if (valeur > 16)
			throw new IOException("Des entrées de plus que 16 ne sont pas autorisés.");
		else if (valeur < 0)
			throw new IOException("Des entrées de moins que 0 ne sont pas autorisés.");
		return calculateurFactorielle(valeur-1)*valeur;
	}
}