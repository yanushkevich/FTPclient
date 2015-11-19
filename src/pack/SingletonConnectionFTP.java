package pack;

import it.sauronsoftware.ftp4j.FTPClient;

public class SingletonConnectionFTP {

	private static FTPClient client;

	SingletonConnectionFTP() {

	}

	public static FTPClient getInstance() {
		if (null == client) {
			client = new FTPClient();
		}
		return client;
	}

}
