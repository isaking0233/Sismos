# Sistema de Predicción y Observación de Sismos

Este proyecto es un **sistema de predicción y observación de sismos** desarrollado en Spring Boot, con **autenticación de usuarios** y un frontend minimalista. Utiliza una base de datos MySQL poblada desde un archivo CSV y ofrece vistas de registro y consulta de eventos.

---

## Tecnologías

- Java 17
- Spring Boot
- Maven
- MySQL + phpMyAdmin
- Docker Compose (opcional para base de datos)


## Prerrequisitos

1. **Java 17** y **Maven** instalados en tu máquina.  
2. **MySQL** y **phpMyAdmin**, bien sea instalados localmente o mediante Docker Compose.  
   - Si usas Docker Compose, desde la raíz del proyecto:
     ```bash
     docker-compose up -d
     ```
   - phpMyAdmin quedará disponible en `http://localhost:8080` (por defecto).

---

## Pasos de instalación y ejecución

1. **Abre** la carpeta del proyecto en una terminal.  
2. **Importa** el CSV en la base de datos a través de phpMyAdmin:  
   1. Abre tu navegador en `http://localhost:8080`.  
   2. Accede a la base de datos (por ejemplo `loginsystem`).  
   3. Ve a la pestaña **Importar**.  
   4. Selecciona el archivo `src/main/resources/sismos_mexico.csv`.  
   5. Asegúrate de que:  
      - Formato: CSV  
      - Delimitador de campos: `,`  
      - La primera fila contiene nombres de columna.  
   6. Ejecuta la importación y verifica que la tabla **sismos** se haya poblado.  
3. **Ejecuta** la aplicación Spring Boot:
   ```bash
   mvn spring-boot:run
4. Evidencias y tutorial de uso

   ### Loggeo y registro
   Despues de haber importado el archivo csv a una base de datos y correr el proyecto
   vas a tener la posibilidad de registrarte y loggearte como en cualquier otra pagina web
   ![Registro](capturas/img0.png)
    ### Observar sismos y magnitudes
   Dentro del panel podras tener opciones como la gestion y modificaion de tu informacionde perfil
   y observar los diversos sismos que han emergido dentro de la republica Mexicana por regiones y magnitudes
   ![Registro](capturas/img1.png)
   ![Registro](capturas/img2.png)
    ### Predecir sismos segun la probabilidad de ocurrencia
   Se tiene la posibilidad de predecir sismo en base a los datos obtenidos anteriormente, usando probabilidad
   ![Registro](capturas/img3.png)
   ![Registro](capturas/img4.png)
   Asi funciona la predicción:
   Calcular probabilidad simple usando fórmula de Poisson

   𝑃(𝑛≥1)=1−𝑒^(−𝜆⋅t)

   Por cuadrante se calcula:
   𝜆
   λ = número de sismos ≥ magnitud mínima por año.
   Luego usamos:

   𝑃(𝑛≥1)=1−𝑒^(−𝜆⋅30)
 
   El resultado se transforma en un color para el cuadrante.

6. Pruebas de caja negra
    ### Pruebas en postman
    Endpoint de prueba: GET http://localhost:8080/api/sismos/count
   ![Registro](capturas/img5.png)
   ![Registro](capturas/img6.png)

   Cómo verificar en Postman:
   Selecciona método (GET o POST).
   URL: http://localhost:8080/register
   Params (solo para GET) o pestaña Body → x-www-form-urlencoded (para POST).

   Enviar y comprobar:
   Status en la barra superior.
   Cuerpo (HTML o JSON) y/o Location en headers si hay redirect.
   Cookies o Set-Cookie si manejas sesión inmediata.

   Resultados esperados:
   200 OK en ambos casos
   Body: el HTML de la página de registro/login
   Si mi controlador detecta email/password en query-string podrías mostrar un mensaje de “¡Bienvenido, pre-registro!” o simplemente ignorarlos y renderizar el formulario vacío
   No debe crear usuario ni hacer redirect en esta llamada
   ![Registro](capturas/img7.png)
7. Dockerizacion
   Es comveniente no dockerizarlo ya que no logramos implementar bien el uso del csv para importar la base de datos
   sin embargo si se logro dockerizar sin problemas, si es que se desea
   ![Registro](capturas/img8.png)
   
