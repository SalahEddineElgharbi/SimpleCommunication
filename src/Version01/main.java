package Version01;

import Version01.SimpleConnexion;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        SimpleConnexion Bob = new SimpleConnexion("bob");
        SimpleConnexion Alice = new SimpleConnexion("Alice");


        SimpleConnexion current = Bob;
        while (true){
            try {

                String encrypted = current.Encryption(input);
                System.out.println("\n  après chiffrer le message :  " + encrypted);
                System.out.println(" ****   le message est envoyee   **** ");
                System.out.println( " " + (current.equals(Bob) ? Alice.getName() : Bob.getName()) + " \n  j'ai bien compris, vous été essayée de me dit : : " + current.Decryption(encrypted));


                if (current.equals(Bob))
                    current = Alice;

                else
                    current = Bob;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
