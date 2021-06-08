package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String regex = "[0-9,+,-./,*,(,)]+";
        //String str = "-100-20-10+10-5+10";
        //String str = "-100-4*5-10+10*(-1)";
        //String str = "(-4)*5-10";
        //String str = "10+(-10)*(-5)";
        //String str = "-100/20+((20+40)*(10*2)4)";
        // String str = "(-(-1)*(2))-((2-10)/(1+3))";
        //String str = "10*(-10)";
        //String str = "-(-10)/10+10*10-10";
        //String str = "-154*45+43+4-554*293+545*(-4)+(-343)*5";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string");
        String str = sc.next();
        MathChallenge mathChallenge = new MathChallenge();
        boolean isValidate = str.matches(regex);
        if (isValidate && mathChallenge.checkIsCorrectString(str) == 1) {
            System.out.println(mathChallenge.mathChallengeWithBracket(str));
        } else System.out.println("Uncorrect string");
    }


}
