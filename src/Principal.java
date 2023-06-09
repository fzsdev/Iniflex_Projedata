import entity.Funcionario;
import entity.Pessoa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {

        System.out.println("\nTeste Infiflex - Projedata!!!");
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"),
                new Funcionario("Laura", LocalDate.of(1993, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente")
        ));

        // 3.3 - Imprimir todos os funcionários com todas as informações
        System.out.println("\n________________________________________");
        System.out.println("Lista de Funcionarios:\n");
        funcionarios.forEach(System.out::println);

        // 3.2 - Remover o funcionário "João" da lista
        System.out.println();
        System.out.println(funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João")) +
                "\n(boolean) = Funcionario João Removido.");
        // 3.4 - Aumentar o salário dos funcionários em 10% + formatação

        DecimalFormat df = new DecimalFormat("0.##");

        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.1"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
            String salarioFormatado = df.format(funcionario.getSalario());
            funcionario.setSalario(new BigDecimal(salarioFormatado));
        });

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\n________________________________________");

        System.out.println("Funcionários agrupados por função:\n");
        funcionariosFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + "\033[1m" + funcao + "\033[0m");
            lista.forEach(System.out::println);
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                }).collect(Collectors.toList());

        System.out.println("\n________________________________________");
        System.out.println("Aniversariantes no mês 10 e 12:");
        aniversariantes.forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(Pessoa::getIdade))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            System.out.println("\n________________________________________");
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + funcionarioMaisVelho.getIdade());
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n________________________________________");
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat df2 = new DecimalFormat("#,##0.00", symbols);

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String salarioTotalFormatado = df2.format(totalSalarios);
        System.out.println("\nTotal dos salários dos funcionários: R$ " + salarioTotalFormatado);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        System.out.println("\n________________________________________");
        System.out.println("Salários em múltiplos do salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal multiplicador = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + multiplicador.toPlainString() + " salários mínimos");
        });
    }
}
