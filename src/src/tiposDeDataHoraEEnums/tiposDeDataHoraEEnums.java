package tiposDeDataHoraEEnums;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class tiposDeDataHoraEEnums {
    public static void main(String[] args) {
        Evento evento = new Evento("Reunião de Projeto",
            java.time.LocalDateTime.of(2025, 9, 6, 12, 30),
            DiaDaSemana.SABADO);

        evento.exibirEvento();

        evento.setDataHora(evento.getDataHora().plusDays(5));
        System.out.println("ADICIONANDO 5 DIAS");
        evento.exibirEvento();

        ZonedDateTime dataSP = evento.getDataHora().atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println("FUSO SAO PAULO: " + dataSP);

        ZonedDateTime dataGMT = evento.getDataHora().atZone(ZoneId.of("GMT"));
        System.out.println("FUSO GMT: " + dataGMT);
    }
}
