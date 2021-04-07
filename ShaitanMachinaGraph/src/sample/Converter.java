package sample;

public class Converter {

    public static String binaryScrambler(String s) {
        StringBuilder result = new StringBuilder();
        boolean a;
        boolean b;
        boolean c;
        result.append(s.substring(0,3));
        a = s.charAt(3) == '1';
        b = result.substring(0,1).equals("1");
        result.append((a^b)?"1":"0");
        a = s.charAt(4) == '1';
        b = result.charAt(1) == '1';
        result.append((a^b)?"1":"0");
        for (int i = 5; i < s.length(); i++) {
            a = s.charAt(i)== '1';
            b = result.charAt(i-3)== '1';
            c = result.charAt(i-5) == '1';
            result.append((a^b^c)?"1":"0");
        }
        return result.toString();
    }

    public static String stringSplit(String s, int split) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i += split) {
            result.append(s.substring(i, i + split) + " ");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static String stringToBinary(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ' || c == '.') {
                result.append("00" + Integer.toBinaryString(c));
            } else if ((int)c < 1088){
                result.append(Integer.toBinaryString(c - 912)); //912
            } else {
                result.append(Integer.toBinaryString(c - 864)); //912
            }
        }
        return result.toString();
    }

    public static String quadBinaryToPent(String s) {
        StringBuilder result = new StringBuilder();
        String replacer;
        for (int i = 0; i < s.length(); i += 4) {
            replacer = s.substring(i, i + 4);
            switch (replacer) {
                case "0000":
                    replacer = "11110";
                    break;
                case "0001":
                    replacer = "01001";
                    break;
                case "0010":
                    replacer = "10100";
                    break;
                case "0011":
                    replacer = "10101";
                    break;
                case "0100":
                    replacer = "01010";
                    break;
                case "0101":
                    replacer = "01011";
                    break;
                case "0110":
                    replacer = "01110";
                    break;
                case "0111":
                    replacer = "01111";
                    break;
                case "1000":
                    replacer = "10010";
                    break;
                case "1001":
                    replacer = "10011";
                    break;
                case "1010":
                    replacer = "10110";
                    break;
                case "1011":
                    replacer = "10111";
                    break;
                case "1100":
                    replacer = "11010";
                    break;
                case "1101":
                    replacer = "11011";
                    break;
                case "1110":
                    replacer = "11100";
                    break;
                case "1111":
                    replacer = "11101";
                    break;
            }
            result.append(replacer);
        }
        return result.toString();
    }

}
