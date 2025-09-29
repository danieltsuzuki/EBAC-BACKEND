package padroesDeProjeto.model.padroes;

import padroesDeProjeto.model.Esporte;

public class PraticarFutebol implements PraticavelStrategy {
    public void praticar(Esporte esporte) {
            System.out.println(esporte.listarAtletas() + " estão praticando futebol");
    }
}
