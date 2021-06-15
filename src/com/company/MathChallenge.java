package com.company;

import com.company.exception.DivisionByZeroException;

import java.util.Stack;

public class MathChallenge {
    //method for calculate string with bracket
    public String mathChallengeWithBracket(String mathString) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mathString);
        StringBuffer resultString = new StringBuffer();
        //create stack for save our open bracket
        Stack<Integer> indexStack = new Stack<>();
        //create variable for save index of open nd close brackets
        int indexOfOpenBracket = 0;
        int indexOfCloseBracket = 0;
        while ((stringBuffer.indexOf("(") != -1)) {
            //we check whether it is necessary to put multiplication signs between brackets
            checkIsOperandBeforeBracket(stringBuffer);
            checkIsOperandAfterBracket(stringBuffer);
            for (int i = 0; i < stringBuffer.length(); i++) {
                //if we search open bracket i push her indexes  to stack and set indexOfOpenBracket by last element in stack
                if (stringBuffer.charAt(i) == '(') {
                    indexStack.push(i);
                    indexOfOpenBracket = indexStack.lastElement();
                }
                //if i search close bracket i delete from stack last element, set index of close bracket by i
                if (stringBuffer.charAt(i) == (')')) {
                    indexStack.remove(indexStack.lastElement());
                    indexOfCloseBracket = i;
                    //delete our open and close bracket
                    stringBuffer.deleteCharAt(i);
                    stringBuffer.deleteCharAt(indexOfOpenBracket);

                    //i write in new string our expression from our bracket
                    for (int j = indexOfOpenBracket; j < indexOfCloseBracket - 1; j++) {
                        resultString.append(stringBuffer.charAt(j));
                    }
                    try {
                        //replace our string on our indexes with result
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

    //function for caclulate string that doesnt contain brackets
    private String mathChallengeWithOperand(String mathString) throws DivisionByZeroException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        /*we add our string to stringBuffer*/
        stringBuffer.append(mathString);
        //check if our string contain double minus if contain we replace it with plus
        checkForDoubleMinus(stringBuffer);
        while (stringBuffer.indexOf("*") != -1) {
            /*if our string contain multiplication we call operationMultiplication*/
            operationMultiplication(stringBuffer);
        }

        //check if our string contain double minus  we replace it with plus
        checkForDoubleMinus(stringBuffer);
        while ((stringBuffer.indexOf("/") != -1)) {
            /*if our string contain division we call operationDivision*/
            operationDivision(stringBuffer);
        }

        //check if our string contain double minus  we replace it with plus
        checkForDoubleMinus(stringBuffer);
        //check if our string contain minus and plus in a row  we replace it with plus
        checkIsMinusAndPlus(stringBuffer);
        /*
         * we count minus in our string,  if count = 1 and first numeric is negative and we have operation plus we must to go to next step because we will have a loop
         * */
        if (countOperandMinus(stringBuffer) > 1 || stringBuffer.charAt(0) != '-') {
            while (stringBuffer.indexOf("-") != -1 && !isNegativeNumber(stringBuffer)) {
                if (countOperandMinus(stringBuffer) > 1 || stringBuffer.charAt(0) != '-') {
                    operationMinus(stringBuffer);
                } else {
                    operationPlus(stringBuffer);
                }
            }
        }

        /*
         * we check if our string is positive numeric and delete plus so that our results looks live ("2") not ("+2")*/
        if (isPositiveNumber(stringBuffer)) {
            stringBuffer.deleteCharAt(0);
        }
        while ((stringBuffer.indexOf("+") != -1 && !isPositiveNumber(stringBuffer))) {
            operationPlus(stringBuffer);
        }
        return stringBuffer.toString();
    }

    /*
     * this function checks if our string contain one numeric and this numeric is negative is positive, like this "-1"
     * */
    private Boolean isNegativeNumber(StringBuffer str) {
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

    /*
     * this function checks if our string contain one numeric and this numeric is positive is positive, like this "+1";
     * */
    private Boolean isPositiveNumber(StringBuffer str) {
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

    /*
     * this function checks if we have a * before bracket, if we not have our function will perform such an action:
     * 5(5+1) -> 5*(3+1);
     * */
    private StringBuffer checkIsOperandBeforeBracket(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '(' && i > 0 && Character.isDigit(stringBuffer.charAt(i - 1))) {
                stringBuffer.insert(i, '*');
            }
        }
        return stringBuffer;
    }

    /*
     * this function checks if we have a * after bracket, if we not have our function will perform such an action:
     * (5+1)5 -> (5+1)*5;
     * */
    private StringBuffer checkIsOperandAfterBracket(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ')' && i + 1 < stringBuffer.length() && Character.isDigit(stringBuffer.charAt(i + 1)) ||
                    stringBuffer.charAt(i) == ')' && i + 1 < stringBuffer.length() && stringBuffer.charAt(i + 1) == '(') {
                stringBuffer.insert(i + 1, '*');
            }
        }
        return stringBuffer;
    }

    /*
     * this function checks if we have minus and plus in a row and turns it into a minus
     * */
    private StringBuffer checkIsMinusAndPlus(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '+' && stringBuffer.charAt(i + 1) == '-' && i < stringBuffer.length() - 1) {
                stringBuffer.deleteCharAt(i);
            }
        }
        return stringBuffer;
    }

    /*
     * this function checks if we have two minuses in a row and turns it into a plus
     * */
    private StringBuffer checkForDoubleMinus(StringBuffer stringBuffer) {
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


    private void operationPlus(StringBuffer string) {
        StringBuffer rightNumeric = new StringBuffer();
        StringBuffer leftNumeric = new StringBuffer();
        Integer result;
        int rightIndex = 0;
        int leftIndex = 0;
        /*
         * if first char at string is minus so our first numeric is negative
         * we delete first char - minus, search left and right numeric and subtract a right numeric from left numeric;
         * and replace our string with our result by our left and right indices;
         * */
        if (string.charAt(0) == '-') {
            string.deleteCharAt(0);
            int index = string.indexOf("+");
            for (int i = 1; i < index + 1; i++) {
                //check if we are within the array and check if next element is digit
                if (index - i >= 0 && Character.isDigit(string.charAt(index - i))) {
                    //add this digit to right numeric
                    leftNumeric.append(string.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            int leftResult = Integer.parseInt(String.valueOf(leftNumeric.reverse()));
            int endArray = string.length() - index;
            for (int i = 1; i < endArray; i++) {
                // check if next element is digit
                if (Character.isDigit(string.charAt(index + i))) {
                    //add this digit to left numeric
                    rightNumeric.append(string.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            int rightResult = Integer.parseInt(String.valueOf(rightNumeric));
            //calculate result
            result = rightResult - leftResult;
            //replace string by our result
            string.replace(leftIndex, rightIndex, Integer.toString(result));
        }
        /*
         * if our numeric in string is positive, we search left numeric and right
         * then we add left and right numerics
         * and replace our string with our result by our left and right indices;
         * */

        else {
            int index = string.indexOf("+");
            for (int i = 1; i < index + 1; i++) {
                //check if next element is digit
                if (index - i >= 0 && Character.isDigit(string.charAt(index - i))) {
                    //add this digit to left nimeric
                    leftNumeric.append(string.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            Integer leftResult = Integer.parseInt(String.valueOf(leftNumeric.reverse()));
            for (int i = 1; i < string.length() - index; i++) {
                //check if next element is digit
                if (Character.isDigit(string.charAt(index + i))) {
                    //add thsi digit to right numeric
                    rightNumeric.append(string.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            Integer rightResult = Integer.parseInt(String.valueOf(rightNumeric));

            //calculate result
            result = leftResult + rightResult;
            //replace string by result
            string.replace(leftIndex, rightIndex, Integer.toString(result));
        }

    }

    private void operationMinus(StringBuffer str) {
        Integer result;
        int rightIndex = 0;
        int leftIndex = 0;
        StringBuffer rightNumeric = new StringBuffer();
        StringBuffer leftNumeric = new StringBuffer();
        /*
         * if in our string first char is minus and next operation is plus
         * i delete first char check         * */
        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            //check if next operation after our negative numeric is plus
            // if it plus i search index of plus then search left and right numerics then i
            //subtract the right result from left result and multiply by -1 and replace our string
            if (str.indexOf("+") != -1 && str.indexOf("+") < str.indexOf("-")) {
                int index = str.indexOf("+");
                for (int i = 1; i < index + 1; i++) {
                    //check if next element is digit
                    if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                        //add this digit to left numeric
                        leftNumeric.append(str.charAt(index - i));
                        leftIndex = index - i;
                    } else break;
                }
                Integer leftResult = Integer.parseInt(String.valueOf(leftNumeric.reverse()));
                int endArray = str.length() - index;
                for (int i = 1; i < endArray; i++) {
                    //check if next element is digit
                    if (Character.isDigit(str.charAt(index + i))) {
                        //add thsi digit to right numeric
                        rightNumeric.append(str.charAt(index + i));
                        rightIndex = index + i + 1;
                    } else break;
                }
                Integer rightResult = Integer.parseInt(String.valueOf(rightNumeric));
                //calculate result
                result = (leftResult - rightResult) * -1;
                //replace our string
                str.replace(leftIndex, rightIndex, Integer.toString(result));
            }
            /*
             * if next operation is minus i search right and left result
             * add them and multiply by -1
             * replace string by left and right index
             * */

            else {
                int index = str.indexOf("-");
                for (int i = 1; i < index + 1; i++) {
                    //check if next element is digit
                    if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                        //add this digit to left numeric
                        leftNumeric.append(str.charAt(index - i));
                        leftIndex = index - i;
                    } else break;
                }
                Integer leftResult = Integer.parseInt(String.valueOf(leftNumeric.reverse()));
                int endArray = str.length() - index;
                for (int i = 1; i < endArray; i++) {
                    //check if next element is digit
                    if (Character.isDigit(str.charAt(index + i))) {
                        //add this digit to right numeric
                        rightNumeric.append(str.charAt(index + i));
                        rightIndex = index + i + 1;
                    } else break;
                }
                Integer rightResult = Integer.parseInt(String.valueOf(rightNumeric));
                //calculte result
                result = (leftResult + rightResult) * -1;
                //replace our string
                str.replace(leftIndex, rightIndex, Integer.toString(result));
            }
        }
        /*
         * if first character not minus
         * i search index of minus, left and right result
         * replace string by index of our left and right result
         * */
        else {
            int index = str.indexOf("-");
            for (int i = 1; i < index + 1; i++) {
                //check if next element is digit
                if (index - i >= 0 && Character.isDigit(str.charAt(index - i))) {
                    //add this digit to left numeric
                    leftNumeric.append(str.charAt(index - i));
                    leftIndex = index - i;
                } else break;
            }
            Integer leftResult = Integer.parseInt(String.valueOf(leftNumeric.reverse()));
            int endArray = str.length() - index;
            for (int i = 1; i < endArray; i++) {
                //check if next element is digit
                if (Character.isDigit(str.charAt(index + i))) {
                    //add this digit to right numeric
                    rightNumeric.append(str.charAt(index + i));
                    rightIndex = index + i + 1;
                } else break;
            }
            Integer rightResult = Integer.parseInt(String.valueOf(rightNumeric));
            result = leftResult - rightResult;
            str.replace(leftIndex, rightIndex, Integer.toString(result));
        }
    }

    private void operationMultiplication(StringBuffer str) {
        Integer result = 0;
        int index = str.indexOf("*");
        int rightIndex = index;
        int leftIndex = index;
        //search our left result
        while (leftIndex != 0 && (Character.isDigit(str.charAt(leftIndex - 1)) || (String.valueOf(str.charAt(leftIndex - 1)).equals("-")
                && (leftIndex - 1 == 0 || isAllowedSymbol(str.charAt(leftIndex - 2)))))) {
            leftIndex--;

        }
        Integer leftResult = Integer.parseInt(str.substring(leftIndex, index));

        //search our right result
        while (rightIndex != str.length() - 1 && (Character.isDigit(str.charAt(rightIndex + 1)) || (rightIndex == index && String
                .valueOf(str.charAt(index + 1)).equals("-")))) {
            rightIndex++;
        }
        Integer rightResult = Integer.parseInt(str.substring(index + 1, rightIndex + 1));
        //calculate our result
        result = leftResult * rightResult;
        //replace our string
        str.replace(leftIndex, rightIndex + 1, Integer.toString(result));
    }


    private void operationDivision(StringBuffer str) throws DivisionByZeroException {
        Integer result = 0;
        int index = str.indexOf("/");
        int rightIndex = index;
        int leftIndex = index;
        /*
         * search our left numeric
         *
         * */
        while (leftIndex != 0 && (Character.isDigit(str.charAt(leftIndex - 1)) || (String.valueOf(str.charAt(leftIndex - 1)).equals("-")
                && (leftIndex - 1 == 0 || isAllowedSymbol(str.charAt(leftIndex - 2)))))) {
            leftIndex--;

        }
        Integer leftResult = Integer.parseInt(str.substring(leftIndex, index));

        /*
         * search our right numeric
         * */
        while (rightIndex != str.length() - 1 && (Character.isDigit(str.charAt(rightIndex + 1)) || (rightIndex == index && String
                .valueOf(str.charAt(index + 1)).equals("-")))) {
            rightIndex++;
        }
        Integer rightResult = Integer.parseInt(str.substring(index + 1, rightIndex + 1));
        //check situation of division by zero
        if (rightResult == 0) {
            throw new DivisionByZeroException("");
        }
        //calculate our result
        result = leftResult / rightResult;
        //replace our string by result
        str.replace(leftIndex, rightIndex + 1, Integer.toString(result));
    }

    /*
    this function checks if the string we entered is correct;
    this function checks that the string has no double characters of operations;
    uncorrect string: "++1*2-3""
    * */
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

    /*
     * the function counts how many minuses in string
     * */
    private int countOperandMinus(StringBuffer stringBuffer) {
        int count = 0;
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '-') {
                count++;
            }
        }
        return count;
    }

    /*
     * function return true if our character is + or - or / or *
     * */
    private static boolean isAllowedSymbol(char c) {
        String cString = String.valueOf(c);
        return cString.equals("-") || cString.equals("+") || cString.equals("/") || cString.equals("*");
    }

}
