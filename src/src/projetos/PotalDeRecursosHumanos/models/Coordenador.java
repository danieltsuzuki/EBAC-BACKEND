package projetos.PotalDeRecursosHumanos.models;

import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;

public class Coordenador extends Funcionario implements Registravel {
    public Coordenador(String nome) {
        super(nome, TipoFuncionario.COORDENADOR);
    }

    @Override
    public void registrarHora(PontoEntradaSaida pontoEntradaSaida) throws HoraExtraException {
        this.adicionarPonto(pontoEntradaSaida);
    }
}
