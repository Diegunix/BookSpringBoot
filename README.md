# book-application
## Spring Boot, JSF Primefaces and Test with Spock, Geb and Selenium.

### Objetivo
En este ejemplo se ha construido un pequeño proyecto que gestiona una biblioteca añadiendo, eliminando y listando libros.
Sobre este ejemplo se han creado distintos tipos de test:
- Unos test unitarios sobre los servicios donde se mockean los repository.
- Unos test de integración donde se prueba mediante selenium la aplicación.
- Y finalmente unos test end to end donde se hacen pruebas reales sobre la aplicación que debe estar levantada.

### Claves

Se han creado dos perfiles en maven:
* sinTest: se compila sin ejecutar ningun test.
* it: compila la aplicación ejecutando todos los test de integración.


Por defecto solo se ejecutan los test unitarios al compilar la aplicación.

---