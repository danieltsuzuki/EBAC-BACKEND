package projetos.PotalDeRecursosHumanos.models;

import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;

public class Analista extends Funcionario implements Registravel {
    public Analista(String nome) {
       super(nome, TipoFuncionario.ANALISTA);
    }

    @Override
    public void registrarHora(PontoEntradaSaida pontoEntradaSaida) throws HoraExtraException {
        this.adicionarPonto(pontoEntradaSaida);
    }
}
