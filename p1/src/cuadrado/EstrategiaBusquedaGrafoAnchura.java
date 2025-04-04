package es.udc.sistemasinteligentes.cuadrado;

import es.udc.sistemasinteligentes.*;

import java.util.*;

public class EstrategiaBusquedaGrafoAnchura implements EstrategiaBusqueda {
    int nodosCreados = 0, nodosExpandidos = 0;

    public Nodo[] reconstruyeSol(Nodo n) {
        ArrayList<Nodo> sol = new ArrayList<>();
        Nodo a = n;
        while (a != null) {
            sol.add(a);
            a = a.getP();
        }
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }

    public List<Nodo> sucesores(Nodo n, ProblemaBusqueda p) {
        List<Nodo> suc = new ArrayList<>();
        Accion[] accionesDisponibles = p.acciones(n.getE());
        for (Accion acc : accionesDisponibles) {
            nodosCreados++;
            Estado sc = p.result(n.getE(), acc);
            Nodo nh = Nodo.crearNodo(sc, n, acc);
            suc.add(nh);
        }
        return suc;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        int i = 1;
        Estado estadoActual = p.getEstadoInicial();
        Nodo n = Nodo.crearNodo(estadoActual, null, null);
        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        if (p.esMeta(estadoActual)) {
            System.out.println((i++) + " - FIN - " + estadoActual);
            System.out.println("Nodos explorados:" + nodosExpandidos + " Nodos creados:" + nodosCreados);
            return reconstruyeSol(n);
        }

        Queue<Nodo> frontera = new LinkedList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        frontera.add(n);

        while (!frontera.isEmpty()) {
            n = frontera.poll();
            Estado s = n.getE();
            explorados.add(s);

            List<Nodo> h = sucesores(n, p);
            for (Nodo suc : h) {
                Estado estadoSuc = suc.getE();
                if (p.esMeta(estadoSuc)) {
                    System.out.println((i++) + " - FIN - " + estadoSuc);
                    System.out.println("Nodos explorados:" + nodosExpandidos + " Nodos creados:" + nodosCreados);
                    return reconstruyeSol(suc);
                }
                if (!explorados.contains(estadoSuc)) {
                    nodosExpandidos++;
                    frontera.add(suc);
                    System.out.println((i++) + " - " + estadoSuc + " NO explorado");
                } else {
                    System.out.println((i++) + " - " + estadoSuc + " ya explorado");
                }
            }
        }
        throw new Exception("No se ha podido encontrar una solución");
    }
}

