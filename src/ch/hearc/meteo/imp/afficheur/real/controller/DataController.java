
package ch.hearc.meteo.imp.afficheur.real.controller;

import java.util.ArrayList;
import java.util.List;

import ch.hearc.meteo.imp.afficheur.real.data.Station;

public class DataController
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public DataController()
		{
		listStations = new ArrayList<Station>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addStation(Station station) {
		listStations.add(station);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private List<Station> listStations;

	}
