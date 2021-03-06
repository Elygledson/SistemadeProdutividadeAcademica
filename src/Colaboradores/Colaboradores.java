package Colaboradores;

import java.util.ArrayList;
import java.util.Scanner;

import Producao.ProducaoAcademica;
import main.Projetos;
import main.Sistema;

public class Colaboradores{
    private String nome;

	private String tipo;

	private String email;

	private String grau;

    private Integer SecretCode;
    /* projetos que está participando ou participou */
    public ArrayList<Projetos> projetos;
    /* publicações feitas */
    public ArrayList<ProducaoAcademica> producao;

    public static Scanner input = new Scanner(System.in);

    public Colaboradores(String nome, String tipo, String email, String grau, Integer SecretCode) {
        this.nome = nome;
        this.setTipo(tipo);
        this.grau = grau;
        this.email = email;
        this.SecretCode = SecretCode;
        this.projetos = new ArrayList<Projetos>();
        this.producao = new ArrayList<ProducaoAcademica>();
    }

    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	public String getNome() {
        return this.nome;
    }

    public String getGrau()
    {
        return this.grau;
    }

    public Integer getSecretCode() {
        return this.SecretCode;
    }

    public String getEmail() {
        return this.email;
    }

    public static boolean VerificarEmail(String email, ArrayList<Colaboradores> colaboradores) {
        for (int i = 0; i < colaboradores.size(); i++) {
            if (email.equals(colaboradores.get(i).email)) {
                return true;
            }
        }
        return false;
    }

    public void AddProjeto(ArrayList<Projetos> ProjCadastrados, Colaboradores colaborador) {
        if (ProjCadastrados.size() == 0) {
            System.out.println("\nNenhum projeto cadastrado!\n");
            return;
        }
        var getInput = Sistema.BuscarProjeto(ProjCadastrados);

        if (Projetos.Verificacao(colaborador, ProjCadastrados.get(getInput - 1).getTitulo())) {
            System.out.println("ERRO: você já está participando desse projeto!\n");
            return;
        }

        if (colaborador.grau.equals("GRADUAÇÂO")) {
            if (colaborador.projetos.size() < 2 && ProjCadastrados.get(getInput - 1).getStatus().equals("EM ELABORAÇÃO")) {
                this.projetos.add(ProjCadastrados.get(getInput - 1));
                ProjCadastrados.get(getInput - 1).participantes.add(colaborador);
                System.out.println("Colaborador(a) associado ao projeto!");
            } else {
                System.out.println("De acordo com as regras do sistema alunos de graduação não pode está em mais de dois projetos.");
                System.out.println("O cadastramento de alunos a projetos só é permitido quando o projeto está em elaboração.");
            }
        } 
        else {
            if (ProjCadastrados.get(getInput - 1).getStatus().equals("EM ELABORAÇÃO")) {
                this.projetos.add(ProjCadastrados.get(getInput - 1));
                ProjCadastrados.get(getInput - 1).participantes.add(colaborador);
                System.out.println("Colaborador(a) associado ao projeto!");
            } else {
                System.out.println("Aviso: a operação falhou pois esse projeto já está em andamento.");
            }
        }
        System.out.println("\nVocê deseja inserir o(a) colaborador(a) em mais algum projeto?\nSelecione a opção:\n[1] - SIM\n[2] - NÃO");
        var response = Integer.parseInt(input.nextLine());
        while(response > 2 || response <= 0)
        {
            System.out.println("Comando inválido!Tente novamente");
            response = Integer.parseInt(input.nextLine());
        }
         if(response == 1){
            AddProjeto(ProjCadastrados,colaborador);
         }

         return;

    }

    public void ListarPublicacoes(String tipo)
    {
        ProducaoAcademica.Sort(this.producao);
        for(int i = 0;i < this.producao.size();i++)
        {   
            if(this.producao.get(i).Tipo.equals(tipo)){

            for(int j = 0;j < this.producao.get(i).Projetos.size();j++)
            System.out.printf("[NOME DO PROJETO] %s\n",this.producao.get(i).getProjeto(j));

            for(int j = 0;j < this.producao.get(i).Autores.size();j++)
            System.out.printf("[NOME DO(S) AUTOR(ES)] %s\n",this.producao.get(i).getAutores(j));

             System.out.printf("[TITULO] -> %s\n[NOME DA CONFERÊNCIA] -> %s\n[TIPO] -> %s \n[LOCAL] -> %s\n[ANO] -> %d\n\n",this.producao.get(i).getTitulo(),this.producao.get(i).getNome(),
             this.producao.get(i).getTipo(),this.producao.get(i).getLocal(),this.producao.get(i).getAno());
            }
        }
    }

    public void ListarOrientacoes(String tipo)
    {
            for(int i = 0;i < this.producao.size();i++)
            {   
                if(this.producao.get(i).Tipo.equals(tipo)){
                 System.out.printf("\n[TITULO] -> %s\n[TIPO] -> %s\n[ANO] -> %s\n",this.producao.get(i).getTitulo(),this.producao.get(i).getTipo(),this.producao.get(i).getAno());

                 for(int j = 0;j < this.producao.get(i).Projetos.size();j++)
                     System.out.printf("[NOME DO PROJETO] %s\n",this.producao.get(i).getProjeto(j));
                
                 for(int j = 0;j < this.producao.get(i).Alunos.size();j++)
                    System.out.printf("[NOME DO ALUNO]: %s\n",this.producao.get(i).getAluno(j));

                }
            }
    }

    public void ListarPorStatus(String status)
    {
        Projetos.Sort(this.projetos);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\nProjeto(s) %s\n\n",status.toLowerCase());
        for(int i = 0; i < this.projetos.size();i++){
            if(this.projetos.get(i).getStatus().equals(status)) {
            System.out.printf("[OPÇÃO]\n[%d] - [STATUS] -> ''%s'' [TÍTULO] -> %s [DATA DE INÍCIO] -> %s [DATA DE TERMINO] -> %s [FINANCIADORA] -> %s [VALOR] -> R$%.2f \n",
            i + 1,this.projetos.get(i).getStatus(), this.projetos.get(i).getTitulo(),
                    this.projetos.get(i).getDataInicio(),
            this.projetos.get(i).getDataTermino(), this.projetos.get(i).A_financiadora,
                    this.projetos.get(i).getValor());
            }
        }
    }

    public void ListarColaborador()
    {
        System.out.println("\nDados do colaborador:\n");
        System.out.printf("[NOME]  ->%s \n[TIPO]  ->[%s|%s]\n",this.nome,this.getTipo(),this.grau);
       
        if(this.projetos.isEmpty()){ 
            System.out.printf("AVISO: No momento  ''%s'' não está participando de nenhum projeto.\n",this.nome);
        }else{
            System.out.printf("\n %s está participando dos projetos abaixo:\n",this.nome);
            ListarPorStatus("EM ELABORAÇÃO");
            ListarPorStatus("EM ANDAMENTO");
            ListarPorStatus("CONCLUÍDOS");
        }
        System.out.printf("\n%s publicou:\n",this.nome);
        if(this.producao.size() == 0)
        {
            System.out.printf("AVISO: ''%s'' não possui produções acadêmicas\n",this.nome);
        }
        else{
        ListarPublicacoes("PUBLICAÇÃO");
        ListarOrientacoes("ORIENTAÇÃO");
        }
        
    }

    public static void ListarColaboradores(ArrayList<Colaboradores> colaboradores)
    {

        if(colaboradores.isEmpty()){
            System.out.println("Ainda não há colaboradores cadastrados!");
            return;
        }

        for(int i = 0;i < colaboradores.size();i++)
        {

            System.out.println("\nDados do colaborador:\n");
            System.out.printf("[NOME]  ->%s \n[TIPO]  ->[%s|%s]\n",colaboradores.get(i).nome,colaboradores.get(i).getTipo(),colaboradores.get(i).grau);
           
            if(colaboradores.get(i).projetos.isEmpty()){ 
                System.out.printf("AVISO: No momento  ''%s'' não está participando de nenhum projeto.\n",colaboradores.get(i).nome); continue;
            }

            System.out.println("Participando dos projetos:");
            for(int j = 0; j < colaboradores.get(i).projetos.size(); j++){
                
                System.out.printf("[%d] [TITULO] -> %s \n  [DATA DE INÍCIO] -> %s [DATA DE TERMINO] -> %s \n  [FINANCIADOR] -> %s \n  [VALOR] ->R$ %.2f\n",j + 1,
                colaboradores.get(i).projetos.get(j).getTitulo(),colaboradores.get(i).projetos.get(j).getDataInicio(),colaboradores.get(i).projetos.get(j).getDataTermino(),
                colaboradores.get(i).projetos.get(j).A_financiadora,colaboradores.get(i).projetos.get(j).getValor());
            }

        }
    }
}
