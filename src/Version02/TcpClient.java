package Version02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Elgharbi
 */

public class TcpClient {

    private static String ciphertext;



    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        //{
        try {

                Scanner sc = new Scanner(System.in);
                int myport = 0;

           while (myport !=7000){

               System.out.print("Entrée  le numéro de port  : ");
               myport = sc.nextInt();
               if ((49151 < myport) && (myport <= 65535)) {
                   myport = 7000;
                   // puisque jai utilise la même machine
                   socket = new Socket("localhost", myport);
                   System.out.println(" *************** Bienvenue ****************** ");




               } else if (1023 < myport && myport <= 49152) {
                   System.out.println("sont appelés «ports enregistrés» ");

               } else if (0 < myport && myport <= 1023) {
                   System.out.println("0 à 1023 sont les «ports reconnus» ");

               } else {
                   System.out.println("demande une autres ports ? ");
               }

           }


           // pour le flux de communication entrant et sortnet

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

//ecriture el la lecture au niveau du buffer
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.print("  Entrée  le cle pour lancer la commuinication : ");
            int Key = sc.nextInt();

            if (Key == 5) {
                System.out.println("      <<<<  Le cle est valide  >>>> ");
                while (true) {

                    System.out.print("Bob ... :  ");
                    String encrypted = Encryption(new Scanner(System.in), Key);
                    bufferedWriter.write(encrypted);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    // from alice :
                    System.out.println("Alice : " + bufferedReader.readLine());


                }

            }else {
                System.out.println("      X  -  le cle nest pas valide   -  X ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();  //fermer la socket ainsi la communication s’arrete
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (outputStreamWriter != null)
                    outputStreamWriter.close();
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (bufferedReader != null)
                    bufferedReader.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
        public static String Encryption(Scanner input, int Key) throws RemoteException {

            //System.out.println( name + " >>> est crée un message : ");
            String message = input.nextLine();

            char[] msg = message.toCharArray();
            ciphertext="";
            for (char c : msg) {

                c += Key;
                ciphertext += c;
            }

            return ciphertext;



    }

//public String encrypt

}


