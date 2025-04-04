package es.udc.sistemasinteligentes.cuadrado;

import es.udc.sistemasinteligentes.EstrategiaBusquedaInformada;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ProblemaCuadradoMagico problema = new ProblemaCuadradoMagico(
                new ProblemaCuadradoMagico.EstadoCuadrado(
                        new int[][]{{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 0, 0}}));
        HeuristicaCuadrado h = new HeuristicaCuadrado();
/*
        EstrategiaBusqueda buscadorAnch = new EstrategiaBusquedaGrafoAnchura();
        System.out.println(Arrays.toString(buscadorAnch.soluciona(problema)));
*/
        /*
        EstrategiaBusqueda buscadorProf = new EstrategiaBusquedaGrafoProfundidad();
        System.out.println(Arrays.toString(buscadorProf.soluciona(problema)));*/

        EstrategiaBusquedaInformada buscadorA = new EstrategiaBusquedaA();
        System.out.println(Arrays.toString(buscadorA.soluciona(problema, h)));
    }
}
