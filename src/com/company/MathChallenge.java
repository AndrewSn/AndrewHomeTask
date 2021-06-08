package com.company;

import com.company.exception.DivisionByZeroException;

import java.util.Stack;

public class MathChallenge {
    String mathChallengeWithBracket(String mathString) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mathString);
        StringBuffer resultString = new StringBuffer();
        Stack<Integer> indexStack = new Stack<>();
        int indexOfOpenBracket = 0;
        int indexOfCloseBracket = 0;
        while ((stringBuffer.indexOf("(") != -1)) {
            checkIsOperandBeforeBracket(stringBuffer);
            checkIsOperandAfterBracket(stringBuffer);
            for (int i = 0; i < stringBuffer.length(); i++) {
                if (stringBuffer.charAt(i) == '(') {
                    indexStack.push(i);
                    indexOfOpenBracket = indexStack.lastElement();
                }
                if (stringBuffer.charAt(i) == (')')) {
                    indexStack.remove(indexStack.lastElement());
                    indexOfCloseBracket = i;
                    stringBuffer.deleteCharAt(i);
                    stringBuffer.deleteCharAt(indexOfOpenBracket);

                    for (int j = indexOfOpenBracket; j < indexOfCloseBracket - 1; j++) {
                        resultString.append(stringBuffer.charAt(j));
                    }
                    try {
                        stringBuffer.replace(indexOfOpenBracket, indexOfCloseBracket - 1, mathChallengeWithOperand(resultString.toString()));
                    } catch (DivisionByZeroException e) {
                        return null;
                    }
                    i = 0;
                    resultString.setLength(0);
                    indexOfCloseBracket = 0;
                    indexOfOpenBracket = 0;
                }
            }
        }
        try {
            return mathChallengeWithOperand(stringBuffer.toString());
        } catch (DivisionByZeroException e) {
            return null;
        }
    }


    String mathChallengeWithOperand(String mathString) throws DivisionByZeroException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append(mathString);
        if (mathString.isEmpty()) {
            System.out.println("String is empty");
        }
        checkForDoubleMinus(stringBuffer);
        while (stringBuffer.indexOf("*") != -1) {
            operationMultiplication(stringBuffer);
        }
        checkForDoubleMinus(stringBuffer);
        while ((stringBuffer.indexOf("/") != -1)) {
            operationDivision(stringBuffer);
        }

        checkForDoubleMinus(stringBuffer);
        checkIsMinusAndPlus(stringBuffer);
        if (countOperandMinus(stringBuffer) > 1 || stringBuffer.charAt(0) != '-') {
            while (stringBuffer.indexOf("-") != -1 && !isNegativeNumber(stringBuffer)) {
                if (countOperandMinus(stringBuffer) > 1 || stringBuffer.charAt(0) != '-') {
                    operationMinus(stringBuffer);
                } else {
                    operationPlus(stringBuffer);
                }
            }
        }


        if (isPositiveNumber(stringBuffer)) {
            stringBuffer.deleteCharAt(0);
        }
        while ((stringBuffer.indexOf("+") != -1 && !isPositiveNumber(stringBuffer))) {
            operationPlus(stringBuffer);
        }
        return stringBuffer.toString();
    }

    Boolean isNegativeNumber(StringBuffer str) {
        if (str.charAt(0) == '-') {
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    Boolean isPositiveNumber(StringBuffer str) {
        if (str.charAt(0) == '+') {
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    StringBuffer checkIsOperandBeforeBracket(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '(' && i > 0 && Character.isDigit(stringBuffer.charAt(i - 1))) {
                stringBuffer.insert(i, '*');
            }
        }
        return stringBuffer;
    }

    StringBuffer checkIsOperandAfterBracket(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ')' && i + 1 < stringBuffer.length() && Character.isDigit(stringBuffer.charAt(i + 1)) ||
                    stringBuffer.charAt(i) == ')' && i + 1 < stringBuffer.length() && stringBuffer.charAt(i + 1) == '(') {
                stringBuffer.insert(i + 1, '*');
            }
        }
        return stringBuffer;
    }

    StringBuffer checkIsMinusAndPlus(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '+' && stringBuffer.charAt(i + 1) == '-' && i < stringBuffer.length() - 1) {
                stringBuffer.deleteCharAt(i);
            }
        }
        return stringBuffer;
    }
    StringBuffer checkForDoubleMinus(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '-' && stringBuffer.charAt(i + 1) == '-' && i < stringBuffer.length() - 1) {
                if (i == 0) {
                    stringBuffer.replace(i, i + 2, "");
                } else {
                    stringBuffer.replace(i, i + 2, "+");
                }
            }
        }
        return stringBuffer;
    }


    String operationPlus(StringBuffer string) {
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        int result = 0;
        int rightIndex = 0;
        int leftIndex = 0;
        stringBuffer1.setLength(0);
        stringBuffer2.setLength(0);
        if (string.charAt(0) == '-') {
            string.deleteCharAt(0);
            int index = string.indexOf("+");
            for (int i = 1; i < index + 1; i++) {
                if (index - i >= 0 && Character.isDigit(string.charAt(index - i))) {
                    stringBuffer1.append(string.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            int leftResult = Integer.parseInt(String.valueOf(stringBuffer1.reverse()));
            int endArray = string.length() - index;
            for (int i = 1; i < endArray; i++) {
                if (Character.isDigit(string.charAt(index + i))) {
                    stringBuffer2.append(string.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            int rightResult = Integer.parseInt(String.valueOf(stringBuffer2));
            result = rightResult - leftResult;
            string.replace(leftIndex, rightIndex, Integer.toString(result));
        } else {
            int index = string.indexOf("+");
            for (int i = 1; i < index + 1; i++) {
                if (index - i >= 0 && Character.isDigit(string.charAt(index - i))) {
                    stringBuffer1.append(string.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            int leftResult = Integer.parseInt(String.valueOf(stringBuffer1.reverse()));
            int endArray = string.length() - index;
            for (int i = 1; i < endArray; i++) {
                if (Character.isDigit(string.charAt(index + i))) {
                    stringBuffer2.append(string.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            int rightResult = Integer.parseInt(String.valueOf(stringBuffer2));


            result = leftResult + rightResult;
            string.replace(leftIndex, rightIndex, Integer.toString(result));
        }
        return string.toString();
    }

    void operationMinus(StringBuffer str) {
        int result = 0;
        int rightIndex = 0;
        int leftIndex = 0;
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer1.setLength(0);
        stringBuffer2.setLength(0);
        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            if (str.indexOf("+") != -1 && str.indexOf("+") < str.indexOf("-")) {
                int index = str.indexOf("+");
                for (int i = 1; i < index + 1; i++) {
                    if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                        stringBuffer1.append(str.charAt(index - i));
                        leftIndex = index - i;
                    } else break;
                }
                int leftResult = Integer.parseInt(String.valueOf(stringBuffer1.reverse()));
                int endArray = str.length() - index;
                for (int i = 1; i < endArray; i++) {
                    if (Character.isDigit(str.charAt(index + i))) {
                        stringBuffer2.append(str.charAt(index + i));
                        rightIndex = index + i + 1;
                    } else break;
                }
                int rightResult = Integer.parseInt(String.valueOf(stringBuffer2));
                result = (leftResult - rightResult) * -1;
                str.replace(leftIndex, rightIndex, Integer.toString(result));
            } else {
                int index = str.indexOf("-");
                for (int i = 1; i < index + 1; i++) {
                    if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                        stringBuffer1.append(str.charAt(index - i));
                        leftIndex = index - i;
                    } else break;
                }
                int leftResult = Integer.parseInt(String.valueOf(stringBuffer1.reverse()));
                int endArray = str.length() - index;
                for (int i = 1; i < endArray; i++) {
                    if (Character.isDigit(str.charAt(index + i))) {
                        stringBuffer2.append(str.charAt(index + i));
                        rightIndex = index + i + 1;
                    } else break;
                }
                int rightResult = Integer.parseInt(String.valueOf(stringBuffer2));
                result = (leftResult + rightResult) * -1;
                str.replace(leftIndex, rightIndex, Integer.toString(result));
            }
        } else {
            int index = str.indexOf("-");
            for (int i = 1; i < index + 1; i++) {
                if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                    stringBuffer1.append(str.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            int leftResult = Integer.parseInt(String.valueOf(stringBuffer1.reverse()));
            int endArray = str.length() - index;
            for (int i = 1; i < endArray; i++) {
                if (Character.isDigit(str.charAt(index + i))) {
                    stringBuffer2.append(str.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            int rightResult = Integer.parseInt(String.valueOf(stringBuffer2));
            result = leftResult - rightResult;
            str.replace(leftIndex, rightIndex, Integer.toString(result));
        }
    }

    void operationMultiplication(StringBuffer str) {
        int result = 0;
        int index = str.indexOf("*");
        int rightIndex = index;
        int leftIndex = index;
        while (leftIndex != 0 && (Character.isDigit(str.charAt(leftIndex - 1)) || (String.valueOf(str.charAt(leftIndex - 1)).equals("-")
                && (leftIndex - 1 == 0 || isAllowedSymbol(str.charAt(leftIndex - 2)))))) {
            leftIndex--;

        }
        Integer leftResult = Integer.parseInt(str.substring(leftIndex, index));

        while (rightIndex != str.length() - 1 && (Character.isDigit(str.charAt(rightIndex + 1)) || (rightIndex == index && String
                .valueOf(str.charAt(index + 1)).equals("-")))) {
            rightIndex++;
        }
        Integer rightResult = Integer.parseInt(str.substring(index + 1, rightIndex + 1));
        result = leftResult * rightResult;
        str.replace(leftIndex, rightIndex + 1, Integer.toString(result));
    }


    void operationDivision(StringBuffer str) throws DivisionByZeroException {
        int result = 0;
        int index = str.indexOf("/");
        int rightIndex = index;
        int leftIndex = index;
        while (leftIndex != 0 && (Character.isDigit(str.charAt(leftIndex - 1)) || (String.valueOf(str.charAt(leftIndex - 1)).equals("-")
                && (leftIndex - 1 == 0 || isAllowedSymbol(str.charAt(leftIndex - 2)))))) {
            leftIndex--;

        }
        Integer leftResult = Integer.parseInt(str.substring(leftIndex, index));

        while (rightIndex != str.length() - 1 && (Character.isDigit(str.charAt(rightIndex + 1)) || (rightIndex == index && String
                .valueOf(str.charAt(index + 1)).equals("-")))) {
            rightIndex++;
        }
        Integer rightResult = Integer.parseInt(str.substring(index + 1, rightIndex + 1));
        if (rightResult == 0) {
            throw new DivisionByZeroException("");
        }
        result = leftResult / rightResult;
        str.replace(leftIndex, rightIndex + 1, Integer.toString(result));
    }


    int checkIsCorrectString(String string) {
        int check = 2;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ')' && string.charAt(i) != '(' && !Character.isDigit(string.charAt(i)) && string.charAt(i + 1) != '(' && string.charAt(i + 1) != ')'
                    && !Character.isDigit(string.charAt(i + 1))) {
                check = 0;
                break;

            } else {
                check = 1;
            }
        }
        return check;
    }

    int countOperandMinus(StringBuffer stringBuffer) {
        int count = 0;
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '-') {
                count++;
            }
        }
        return count;
    }

    public static boolean isAllowedSymbol(char c) {
        String cString = String.valueOf(c);
        return cString.equals("-") || cString.equals("+") || cString.equals("/") || cString.equals("*");
    }

}
