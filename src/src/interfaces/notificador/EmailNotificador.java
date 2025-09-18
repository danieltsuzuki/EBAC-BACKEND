package interfaces.notificador;

public class EmailNotificador implements Notificar{
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Email: " + mensagem);
    }
}
