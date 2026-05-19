# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Что это за репозиторий

Личная учебная песочница по Kotlin. Это **не** единое приложение — большинство файлов `.kt`
самодостаточны, каждый со своей `fun main()`, и демонстрируют одну возможность языка или
библиотеки. Файлы с числовыми префиксами (`1_Basic.kt`, `2_Loops.kt`, ...) рассчитаны на
чтение по порядку как туториал. Несколько файлов — настоящие приложения Spring Boot
(`@SpringBootApplication`).

## Сборка и запуск

Активный инструмент сборки — Gradle (`build.gradle`); wrapper закоммичен. Используйте wrapper:

```
.\gradlew.bat build          # скомпилировать всё
.\gradlew.bat compileKotlin  # скомпилировать только основные исходники
```

`pom.xml` — устаревший альтернативный дескриптор сборки со старыми версиями зависимостей —
игнорируйте его; не редактируйте его при изменении зависимостей.

Набора тестовых исходников **нет**, тестов нет. `.\gradlew.bat test` ничего не делает.

### Запуск примера

Большинство файлов — самостоятельные учебные сниппеты: запускайте их `main()` напрямую из
IDE (каждый файл `.kt` с `main` — независимая запускаемая программа). Плагина `application` в
Gradle нет, поэтому цели `gradle run` тоже нет.

Приложения Spring Boot запускаются через `runApplication` в своей `main()`:
- `netty/TransactionSgbApplication.kt`
- `reactive/practice/FluxDemoApplication.kt` (и другие `*DemoApplication.kt` в `reactive/practice`)

## Структура кода

Все исходники лежат в `src/main/kotlin/ru/stepanov/kotlin_awesome/` и разбиты на три области:

- **`ktawesome/`** — обзор языка Kotlin (`core/basic`, `core/control_flow`, `core/functional`,
  `core/collections`, `core/scope_functions`, `core/special_classes`, `core/awesome` для
  делегирования) и `coroutines/` (основы/scope, затем отмена/таймауты).
- **`reactive/`** — Project Reactor. В `theory/` — нумерованные примеры по одному понятию
  (создание, backpressure, zip, планировщики, обработка ошибок, sinks); в `practice/` —
  запускаемые демо Spring WebFlux с эндпоинтами `Flux`/`Mono`.
- **`netty/`** — единственная «настоящая» подсистема: SSL/mTLS TCP-клиент Netty для
  авторизации транзакций SmartVista (SGB). В `service/netty/` — конвейер Netty (клиент,
  handler, encoder/decoder); `service/smartvista/SVPackager.kt` отвечает за упаковку
  сообщений; `config/SslConfig.kt` собирает SSL-контекст.

## Язык общения

- Общайся с пользователем в консоли **по-русски**.
- Все комментарии в коде пиши **по-русски**.

## Соглашения, специфичные для этого репозитория

- Имена файлов часто используют числовой/буквенный префикс для кодирования порядка чтения
  (`1_`, `2a__`, `b_`). В именах файлов встречаются подчёркивания и `&` — сохраняйте этот
  стиль, добавляя соседние примеры.
- Классы с аннотациями Spring (`@SpringBootApplication`, `@RestController`) объявлены явно
  как `open`, хотя плагин `kotlin-spring` сделал бы их открытыми сам — существующий код так
  делает, придерживайтесь этого.
- Gradle `group` — `ru.raiffeisen.rb`, тогда как корень пакета исходников —
  `ru.stepanov.kotlin_awesome`. Это несоответствие существовало изначально; не «исправляйте» его.
- Целевой байткод — Java 8 (`jvmTarget = "1.8"`).
