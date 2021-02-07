# Week-Weather
Aplicación que muestra el pronóstico del clima actual en Santiago, CL  junto al pronóstico de los siguientes 8 días. Hace uso del API de climas de https://openweathermap.org/ Utiliza Clean Architecture, MVVM, DI con Dagger, Corrutinas.

Seccion Principal

En esta vista se encuentra dos sub secciones:

1) Clima actual de la ciudad : 
   Incluye temperatura, sensación térmica con descripción del clima, viento, presión y humedad.

2) Listado de Pronóstico de clima para 8 días
   Cada elemento incluye un día en formato timestamp, descripción del clima, temperaturas mínima y máxima.
   Al seleccionar un día del listado se va al detalle del día.
   
Sección Detalle del día

 Incluye información del día seleccionado, descripción del clima con temperaturas máxima y mínima, temperatura y sensación térmica de la mañana, tarde y noche del día consultado.
 En caso de que no se encuentre información del día, la aplicación lo señala con un aviso.
 
Para hacer consumo de las API en Openweathermap es necesario registrarse para obtener un Access Token. La construccíon del endpoint de consulta se compone de diversos parámetros entre los que se encuentran:
- latitud del lugar a consultar
- longitud del lugar a consultar
- lenguaje para describir el clima
- unidad de medida
- app Id

