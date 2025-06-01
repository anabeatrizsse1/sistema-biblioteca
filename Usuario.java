public abstract class  Usuario {
    private int id;
    private String nome;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // metodo abstrato que será implementado pelas subclasses (Aluno, Professor)
    // define a regra de como cada tipo de usuario pode emprestar livros
    public abstract boolean emprestar(Livro livro);

    public String getNome(){
        return nome;
    }

    // metodo abstrato que será implementado pelas subclasses
    // define o que acontece quando o usuario devolve um livro
    public abstract  void registrarDevolucao();

}
