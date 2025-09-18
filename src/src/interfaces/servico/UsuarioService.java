package interfaces.servico;

import interfaces.notificador.Notificar;

public class UsuarioService {
    private final Notificar notificador;

    public UsuarioService(Notificar notificador) {
        this.notificador = notificador;
    }

    public void registrar(String nome) {
        System.out.println("Usuário " + nome + " registrado.");
        notificador.enviarMensagem("Bem-vindo " + nome + "!");
    }
}
