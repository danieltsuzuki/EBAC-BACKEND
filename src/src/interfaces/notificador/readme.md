<b>Contexto:</b> Você está desenvolvendo um sistema de notificações. Ele deve ser capaz de notificar um usuário por e-mail, SMS ou WhatsApp, dependendo da implementação desejada. Passo a passo:

- Crie uma interface chamada Notificador com o método enviarMensagem, que recebe um parâmetro mensagem do tipo String.
- Implemente três classes que usam essa interface:
- - EmailNotificador
- - SmsNotificador
- - WhatsappNotificador

<b>Observação:</b> Cada uma dessas classes deve implementar o método enviarMensagem, que tem o retorno void, e a lógica de simular uma saída de mensagem, utilizando o System.out.println().
- Crie uma classe UsuarioService que tem um atributo do tipo Notificador. Utilize injeção de dependência via construtor para esse atributo.
- Crie um método dentro da classe UsuarioService chamado registrar, com o retorno do método do tipo void, e que receba um parâmetro nome do tipo String.

A lógica dentro do método deve ser essa:
```
{
System.out.println("Usuário " + nome + " registrado.");
notificador.enviarMensagem("Bem-vindo " + nome + "!");
}
```
- Teste sua aplicação criando instâncias diferentes do UsuarioService, passando diferentes notificadores.

Exemplos:

- new UsuarioService(new EmailNotificador()).registrar("João");

- new UsuarioService(new WhatsappNotificador()).registrar("Maria");