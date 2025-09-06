## Você vai criar uma Agenda de Eventos Simples.

- Crie um enum chamado DiaDaSemana, com os dias da semana:
- - SEGUNDA, TERÇA, QUARTA, QUINTA, SEXTA, SÁBADO e DOMINGO.
- Crie uma classe Evento com os seguintes atributos:
- - nome (String)
- - dataHora (LocalDateTime)
- - diaDaSemana (DiaDaSemana)
- A classe deve ter os seguintes métodos:
- - Construtor da Classe, que recebe o nome do evento, a data e hora do evento, e o dia da semana.
- - Método exibirEvento() que imprime os detalhes do evento, como nome, data e hora.
- Crie uma instância de Evento, representando um evento agendado para a data atual na qual você está realizando o exercicio.
- Adicione 5 dias à data do evento utilizando o método plusDays() e imprima a nova data e hora do evento.
- Converta a data e hora do evento para o fuso horário de São Paulo (BRT) e para GMT, utilizando ZoneId(efetua uma busca no google).
- Imprima as datas e horas convertidas, de São Paulo e GMT.