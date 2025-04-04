<h1>Practica 4</h1>

Para el actions

Supongamos [0,n) el intervalo de indices de las variables del que participan en la FO

Actions:
1º Si no es posible seguir, devuelvo una lista vacía
2º Si es la ultima variable, devuelvo una lista con la mejor opción
3º Una lista de varias opciones[alternativa1,  2 ,3 …..]
Que significa que no es posible seguir:
	- Ha llegado al limite superior del intervalo
	- Las propiedades individuales indican solución
La mejor opción SUELE SER CASI SIEMPRE opción voraz (GreedyActions) -> la mejor según la función objetivo.
