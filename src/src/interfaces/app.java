package interfaces;

import interfaces.notificador.EmailNotificador;
import interfaces.notificador.SmsNotificador;
import interfaces.notificador.WhatsappNotificador;
import interfaces.servico.UsuarioService;

public class app {
    public static void main(String[] args) {
        new UsuarioService(new EmailNotificador()).registrar("João");
        new UsuarioService(new WhatsappNotificador()).registrar("Maria");
        new UsuarioService(new SmsNotificador()).registrar("Alice");
    }
}
