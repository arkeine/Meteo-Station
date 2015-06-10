package ch.hearc.meteo.imp.com.real.com;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import ch.hearc.meteo.imp.com.logique.MeteoServiceCallback_I;
import ch.hearc.meteo.imp.com.real.com.trame.TrameDecoder;
import ch.hearc.meteo.imp.com.real.com.trame.TrameEncoder;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;

// TODO student
//  Implémenter cette classe
//  Updater l'implémentation de MeteoServiceFactory.create()

/**
 * <pre>
 * Aucune connaissance des autres aspects du projet ici
 * 
 * Ouvrer les flux vers le port com
 * ecouter les trames arrivantes (pas boucle, mais listener)
 * decoder trame
 * avertir meteoServiceCallback
 *
 * </pre>
 */
public class ComConnexion implements ComConnexions_I {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ComConnexion(MeteoServiceCallback_I meteoServiceCallback,
			String portName, ComOption comOption) {
		this.comOption = comOption;
		this.portName = portName;
		this.meteoServiceCallback = meteoServiceCallback;
	}

	/**
	 * <pre>
	 * Problem :
	 * 		MeteoService est un MeteoServiceCallback_I
	 * 		ComConnexions_I utilise MeteoServiceCallback_I
	 * 		MeteoService utilise ComConnexions_I
	 * 
	 * On est dans la situation
	 * 		A(B)
	 * 		B(A)
	 * 
	 * Solution
	 * 		 B
	 * 		 A(B)
	 *  	 B.setA(A)
	 * 
	 *  Autrement dit:
	 * 
	 * 	ComConnexions_I comConnexion=new ComConnexion( portName,  comOption);
	 *      MeteoService_I meteoService=new MeteoService(comConnexion);
	 *      comConnexion.setMeteoServiceCallback(meteoService);
	 * 
	 *      Ce travail doit se faire dans la factory
	 * 
	 *  Warning : call next
	 *  	setMeteoServiceCallback(MeteoServiceCallback_I meteoServiceCallback)
	 *
	 * </pre>
	 */
	public ComConnexion(String portName, ComOption comOption) {
		this(null, portName, comOption);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void start() {
		try {
			serialPort.addEventListener(new SerialPortEventListener() {

				@Override
				public void serialEvent(SerialPortEvent event) {
					switch (event.getEventType()) {
					case SerialPortEvent.DATA_AVAILABLE:
						treatData();
						break;
					}
				}
			});
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
		serialPort.notifyOnDataAvailable(true);
	}

	@Override
	public void stop() {
		serialPort.notifyOnDataAvailable(false);
		serialPort.removeEventListener();
	}

	@Override
	public void connect() throws Exception {
		portId = CommPortIdentifier
				.getPortIdentifier(portName);

		serialPort = (SerialPort) portId.open(this.getClass().getSimpleName(),
				10000);
		serialPort.setSerialPortParams(comOption.getSpeed(),
				comOption.getDataBit(), comOption.getStopBit(),
				comOption.getParity());
		serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

		isr = new InputStreamReader(serialPort.getInputStream());
		reader = new BufferedReader(isr);
		os = serialPort.getOutputStream();
	}

	@Override
	public void disconnect() throws Exception {
		isr.close();
		reader.close();
		serialPort.close();
	}

	@Override
	public void askAltitudeAsync() throws Exception {
		os.write(TrameEncoder.coder("010200"));
	}

	@Override
	public void askPressionAsync() throws Exception {
		os.write(TrameEncoder.coder("010000"));
	}

	@Override
	public void askTemperatureAsync() throws Exception {
		os.write(TrameEncoder.coder("010100"));
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	@Override
	public String getNamePort() {
		return portName;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override
	public void setMeteoServiceCallback(
			MeteoServiceCallback_I meteoServiceCallback) {
		this.meteoServiceCallback = meteoServiceCallback;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void treatData() {
		try {
			String line = reader.readLine();
			float value = TrameDecoder.valeur(line);

			switch (TrameDecoder.dataType(line)) {
			case PRESSION:
				meteoServiceCallback.pressionPerformed(value);
				break;
			case TEMPERATURE:
				meteoServiceCallback.temperaturePerformed(value);
				break;
			case ALTITUDE:
				meteoServiceCallback.altitudePerformed(value);
				break;
			}

		} catch (IOException e) {
			System.err.println("Can't read next line");
		} catch (MeteoServiceException e) {
			System.err.println(e.getMessage());
		}

	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private ComOption comOption;
	private String portName;
	private MeteoServiceCallback_I meteoServiceCallback;

	// Tools
	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private OutputStream os;
	private BufferedReader reader;
	private InputStreamReader isr;

}
