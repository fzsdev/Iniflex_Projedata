import entity.Funcionario;
import entity.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        Funcionario f1 = new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador");
        Funcionario f2 = new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador");
        Funcionario f3 = new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador");
        Funcionario f4 = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor");
        Funcionario f5 = new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista");
        Funcionario f6 = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador");
        Funcionario f7 = new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador");
        Funcionario f8 = new Funcionario("Laura", LocalDate.of(1993, 7, 8), BigDecimal.valueOf(3017.45), "Gerente");
        Funcionario f9 = new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista");
        Funcionario f10 = new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente");

        funcionarios.add(f1);
        funcionarios.add(f2);
        funcionarios.add(f3);
        funcionarios.add(f4);
        funcionarios.add(f5);
        funcionarios.add(f6);
        funcionarios.add(f7);
        funcionarios.add(f8);
        funcionarios.add(f9);
        funcionarios.add(f10);

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas as informações
        System.out.println("\nLista de Funcionarios: \n");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentar o salário dos funcionários em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.1"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .collect(Collectors.toList());

        System.out.println("\nAniversariantes no mês 10 e 12:");
        aniversariantes.forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(Pessoa::getIdade))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            System.out.println("\nFuncionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + funcionarioMaisVelho.getIdade());
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em múltiplos do salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal multiplicador = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + multiplicador.toPlainString() + " salários mínimos");
        });
    }
}
