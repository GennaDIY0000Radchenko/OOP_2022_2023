package Lab_4;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;

import java.io.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import org.json.simple.parser.ParseException;
import java.nio.file.Paths;

import java.util.*;

public class Lab_4_main {
    public static void main (String [] args) {
        String path = "C:/Users/админ/IdeaProjects/first_java/src/Lab_4/export_json.json";
        int id;
        ArrayList<ArrayList<Double>> ret;

        while (true) {
            int choice = make_choice(new String[]{"history", "example", "change path", "exit"});
            if (choice == 0) {
                history(path);
                choose_from_history(path);
            } else if (choice == 1) {
                id = example(path);
                choice = make_choice(new String[]{"main", "full solve"});
                if (choice == 1) {
                    ret = get_from_history(id, path);
                    full_solve_print(ret.get(0), ret.get(1));
                }
            } else if (choice == 2) {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter new path : ");
                path = in.nextLine();
            } else if (choice == 3) {
                System.exit(0);
            }
        }
    }

    public static void print_mn(ArrayList<Double> list) {
        Double[] l = list.toArray(new Double[0]);
        for (int i = 0; i < l.length; i++) {
            if (l[i] != 0) {
                System.out.print(l[i]);
                if (l.length - i - 1 > 1) {
                    System.out.printf("*x^%d ", l.length - i - 1);
                } else if (l.length - i - 1 == 1) {
                    System.out.print("*x ");
                } else {
                    System.out.print("");
                }
                if (i + 1 < l.length) {
                    System.out.print("+ ");
                }
            }
        }
        System.out.println();
    }


    public static void print_mn(Double[] l) {
        for (int i = 0; i < l.length; i++) {
            if (l[i] != 0) {
                System.out.print(l[i]);
                if (l.length - i - 1 > 1) {
                    System.out.printf("*x^%d ", l.length - i - 1);
                } else if (l.length - i - 1 == 1) {
                    System.out.print("*x ");
                } else {
                    System.out.print("");
                }
                if (i + 1 < l.length) {
                    System.out.print("+ ");
                }
            }
        }
        System.out.println();
    }


    public static void print_mn_num(Double[] l, Double num, Integer power) {
        for (int i = 0; i < l.length; i++) {
            if (l[i] != 0) {
                System.out.print(l[i] * num);
                if (power - i - 1 > 1) {
                    System.out.printf("*x^%d ", power - i - 1);
                } else if (power - i - 1 == 1) {
                    System.out.print("*x ");
                } else {
                    System.out.print("");
                }
                if (i + 1 < l.length) {
                    System.out.print("+ ");
                }
            }
        }
        System.out.println();
    }


    public static Integer save(ArrayList<Double> f, ArrayList<Double> s, ArrayList<Double> r, ArrayList<Double> o, String path) {
        int id = 1;
        ArrayList<String> lines = null;
        try {
            lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        } catch (NoSuchFileException e) {
            try {
                File file = new File(path);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("{}");
                fileWriter.flush();
                fileWriter.close();

                lines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert lines != null;
        String[] l = lines.toArray(new String[0]);
        for(int i = 0; i < lines.size(); i++)
        {
            if (i+1 >= lines.size()) {
                int i_s = l[i].indexOf('"');
                int i_e = l[i].indexOf('"', i_s+1);
                int num = 1;
                try {
                    num = (Integer.parseInt(l[i].substring(i_s+1, i_e))+1);
                    id = num;
                    l[i] = l[i].substring(0, l[i].length()-1);
                    l[i] += ",\n\"" + num + "\" : {" + "\"f\" : " + f + ", \"s\" : " + s + ", \"r\" : " + r + ", \"o\" : " + o + "}}";
                } catch (StringIndexOutOfBoundsException e) {
                    l[i] = l[i].substring(0, l[i].length()-1);
                    l[i] += "\"" + num + "\" : {" + "\"f\" : " + f + ", \"s\" : " + s + ", \"r\" : " + r + ", \"o\" : " + o + "}}";
                }
            }
        }
        try {
            Files.write(Paths.get(path), List.of(l));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }


    public static ArrayList<Double> toArrayListDouble(Double[] array){
        return new ArrayList<>(Arrays.asList(array));
    }


    public static ArrayList<Double> toArrayListDouble_object(Object obj) {
        return (ArrayList<Double>) obj;
    }


    public static void history(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            int num = 1;
            JSONObject a = (JSONObject) jsonObject.get(String.valueOf(num));
            System.out.println(" ");
            while (a != null) {
                Object f = a.get("f");
                Object s = a.get("s");
                Object r = a.get("r");
                Object o = a.get("o");

                System.out.println("-----" + num + "-----");
                System.out.print("Divisible: ");
                print_mn(toArrayListDouble_object(f));
                System.out.print("Divisor  : ");
                print_mn(toArrayListDouble_object(s));
                System.out.print("Fraction : ");
                print_mn(toArrayListDouble_object(r));
                System.out.print("Reminder : ");
                print_mn(toArrayListDouble_object(o));

                System.out.println();

                num += 1;
                a = (JSONObject) jsonObject.get(String.valueOf(num));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Double> give_mn(String start_mes, String every_mn, String end_mn) {
        if (! start_mes.equals("")) {
            System.out.println(start_mes);}
        ArrayList<Double> res = new ArrayList<>();
        boolean enter = true;
        while (enter){
            Scanner in = new Scanner(System.in);
            System.out.print(every_mn);
            String num = in.next();
            if (num.equals(end_mn)){
                enter = false;
            }
            else {
                res.add(Double.valueOf(num));
            }
        }
        return res;
    }


    public static Integer make_choice(String[] string_array) {
        System.out.println(" ");
        System.out.print(" | ");
        for (String s : string_array) {
            System.out.print(s);
            System.out.print(" | ");
        }
        System.out.println(" ");
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Your choice : ");
            String choice = in.nextLine();
            if (Arrays.asList(string_array).contains(choice)) {
                System.out.println(" ");
                return Arrays.asList(string_array).indexOf(choice);
            }
            else {
                System.out.println("Please, enter correctly !");
            }
        }
    }


    public static void choose_from_history(String path) {
        JSONParser parser = new JSONParser();
        int num;
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter ID or 'pass'");
                System.out.print("Your choice : ");
                String choice = in.nextLine();
                if (choice.equals("pass")) {break;}
                else {
                    try {
                        num = Integer.parseInt(choice);
                        if (jsonObject.get(String.valueOf(num)) != null) {
                            ArrayList<ArrayList<Double>> ret = get_from_history(num, path);
                            System.out.println("---------- " + num + " ----------");
                            System.out.print("Divisible: ");
                            print_mn(ret.get(0));
                            System.out.print("Divisor  : ");
                            print_mn(ret.get(1));
                            System.out.print("Fraction : ");
                            print_mn(ret.get(2));
                            System.out.print("Reminder : ");
                            print_mn(ret.get(3));
                            full_solve_print(ret.get(0), ret.get(1));
                            System.out.println(" ");
                        }
                        else {
                            System.out.println("No such ID");
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<ArrayList<Double>> solve(Double[] first, Double[] second) {
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i <= first.length - second.length; i++) {
            if (first[i] != 0){
                double k = first[i]/second[0];
                for (int q = 0; q < second.length; q++) {
                    first[i+q] -= second[q] * k;
                }
                result.add(k);
            }
            else {result.add(0.0);}
        }
        ArrayList<ArrayList<Double>> ret = new ArrayList<>();
        ret.add(result);
        ret.add(toArrayListDouble(first));
        return ret;
    }


    public static void full_solve_print(ArrayList<Double> f, ArrayList<Double> s) {
        Double[] first = f.toArray(new Double[0]);
        Double[] second = s.toArray(new Double[0]);
        System.out.println("---------- FULL SOLVE ----------");
        print_mn(first);
        for (int i = 0; i <= first.length - second.length; i++) {
            if (first[i] != 0){
                double k = first[i]/second[0];
                for (int q = 0; q < second.length; q++) {
                    first[i+q] -= second[q] * k;
                }
                print_mn_num(second, k, first.length - i);
                System.out.println("--------------------");
                print_mn(first);
            }
        }
    }


    public static ArrayList<ArrayList<Double>> get_from_history(Integer num, String path) {
        ArrayList<ArrayList<Double>> ret = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject a = (JSONObject) jsonObject.get(String.valueOf(num));

            Object f = a.get("f");
            Object s = a.get("s");
            Object r = a.get("r");
            Object o = a.get("o");

            ret.add(toArrayListDouble_object(f));
            ret.add(toArrayListDouble_object(s));
            ret.add(toArrayListDouble_object(r));
            ret.add(toArrayListDouble_object(o));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static Integer example(String path) {
        int id;
        ArrayList<Double> f;
        f = give_mn("-- Divisible --", "Enter number : ", "end");
        Double[] first = f.toArray(new Double[0]);

        System.out.println(" ");

        ArrayList<Double> s;
        s = give_mn("-- Divisor --", "Enter number : ", "end");
        Double[] second = s.toArray(new Double[0]);

        System.out.println(" ");

        System.out.print("Divisible: ");
        print_mn(f);
        System.out.print("Divisor  : ");
        print_mn(s);

        ArrayList<ArrayList<Double>> ret = solve(first, second);
        ArrayList<Double> result = ret.get(0);
        ArrayList<Double> reminder = ret.get(1);

        System.out.print("Fraction : ");
        print_mn(result);
        System.out.print("Reminder : ");
        print_mn(reminder);

        System.out.println(" ");

        id = save(f, s, result, reminder, path);
        return id;
    }
}