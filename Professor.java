public class Professor extends Usuario {
    public Professor(int id, String nome) {
        super(id, nome);
    }

    @Override
    public boolean emprestar(Livro livro) {
        return livro.isDisponivel();
    }

    @Override
    public void registrarDevolucao() {
        // Nenhuma acao porque professores não precisam ganhar credito ao devolver
    }
}
