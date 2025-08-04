import java.util.Scanner;

class Task1calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String operator;
        double number1, number2;
        char running = 'y';
        while (running == 'y'){
            System.out.println("Enter + for addition, - for subtraction, / for division, * for multiplication");
            System.out.print("Enter number 1 - ");
            number1 = sc.nextDouble();
            System.out.print("Enter number 2 - ");
            number2 = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter operator - ");
            operator = sc.nextLine();
            switch (operator) {
                case "+":
                    System.out.println("The addition is - "+ (number1+ number2));
                    break;
                case "-":
                    System.out.println("The subtraction is - "+ (number1-number2));
                    break;
                case "/":
                    System.out.println("The result is - " + (number1/number2));
                    break;
                case "*":
                    System.out.println("The product is - " + (number1*number2));
                    break;
            }
            System.out.print("Do you want to continue or not? - ");
            running = sc.nextLine().charAt(0);
        }
    }
}