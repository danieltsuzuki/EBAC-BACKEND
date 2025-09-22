package projetos.PotalDeRecursosHumanos.models;

import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;

public interface Registravel {
    void registrarHora(PontoEntradaSaida pontoEntradaSaida) throws HoraExtraException;
}
