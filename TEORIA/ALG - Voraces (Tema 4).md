# TEMA 4 - Algoritmos voraces (greedy) #
## Problema de la mochila ## 
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
### Ejemplo de problema (n = 5, W (límite) = 100)
| Mineral    | 1  | 2  | 3  | 4  | 5  |
|------------|----|----|----|----|----|
| w (Tm)     | 10 | 20 | 30 | 40 | 50 |
| v (x1000$) | 20 | 30 | 66 | 40 | 60 |
<p>Tenemos dos posibilidades (heurísticos) para resolver este problema</p>
<ul>
<li>Selección del objeto más valioso</li>
<li>Selección de objeto menos pesado</li>
</ul>
