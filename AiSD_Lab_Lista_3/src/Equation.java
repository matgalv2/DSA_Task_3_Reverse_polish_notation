import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Equation
{
    private Scanner scanner;
    private String [] equation;
    private ArrayList<String> equationInONP;

    private Operator leftParenthesis = new Operator("(",0);
    private Operator rightParenthesis = new Operator(")",0);
    private Operator plus = new Operator("+",1);
    private Operator minus = new Operator("-",1);
    private Operator multiplier = new Operator("*",2);
    private Operator quotient = new Operator("/",2);


    public Equation()
    {
        equationInONP = new ArrayList<>();
    }

    public void setEquation()
    {
        this.scanner = new Scanner(System.in);
        System.out.print("Enter math equation: ");
        String equation = scanner.nextLine();
        this.equation = equation.split("(?=[-+*/()=])|(?<=[^-+*/=][-+*/=])|(?<=[()])");
        this.scanner.close();
    }


    public void saveEquation(String fileName) throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(new File(fileName + ".txt"));
        Scanner scanner_in = new Scanner(System.in);
        System.out.print("Enter the equation: ");
        printWriter.write(scanner_in.nextLine());
        printWriter.close();
        scanner_in.close();
    }

    public void loadEquation(String fileName) throws FileNotFoundException
    {
        Scanner scanner_file = new Scanner(new File("C:/Users/Mateusz/IdeaProjects/AiSD_Lab_Lista_3/" + fileName + ".txt"));
        this.equation = scanner_file.nextLine().split("(?=[-+*/()=])|(?<=[^-+*/=][-+*/=])|(?<=[()])");
        scanner_file.close();
    }

    public boolean isOperator(String symbol)
    {
        ArrayList<Operator> operatorsList = new ArrayList<>();
        operatorsList.add(plus);
        operatorsList.add(minus);
        operatorsList.add(multiplier);
        operatorsList.add(quotient);
        for(Operator op : operatorsList)
        {
            if(op.getSymbol().equals(symbol))
            {
                return true;
            }
        }
        return false;
    }

    public int operatorPriority(String operator)
    {
        if(operator.equals(plus.getSymbol()))
        {
            return plus.getPriority();
        }
        else if(operator.equals(minus.getSymbol()))
        {
            return minus.getPriority();
        }
        else if(operator.equals(multiplier.getSymbol()))
        {
            return multiplier.getPriority();
        }
        else if(operator.equals(quotient.getSymbol()))
        {
            return quotient.getPriority();
        }
//        else if(operator.equals((leftParenthesis.getSymbol())))
//        {
//            return leftParenthesis.getPriority();
//        }
        else
            {
                return leftParenthesis.getPriority();
            }
    }


    public void conversionToONP()
    {
//        String [] equation = equation1.split("(?=[-+*/()=])|(?<=[^-+*/=][-+*/=])|(?<=[()])");
        Stack<String> stack = new Stack<>(); //k01
//        ArrayList<String> equationInONP = new ArrayList<>();
        int i = 0;
        boolean highest = false;

        while(!equation[i].equals("=")) //step 02 i step 03
        {
            highest = false; //  to avoid problem of not entering the loop in step 18
            if(!equation[i].equals("(")) //step 08
            {
                if(!equation[i].equals(")")) //step 11
                {
                    if(!isOperator(equation[i])) //step 17
                    {
                        equationInONP.add(equation[i]); //step 24 & step 25
                    }
                    else //step 18
                    {
                        while(!stack.empty() && !highest)
                        {
                            if(operatorPriority(equation[i])==2 || operatorPriority(equation[i]) > operatorPriority(stack.peek())) //step 19
                            {
                                stack.push(equation[i]);
                                highest = true;
                            }
                            else
                            {
                                equationInONP.add(stack.pop()); //step 20 & step 21
                            }
                        }
                        if(!highest)
                        {
                            stack.push(equation[i]); //step 22 & step 23
                        }
                    }
                }
                else if (equation[i].equals(")")) //step 11
                {
                    while(!stack.peek().equals("(")) //step 12
                    {
                        equationInONP.add(stack.pop()); //step 13 & step 14
                    }
                    stack.pop(); //step 15 & step 16
                }

            }
            else if(equation[i].equals("("))
            {
                stack.push(equation[i]); //step 09 & step 10
            }

            i++;
        }
        while(!stack.empty()) //step 04
        {
            equationInONP.add(stack.pop()); //step 05 & step 06
        }
        //step 07
        for (String s : equationInONP)
        {
            System.out.print(s + " ");
        }
    }

    public void calculate()
    {
        Stack<Integer> stack = new Stack<>();
        int result;
        int tmp;

        for (int i = 0; i < equationInONP.size(); i++)
        {
            if(isOperator(equationInONP.get(i)))
            {
                if(equationInONP.get(i).equals("+"))
                {
                    tmp = stack.pop();
                    result = stack.pop() + tmp;
                }
                else if(equationInONP.get(i).equals("-"))
                {
                    tmp = stack.pop();
                    result = stack.pop() - tmp;
                }
                else if(equationInONP.get(i).equals("*"))
                {
                    tmp = stack.pop();
                    result = stack.pop() * tmp;
                }
                else
                {
                    tmp = stack.pop();
                    result = stack.pop() / tmp;
                }
                stack.push(result);

            }
            else
            {
                stack.push(Integer.parseInt(equationInONP.get(i)));
            }
        }

        System.out.println("\n" + stack.pop());
    }

}