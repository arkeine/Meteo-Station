
package ch.hearc.meteo.imp.afficheur.real.tools;

public class MathTools
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static String arrondir(float value)
		{
		int valueINT = (int)(value * 100);
		float arrondi = valueINT / (float)100;

		return "" + arrondi;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
