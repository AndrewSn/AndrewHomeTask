package com.company.test;


import com.company.MathChallenge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CalculatorTest {

    @Test
    public void calculateStringWithTwoPositiveNumericByPlus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "21+1090";
        String str1 = "1050+10110";
        String str2 = "12+6";
        String str3 = "100654+123456";
        String str4 = "351+123";
        assertEquals("1111", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("11160", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("18", mathChallenge.mathChallengeWithBracket(str2));
        assertEquals("224110", mathChallenge.mathChallengeWithBracket(str3));
        assertEquals("474", mathChallenge.mathChallengeWithBracket(str4));
    }

    @Test
    public void calculateStringWithOnePositiveNumericByPlus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10+20";
        String str1 = "10+(-20)";
        String str2 = "-1153+355";
        String str3 = "25461+(-11562)";
        assertEquals("-798", mathChallenge.mathChallengeWithBracket(str2));
        assertEquals("10", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-10", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("13899", mathChallenge.mathChallengeWithBracket(str3));
    }

    @Test
    public void calculateStringWithTwoNegativeNumericByPlus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10+(-20)";
        String str2 = "-1153+(-355)";
        String str3 = "-25461+(-12562)";
        assertEquals("-1508", mathChallenge.mathChallengeWithBracket(str2));
        assertEquals("-30", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-38023", mathChallenge.mathChallengeWithBracket(str3));
    }

    @Test
    public void calculateStringWithTwoPositiveNumericByMinus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "10-20";
        String str2 = "1153-355";
        String str3 = "25461-12562";
        assertEquals("-10", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("798", mathChallenge.mathChallengeWithBracket(str2));
        assertEquals("12899", mathChallenge.mathChallengeWithBracket(str3));
    }

    @Test
    public void calculateStringWithOnePositiveNumericByMinus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10-20";
        String str1 = "(-1153)-355";
        String str2 = "26461-(-11562)";
        assertEquals("-30", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-1508", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("38023", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithTwoNegativeNumericByMinus() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10-(-20)";
        String str1 = "(-1153)-(-355)";
        String str2 = "-26461-(-11562)";
        assertEquals("10", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-798", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("-14899", mathChallenge.mathChallengeWithBracket(str2));
    }


    @Test
    public void calculateStringWithTwoPositiveNumericByMultiplication() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "10*20";
        String str1 = "1153*355";
        String str2 = "26461*11562";
        assertEquals("200", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("409315", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("305942082", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithOnePositiveNumericByMultiplication() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10*20";
        String str1 = "1153*(-355)";
        String str2 = "-26461*11562";
        assertEquals("-200", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-409315", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("-305942082", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithTwoNegativeNumericByMultiplication() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-10*(-20)";
        String str1 = "-1153*(-355)";
        String str2 = "-26461*(-11562)";
        assertEquals("200", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("409315", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("305942082", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithTwoPositiveNumericByDivision() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "20/10";
        String str1 = "409315/355";
        String str2 = "305942082/11562";
        assertEquals("2", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("1153", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("26461", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithOnePositiveNumericByDivision() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "20/(-10)";
        String str1 = "-409315/355";
        String str2 = "305942082/(-11562)";
        assertEquals("-2", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-1153", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("-26461", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithTwoNegativeNumericByDivision() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-20/(-10)";
        String str1 = "-409315/(-355)";
        String str2 = "-305942082/(-11562)";
        assertEquals("2", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("1153", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("26461", mathChallenge.mathChallengeWithBracket(str2));
    }

    @Test
    public void calculateStringWithDifferentsNumericAndOperation() {
        MathChallenge mathChallenge = new MathChallenge();
        String str = "-100-20-10+10-5+10";
        String str1 = "-100-4*5-10+10*(-1)";
        String str2 = "(-4)*5-10";
        String str3 = "10+(-10)*(-5)";
        String str4 = "-100/20+((20+40)*(10*2))*(-4)";
        String str5 = "(-(-1)*(2))-((2-10)/(1+3))";
        String str6 = "10*(-10)-501+155";
        String str7 = "-(-10)/10+10*10-10";
        String str8 = "-154*45+43+4-554*293+545*(-4)+(-343)*5";
        assertEquals("-115", mathChallenge.mathChallengeWithBracket(str));
        assertEquals("-140", mathChallenge.mathChallengeWithBracket(str1));
        assertEquals("-30", mathChallenge.mathChallengeWithBracket(str2));
        assertEquals("60", mathChallenge.mathChallengeWithBracket(str3));
        assertEquals("-4805", mathChallenge.mathChallengeWithBracket(str4));
        assertEquals("4", mathChallenge.mathChallengeWithBracket(str5));
        assertEquals("-446", mathChallenge.mathChallengeWithBracket(str6));
        assertEquals("91", mathChallenge.mathChallengeWithBracket(str7));
        assertEquals("-173100", mathChallenge.mathChallengeWithBracket(str8));
    }

}
