package interfaces.notificador;

public class WhatsappNotificador implements Notificar{
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Whatsapp: " + mensagem);
    }
}
