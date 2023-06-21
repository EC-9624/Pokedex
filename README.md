# Android Pokédex App
The Android Pokédex App is a comprehensive Pokémon information application built using Android Studio. This repository contains all the source code and necessary resources to build and run the app.

<p align="center">
  <img src="https://github.com/EC-9624/Pokedex/assets/71120764/4b737ad2-f004-4b2a-af43-a7b6d40ffb39" />
</p>

## Installation
To use the Android Pokédex App, follow these steps:

Clone the repository to your local machine using git clone https://github.com/username/android-pokedex-app.git.
Open the project in Android Studio.
Build the project to automatically download the required dependencies.
Connect an Android device or emulator.
Run the app from Android Studio, and it will be installed on the connected device.

## Dependencies
The Android Pokédex App relies on the following dependencies:
Glide: Image loading and caching library.
RecyclerView: UI component for displaying a list of items efficiently.
ViewPager2: UI component for implementing swipeable screens.

Please refer to the build.gradle file for the specific versions of these dependencies used in the project.

## API
This app utilizes the PokéAPI (https://pokeapi.co/) to fetch Pokémon data. The PokéAPI is a free and open API providing a vast collection of Pokémon-related information.
However, to reduce the use of internet connection, the app stores Pokémon data in a local JSON file. Only the images of the Pokémon require an internet connection to be fetched from the PokéAPI.

