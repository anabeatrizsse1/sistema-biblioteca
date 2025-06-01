public class Livro {

    private int id;
private String titulo;
private int valorCredito;
private boolean disponivel = true;
private Usuario emprestadoPara;

public Livro(int id, String livro) {
    this.id = id;
    this.titulo = livro;

}

 public String getTitulo() {
    return titulo;
 }

    public Usuario getEmprestadoPara() {
        return emprestadoPara;
    }
    public boolean isDisponivel() {
    return disponivel;
    }

    public void emprestadoPara(Usuario usuario) {//funcao que mostra o livro indisponivel e para qual usuario ele foi emprestado
    this.disponivel = false;
    this.emprestadoPara = usuario; //usuario que esta com o livro
    }
    public void devolver(){ // funcao feita para devolver o livro que o usuario esta
    this.disponivel = true; // livro fica disponivel
    this.emprestadoPara = null; //nao esta sendo emprestado a ninguem

    }
}
