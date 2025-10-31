# Prueba-Tecnica-Curso-1

Desarrollo de una Aplicación de Gestión de Clientes conectada a una Base de Datos SQL.



\# 🧩 GestUser – Aplicación de Gestión de Clientes



\*\*GestUser\*\* es una aplicación de consola desarrollada en \*\*Java\*\* que permite 

realizar una gestión completa de clientes mediante un \*\*CRUD\*\* conectado a una base de datos \*\*MySQL\*\* usando \*\*JPA/Hibernate\*\*.  

Su diseño busca ser simple, intuitivo y totalmente funcional desde la línea de comandos.



---



\## ⚙️ Funcionalidades principales

\- ➕ \*\*Crear\*\* nuevos clientes con: nombre, apellido, sexo, ciudad, fecha de nacimiento, teléfono y correo electrónico.  

\- 📋 \*\*Listar\*\* todos los clientes registrados.  

\- ✏️ \*\*Modificar\*\* datos de clientes existentes, campo por campo.  

\- ❌ \*\*Eliminar\*\* clientes por su ID.  

\- 🔍 \*\*Buscar\*\* clientes por ciudad.  

\- ⚠️ \*\*Validación\*\* de datos y mensajes claros de error o confirmación.



---



\## 🧱 Requisitos del sistema

\- ☕ \*\*Java 17 o superior\*\*  

\- 🗄️ \*\*MySQL\*\* en ejecución en el \*\*puerto 3306\*\* y la Base de Datos creada con nombre "clientes"

\- ⚙️ \*\*JPA / Hibernate\*\* configurado con la unidad de persistencia `miUnidad`  

\- 📦 Dependencia principal: `jakarta.persistence`



----------------------------------------------------------------------------------------------------------------------



\## 🖥️ Ejemplo de ejecución



A continuación se muestra un ejemplo de cómo se ve la aplicación al ejecutarla en la consola:



```console

Bienvenido a GestUser!

El programa Nª1 para la gestión de Clientes!



CRUD de Clientes

1 - Crear nuevo Cliente

2 - Listar Clientes

3 - Modificar un Cliente

4 - Eliminar un Cliente

5 - Buscar Clientes por Ciudad

0 - Salir del programa

Elija una opción: 1



Nombre: Ana

Apellido: López

Sexo (M/F): F

Ciudad: Madrid

Fecha de Nacimiento: 1990-05-10

Teléfono: 678123456

Correo electrónico: ana.lopez@mail.com



✅ Nuevo cliente registrado!



CRUD de Clientes

1 - Crear nuevo Cliente

2 - Listar Clientes

3 - Modificar un Cliente

4 - Eliminar un Cliente

5 - Buscar Clientes por Ciudad

0 - Salir del programa

Elija una opción: 2



Listado de clientes:

Cliente: id = 1, nombre = 'Ana', apellido = 'López', sexo = F, ciudad = 'Madrid', 

&nbsp;	fechaNacimiento = '1990-05-10', telefono = '666123456', correo = 'ana.lopez@mail.com'



CRUD de Clientes

1 - Crear nuevo Cliente

2 - Listar Clientes

3 - Modificar un Cliente

4 - Eliminar un Cliente

5 - Buscar Clientes por Ciudad

0 - Salir del programa

Elija una opción: 0



Adiós! Gracias por utilizar GestUser!







