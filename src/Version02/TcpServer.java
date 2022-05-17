package Version02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author Elgharbi
 */


public class TcpServer {

    private static String text;

    public static void main(String[] args){

    Socket socket = null;
    ServerSocket serverSocket = null;
    InputStreamReader inputStreamReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;


        try {
            serverSocket = new ServerSocket(7000);
            //Scanner sc = new Scanner();
            //int myport = sc.nextInt();
            // on envoie le donnee
            while (true) {

                try {



                    socket = serverSocket.accept();
                    System.out.println("aaa vous ete la !!! ");

                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());


                    bufferedReader = new BufferedReader(inputStreamReader);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);

                    while (true) {
                        String msgg = bufferedReader.readLine();
                        System.out.print("Alice :  ");
                        System.out.println("Recupree enctypted message de Bob >>>> :" + msgg);


                        //traitment

                        String message = Decryption(msgg, 5);
                        System.out.println(" jai bien comprnedre bob, vous  ete esyee de ma dit : " + message);


                        if (message.equalsIgnoreCase("by") || new Scanner(System.in).nextLine().equalsIgnoreCase("end"))
                        {
                            System.out.println("  *********************  bonjournee, By  ********************** ");
                            break;
                        }

                        Scanner sc = new Scanner(System.in);
                        System.out.print("Alice ... :  ");
                        String s =sc.nextLine();


                        bufferedWriter.write(s);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();


                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
                    socket.close();
                    serverSocket.close();
                    inputStreamReader.close();
                    outputStreamWriter.close();
                    bufferedWriter.close();
                    bufferedReader.close();



            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public static String Decryption(String ciphertext, int Key) {
        char[] cipher = ciphertext.toCharArray();

        System.out.print(" >>> le message est recoit");
        text="";
        for (char m : cipher) {
            m -= Key;
            text+=m;
        }
        return text;
    }
}



/*

// if we need more then one :

public int numclient=0;
public void accept_connexion() {
	try {
	ServerSocket ss=new ServerSocket(123);
	while(true){
	Socket s=ss.accept();
	System.out.println("Connexion reussie avec le client "+numclient++);
	new dateclient(s).start();
	}
	} catch (IOException e) {
		e.printStackTrace();
	}}
public class dateclient extends Thread
{ Socket socket;
	public dateclient(Socket s){
	this.socket=s;
}
	public void run(){
		OutputStream os;
		try {
			os = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os,true);
			pw.println("Bonjour, vous etes le client"+ " numero "+ " la date est "+ new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}}}



 */