package ch.hearc.meteo.imp.com.port;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import ch.hearc.meteo.imp.com.logique.MeteoServiceCallback_I;
import ch.hearc.meteo.imp.com.real.MeteoService;
import ch.hearc.meteo.imp.com.real.MeteoServiceFactory;
import ch.hearc.meteo.imp.com.real.com.ComConnexion;
import ch.hearc.meteo.imp.com.real.com.ComOption;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoAdapter;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoListener_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.com.port.MeteoPortDetectionService_I;

public class MeteoPortDetectionService implements MeteoPortDetectionService_I {

	public MeteoPortDetectionService() {
		data_recieved = false;
	}

	@Override
	public List<String> findListPortSerie() {
		ArrayList<String> listPortSerie = new ArrayList<String>();
		Enumeration<CommPortIdentifier> portEnum = (Enumeration<CommPortIdentifier>) CommPortIdentifier
				.getPortIdentifiers();

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier port = portEnum.nextElement();
			if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				listPortSerie.add(port.getName());
			}
		}
		return listPortSerie;
	}

	@Override
	public boolean isStationMeteoAvailable(String portName, long timeoutMS) {
		if (portName.equals("COM5")) {
			return false;
		}

		MeteoService meteoService = (MeteoService) (new MeteoServiceFactory()
				.create(portName));
		meteoService.addMeteoListener(new MeteoAdapter() {

			@Override
			public void altitudePerformed(MeteoEvent event) {
				System.out.println("message alttitude");
				data_recieved = true;
			}
		});

		data_recieved = false;

		try {
			System.out.println("Port : " + portName
					+ " test is Station Meteo Available");
			meteoService.connect();
			meteoService.startHardware();
			meteoService.askAltitudeAsync();
			meteoService.askAltitudeAsync();
			Thread.sleep(timeoutMS);

			meteoService.disconnect();

		} catch (Exception e) {
			// for debug
			System.out.println(e.getMessage() + " => "
					+ e.getCause().toString());
		}
		return data_recieved;
	}

	@Override
	public List<String> findListPortMeteo(List<String> listPortExcluded) {
		// Step1
		List<String> listPortCom = findListPortSerie();
		// Step2
		for (String portName : listPortCom) {
			if (listPortExcluded.contains(portName)) {
				listPortCom.remove(portName);
			}
		}
		// Step3
		List<String> listPortComMeteoAvailable = new ArrayList<String>();
		// Step4
		for (String portName : listPortCom) {
			if (isStationMeteoAvailable(portName, 500)) {
				listPortComMeteoAvailable.add(portName);
			}
		}

		// Output
		return listPortComMeteoAvailable;
	}

	@Override
	public List<String> findListPortMeteo() {
		List<String> listPortExcluded = new ArrayList<String>(0);
		return findListPortMeteo(listPortExcluded);
	}

	private boolean data_recieved;
}
