package padroesDeProjeto.model.padroes;

import padroesDeProjeto.model.Esporte;

public class PraticarBasquete implements PraticavelStrategy {
    @Override
    public void praticar(Esporte esporte) {
        System.out.println(esporte.listarAtletas() + " estão praticando basquete");
    }
}
