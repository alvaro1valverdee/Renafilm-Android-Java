# 🎬 Renafilm (Streamify) - Catálogo VOD Nativo

Aplicación móvil nativa para Android diseñada para la consulta y gestión de catálogos de plataformas de streaming (VOD). 

Este proyecto es el resultado de las prácticas de Programación Multimedia y Dispositivos Móviles (PMDM). Actualmente funciona como un MVP (Producto Mínimo Viable) para demostrar el dominio en la creación de interfaces de usuario complejas y navegación en Android, utilizando un conjunto de datos simulados (Mock Data) a la espera de su integración con una API externa.

## 🚀 Características y Funcionalidades

* **Sistema de Autenticación:** Pantallas de Login y Registro de usuarios con validación de campos.
* **Gestión de Sesión:** Uso de `SharedPreferences` para mantener el estado de la sesión del usuario (admin / 1234).
* **Navegación Dinámica:** Implementación de menús superiores (Options Menu) para acceder a las secciones de Ayuda, Acerca de y Cierre de sesión.
* **Listas y Cuadrículas:** Uso intensivo de `RecyclerView` y adaptadores personalizados para mostrar:
  * Un listado lineal de plataformas (Netflix, HBO, Disney+, etc.).
  * Un mosaico en formato cuadrícula (Grid) de 3 columnas para las carátulas de las películas.
* **Vistas de Detalle:** Navegación mediante `Intents` pasando parámetros para mostrar la información ampliada de cada película (sinopsis, valoración y póster).

## 💻 Stack Tecnológico

* **Lógica:** Java (Android SDK)
* **Interfaces:** XML Nativo (Uso de ConstraintLayout, LinearLayout)
* **Componentes:** RecyclerView, SharedPreferences, Intents, Menús Nativos.
* **Entorno:** Android Studio

## 🗺️ Roadmap y Próximas Actualizaciones

Al tratarse de un proyecto vivo, las siguientes fases de desarrollo incluyen:

- [x] Diseño completo de UI/UX en XML.
- [x] Lógica de navegación y persistencia de sesión local.
- [ ] **En desarrollo:** Sustitución de los datos estáticos (Hardcoded) por el consumo asíncrono de una API REST (ej. TMDB o similar) utilizando librerías como Retrofit.
- [ ] **Pendiente:** Implementación de una base de datos local (SQLite / Room) para guardar películas en una lista de "Favoritos".

## 📸 Capturas de Pantalla

<img width="232" height="512" alt="Login" src="https://github.com/user-attachments/assets/3f350169-f4d5-4b24-961b-4568b68821ef" />
<img width="241" height="512" alt="Lista de Plataformas" src="https://github.com/user-attachments/assets/b6189e70-abe0-4cf3-816f-ed0728b18f12" />
<img width="245" height="512" alt="Peliculas" src="https://github.com/user-attachments/assets/a1fce124-21ed-406c-a0e6-bee4d875878a" />
<img width="221" height="512" alt="Detalle Pelicula" src="https://github.com/user-attachments/assets/1aceb517-38a1-4215-ba7d-a8a73ff62629" />

