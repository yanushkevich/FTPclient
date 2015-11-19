package pack;

import java.io.IOException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class FTPclient {

	public static void main(String[] args) throws IllegalStateException, IOException, FTPIllegalReplyException,
			FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException {
		// TODO Auto-generated method stub

		Utils.showMenu();

		Scanner input = new Scanner(System.in);
		boolean quite = true;

		do {
			String command = input.nextLine();

			switch (command) {
			case "help":
				Utils.showMenu();
				break;

			case "connect":
				try {
					Utils.connectFtpServer();
					Utils.listDirFoldersAndFiles();
				} catch (Exception e) {
					System.out.println("Please, try again, problems with connection to FTP server");
				}
				break;

			case "up":
				Utils.directoryUp();
				break;

			case "download":
				System.out.println("Enter the file name");
				String fileName = input.nextLine();
				try {
					Utils.downloadFile(fileName);
				} catch (Exception e) {
					System.out.println("Please, try again, no such filename");
				}
				break;

			case "move":
				System.out.println("Enter the directory");
				String dir = input.nextLine();
				try {
					Utils.changeDirectory(dir);
				} catch (Exception e) {
					System.out.println("Please, try again, no such directory");
				}
				break;

			case "quite":
				System.out.println("Programm is closed");
				System.exit(0);
				break;

			default:
				System.out.println("Please, make your choise");

			}

		} while (quite);

	}

}
