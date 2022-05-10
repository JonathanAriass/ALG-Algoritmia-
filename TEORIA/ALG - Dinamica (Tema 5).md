# TEMA 5 - Programacion dinamica
## Play-off de baloncesto
<p>Dos equipos A y B disputan una eliminatoria de la fase final de la liga.<br>Jugaran 2n-1 partidos y el ganador sera el primer equipo que consigan n victorias.<br>El equipo A tiene una probabilidad constante p de ganar un partido, mientras que B tiene una probabilidad q de ganar (q = 1 - p).<br>Queremos conocer la probabilidad de que uno de estos equipos gane el play-off al principio de la eliminatoria y una vez jugados varios partidos.<br><br>P(i, j) se define como la probabilidad de que A gane la eliminatoria cuando le quedan por ganar i partidos a A y j partidos 
a B.<br>Se puede obtener facilmente el valor de P(i, j) segun la expresion:</p>
<ul>
<li>1 si i = 0 y j = 0</li>
<li>0 si i > 0 y j = 0</li>
<li>p * P(i-1, j) + q * P(i, j-1) si i > 0 y j > 0</li>
</ul>
<p> Por lo tanto el algoritmo de divide y venceras tendra el siguiente aspecto</p>

```
public double probabilidadDv(int i,int j)
{
	if (i==0)
		return 1.0;
	else
		if (j==0)
			return 0.0;
		else
			return probabilidadGanarA
			* probabilidadDv(i-1,j)
			+ (1-probabilidadGanarA)
			* probabilidadDv(i,j-1);
}
```

### Analisis del algoritmo
<p>Incoveniente: se estan repitiendo calculos innecesariamente.<br>La complejidad es exponencial, del orden de O(2**i+j) si i+j = n.<br>Para acelerar el algoritmo: declaramos una tabla del tamaño adecuado y vamos rellenando las entradas.<br><br>Divide y venceras divide los problemas en subproblemas y combina las soluciones par resolver el problema original.<br>El diseño de divide y venceras puede llegar a ser poco optimo porque se divida el problema inicial en un numero muy alto de subproblemas. Ademas de las posibles repeticiones de subproblemas que se solucionaran tantas veces como se repitan.</p>

### Ejemplo de ineficiencia (Fibonacci)

```
public static long fibDyV (int n)
{
	if (n<=1)
		return n;
	else return fibDyV(n-1)+fibDyV(n-2);
}
```
<p>Al desarrollar el arbol de ejecucion de este algoritmo se puede apreciar que se va a resolver el fibonacci de 1 muchisimas veces, por lo que este algoritmo para este problema es muy ineficiente.<br>Una forma de resolver este problema de una forma mas optima es con programacion dinamica, como por ejemplo:</p>
```
public static long fibPD (int n)
{
	long[]f=new long[n+1]; // subsoluciones
	f[0]=0;f[1]=1;
	for (int i=2;i<=n;i++)
		f[i]=f[i-1]+f[i-2];
	return f[n];
}
```
<p>La mejora de la eficiencia se puede tener en cuenta cuando el numero de problemas distintos es polinomico, podemos resolver cada subproblema una vez y podemos guardar su solucion para un uso posterior.<br>La idea de esto es evitar realizar dos veces el mismo subproblema.</p>

### Probabilidad de ganar play-offs con programacion dinamica

```
public double probabilidadPd(int i,int j)
{
	double[][] a= new double[i+1][j+1]; // necesitamos índices desde 0 hasta i
	
	for (int v= 1; v<j+1; v++) { // Rellenamos casos trivales
		a[0][v]= 1.0;
	}
	
	for (int u= 1; u<i+1; u++) {
		a[u][0]= 0.0;
	}
	
	for (int u= 1; u<i+1; u++) // Rellenamos el resto de las celdas
		for (int v= 1; v<j+1; v++) {
			a[u][v]= probabilidadGanarA * a[u-1][v]
						+ (1-probabilidadGanarA) * a[u][v-1];
		}
	
	return a[i][j];
}
```

### Comparacion divide y venceras / programacion dinamica
<ul>
<li>Divide y venceras es un metodo de refinamiento progresivo (descendente):</li>
	<ul>
	<li>Atacamos el caso completo que vamos dividiendo en subcasos mas pequeños.</li>
	</ul>
<li>Programacion dinamica es una tecnica ascendente:</li>
<ul>
	<li>Se empieza por los subcasos mas pequeños y combinando las soluciones se obtienen respuestas a subscasos cada vez mayores, hasta que llegamos al caso completo.</li>
	</ul>
</ul>
<p>Recordamos de nuevo la expresion de la probabilidad:</p>
<ul>
<li>1 si i = 0 y j = 0</li>
<li>0 si i > 0 y j = 0</li>
<li>p * P(i-1, j) + q * P(i, j-1) si i > 0 y j > 0</li>
</ul>
<p>La tabla con la traza de ejecucion (n = 3 y p = q - 0.5):</p>
|i/j| 0 | 1 | 2 | 3 |
|---|---|---|---|---|
| 0 |   | 1 | 1 | 1 |
| 1 | 0 | 0.5| 0.75| 0.87|
| 2 | 0 | 0.25 | 0.5 | 0.68 |
| 3 | 0 | 0.12 | 0.31 | 0.5 |

<p> La complejidad de este algoritmo es de O(n**2)</p>

# Ejemplos de programacion dinamica
## Aplicada a problemas de optimizacion
<p>Algoritmo de Floyd-Warshall de busqueda del camino minimo entre dos nodos en grafos dirigidos ponderados.</p>

## Principio de optimalidad de Bellman
<p>Una secuencia optima de decisiones que resuelve un problema debe cumplir que cualquier subsecuencia de decisiones, que tenga el mismo estado final, debe ser tambien optima respecto al subproblema correspondiente.<br><br>La aplicacion de dicho principio viene dada por:</p>
<ul>
<li>Si siempre se conoce cual es la decision que debe tomarse a continuacion para obtener la secuencia optima, el problema se resuelve con una estrategia voraz.</li>
<li>Principio de optimalidad, sigue siendo posible el ir tomando decisiones elementales, en la confianza de que la combinacion de ellas seguira siendo optima, pero sera entonces necesario explorar muchas secuencias de decisiones para dar con la correcta.</li>
</ul>

## Mochila 0/1 (sin fragmentacion)
Datos de un problema concreto:
* Numero de objetos: n = 3
* Peso limite: W = 10
| Objeto | 1 | 2 | 3 | 
|-|-|-|-|
|wi|6|5|5|
|vi|8|5|5|

Resolucion del problema con programacion dinamica:
* Tabla V
	* Filas: los objetos i
	* Columnas: pesos maximos de la mochila
* V[i, j] --> Valor maximo de los objetos que se transportan
* Solucion a nuestro problema V[n, W], V[3,10] (esquina inferior izquierda)

La funcion que permite calcular los valores de la matriz es la siguiente:
* -Infinito si j<0
* 0 si i = 0 y j >= 0
* max (V[i-1, j], V[i-1, j - wi] + vi) en otro caso
Siendo i el numero del objeto que deseamos meter en la mochila y j el peso maximo de la mochila.

Se va rellenando la tabla en funcion del peso de los objetos, por ejemplo al principio solo van a haber ceros puesto que el peso maximo de la mochila es menor que el de los objetos hasta el momento que llegamos a la quinta columna o sexta y ya se pueden meter objetos en la mochila. Siendo esto:
| i \ j | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|-------|---|---|---|---|---|---|---|---|---|---|----|
| 1     | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 8 | 8 | 8 | 8  |
| 2     | 0 | 0 | 0 | 0 | 0 | 5 | 8 | 8 | 8 | 8 | 8  |
| 3     | 0 | 0 | 0 | 0 | 0 | 5 | 8 | 8 | 8 | 8 | 10 |

Siendo la solucion a este problema la casilla V[3,10] = 10 (solucion optima en este caso, puede haber mas de una).
