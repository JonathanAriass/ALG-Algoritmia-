# Tema 6 - Backtracking
### Problemas representados en forma de grafos
Muchos problemas se pueden concebir en terminos de grafos abstractos.
Un nodo del grafo representa el estado del problema. Las aristas representan cambios validos.
Para solucionar el problema debemos buscar un nodo solucion y un camino en el grafo asociado.
Por ejemplo como en la practica de backtracking, un nodo representa una posible solucion para la recomposicion de la imagen y la arista es el añadir una nueva imagen al subproblema.

### Ejemplo de recorrido en profundidad de un grafo
```
public recorridoProfundidad(Grafo g)  
{  
	Nodo n;  
	
	for (; g.hayNodos(); n= g.siguienteNodo())  
		if (!n.getVisitado())  
			recorridoProfundidad(n);  
}  

private recorridoProfundidad(Nodo n)  
{  
	Nodo nodoAdyacente;  
	n.setVisitado(true);  
	
	for (; n.quedanAdyacentes(); nodoAdyacente= n.getSiguienteAdyacente())  
		if (!nodoAdyacente.getVisitado())  
			recorridoProfundidad(nodoAdyacente);  
}
```
En este ejemplo se van recorriendo los nodos segun un grafo pasado por parametro. Se obtiene el primer nodo del grafo y se comprueba si ya ha sido visitado, en caso de que no lo este se llama a la funcion que recorre el grafo en profundidad a traves de este nodo y lo marca como visitado, asi recursivamente hasta que todos los nodos esten visitados.

### Problemas de backtracking
Cuando un grafo tiene un numero muy elevado de nodos es imposible construirlo explicitamente, por lo que se tienen que ir descartando soluciones una vez se va construyendo el arbol de soluciones. De esta forma nos ahorramos tiempo de computacion y espacio en memoria al ir descartando nodos (soluciones).

### Construccion de una solucion del problema
Cada solucion de un problema de backtracking se puede expresar como un vector (x1, x2 , ..., xn) en el cual se encontrara informacion para posteriormente analizar esta solucion. Este vector se va construyendo progresivamente, es decir, que para un nivel k la solucion es parcial hasta que el nivel k llegue al nivel n de altura.

### Recorrido en profundidad del arbol
Con la tecnica del backtracking se realiza un recorrido en profundidad del arbol implicito, cuyo objetivo es ir encontrando los estados que sean solucion al problema.
Se puede encontrar una unica solucion y parar la ejecucion y bien encontrar mas de una solucion (o bien todas) para asi encontrar la solucion optima.

En caso de que el recorrido que se este ejecutando en un instante no tiene exito, es decir, no encuentra una solucion, se vuelve atras. Volver atras quiere decir que se explora el nodo hermano o hijo del arbol en caso de que los tenga sin explorar.

El esquema general de Backtracking tiene la siguiente forma:

```
public static void backtracking(Estado e)  
{  
	if (e.esSolucion()) {  
		System.out.println(e);  
		haySolucion= true;  
	}  
	else {  
		Estado estadoHijo= null;  
		while(e.hasNextHijos() && !haySolucion) {  
			// siguiente estado hijo válido  
			estadoHijo= e.nextHijo();  
			if (estadoHijo!=null) // puede que no queden hijos válidos  
				backtracking(estadoHijo);  
		}  
	}  
}
```

Se tendra que tener en cuenta tambien la clase **Estado**:

```
public interface Estado  
{  
	/** Devuelve true si este estado es una solución al problema */  
	public abstract boolean esSolucion();  
	
	/** Devuelve el siguiente hijo válido del estado  
	* @return devuelve una referencia al siguiente hijo válido, null si no hay  
	*/  
	public abstract Estado nextHijo();
	  
	/** Devuelve true si quedan hijos *posibles* (puede que no quede ninguno  
	válido)  
	* @return true- si quedan hijos posibles */  
	public abstract boolean hasNextHijos();  
}
```

## Ejemplo de resolucion de problema con backtracking
Pongamos el ejemplo de las reinas en un tablero de ajedrez de tal forma que se puedan colocar tantas como se pueda sin que se coman entre si. El codigo tiene el siguiente aspecto:

<details><summary>El codigo tiene el siguiente aspecto:</summary><br/>

Clase completa
```java
public class AjedrezReinasUna
{
	static int n;
	static int[]sol; //indica nº fila en que se coloca una reina
	static boolean[]a;//indica si se puede colocar una reina en la fila i
	static boolean[]b;//indica si se puede colocar una reina en la diagonal i+j
	static boolean[]c;//indica si se puede colocar una reina en la diagonal i-j+n-1
	static boolean seEncontro; // ¿solución encontrada?

	public static void main (String arg[])
	{
		n=Integer.parseInt(arg[0]);
		sol = new int [n];
		a= new boolean [n];
		for (int i=0;i<n;i++) a[i]=true;
	
		b= new boolean [2*n-1];
		for (int i=0;i<2*n-2;i++) b[i]=true;
	
		c= new boolean [2*n-1];
		for (int i=0;i<2*n-2;i++) c[i]=true;
	
		seEncontro=false;
		
		backtracking (0);
	
		if (!seEncontro) System.out.println ("NO HAY SOLUCION");
	}

  

	static void backtracking (int j)
	{
	
		if (j==n) // ya están colocadas las n reinas
		{
			seEncontro=true;
			System.out.println("SOLUCION ENCONTRADA");
			for (int k=0;k<n;k++)
				System.out.println("COLUMNA "+k+" ** FILA "+sol[k]);
			System.out.println ();
		}
		else
			for (int i=0;i<n;i++)
				if (a[i] && b[i+j] && c[i-j+n-1]
					&& !seEncontro) // CORTOCIRCUITO
				{
					sol[j] =i;	
					a[i]=false;
					b[i+j]=false;
					c[i-j+n-1]=false;
	
					System.out.println ();
					for (int k=0;k<n;k++)
						System.out.println("COLUMNA "+k+" ** FILA "+sol[k]);
					System.out.println ();
	
					backtracking (j+1);
	
					a[i]=true;
					b[i+j]=true;
					c[i-j+n-1]=true;
				}
	}
}
```

</details>


