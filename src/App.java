import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        
        int comando = 0;
        ArrayList<Colaboradores> pessoa = new ArrayList<Colaboradores>();
        while( comando != 4){
        System.out.println("\nSelecione uma opção:");
        System.out.println("1 - Cadastrar projeto.");
        System.out.println("2 - Cadastrar participante.");
        System.out.println("3 - Mostra lista de colaboradores");
        System.out.println("4 - Sair.");
        Scanner numero = new Scanner(System.in);
        comando = numero.nextInt();
        Scanner ler = new Scanner(System.in);
        switch (comando){
            case 1:
                System.out.println("\nNome do Projeto");
                var titulo = ler.nextLine();
                System.out.println("Data de início:");
                var DataInicio = ler.nextLine(); 
                System.out.println("Data de termino:");
                var DataTermino = ler.nextLine(); 
                System.out.println("Agente financiadora:");
                var financiadora = ler.nextLine(); 
                System.out.println("Valor:");
                var valor = ler.nextDouble(); 
                ler.nextLine(); 
                System.out.println("Objetivo:");
                var Objetivo = ler.nextLine(); 
                System.out.println("Descrição:");
                var Descricao = ler.nextLine(); 
                Projetos primeiro = new Projetos(titulo,DataInicio,DataTermino,
                financiadora,valor,Objetivo,Descricao);
                primeiro.mostrar();
                break;
            case 2:
                System.out.println("\nDigite o nome do coloborador:");
                var nome = ler.nextLine();
                System.out.println("Digite o tipo de formação:");
                var tipo = ler.nextLine(); 
                Colaboradores colaborador = new Colaboradores(nome,tipo,"");
                colaborador.Mostrar();
                pessoa.add(colaborador);
                break;
            case 3:
               /*pessoa.get(0).MostraParticipante();*/
               break;

            case 4:
               break;

        }
    }
}
}