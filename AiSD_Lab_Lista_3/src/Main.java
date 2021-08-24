import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{

    public static void main(String[]args) throws FileNotFoundException
    {
        /*
             equation has equality sign at the end
             do not use space between operators and numbers
        */


        Equation equation = new Equation();
//        equation.saveEquation("A");
//        equation.loadEquation("A");
//        equation.conversionToONP();
//        equation.calculate();

        equation.setEquation();
        equation.conversionToONP();
        equation.calculate();
    }

}