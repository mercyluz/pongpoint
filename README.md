## Pong Point 

## Descripción

Api programa donde puedes elliminar , actualizar, añadir, listar una reserva de mesas de tenis en una instalación deportiva


<p align="center">
	  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/logo.png"width=40% height=40%/>
</p>




## Herramientas



<p align="center">
	  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/herramientas.png"width=40% height=40%/>
</p>







## Ejecutar el Proyecto

To run app:
```bash
mvn spring-boot:run
```
## Base de datos
Nos conectamos a la consola de H2

```bash
# http://localhost:8080/h2-console
```
tablas Poingpoint 
<p align="center">
  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/cliente.png"width=50% height=50%/>
	
</p>
tablas 
<p align="center">
	  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/alltables.png"width=50% height=50%/>

## Endpoints


```bash
# http://localhost:8080/
```
GET ver las mesas.
<p align="center">
  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/gettable.png"width=50% height=50%/>
	
</p>
Delete Eliminar cliente
<p align="center">
	  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/borrarcliente.png"width=50% height=50%/>


</p>
POST agregar mesa
<p align="center">
	  <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/posttable.png"width=50% height=50%/>


## Estructura de la Aplicación

A continuación se muestra una visión general de los principales directorios y archivos en el proyecto



```
.
├── .mvn
│   └── wrapper
├── .vscode
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── factoriaf5
│   │   │           └── pongpoint
│   │   │               ├── controllers
│   │   │               ├── DTOS
│   │   │               ├── models
│   │   │               ├── repositories
│   │   │               └── services
│   │   └── resources
│   └── test
│       └── java
│           └── org
│               └── factoriaf5
│                   └── pongpoint
│                       ├── controllers
│                       ├── DTOS
│                       ├── models
│                       └── services


```
## Diagrama UML
<p align="center">
	  <img src="https://github.com/mercyluz/RockPaperScissor/blob/main/umlproyectopersonal.png"width=50% height=50%/>

</p>




```
## Ejecucion de los test
Para asegurarte de que todo está funcionando como se espera, puedes ejecutar las pruebas unitarias e integrales incluidas en el proyecto. Usa el siguiente comando para ejecutar todas las pruebas:"

```bash
mvn test
```
<p align="center">
	   <img src= "https://github.com/mercyluz/RockPaperScissor/blob/main/testproyectopersonal1.png"width=40% height=40%/>
</p>

