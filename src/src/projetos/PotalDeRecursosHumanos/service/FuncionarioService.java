package projetos.PotalDeRecursosHumanos.service;

import projetos.PotalDeRecursosHumanos.exceptions.FuncionarioNaoEncontradoException;
import projetos.PotalDeRecursosHumanos.models.Funcionario;
import projetos.PotalDeRecursosHumanos.models.PontoEntradaSaida;

import java.util.List;
import java.util.UUID;

public class FuncionarioService {
    private final List<Funcionario> listaFuncionarios;

    public FuncionarioService(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);
    }

    public Funcionario buscarFuncionarioPorId(UUID id) throws FuncionarioNaoEncontradoException {
        return this.listaFuncionarios.stream().filter(f -> f.getId().equals(id)).findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionario com " + id + " não encontrado"));
    }

    public void removerFuncionarioPorId(UUID id) throws FuncionarioNaoEncontradoException {
        Funcionario funcionario = this.buscarFuncionarioPorId(id);
        this.listaFuncionarios.remove(funcionario);
        System.out.println("Funcionario com ID: " + id + " removido com sucesso!");
    }

    public void listarPontosPorFuncionarioId(UUID id) throws FuncionarioNaoEncontradoException {
        for (PontoEntradaSaida ponto : this.buscarFuncionarioPorId(id).getPontosEntradaSaida()) {
            System.out.println(ponto);
        }
    }


}
