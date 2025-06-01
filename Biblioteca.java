import java.util.List;

public interface Biblioteca{
    Livro buscarPorTitulo(String titulo); // busca o livro pelo titulo na biblioteca
    List<Livro> listarTodos(); // lista todos os livros armazenasdos na biblioteca
}
