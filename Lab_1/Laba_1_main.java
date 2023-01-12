package Lab_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Laba_1_main {
    public static void main(String[] args)
    {
//        System.out.println(Add(""));
//        System.out.println(Add("0"));
//        System.out.println(Add("2,3,4,8"));
//
//        System.out.println("#####");
//
//        System.out.println(Add("2,3\n4,8"));
//        System.out.println(Add("1\n2\n3\n4"));
//        System.out.println(Add("0\n0"));
//
//        System.out.println("#####");
//
//        System.out.println(Add("//[;]\n2;3;4;8"));
//        System.out.println(Add("//[,]\n1,2,3,4"));
//        System.out.println(Add("//[']\n"));
//
//        System.out.println("#####");
//
//        System.out.println(Add("//[;]\n2;-3;4;-8"));
//        System.out.println(Add("//[,]\n1,-2,-3,4"));
//        System.out.println(Add("-3,-5,-6"));
//
//        System.out.println("#####");
//
//        System.out.println(Add("//[;]\n717;816;489"));
//        System.out.println(Add("//[,]\n999,1000,1001"));
//        System.out.println(Add("-1004,1004,502,502"));
//
//        System.out.println("#####");
//
//        System.out.println(Add("//[;][...]\n1;2;3...4...4"));
//        System.out.println(Add("//[,p][\n@]\n999,p1000\n@1001"));
//        System.out.println(Add("//[*]\n"));

//        System.out.println(Add("//[***][*]\n1*8***6*2"));
//        System.out.println(Add("//[1]\n61710"));
//        System.out.println(Add(""));
        System.out.println(Add("//[1][11][,,]\n212112,1001,,-5"));
    }

    public static int Add(String line)
    {
        int result = 0;

        String[] delimeters = new String[20];

        String[] list_numbers;

        String[] list_negative = new String[50];
        int count_negative = 0;

        if (line.length() == 0){return 0;}

        // If delimeter(-s) changing //

        if (line.charAt(0) == '/')
        {
            delimeters = WriteDelimeters(line);
            line = CutString(line);
            line = ChangeDelimetersInLine(line, delimeters); delimeters[0] = ",";
        }
        else
        {delimeters[0] = ","; delimeters[1] = "\n";}

        // If Line isn't empty //

        if (line.length() != 0)
        {
            list_numbers = LineDelimeter(line, delimeters);
            for (int i = 1; i < Integer.parseInt(list_numbers[0]); i ++)
            {
                if (Integer.parseInt(list_numbers[i]) < 0)
                {
                    list_negative [count_negative] = list_numbers[i];
                    count_negative ++;
                }
                if (Integer.parseInt(list_numbers[i]) <= 1000)
                {
                    result += Integer.parseInt(list_numbers[i]);
                }
            }
        }

        // If found negative number(-s) //

        if (count_negative > 0)
        {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < count_negative; i ++)
            {
                message.append(list_negative[i]).append(" ");
            }
            System.out.println("Negatives not allowed : " + message);
            // System.out.print("But result = ");
        }

        return result;
    }
    public static String[] LineDelimeter(String line, String[] delimeter)
    {
        String[] result = new String[101];
        int int_number = 1;
        int start = 0;
        for (int i = 0; i < line.length(); i++)
        {
            if (IsThisStrInMassStr(String.valueOf(line.charAt(i)), delimeter))
            {
                result[int_number] = line.substring(start, i);
                int_number ++;
                start = i + 1;
            }
        }
        result[int_number] = line.substring(start);
        int_number ++;
        result[0] = Integer.toString(int_number);

        return result;
    }
    public static String CutString(String line)
    {
        int mark = 0;
        for (int i = 1; i < line.length(); i ++)
        {
            if (line.charAt(i) == '\n' & line.charAt(i - 1) == ']')
            {
                mark = i;
                break;
            }
        }

        return line.substring(mark + 1);
    }
    public static boolean IsThisStrInMassStr(String symbol, String[] delimeters)
    {
        boolean result = false;
        for (String c : delimeters)
        {if (Objects.equals(symbol, c)) {result = true; break;}}

        return result;
    }
    public static String[] WriteDelimeters(String line)
    {
        String[] result = new String[20];
        int count = 0;
        int start = 0;
        for (int i = 1; i < line.length(); i ++)
        {
            if (line.charAt(i) == '[')
            {start = i + 1;}
            else if (line.charAt(i) == ']')
            {result[count] = line.substring(start, i); count ++;}
            else if (line.charAt(i) == '\n' & line.charAt(i - 1) == ']')
            {break;}
        }
        Arrays.sort(result,0, count, Collections.reverseOrder());
        return result;
    }
    public static String ChangeDelimetersInLine(String line, String[] delimeters)
    {
        boolean smth_changes;
        int len_str;
        for (String str : delimeters)
        {
            smth_changes = true;
            while (smth_changes)
            {
                smth_changes = false;
                if (str == null){break;} else if (str.equals(",")){break;}
                len_str = str.length();
                for (int i = 0; i < line.length() - len_str + 1; i++) {
                    if (line.substring(i, i + len_str).equals(str)) {
                        line = line.substring(0, i) + "," + line.substring(i + len_str);
                        smth_changes = true;
                        break;
                    }
                }
            }
        }

        return line;
    }
}
