# Ramificacion y poda
## Exploracion de un arbol de estados
Esta tecnica trata de explorar un arbol implicito, igual que en backtracking. Un nodo del arbol representa el estado del problema. Las aristas representan cambios validos.
Para solucionar el problema debemos buscar el nodo solucion y un camino en el arbol asociado.

Al contrario de backtracking el recorrido que se toma es un recorrido en anchura y no en profundidad. Esto viene dado por la creacion de todos los nodos hijos antes de pasar al proximo nivel del arbol. Un ejemplo de este recorrido es el siguiente:

![Recorrido en profundidad](Recorrido_profundidad.png)
