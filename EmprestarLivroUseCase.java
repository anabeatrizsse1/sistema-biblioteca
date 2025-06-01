    public class EmprestarLivroUseCase {
        private final Biblioteca repo;

        public EmprestarLivroUseCase(Biblioteca repo) {
            this.repo = repo;
        }

        public void executar(String titulo, Usuario usuario) { // funcao para executar
            Livro livro = repo.buscarPorTitulo(titulo); //busca pelo titulo do livro na biblioteca
            if (livro == null) { // se livro nao existir retorna uma mensagem de livro nao encontrado
                System.out.println("Livro não encontrado.");
                return;
            }

            if (!usuario.emprestar(livro)) { // verifica se o usuario pode pegar o livro emprestado
                System.out.println("Usuário não pode pegar este livro.");
                return;
            }

            livro.emprestadoPara(usuario); // mostra pra quem foi emprestado o livro, caso o usuario nao tiver conseguido pegar

            if (usuario instanceof Aluno) { //verifica se o usuario eh uma instancia da classe Aluno
                //faz um cast do tipo Usuario para Aluno
                ((Aluno) usuario).usouCredito(); // chama o metodo usouCredito() do aluno
            }

            System.out.println(usuario.getNome() + " pegou emprestado: " + livro.getTitulo());
        }
    }


