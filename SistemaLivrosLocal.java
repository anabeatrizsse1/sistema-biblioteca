import java.util.ArrayList;
import java.util.List;

public class SistemaLivrosLocal implements Biblioteca {
    private List<Livro> livros = new ArrayList<>();

    public void adicionarLivro(Livro livro) { // funcao que adiciona o livro a biblioteca
        livros.add(livro);
    }

    @Override
    public Livro buscarPorTitulo(String titulo) { // pecorre a lista de livros
        return livros.stream() // filtra os livros que o titulo eh igual ao argumento
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo)) // pega o primeiro livro que atender ao filtro
                .findFirst()// se nao encontrar, retorna null
                .orElse(null);
    }

    @Override
    public List<Livro> listarTodos() {
        return livros; // retorna a lista de todos os livros armazenados na biblioteca
    }
}
