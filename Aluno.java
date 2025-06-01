public class Aluno extends Usuario {

    private int creditos;

    public Aluno(int id, String nome, int creditos) {
        super(id, nome);
        this.creditos=creditos;
    }
    public int getCreditos() {
        return creditos;
    }
    public void usouCredito(){
        this.creditos--;
    }

    public boolean temCredito(){
        return creditos > 0;
    }

    // metodo que verifica se o livro pode ser emprestado
    public boolean emprestar (Livro livro){
        return livro.isDisponivel() && temCredito(); // retorna true se o livro estiver disponivel
                                                    // e se o usuario (aluno) tiver creditos suficientes
    }
    @Override
    public void registrarDevolucao(){ // quando o aluno devolve um livro, ele recupera 1 credito
        this.creditos++;
    }

    }

