import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SistemaLivrosLocal repo = new SistemaLivrosLocal();

        // Adiciona 5 livros à biblioteca
        repo.adicionarLivro(new Livro(1, "Entendendo Algoritmos"));
        repo.adicionarLivro(new Livro(2, "Clean Code"));
        repo.adicionarLivro(new Livro(3, "O Programador Pragmático"));
        repo.adicionarLivro(new Livro(4, "Java: Como Programar"));
        repo.adicionarLivro(new Livro(5, "Estruturas de Dados e Algoritmos em Java"));

        EmprestarLivroUseCase emprestar = new EmprestarLivroUseCase(repo);
        DevolverLivroUseCase devolver = new DevolverLivroUseCase(repo);

        Map<String, Usuario> usuarios = new HashMap<>();

        while (true) {
            System.out.print("\nDigite seu nome: ");
            String nome = scanner.nextLine().trim();

            Usuario usuario = usuarios.get(nome);
            if (usuario == null) {
                System.out.print("Você é Aluno ou Professor? (A/P): ");
                String tipo = scanner.nextLine();

                if (tipo.equalsIgnoreCase("A")) {
                    usuario = new Aluno(usuarios.size() + 1, nome, 1);
                } else {
                    usuario = new Professor(usuarios.size() + 1, nome);
                }

                usuarios.put(nome, usuario);
            }

            // Verifica se o usuário já tem um livro emprestado
            Livro livroDoUsuario = null;
            for (Livro l : repo.listarTodos()) {
                if (usuario.equals(l.getEmprestadoPara())) {
                    livroDoUsuario = l;
                    break;
                }
            }

            if (livroDoUsuario != null) {
                System.out.println("\n📕 Você já tem um livro emprestado: " + livroDoUsuario.getTitulo());
                if (usuario instanceof Aluno) {
                    System.out.println("Seus créditos: " + ((Aluno) usuario).getCreditos());
                }

                System.out.print("Deseja devolver esse livro agora? (s/n): ");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    devolver.executar(livroDoUsuario.getTitulo(), usuario);
                } else {
                    System.out.println("Você não pode pegar outro livro até devolver o atual.");
                    continue;
                }
            }

            // Mostra os livros com numeração
            List<Livro> todos = repo.listarTodos();
            System.out.println("\n📚 Livros disponíveis:");
            for (int i = 0; i < todos.size(); i++) {
                Livro l = todos.get(i);
                String status = l.isDisponivel()
                        ? "Disponível"
                        : "EMPRESTADO por: " + l.getEmprestadoPara().getNome();
                System.out.printf("[%d] %s (%s)\n", i + 1, l.getTitulo(), status);
            }

            System.out.print("\nDigite o número do livro que deseja pegar emprestado: ");
            int indice = -1;
            try {
                indice = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            if (indice < 0 || indice >= todos.size()) {
                System.out.println("Livro inválido.");
                continue;
            }

            Livro escolhido = todos.get(indice);

            if (usuario instanceof Aluno) {
                System.out.println("Créditos antes: " + ((Aluno) usuario).getCreditos());
            }

            emprestar.executar(escolhido.getTitulo(), usuario);

            if (usuario instanceof Aluno) {
                System.out.println("Créditos após: " + ((Aluno) usuario).getCreditos());
            }

            // Pergunta se deseja devolver o livro
            if (escolhido.getEmprestadoPara() == usuario) {
                System.out.print("Deseja devolver esse livro agora? (s/n): ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("s")) {
                    devolver.executar(escolhido.getTitulo(), usuario);

                    if (usuario instanceof Aluno) {
                        System.out.println("Créditos após devolução: " + ((Aluno) usuario).getCreditos());
                    }
                }
            }

            System.out.println("\n---- NOVO USUÁRIO ----");
        }
    }
}
