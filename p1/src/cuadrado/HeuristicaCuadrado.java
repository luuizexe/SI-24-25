package es.udc.sistemasinteligentes.cuadrado;

import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.Estado;


public class HeuristicaCuadrado extends Heuristica {
/*
    @Override
    public float evalua(Estado es) {
        ProblemaCuadradoMagico.EstadoCuadrado estado = (ProblemaCuadradoMagico.EstadoCuadrado) es;
        int tam = estado.cuadrado.length;
        int sumaEsperada = (tam * (tam * tam + 1)) / 2;
        float diferenciaTotal = 0;

        for (int i = 0; i < tam; i++) {
            int sumaFila = 0, sumaColumna = 0;
            for (int j = 0; j < tam; j++) {
                sumaFila += estado.cuadrado[i][j];
                sumaColumna += estado.cuadrado[j][i];
            }
            diferenciaTotal += Math.abs(sumaEsperada - sumaFila);
            diferenciaTotal += Math.abs(sumaEsperada - sumaColumna);
        }

        int sumaDiagonal1 = 0, sumaDiagonal2 = 0;
        for (int i = 0; i < tam; i++) {
            sumaDiagonal1 += estado.cuadrado[i][i];
            sumaDiagonal2 += estado.cuadrado[i][tam - 1 - i];
        }
        diferenciaTotal += Math.abs(sumaEsperada - sumaDiagonal1);
        diferenciaTotal += Math.abs(sumaEsperada - sumaDiagonal2);

        return diferenciaTotal;
    }*/

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado estado = (ProblemaCuadradoMagico.EstadoCuadrado) e;
        int n = estado.cuadrado.length;
        int casillasVacias = 0;

        // Cada casilla sin poner se suma 1 al coste
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (estado.cuadrado[i][j] == 0) {
                    casillasVacias++;
                }
            }
        }
        return casillasVacias;
    }
}