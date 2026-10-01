# 🎬 Renafilm - Catálogo de Películas y Series

Aplicación móvil nativa para Android diseñada para tener un catálogo de películas y sus puntuaciones[cite: 7].

Este proyecto es el resultado de las prácticas del módulo de Programación Multimedia y Dispositivos Móviles (PMDM) correspondientes al curso 2025-2026[cite: 13]. Inicialmente concebido como un MVP, el proyecto ha evolucionado para integrarse con una API externa, demostrando el dominio en la creación de interfaces, navegación en Android y consumo asíncrono de red.

## 🚀 Características y Funcionalidades

*   **Consumo de API REST:** Conexión asíncrona con The Movie Database (TMDB) para obtener los catálogos actualizados.
*   **Carga Eficiente de Imágenes:** Renderizado de pósteres y carátulas en red sin bloquear el hilo principal.
*   **Sistema de Autenticación:** Pantalla de bienvenida[cite: 7] seguida de Login y Registro con validación de credenciales (admin / 1234) gestionadas mediante SharedPreferences[cite: 8, 12].
*   **Panel de Navegación (Dashboard):** Menú principal con acceso rápido a categorías específicas: Películas Populares, Series Populares, Películas Mejor Valoradas y Favoritos[cite: 9].
*   **Listado en Cuadrícula (Grid):** Visualización del catálogo mediante `RecyclerView` con carátulas, títulos, sistema de valoración de cinco estrellas y botón para marcar como favorito[cite: 10].
*   **Vistas de Detalle:** Pantallas dedicadas para cada título mostrando el póster a gran resolución, puntuación exacta y sinopsis[cite: 11].
*   **Soporte al Usuario:** Integración de una Guía de Ayuda (FAQ) interactiva[cite: 12] y panel de créditos del desarrollador[cite: 13].

## 💻 Stack Tecnológico

*   **Lógica:** Java (Android SDK)
*   **Interfaces:** XML Nativo (ConstraintLayout, LinearLayout, CardView)
*   **Cliente HTTP / API:** Retrofit para peticiones REST y parseo JSON.
*   **Gestión de Imágenes:** Picasso para la carga y caché de imágenes externas.
*   **Componentes:** RecyclerView, SharedPreferences, Intents.
*   **Entorno y Gestión:** Android Studio, Git/GitHub, control del roadmap mediante tableros Kanban (Trello).

## 🗺️ Roadmap y Estado del Proyecto

Al tratarse de un proyecto vivo, las fases de desarrollo se organizan de la siguiente manera:
*   ✅ Diseño completo de UI/UX en XML con vistas de Bienvenida, Login y Dashboard por tarjetas.
*   ✅ Sustitución de los datos estáticos por el consumo asíncrono de la API REST de TMDB utilizando Retrofit y Picasso.
*   ✅ Implementación de menús estáticos y persistencia de sesión local.
*   ⏳ **En Desarrollo (Próxima actualización):** Implementación de la lógica interna para las categorías "Películas Favoritas" y "Series Favoritas"[cite: 9]. Actualmente la interfaz visual interactiva está construida[cite: 10, 11], pero la persistencia de datos (guardado en base de datos local SQLite / Room) se encuentra en pleno proceso de desarrollo.

## 📸 Capturas de Pantalla

<p align="center">
  <img src="https://github.com/user-attachments/assets/489fe4d6-6b17-4784-9e8d-d1fa8d4ca782" alt="Bienvenida" height="350" />
  <img src="https://github.com/user-attachments/assets/1eca5ccc-8604-4844-b862-7e52a8a4c68d" alt="Login" height="350" />
  <img src="https://github.com/user-attachments/assets/d9107e35-53e0-4110-9425-64f625771fbe" alt="Dashboard" height="350" />
  <img src="https://github.com/user-attachments/assets/5addc6a9-d71a-4b0e-a14f-a3cf036b315f" alt="Catálogo" height="350" />
  <br><br>
  <img src="https://github.com/user-attachments/assets/c7aa5fdf-a252-4494-a714-593d2d3bf2ae" alt="Detalle de Película" height="350" />
  <img src="https://github.com/user-attachments/assets/f3dbdbb1-d085-468b-8bed-4478619129f7" alt="Ayuda y Soporte" height="350" />
  <img src="https://github.com/user-attachments/assets/34fd8c07-3ee8-409a-bab0-d5cd35912dd1" alt="Créditos" height="350" />
</p>



