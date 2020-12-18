package day18;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day18/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Expression createExpression(List<String> expr, int i) {
        if (expr.get(i).contains(")")) {
            int searchPtr = i;
            int depth = Util.countSubStr(expr.get(searchPtr), ")");
            while (depth != 0) {
                searchPtr--;
                if (expr.get(searchPtr).contains(")")) depth = depth + Util.countSubStr(expr.get(searchPtr), ")");
                if (expr.get(searchPtr).contains("(")) depth = depth - Util.countSubStr(expr.get(searchPtr), "(");
                ;
            }
            expr.set(i, Util.removeLastChar(expr.get(i)));
            expr.set(searchPtr, expr.get(searchPtr).substring(1));
            if (searchPtr == 0) {
                return createExpression(expr.subList(searchPtr, i + 1), i - searchPtr);
            } else {
                return new ArithmeticExpression(
                        createExpression(expr, searchPtr - 2),
                        createExpression(expr.subList(searchPtr, i + 1), i - searchPtr),
                        Operator.fromString(expr.get(searchPtr - 1)));
            }
        }
        if (i > 1) {
            return new ArithmeticExpression(
                    createExpression(expr, i - 2),
                    new Constant(expr.get(i)),
                    Operator.fromString(expr.get(i - 1)));
        } else {
            return new Constant(expr.get(i));
        }
    }

    public static List<String> bracketize(List<String> expr) {
        for (int i = 0; i < expr.size(); i++) {
            if (expr.get(i).equals("+")) {
                //put left bracket
                int l_ptr = i - 1;
                int depth = Util.countSubStr(expr.get(l_ptr), ")");
                while (depth > 0) {
                    l_ptr--;
                    depth = depth + Util.countSubStr(expr.get(l_ptr), ")");
                    depth = depth - Util.countSubStr(expr.get(l_ptr), "(");
                    ;
                }
                expr.set(l_ptr, "(" + expr.get(l_ptr));

                //put right bracket
                int r_ptr = i + 1;
                depth = Util.countSubStr(expr.get(r_ptr), "(");
                while (depth > 0) {
                    r_ptr++;
                    depth = depth + Util.countSubStr(expr.get(r_ptr), "(");
                    depth = depth - Util.countSubStr(expr.get(r_ptr), ")");
                    ;
                }
                expr.set(r_ptr, expr.get(r_ptr) + ")");
            }
        }
        return expr;
    }

    public static void test() {
        List<String> t_expr1 = Arrays.asList("2 * 3 + (4 * 5)".split(" "));
        List<String> t_expr2 = Arrays.asList("5 + (8 * 3 + 9 + 3 * 4 * 3)".split(" "));
        List<String> t_expr3 = Arrays.asList("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))".split(" "));
        List<String> t_expr4 = Arrays.asList("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2".split(" "));
        List<String> t_expr5 = Arrays.asList("2 * (7 + 3) + 3 * ((5 + 5) * 10)".split(" "));
        System.out.println(createExpression(t_expr1, t_expr1.size() - 1).evaluate());
        System.out.println(createExpression(t_expr2, t_expr2.size() - 1).evaluate());
        System.out.println(createExpression(t_expr3, t_expr3.size() - 1).evaluate());
        System.out.println(createExpression(t_expr4, t_expr4.size() - 1).evaluate());
        System.out.println(createExpression(t_expr5, t_expr5.size() - 1).evaluate());

        t_expr1 = bracketize(Arrays.asList("1 + (2 * 3) + (4 * (5 + 6))".split(" ")));
        t_expr2 = bracketize(Arrays.asList("2 * 3 + (4 * 5)".split(" ")));
        t_expr3 = bracketize(Arrays.asList("5 + (8 * 3 + 9 + 3 * 4 * 3)".split(" ")));
        t_expr4 = bracketize(Arrays.asList("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))".split(" ")));
        t_expr5 = bracketize(Arrays.asList("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2".split(" ")));
        System.out.println(createExpression(t_expr1, t_expr1.size() - 1).evaluate());
        System.out.println(createExpression(t_expr2, t_expr2.size() - 1).evaluate());
        System.out.println(createExpression(t_expr3, t_expr3.size() - 1).evaluate());
        System.out.println(createExpression(t_expr4, t_expr4.size() - 1).evaluate());
        System.out.println(createExpression(t_expr5, t_expr5.size() - 1).evaluate());
    }


    public static void main(String[] args) {
        System.out.println("---ex1---");
        long ans_ex1 = Arrays.stream(input)
                .map(x -> Arrays.asList(x.split(" ")))
                .map(x -> createExpression(x, x.size() - 1).evaluate())
                .reduce(Long::sum)
                .get();
        System.out.println(ans_ex1);

        System.out.println("---ex2---");
        long ans_ex2 = Arrays.stream(input)
                .map(x -> bracketize(Arrays.asList(x.split(" "))))
                .map(x -> createExpression(x, x.size() - 1).evaluate())
                .reduce(Long::sum)
                .get();
        System.out.println(ans_ex2);
    }
}
