О проекте

HomeChat Client - это приложение для обмена сообщениями в групповом чате с использованием WebSocket.

Основные функции и особенности

    Обмен сообщениями в реальном времени через WebSocket

    Авторизация пользователей с сохранением сессии

    Раздельное отображение сообщений (свои/чужие)

    Мониторинг состояния подключения

    Безопасное WebSocket соединение (WSS)

    Управление состоянием через Kotlin Flows

    Сохранение сессии между перезапусками приложения

Приложение построено по архитектуре MVVM (Model-View-ViewModel)

Технологический стек

    Kotlin - основной язык разработки

    Android Jetpack Components:

        ViewModel для управления данными

        LiveData/StateFlow для реактивного программирования

        Navigation Component для навигации

        RecyclerView для списков

    OkHttp + WebSocket для сетевых запросов

    Gson для сериализации/десериализации JSON

    Настройка проекта

    Откройте проект в Android Studio

    Дождитесь завершения синхронизации Gradle

    Обновите URL WebSocket сервера в WebSocketClient.kt:
    
 Настройка проекта

    Откройте проект в Android Studio

    Дождитесь завершения синхронизации Gradle

    Обновите URL WebSocket сервера в WebSocketClient.kt:

// Замените на ваш IP-адрес сервера
val url = "wss://ВАШ_IP_АДРЕС:8443/chat?userId=$userId&username=$username"
