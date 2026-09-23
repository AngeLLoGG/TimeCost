# TimeCost

TimeCost es una aplicacion movil nativa desarrollada para la plataforma Android utilizando Kotlin. Su proposito fundamental es mitigar las compras compulsivas y optimizar la toma de decisiones financieras personales, traduciendo costos monetarios abstractos en horas y minutos de esfuerzo laboral real.

---

## Descripcion del Problema

Al evaluar gastos discrecionales, adquisiciones de articulos prescindibles o consumos cotidianos, los usuarios suelen percibir los precios unicamente como cifras numericas abstractas. Esta desconexion perceptiva impide dimensionar con claridad la cantidad real de tiempo y trabajo invertida para obtener dichos fondos, propiciando compras por impulso y debilitando la disciplina financiera.

## Solucion Propuesta

TimeCost procesa el salario neto mensual y las horas laboradas al mes para calcular con precision la tasa retributiva por hora del usuario en moneda local (S/). A partir del precio de cualquier bien o servicio:

1. Desglosa el importe monetario en tiempo de jornada laboral requerido (horas y minutos).
2. Contrasta este requerimiento con una jornada de trabajo estandar de 8 horas mediante un indicador porcentual y visual.
3. Categoriza el impacto del gasto mediante avisos semaforizados para advertir sobre posibles decisiones perjudiciales para el presupuesto.

---

## Caracteristicas Principales

- **Conversion temporal exacta:** Calculo en tiempo real del valor hora y proyeccion del costo del articulo en formato legible de horas y minutos.
- **Moneda local:** Campos y operaciones formateados en soles peruanos (S/).
- **Indicador de impacto en jornada:** Barra de progreso dinamica que grafica la proporcion consumida respecto a un dia estandar de 8 horas de labor.
- **Clasificacion de impacto mediante niveles:**
  - Impacto bajo: Menos de 1 hora de trabajo.
  - Impacto medio: Fraccion representativa de la jornada diaria (1 a 8 horas).
  - Alerta de alto impacto: Gastos que superan una o multiples jornadas completas de trabajo.
- **Optimizacion de interaccion de usuario:**
  - Despliegue con animacion de entrada coordinada (fade-in y desplazamiento vertical con desaceleracion).
  - Ocultamiento y desenfoque automatico del teclado al momento del calculo para no interferir con la lectura del resultado.
  - Compatibilidad completa con barras del sistema claras e integracion edge-to-edge.
- **Diseno sobrio y minimalista:** Interfaz estructurada bajo criterios de diseno de tarjetas agrupadas (inset grouped layout), garantizando claridad jerarquica y visual.

---

## Arquitectura y Tecnologias

- **Lenguaje:** Kotlin
- **Entorno de desarrollo:** Android Studio
- **Interfaz de usuario:**
  - Layouts XML nativos
  - Material Design Components (MaterialCardView, MaterialButton)
  - Control de ventanas e insets mediante WindowInsetsControllerCompat
- **Animacion:** ObjectAnimator con DecelerateInterpolator
- **Gestion de dependencias y construccion:** Gradle
- **Compatibilidad de sistema:** Android 7.0 (API nivel 24) o superior
- **Control de versiones:** Git y GitHub

---

## Estructura del Repositorio

```text
TimeCost/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/timecost/
│   │       │   └── MainActivity.kt
│   │       └── res/
│   │           ├── drawable/
│   │           │   ├── bg_input_field.xml
│   │           │   └── progress_bar_custom.xml
│   │           └── layout/
│   │               └── activity_main.xml
│   └── build.gradle
├── build.gradle
└── README.md
```

---

## Instalacion y Despliegue Local

Para ejecutar el proyecto en un entorno local de desarrollo:

1. Clonar el repositorio mediante Git:
   ```bash
   git clone https://github.com/TU_USUARIO/TimeCost.git
   ```
2. Iniciar Android Studio y seleccionar la opcion **Open**.
3. Localizar el directorio clonado y abrir el proyecto.
4. Esperar a que el sistema de construccion Gradle descargue las dependencias e indexe los paquetes.
5. Conectar un dispositivo fisico con la depuracion por USB habilitada o iniciar un dispositivo virtual (AVD) configurado con API 24 o superior.
6. Compilar y ejecutar la aplicacion seleccionando **Run 'app'** (atajo `Shift + F10` o presionar el icono Play).

---

## Licencia

Este proyecto ha sido desarrollado con fines academicos y formativos bajo la Licencia MIT. Consulta el archivo `LICENSE` para mayores detalles.
