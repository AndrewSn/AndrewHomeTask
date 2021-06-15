package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String regex = "[0-9,+,-./,*,(,)]+";
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
