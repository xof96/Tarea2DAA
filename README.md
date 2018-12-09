#Tarea 2 DAA

## Descripción

Esta tarea consta de 3 packages principales, los cuales son los siguientes:
* __dijkstra__: Contiene el algoritmo de Dijkstra para cada una
de las 3 estructuras de datos pedidas, Array, Heap, Fibonacci Heap.
* __structs__: Contiene las estructuras de datos que se usan en la tarea,
estas son:
    * _Graph_: Grafo que contiene arreglo de nodos y de vecinos, esta
    estructura se hizo a conveniencia de poder facilitar el trabajo
    de modelar el algoritmo.
    * _GraphWay_: Estrucura de datos que contiene un nodo (int), y un 
    peso (double), sirve para decrementar la llave correctamente.
    * _Heap_: Estructura del Heap.
    * __fibheap__: Package que contiene:
        * _FibonacciNode_: Nodo para el Heap de Fibonacci.
        * _FibonacciHeap_: Heap de Fibonacci.
* __test__: Contiene los test, tanto para responder las preguntas
de la tarea, como para testear las estructuras de datos que se
crearon. 

## Ejecución

* ### Preparativos
Para ejecutar el código, verificar que _javafx_ funcione correctamente, 
es decir, para el caso de IntelliJ, se debe checkear en el panel
de **Project > External Libraries > jfxrt.jar**, si no aparece, se 
debe agregar yendo a **File > Project Structure > Platform Settings >
SDKs > +**, y luego buscar en la ruta donde tiene instalado java,
debiera ser más o menos así _"/Java/jre/lib/ext/jfr.jar"_.

* ### Correr el código
El código funcional se encuentra en los archivos:
* _Test.java_: Contiene un test funcional del algoritmo en las 3
estructuras de datos. Imprime para el Array, Heap y FibonacciHeap,
el tiempo de ejecución, el camino de los nodos y los pesos.
* _FinalTest.java_: Contiene el test que entrega solo los tiempos 
de ejecución para el algoritmo, en una cantidad especificada de 
experimentos.