import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seja bem vindo");
        System.out.println("Digite seu cpf: ");
        String cpf = sc.nextLine();
        System.out.println("Digite sua idade: " );
        int idade = sc.nextInt();
        System.out.println("Digite seu peso:");
        double peso = sc.nextDouble();
        System.out.println("Digite sua altura:");
        float altura = sc.nextFloat();
        System.out.println("Digite seu estado civil:");
        String estadoCivil = sc.next();

        System.out.println("Seu cpf é: " + cpf);
        System.out.println("Sua idade é: " + idade);
        System.out.println("Seu peso é: " + peso);
        System.out.println("Sua altura é: " + altura);
        System.out.println("Seu estado civil é: " + estadoCivil);
    }
}