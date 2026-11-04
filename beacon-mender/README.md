# Beacon Mender (Minecraft Java 26.2, Fabric)

Dodaje do latarni nowy efekt główny **Aura Naprawy** (poziom piramidy III, obok Siły).
Gracz w zasięgu latarni dostaje efekt, a ten co sekundę naprawia **wszystkie przedmioty
z Mendingiem w jego ekwipunku** (hotbar, plecak, zbroja, druga ręka).

## Budowanie
Wymagania: JDK 25, Gradle 9.3+ (lub wrapper z szablonu Fabric).

    gradle build        (albo ./gradlew build, jeśli masz wrapper)

Gotowy plik: build/libs/beacon-mender-1.0.0.jar -> wrzuć do .minecraft/mods
(razem z Fabric API dla 26.2 i zainstalowanym Fabric Loaderem 0.19.3+).

## Ustawienia
Szybkość naprawy: stałe na górze MendingAuraEffect.java.
