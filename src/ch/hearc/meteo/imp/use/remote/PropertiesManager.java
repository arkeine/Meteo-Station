
package ch.hearc.meteo.imp.use.remote;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public static PropertiesManager getInstance() throws IOException

	{
		if(propertiesManager == null)
			{
				propertiesManager = new PropertiesManager();
				}
		return propertiesManager;
	}

	private PropertiesManager() throws IOException
		{
		openFile();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getIpPcCentral()
		{
		return this.ipPcCentral;
		}

	public int getPortPcCentral()
		{
		return this.portPcCentral;
		}

	public String getIpPcLocal()
		{
		return this.ipPcLocal;
		}

	public int getPortPcLocal()
		{
		return this.portPcLocal;
		}

	public int getRmiSocketTimeOut()
		{
		return this.rmiSocketTimeOut;
		}

	public int getRmiConnectionTimeOut()
		{
		return this.rmiConnectionTimeOut;
		}

	public int getRmiConnectionTry()
		{
		return this.rmiConnectionTry;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void openFile() throws IOException
		{
		File f = new File(FILE_NAME);

		if (!f.exists())
			{
			f.createNewFile();
			loadValue(f);
			saveValue(f);
			}
		else
			{
			loadValue(f);
			}
		}

	private void saveValue(File f) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(f);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		Properties property = new Properties();

		property.setProperty(NAME_ipPcCentral, ipPcCentral);
		property.setProperty(NAME_portPcCentral, String.valueOf(portPcCentral));
		property.setProperty(NAME_ipPcLocal, ipPcLocal);
		property.setProperty(NAME_portPcLocal, String.valueOf(portPcLocal));
		property.setProperty(NAME_rmiSocketTimeOut, String.valueOf(rmiSocketTimeOut));
		property.setProperty(NAME_rmiConnectionTimeOut, String.valueOf(rmiConnectionTimeOut));
		property.setProperty(NAME_rmiConnectionTry, String.valueOf(rmiConnectionTry));

		property.store(bos, null);

		bos.close();
		fos.close();
		}

	private void loadValue(File f) throws IOException
		{
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);

		Properties property = new Properties();
		property.load(bis);

		ipPcCentral = property.getProperty(NAME_ipPcCentral, "127.0.0.1");
		portPcCentral = Integer.valueOf(property.getProperty(NAME_portPcCentral, "1097"));
		ipPcLocal = property.getProperty(NAME_ipPcLocal, "127.0.0.2");
		portPcLocal = Integer.valueOf(property.getProperty(NAME_portPcLocal, "1098"));
		rmiSocketTimeOut = Integer.valueOf(property.getProperty(NAME_rmiSocketTimeOut, "10000"));
		rmiConnectionTimeOut = Integer.valueOf(property.getProperty(NAME_rmiConnectionTimeOut, "1000"));
		rmiConnectionTry = Integer.valueOf(property.getProperty(NAME_rmiConnectionTry, "10"));

		bis.close();
		fis.close();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String ipPcCentral;
	private int portPcCentral;
	private String ipPcLocal;
	private int portPcLocal;
	private int rmiSocketTimeOut;
	private int rmiConnectionTimeOut;
	private int rmiConnectionTry;

	private static final String FILE_NAME = "./settings.properties";

	private static final String NAME_ipPcCentral = "ipPcCentral";
	private static final String NAME_portPcCentral = "portPcCentral";
	private static final String NAME_ipPcLocal = "ipPcLocal";
	private static final String NAME_portPcLocal = "portPcLocal";
	private static final String NAME_rmiSocketTimeOut = "rmiSocketTimeOut";
	private static final String NAME_rmiConnectionTimeOut = "rmiConnectionTimeOut";
	private static final String NAME_rmiConnectionTry = "rmiConnectionTry";

	private static PropertiesManager propertiesManager;
	}
