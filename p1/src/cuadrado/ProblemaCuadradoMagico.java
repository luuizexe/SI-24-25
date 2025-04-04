package es.udc.sistemasinteligentes.cuadrado;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.*;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadrado extends Estado {
        public int[][] cuadrado;

        public EstadoCuadrado(int[][] cuadrado) {
            this.cuadrado = cuadrado;
        }

        @Override
        public String toString() {
            return Arrays.deepToString(cuadrado);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EstadoCuadrado that = (EstadoCuadrado) o;
            return Objects.deepEquals(cuadrado, that.cuadrado);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(cuadrado);
        }
    }


    public static class AccionCuadrado extends Accion {
        private final int fila, columna, valor;

        public AccionCuadrado(int fila, int columna, int valor) {
            this.fila = fila;
            this.columna = columna;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return "INSERTAR " + valor + " en (" + fila + "," + columna + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado esCu = (EstadoCuadrado) es;
            return esCu.cuadrado[fila][columna] == 0;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esCu = (EstadoCuadrado) es;
            int tam = esCu.cuadrado.length;
            int[][] nuevoC = new int[tam][tam];
            for (int i = 0; i < tam; i++) {
                System.arraycopy(esCu.cuadrado[i], 0, nuevoC[i], 0, tam);
            }
            nuevoC[fila][columna] = valor;

            return new EstadoCuadrado(nuevoC);
        }
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado estado = (EstadoCuadrado) es;
        int tam = estado.cuadrado.length;
        int sumaEsperada = (tam * (tam * tam + 1)) / 2;

        for (int i = 0; i < tam; i++) {
            int sumaFila = 0, sumaColumna = 0;
            for (int j = 0; j < tam; j++) {
                sumaFila += (estado.cuadrado)[i][j];
                sumaColumna += (estado.cuadrado)[j][i];
            }
            if (sumaFila != sumaEsperada || sumaColumna != sumaEsperada) {
                return false;
            }
        }

        int sumaDiagonal1 = 0, sumaDiagonal2 = 0;
        for (int i = 0; i < tam; i++) {
            sumaDiagonal1 += estado.cuadrado[i][i];
            sumaDiagonal2 += estado.cuadrado[i][tam - 1 - i];
        }
        return sumaDiagonal1 == sumaEsperada && sumaDiagonal2 == sumaEsperada;
    }


    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadrado esCu = (EstadoCuadrado) es;
        List<Accion> acciones = new ArrayList<>();
        Set<Integer> valoresPresentes = new HashSet<>();
        int tam = esCu.cuadrado.length;
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (esCu.cuadrado[i][j] != 0) {
                    valoresPresentes.add(esCu.cuadrado[i][j]);
                }
            }
        }

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (esCu.cuadrado[i][j] == 0) {
                    for (int valor = 1; valor <= tam * tam; valor++) {
                        if (!valoresPresentes.contains(valor)) {
                            acciones.add(new AccionCuadrado(i, j, valor));
                        }
                    }
                }
            }
        }
        return acciones.toArray(new Accion[0]);
    }

    public ProblemaCuadradoMagico(ProblemaCuadradoMagico.EstadoCuadrado estadoInicial) {
        super(estadoInicial);
    }
}
