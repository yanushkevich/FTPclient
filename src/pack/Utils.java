package pack;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class Utils {

	final static String serverName = "ftp.mccme.ru";
	final static String user = "anonymous";
	final static String pass = "123";
	final static String pathToSaveFile = "C:/Users/Anton_Yanushkevich@epam.com/Downloads/";
	
	static FTPClient client = SingletonConnectionFTP.getInstance();

	public static void connectFtpServer()
			throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
		client.connect(serverName);
		client.login(user, pass);
	}

	public static void downloadFile(String name) throws IllegalStateException, FileNotFoundException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
		client.download(name, new java.io.File(pathToSaveFile + name));
	}

	public static void showMenu() {
		System.out.println("First you should connect to server, by executing command 'connect'\n\n"
				+ "Input 'connect' to connect FTP server" + "\nInput 'download' to download a file"
				+ "\nInput 'up' to move in parent directory" + "\nInput 'move' to move to another directory"
				+ "\nInput 'help' to show menu" + "\nInput 'quite' to close programm");

	}

	public static void listDirFoldersAndFiles() throws IllegalStateException, IOException, FTPIllegalReplyException,
			FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException {

		String currentDirectory = client.currentDirectory();
		System.out.println("Current Working directory = " + currentDirectory);

		FTPFile[] list = client.list();

		for (FTPFile dir : list) {
			if (dir.getType() == 1) {
				System.out.println("directory is : " + dir.getName());
			} else {
				System.out.println("file is : " + dir.getName());
			}
		}
	}

	public static void changeDirectory(String dir) throws IllegalStateException, IOException, FTPIllegalReplyException,
			FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException {

		client.changeDirectory(dir);
		listDirFoldersAndFiles();

	}

	public static void directoryUp() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException,
			FTPDataTransferException, FTPAbortedException, FTPListParseException {

		client.changeDirectoryUp();
		listDirFoldersAndFiles();
	}

}
