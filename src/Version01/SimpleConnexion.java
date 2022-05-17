package Version01;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Elgharbi
 */

public class SimpleConnexion {
    private final String name;
    private int Key = 5;
    private String ciphertext, text;

    public String getName() {
        return name;
    }

    SimpleConnexion(String name) {
        this.name = name;
        //this.Key=Key;

    }


    public String Encryption(Scanner input) throws RemoteException {

        System.out.print(name + " ... : ");
        String message = input.nextLine();

        char[] msg = message.toCharArray();
        ciphertext="";
        for (char c : msg) {
            c += Key;
            ciphertext += c;
        }

        return ciphertext;

    }

    public String Decryption (String ciphertext) throws RemoteException {
        char[] cipher = ciphertext.toCharArray();

        System.out.print(" >>>le message est reçoit ");
        text="";
        for (char m : cipher) {
            m -= Key;
            text+=m;
        }

        return text;
    }


}


