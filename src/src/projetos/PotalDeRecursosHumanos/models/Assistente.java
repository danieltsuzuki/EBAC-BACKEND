package projetos.PotalDeRecursosHumanos.models;

import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;

public class Assistente extends Funcionario implements Registravel{
    public Assistente(String nome) {
        super(nome, TipoFuncionario.ASISTENTE);
    }


    @Override
    public void registrarHora(PontoEntradaSaida pontoEntradaSaida) throws HoraExtraException {
        this.adicionarPonto(pontoEntradaSaida);
    }
}
