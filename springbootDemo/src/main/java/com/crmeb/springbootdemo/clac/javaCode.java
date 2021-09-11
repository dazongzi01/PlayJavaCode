package com.crmeb.springbootdemo.clac;//导入相关的包
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


//构建一个class类
public class javaCode implements ActionListener
{

    String input = "";    //设置字符串
    JFrame frame;         //定义一个框架
    JPanel panel;         //定义一个面板
    JButton button;       //定义按钮
    JTextField field;     //定义文本框
    String s[] = {"7","8","9","+","4","5","6","-","1","2","3","*","0","C","=","/"};

    public static void main(String[] args) {
        javaCode a=new javaCode();
        a.go();
    }

    public void go() {
        frame = new JFrame("计算器");//设置框架，并且名字为"计算器"
        panel = new JPanel();
        field = new JTextField(30);//设置文本框，并且规定只能输入30个字符
//        frame.setDefauItCIoseOperation(JFrame.EXlTONCLOSE);//在框架中设置一个可关闭的按钮
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        field.setHorizontalAlignment(JTextFieId.LEFT);//规定文本框中字符水平向左对齐
        field.setHorizontalAlignment(JTextField.LEFT);//规定文本框中字符水平向左对齐
//        fieId.setPreferredSize(new Dimension(200,30));//设置文本框的像素
        field.setPreferredSize(new Dimension(200,30));//设置文本框的像素
        frame.getContentPane().add(field,BorderLayout.NORTH);//将文本框加入到框架的北方（最上方）
        panel.setLayout(new GridLayout(4,4,1,1));//将面板布局设置为16格，每格中间有一个像素的单位
        for (int i=0; i<16; i++){
            button=new JButton(s[i]);//，设置按钮
            button.addActionListener(this);
            panel.add(button);//将按钮加入到画板中
        }
        frame.getContentPane().add(panel,BorderLayout.CENTER);//将画板放入框架的中间
        frame.setSize(300,400);//设置框架像素
        frame.setVisible(true);//显示框架
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();//获取当按钮上的字符串
        int c = 0;
        if (action.equals("+") || action.equals("-") || action.equals("*") || action.equals("/")) {
            input += " " + action + " "; //如果字符为运算符号,则将其放入到input中
        } else if (action.equals("C")) {
            input = ""; //如果字符串为C，字为空值
        } else if (action.equals("=")) {
            input += " = " +  Calculate1(input); //如果是 = ，则将其运算结果放入input中
            field.setText(input);//使用setText方法将输出值放入文本框中。
            file_add(input); //在input置空之前写入文件
            input = "";
            c = 1;
        } else {
            input += action;
        }

        if (c == 0){
            field.setText(input); //如果没有输入=的时候继续给输入框显示
        }
    }

    public String Calculate1(String input) {
        String result = "";
        String[] com = input.split(" "); //将返回的值按空格进行分割，并且存入到数组里
        Stack<BigDecimal> stack = new Stack();//构造一个栈，并且其中文本类型为double型

//        Double m = Double.parseDouble(com[0]);//将数组中的字符串转换为double型
        BigDecimal m = new BigDecimal(com[0]);//将数组中的字符串转换为double型
        stack.push(m);//将数组中第一个值放入栈中
        for(int i=1; i<com.length; i++){
            if(i%2 == 1){
                if(com[i].equals("+")){
//                    stack.push(new BigDecimal(com[i+1])); //将两个数字加起来
                    m = m.add(new BigDecimal(com[i+1]));
                }
                if(com[i].equals("-")){
//                    stack.push(- new BigDecimal(com[i+1]));//将两个数字相减
                    m = m.subtract(new BigDecimal(com[i+1]));
                }
                if(com[i].equals("*")){
//                    Integer d = stack.peek();//首先将栈顶元素复制给d
//                    stack.pop();//移去栈中的栈顶元素
//                    stack.push( d * Integer.parseInt(com[i+1]));//先运算乘法，然后运行其他运算符
                    m = m.multiply(new BigDecimal(com[i+1]));
                }
                if(com[i].equals("/")){
//                    Integer h = Integer.parseInt(com[i+1]);//将被除数的值赋值给h
                    BigDecimal h = new BigDecimal(com[i+1]);//将被除数的值赋值给h
                    if(h.compareTo(BigDecimal.ZERO) > 0){
//                        Integer h1 = stack.peek(); // 将除数的值赋值给hl
//                        stack.pop();
//                        stack.push(h1 / h);//规定运算符优先级
                        m = m.divide(h,5, RoundingMode.HALF_UP);
                    }else{
                        field.setText("分母不能为0");
                    }
                }
            }
        }
//        double d= 0d;
//        while(!stack.isEmpty()){
//            d += stack.peek();//将栈中的值赋值给d
//            stack.pop();//移去栈中的栈顶元素
//        }

//        result =String.valueOf(m);//将运算结果转换成字符串
        result =m.toString();//将运算结果转换成字符串
        return result;//返回运算结果
    }

    /**
     * 计算执行逻辑
     * @param input
     * @return
     */
    public String Calculate(String input) {
        String result = "";
        String[] com = input.split(" "); //将返回的值按空格进行分割，并且存入到数组里
        Stack<Double> stack = new Stack();//构造一个栈，并且其中文本类型为double型
        Double m = Double.parseDouble(com[0]);//将数组中的字符串转换为double型
        stack.push(m);//将数组中第一个值放入栈中
        for(int i=1; i<com.length; i++){
            if(i%2 == 1){
                if(com[i].equals("+")){
                    stack.push(Double.parseDouble(com[i+1]));//将两个数字加起来
                }
                if(com[i].equals("-")){
                    stack.push(-Double.parseDouble(com[i+1]));//将两个数字相减
                }
                if(com[i].equals("*")){
                    double d = stack.peek();//首先将栈顶元素复制给d
                    stack.pop();//移去栈中的栈顶元素
                    stack.push(d * Double.parseDouble(com[i+1]));//先运算乘法，然后运行其他运算符
                }
                if(com[i].equals("/")){
                    double h=Double.parseDouble(com[i+1]);//将被除数的值赋值给h
                    if(h == 0){
                        field.setText("分母不能为0");
                        double h1 = stack.peek(); // 将除数的值赋值给hl
                        stack.pop();
                        stack.push(h1 / h);//规定运算符优先级
                    }
                }
            }
            double d= 0d;
            while(!stack.isEmpty()){
                d += stack.peek();//将栈中的值赋值给d
                stack.pop();//移去栈中的栈顶元素
            }
            result =String.valueOf(d);//将运算结果转换成字符串
        }
        return result;//返回运算结果
    }

    public void file_add(String str) {
        try {
            // 保存文件 绝对路径加文件名称
            String currentFilePath = "/Users/stivepeim/Desktop/fileSave.txt";
            // 备份文件 绝对路径加文件名称
            String backFilePath = "/Users/stivepeim/Desktop/back.txt";

            //首先创建一个备份文件,并读取原文件写入备份数据
            File c = new File(currentFilePath);
            BufferedWriter c_w = new BufferedWriter(new FileWriter(currentFilePath));
            BufferedReader a_r = new BufferedReader(new FileReader(backFilePath));
            String a_copy;
            while((a_copy=a_r.readLine())!=null) {
                c_w.write(a_copy);
                c_w.write("\n");
            }
            c_w.close();

            //再用备份文件先写入原文件缓冲区
            BufferedReader c_r = new BufferedReader(new FileReader(currentFilePath));
            String c_str;
            BufferedWriter a_w = new BufferedWriter(new FileWriter(backFilePath));
            while((c_str=c_r.readLine())!=null) {
                a_w.write(c_str);
                a_w.write("\n");
            }

            //先不要写入，再把需要追加的参数数据写入缓冲区
            a_w .write(str);

            //然后写入
            a_w.close();

            //最后删除备份文件（也可以保留），删除需要中止该文件的所有数据流，最好所有的都结束
            a_r.close();
            a_w.close();
            c_r.close();
            c_w.close();
            //c.delete(); // 备份文件可以删除也可以保留 为什么要备份，其实就是三个容器传递两个内容的逻辑
        }catch (Exception e){
            System.out.println("错了："+e.getMessage());
        }


    }




}















