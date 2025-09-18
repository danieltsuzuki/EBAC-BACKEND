package interfaces.notificador;

public class SmsNotificador implements Notificar {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Sms: " + mensagem);
    }
}
