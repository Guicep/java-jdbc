# JDBC Java
Proyecto sencillo para explorar la persistencia en java utilizando Java Database Connectivity (JDBC).

## Connection Pool
En vez de que por cada consulta se tenga que abrir o cerrar una conexion a la base de datos se opta por implementar una estructura de conexiones abiertas, cuando se requiera una se la provee al usuario y una vez que termine de usarla vuelve a la cola para que alguien mas pueda usarla lo que mejora la performance de la base de datos.

![alt text][logo]

[logo]: https://github.com/Guicep/java-jdbc/tree/main/md/connectionpool.png "connectionpool"
