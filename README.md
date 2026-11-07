SISTEMA DE GESTION DE RESERVAS DE HOTEL

DESCRIPCION DEL PROYECTO
Aplicación de consola desarrollada en Java para administrar las reservas de un hotel. 
Incluye dos tipos de usuario (cliente y personal del hotel que está por jerarquía de niveles --> [0 administrador, 1 gerente, 2 recepción]), 
manejo de habitaciones, registro de reservas-clientes y un sistema de créditos (puntos) para los clientes. Todos los usuarios tienen nombre, 
cédula de 10 dígitos numéricos, estado activo o inactivo y un historial de observaciones con fecha. Las habitaciones se numeran de forma consecutiva y tienen tipo, 
descripción, estado (ocupada o desocupada) y la cédula de su ocupante. Cada reserva se guarda como un registro con la cédula del cliente, la cédula de quien la procesó, 
una nota y la fecha, y vence a las 9:00 del último día de la estadía, momento en que la habitación vuelve a quedar disponible. 
El cliente puede reservar indicando el número de habitación y la cantidad de días (mínimo 1), siempre que esté disponible,
y por cada día reservado acumula 50 puntos. El personal puede reservar a nombre de un cliente o del hotel y cancelar reservas,
además de consultar los datos de los usuarios, las habitaciones y los registros. Los permisos dependen del nivel: el administrador 
y el gerente (0 y 1) pueden crear personal, cambiar niveles y activar o desactivar cuentas indicando una razón, mientras que los 
tres niveles pueden registrar clientes y cancelar reservas. El programa se ejecuta desde un menú principal en consola con las opciones de usuario cliente, 
usuario personal y salir.

TECNOLOGIAS UTILIZADAS
  - Lenguaje: Java
  - JDK: 21 o superior
  - IDE: Apache NetBeans (proyecto con "Java with Ant")
  - Librerias: Scanner: java.util.Scanner;
	       Simple Date Format: java.text.SimpleDateFormat;
	       Calendar: java.util.Calendar;
	       ArrayList: java.util.ArrayList;

INSTRUCCIONES DE EJECUCION
NetBeans
  1. Abrir NetBeans y seleccionar File > Open Project.
  2. Elegir la carpeta Gestion_reservas_hotel.
  3. Clic derecho sobre el proyecto y "Clean and Build".
  4. Clic derecho sobre el proyecto y "Run" (o presionar F6).

AUTORES
ACOSTA MORENO DARLYN LEANDRO
MARTILLO COELLO JOSUE ANDRES
MONTOYA AVILA AXEL EMANUEL
