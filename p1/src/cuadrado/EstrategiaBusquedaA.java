package es.udc.sistemasinteligentes.cuadrado;

import es.udc.sistemasinteligentes.*;

import java.util.*;

public class EstrategiaBusquedaA implements EstrategiaBusquedaInformada {
    int nodosCreados = 0, nodosExpandidos = 0;

    private Nodo[] reconstruyeSol(Nodo n) {
        ArrayList<Nodo> sol = new ArrayList<>();
        Nodo a = n;
        while (a != null) {
            sol.add(a);
            a = a.getP();
        }
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }

    private List<Nodo> sucesores(Nodo n, ProblemaBusqueda p, Heuristica heuristica) {
        List<Nodo> suc = new ArrayList<>();
        Accion[] accionesDisponibles = p.acciones(n.getE());
        for (Accion acc : accionesDisponibles) {
            nodosCreados++;
            Estado sc = p.result(n.getE(), acc);
            Nodo nh = Nodo.crearNodo(sc, n, acc, n.getG() + 1, heuristica.evalua(sc));
            suc.add(nh);
        }
        return suc;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica heuristica) throws Exception {
        int i = 1;
        Estado estadoInicial = p.getEstadoInicial();
        float hIni = heuristica.evalua(estadoInicial);
        Nodo n = Nodo.crearNodo(estadoInicial, null, null, 0, hIni);
        System.out.println((i++) + " - Empezando búsqueda en " + estadoInicial);

        PriorityQueue<Nodo> frontera = new PriorityQueue<>();
        Set<Estado> explorados = new HashSet<>();
        frontera.add(n);

        while (!frontera.isEmpty()) {
            n = frontera.poll();
            Estado s = n.getE();

            if (p.esMeta(s)) {
                System.out.println((i++) + " - FIN - " + s);
                System.out.println("Nodos explorados:" + nodosExpandidos + " Nodos creados:" + nodosCreados);
                return reconstruyeSol(n);
            }

            explorados.add(n.getE());
            List<Nodo> h = sucesores(n, p, heuristica);

            for (Nodo suc : h) {
                Estado estadoSuc = suc.getE();
                if (!explorados.contains(estadoSuc)) {
                    nodosExpandidos++;
                    System.out.println((i++) + " - " + estadoSuc + " NO explorado");
                    frontera.add(suc);
                } else {
                    System.out.println((i++) + " - " + estadoSuc + " ya explorado");
                }
            }

        }
        throw new Exception("No se ha podido encontrar una solución");
    }
}
