package com.kimi.problem;

import java.util.*;

/**
 * 中缀表达式  计算器
 */
public class Caculator {
    String target;
    ArrayList<String> steps=new ArrayList<>();

    public Caculator(String target) {
        this.target = target;
        String s = target.replaceAll("\\s", "");
        int length = s.length();
        String read="";
        for (int i = 0; i < length; i++) {
            String c = s.charAt(i)+"";
            if (isOpChar(c)) {
                steps.add(c+"");
            }else{
                read+=c;
                if (i<length-1){
                    String nextChar = s.charAt(i + 1)+"";
                    if (isOpChar(nextChar)) {
                        steps.add(read);
                        read = "";
                    }
                }else {
                    steps.add(read);
                }
            }
        }
    }

    public static List<String> toMidExpression(String expression) {
        List<String> list = new ArrayList<>();
        String s = expression.replaceAll("\\s", "");
        int length = s.length();
        String read="";
        for (int i = 0; i < length; i++) {
            String c = s.charAt(i)+"";
            if (isOpChar(c)) {
                list.add(c+"");
            }else{
                read+=c;
                if (i<length-1){
                    String nextChar = s.charAt(i + 1)+"";
                    if (isOpChar(nextChar)) {
                        list.add(read);
                        read = "";
                    }
                }else {
                    list.add(read);
                }
            }
        }
       return list;
    }

    /**
     * 是否是数字
     * @param c
     * @return
     */
    public static boolean isNum(String c) {
        if (c == null) {
            return false;
        }
        return  c.matches("^\\d{1,}(\\.\\d{1,})?$");
    }

    /**
     * 是否操作符
     * @param c
     * @return
     */
    public static boolean isOpChar(String c) {
        if (c.equals("+") ||c.equals("-")||c.equals("*")||c.equals("/")||c.equals("(")||c.equals(")")){
            return true;
        }
        return false;
    }

    /**
     * 是否第一个操作符优先级高
     * @param a
     * @param b
     * @return
     */
    public static boolean isTheFirstOpHiger(String a, String b) {
        if ( (a.equals("*")  || a.equals("/")) && (b.equals("+")  || b.equals("-"))){
            return true;
        }
        return false;
    }

    /**
     * 计算结果
     * @return
     */
    public double calculateMid() {
        Stack<Double> nums = new Stack<>();
        Stack<String> ops = new Stack<>();
        String out ;
        //注意 stack 为空的时候 peek 和 pop 会抛出异常
        while (!steps.isEmpty()) {
            //获取给出计算式头部的数字或者符号
            out= steps.remove(0);
            //数字入栈
            if (!isOpChar(out)) {
                nums.push(Double.valueOf(out));
            }
            //字符的话
                // 如果操作符为空入栈
                //否则比较操作符栈顶元素的优先级
                    //栈顶的优先级高的话. 弹出栈顶元素.弹出2个数字计算,计算结果入数字栈,然后当前操作符入栈.
                    //栈顶的优先级低的话,当前操作直接符入栈.
            else{
                //字符
                if (ops.isEmpty()) {
                    ops.push(out);
                }else{
                    String peek = ops.peek();
                    if (isTheFirstOpHiger(out, peek)) {
                        ops.push(out);
                    }else{
                        Double pop1 = nums.pop();
                        Double pop2 = nums.pop();
                        Double res=getResult(pop1, pop2, ops.pop());
                        nums.push(res);
                        ops.push(out);
                    }
                }
            }
        }
        //最后依次弹出符号栈  和两个数计算
        while (!ops.isEmpty()){
            String op = ops.pop();
            Double pop1 = nums.pop();
            Double pop2 = nums.pop();
            nums.push(getResult(pop1, pop2, op));
        }
        //数字栈的最后一位为计算结果
        return nums.pop();
    }

    /**
     *
     * @return
     */
    public static double calculateBackExpression(List<String> items){
        Stack<Double> nums = new Stack<>();
        while (!items.isEmpty()){
            String item = items.remove(0);
            if (isOpChar(item)){
                Double pop0 = nums.pop();
                Double pop1 = nums.pop();
                Double result = getResult(pop0, pop1, item);
                nums.push(result);
            }else{
                nums.push(Double.valueOf(item));
            }
        }
        return nums.pop();
    }


    /**
     * 给出操作数和操作符获取计算结果
     * @param pop1
     * @param pop2
     * @param out
     * @return
     */
    private static Double getResult(Double pop1, Double pop2, String out) {
        if (out.equals("+")) {
            return pop2+pop1;
        }else if(out.equals("-")){
            return pop2-pop1;
        }else if(out.equals("*")){
            return pop2*pop1;
        }else if(out.equals("/")){
            return pop2/pop1;
        }else{
            throw new UnsupportedOperationException("不支持的操作符");
        }
    }

    public static List  midToBackExpression(List<String> items) {
        Stack<String> stack = new Stack<>();
        List resList = new ArrayList();
        String nowCheck;
        while (!items.isEmpty()) {
            nowCheck= items.remove(0);
            //数字入栈
            if (isNum(nowCheck)) {
                resList.add(nowCheck);
            }
            //字符
            else{
                if ("(".equals(nowCheck)) {
                    stack.push(nowCheck);
                } else if (")".equals(nowCheck)) {
                    String pop;
                    do {
                        pop = stack.pop();
                        if (!pop.equals("(")){
                            resList.add(pop);
                        }
                    }while (!pop.equals("("));
                    //操作符
                }else{
                    while (!stack.isEmpty()&&isOpChar(stack.peek())&&isTheFirstOpHiger(stack.peek(),nowCheck)) {
                            resList.add(stack.pop());
                    }
                    stack.push(nowCheck);
                }
            }
        }
        while(!stack.isEmpty()){
            resList.add(stack.pop());
        }
        return resList;
    }

    public static void main(String[] args) {
        String s = " 1/6 *3+2*4-6 ";
        Caculator caculator = new Caculator(s);
        //double res = caculator.calculateMid();
      //  List<String> mids = toMidExpression("3+2*4-5*2-7");
        List<String> mids = toMidExpression("2-1*3-(3+2)*4");
        List back = midToBackExpression(mids);
        System.out.println(back);
        double v = calculateBackExpression(back);
       // System.out.println(back);
        System.out.println(v);

    }

}
