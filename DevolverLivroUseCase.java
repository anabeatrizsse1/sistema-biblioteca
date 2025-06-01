public class DevolverLivroUseCase {
    private final Biblioteca repo;

    public DevolverLivroUseCase(Biblioteca repo) {
        this.repo = repo;
    }

    public void executar(String titulo, Usuario usuario) { //executa e devolucao do livro
        Livro livro = repo.buscarPorTitulo(titulo);

        if (livro == null || livro.isDisponivel()) { // se o livro nao existe ou esta disponivel (nao foi emprestado) nao ha devolucao
            System.out.println("Livro não está emprestado.");
            return;
        }

        if (!livro.getEmprestadoPara().equals(usuario)) { // verifica se o livro foi emprestado por esse usuario
            System.out.println("Este livro não foi emprestado por " + usuario.getNome());
            return;
        }

        livro.devolver(); // realiza a devolucao
        usuario.registrarDevolucao();// regista a devolucao com o aluno ganhando 1 credito

        System.out.println(usuario.getNome() + " devolveu: " + livro.getTitulo());
    }
}
