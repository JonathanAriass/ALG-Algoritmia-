# TEMA 4 - Algoritmos voraces (greedy) #
## Problema de la mochila
<p>n objetos / tipos y una mochila para transportar los objetos. Para cada objeto i = 1, 2, ..., n tiene un peso wi y un valor vi. La mochila puede llevar un peso que no sobrepase W. El objetivo de este problema es maximizar el valor de los objetos transportados respetando la limitación de peso.
</p>
<p> También podemos tener el mismo problema pero la forma de resolverlo es más compleja al tener fragmentación de objetos, asignando una fracción del objeto a la mochila.</p>

## Algoritmos voraces ##
<ul>
<li>Los algoritmos voraces se utilizan en problemas de optimización.</li>
<li>La solución se construye paso a paso.</li>
<li>Basan la busqueda de la solución óptima al problema en buscar localmente la solución óptima.</li>
<li>Para buscar la solución óptima se hace uso de heurísticos, que son reglas basadas en algún conocimiento del problema.</li>
</ul>

### Elementos del problema ###
<ul>
<li>Conjunto de candidatos, las n entradas del problema.</li>
<li>Función de selección que en cada momento determine el candidato para formar la solución (práctica el ZNCC).</li>
<li>Función que compruebe el subcojunto de candidatos que es factible (que pueda contener un solución).</li>
<li>Función que compruebe si un subconjunto es solución.</li>
<li>Función objetivo que determine el valor de la solución hallada.</li>
</ul>

### Toma de decisiones en voraces ###
<p>Estos algoritmos toman decisiones en base a la información que tienen localmente, es decir, no se preocupa de lo que pueda venir después ni de los datos ya analizados.</p>
<p>
Estos algoritmos nunca reconsideran una decisión. La ventaja principal de este tipo de algoritmos es que son faciles de programar y diseñar.
</p>

### Esquema general para resolver problemas con voraces ###
<ul>
<li>Candidatos: los n objetos</li>
<li>Función de selección de un nuevo elemento: en función del heurístico (estado del problema que suele ser un vector de estados)</li>
<li>Función para comprobar si una solución es factible (Sumatorio de los pesos es menor que el paso límite)</li>
</ul>

### Ejemplo de problema (n = 5, W (límite) = 100) ###
| Mineral    | 1  | 2  | 3  | 4  | 5  |
|------------|----|----|----|----|----|
| w (Tm)     | 10 | 20 | 30 | 40 | 50 |
| v (x1000$) | 20 | 30 | 66 | 40 | 60 |
<p>Tenemos dos posibilidades (heurísticos) para resolver este problema</p>
<ul>
<li>Selección del objeto más valioso (decreciente vi)</li>
<li>Selección de objeto menos pesado (creciente wi)</li>
</ul>

| Objetos    | 1  | 2  | 3  | 4  | 5  | Valor |
|------------|----|----|----|----|----|-------|
| Decreciente (vi)     | 0  | 0  | 1  | 0.5| 1  |146|
| Creciente (wi) | 1 | 1 | 1 | 1 | 0 |156|

### Mejorando el heurístico ###
<ul>
<li>Uno que seleccione el objeto restante con mayor coeficiente valor / peso</li>
<li>Dicho heurístico nos proporciona la solución óptima</li>
</ul>

| Objetos    | 1  | 2  | 3  | 4  | 5  | Valor |
|------------|----|----|----|----|----|-------|
| Valor / peso     | 2  | 1.5  | 2.2  | 1| 1.2  ||
| Decreciente (v/w) | 1 | 1 | 1 | 0 | 0.8 |164|

### Mochila sin fragmentación
<p>En esta variante (mochila 0/1) no se pueden dividir los objetos. <br>Esto puede conllevar cambios en el desarrollo de los heurísticos. Pongamos un ejemplo para ver este cambio ( para n=3, W=20 ):</p>

|Mineral | 1 | 2 | 3 |
|--------|---|---|---|
|w (Tm) | 10 | 10 | 12 |
|v (x1000$) | 20 | 20 | 36 |
|v/w | 2 | 2 | 3 |

<ul>
<li>Heuristico v/w --> resultado v = 36</li>
<li>Valor optimo v = 40 (con Decreciente vi o Creciente Wi)</li>
</ul>

<p>Con estos datos vemos que utilizando el heuristico de v/w no obtenemos el valor optimo.</p>
## Caracteristicas algoritmos voraces
<ul>
<li>La principal caracteristica de estos algoritmos:</li>
	<ul>
	<li>Son rapidos y faciles de implementar</li>
	<li>Dependen totalmente de la calidad del heuristico</li>
	</ul>
<li>Si iteramos sobre el conjunto de candidatos la complejidad sera de O(n**2)</li>
<li>Si se hace una ordenacion previa del conjunto de candidatos de forma que:</li>
	<ul>
	<li>La funcion de seleccion no tenga que recorrer todo el array</li>
	<li>Se va seleccionando o descartando cada elemento cada vez una posicion nueva (lineal)</li>
	<li>Complejidad: O(n) + la complejidad de la ordenacion</li>
	</ul>
</ul>

## Esquema general de algoritmos voraces
```
public Estado realizarVoraz(List candidatos)  
{  
	Estado estadoActual= new Estado();  
	while (!candidatos.esVacio() && !estadoActual.esSolucion())  
	{  
		/* Elige la mejor componente  
		* en función de un determinado heurístico */  
		Object x= SeleccionarCandidato(candidatos); // heurístico  
		if (estadoActual.esFactible(x))  
			estadoActual.add(x);  
	}  
	if (estadoActual.esSolucion())  
		return estadoActual;  
	else  
		return null; // No se ha encontrado una solución  
}
```


# Ejemplos de algoritmos voraces
## El problema del cambio
<ul>
<li>Diseñar un algoritmo para pagar una cierta cantidad, utilizando el menor numero posible de monedas.</li>
<li>Ejemplo:</li>
	<ul>
	<li>Tenemos que pagar 2.89$</li>
	</ul>
<li>¿Heuristico?</li>
	<ul>
	<li>Funcion seleccion: Dar la moneda de mayor valor posible</li>
	<li>Funcion factibilidad: **NO** excede lo que queda por devolver</li>
	<li>Solucion: 1 moneda de 2$, 1 moneda de 50 cent, 1 moneda de 20 cent, 1 moneda de 10 cent, 1 moneda de 5 cent, 2 monedas de 2 cent. --> Solucion optima</li>
	</ul>
</ul>

<p>No siempre sera la solucion optima, por ejemplo:</p>
<ul>
<li>Pagar 0.6$</li>
<li>Monedas disponibles: 0.5$, 0.2$, 0.02$</li>
<li>Solucion heuristico: 0.5$ + 0.02$ * 5 --> 6 monedas</li>
<li>Solucion optima: 0.2$ * 3 --> 3 monedas</li>
</ul>

<p>Este problema se podria resover con backtracking, desarrollando todas las posiblidades posibles.</p>
### Implementacion del problema del cambio
```
public void calcularCambio(int[] monedas, int cantidad)  
{  
	int tipoMon= 0;  
	while (cantidad>0 && tipoMon<monedas.length)  
	{  
		// Estado es factible / valido  
		if (cantidad-monedas[tipoMon]>=0)  
		{  
			// nueva moneda en el conjunto solución  
			solucion[tipoMon]++;  
			cantidad-= monedas[tipoMon];  
		}  
		else  
			// pasa a comprobar la moneda de valor inferior  
			tipoMon++;  
	}  
}
```
