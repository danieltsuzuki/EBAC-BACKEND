package projetos.PotalDeRecursosHumanos.models;

public enum TipoFuncionario {
    COORDENADOR(4, "Coordenador"),
    ANALISTA(2, "Analista"),
    ASISTENTE(2, "Asistente"),
    GERENTE(0, "Gerente"),
    ESTAGIARIO(0, "Estagiario");

    private final int maximoHoraExtra;
    private final String tipoFuncionario;

    TipoFuncionario(int maximoHoraExtra, String tipoFuncionario) {
        this.maximoHoraExtra = maximoHoraExtra;
        this.tipoFuncionario = tipoFuncionario;
    }

    public int getMaximoHoraExtra() {
        return maximoHoraExtra;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }
}
