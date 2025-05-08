package Scheduler;

import java.util.Scanner;

public class emailGenerate{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder outStr= new StringBuilder();
        String firstName = sc.nextLine();
        String lastName = sc.nextLine();
        String passKey = sc.nextLine();
        sc.close();

        if ( !firstName.equalsIgnoreCase(lastName) && firstName.length() >4
                && lastName.length() >4 && passKey !=null){
            outStr.append(firstName.substring(0,4).toLowerCase())
                    .append(".")
                    .append(lastName.substring(lastName.length()-4).toLowerCase())
                    .append(passKey)
                    .append("@swiftfood.com");
        }else outStr.append("Invalid");
        System.out.printf(String.valueOf(outStr));

    }

//    ***********************************************************************************************
    
    /*"C:\Program Files\Java\jdk1.8.0_281\bin\java.exe"
        Connected to the target VM, address: '127.0.0.1:54307', transport: 'socket'
        mumtaz
        baano
        75
        mumt.aano75@swiftfood.com
        Disconnected from the target VM, address: '127.0.0.1:54307', transport: 'socket'
        
        Process finished with exit code 0
*/
}
class passwrodValid{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String inStr = scanner.nextLine();
            String outStr;

            if (inStr.length() >= 8) {
                boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false, hasConsecutive = false, hasRepetition = false;

                for (int i = 0; i < inStr.length(); i++) {
                    char c = inStr.charAt(i);
                    if (Character.isUpperCase(c)) hasUpper = true;
                    else if (Character.isLowerCase(c)) hasLower = true;
                    else if (Character.isDigit(c)) hasDigit = true;
                    else hasSpecial = true;

                    if (i > 0 && c == inStr.charAt(i - 1)) hasConsecutive = true;
                    if (inStr.indexOf(c) != i) hasRepetition = true;
                }
                System.out.println("hee "+hasConsecutive);
                System.out.println("heeR "+hasRepetition);

                if (hasUpper && hasLower && hasDigit && hasSpecial && !hasConsecutive) {
                    outStr = "Strong";
                } else if (hasUpper && hasDigit && hasSpecial && !hasRepetition) {
                    outStr = "Partially Strong";
                } else if (inStr.matches("[a-zA-Z0-9]+")) {
                    outStr = "Weak";
                } else {
                    outStr = "Invalid";
                }
            } else {
                outStr = "Invalid";
            }

            System.out.println(outStr);
            scanner.close();
        }
    }
//    ***********************************************************************************************

    class compressConsecutiveCharacters{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inStr = scanner.nextLine();
        StringBuilder outStr = new StringBuilder();

        int count = 1;
        for (int i = 1; i <= inStr.length(); i++) {
            if (i < inStr.length() && inStr.charAt(i) == inStr.charAt(i - 1)) {
                count++;
            } else {
                outStr.append(inStr.charAt(i - 1));
                if (count > 1) {
                    outStr.append(count);
                }
                count = 1;
            }
        }

        System.out.println(outStr.toString());
        scanner.close();
    }
}

class removeDuplicateChar{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inStr1 = scanner.nextLine();
        String inStr = inStr1.replace(" ","");

        StringBuilder outStr = new StringBuilder(inStr.length());
        boolean[] seen = new boolean[256];

        // Traverse through all characters
        for (int i = 0; i < inStr.length(); i++) {
            char c = inStr.charAt(i);

            if (!seen[c] || !seen[c]) {
                outStr.append(c);
                seen[c] = true;
            }
        }
        System.out.println(outStr);
    }
}