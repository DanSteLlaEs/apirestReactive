# apirestReactive
ApiRest para un sitema de control de inventario,Operaciones CRUD

Pasos para correr el proyecto

1* descargar el proyecto
2 revisar que tenga las dependencias que son utilizadas
  *org.springframework.boot:spring-boot-starter-data-mongodb-reactive
  *org.springframework.boot:spring-boot-starter-webflux
  *io.springfox', name: 'springfox-boot-starter', version: '3.0.0'
3 se revisa en la clase principal que se ingresa unos valores para probar la api.
4 se ejecuta y se prueba por medio de postman las api
  * GET= http://localhost:8080/api/productos
  * GET/id = http://localhost:8080/api/productos/1
  * POST= http://localhost:8080/api/productos lleva un body: {
    "id": 10,
    "nombre": "TV Panasonic Pantalla LCD",
    "descripcion": "holaaaaaaaaa ",
    "precio": 456.89,
    "cantidad": 10
}
 *PUT/id   = http://localhost:8080/api/productos/10 lleva un body de lo que se quiere editar
{
    "nombre": "TV Panasonic Pantalla LCD",
    "descripcion": "lisa ",
    "precio": 456.89,
    "cantidad": 10
}
*DELETE/id = http://localhost:8080/api/productos/1
