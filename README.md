# Hidup Sehat - Android Mobile Development

This repository contains the source code for the Hidup Sehat Android application, which promotes healthy living and provides various features to support users in maintaining a healthy lifestyle.

## Table of Contents
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [Features](#features)

## Architecture
The project follows the MVVM (Model-View-ViewModel) design pattern with a Clean Architecture approach. This architecture promotes separation of concerns and provides a scalable and maintainable codebase. Here's an overview of the project's architecture:

- **Presentation Layer**: Contains the UI components, including activities, fragments, and Jetpack Compose screens. It interacts with the ViewModel layer to retrieve and display data to the user.

- **Domain Layer**: Contains the business logic of the application. It defines the various use cases and orchestrates the interactions between the data layer and the presentation layer.

- **Data Layer**: Contains the repositories and data sources responsible for retrieving and persisting data. It abstracts the underlying data storage implementation and provides a clean interface for the Use Case layer.

## Requirements
To build and run the Hidup Sehat Android application, ensure you have the following software installed:

- Android Studio: 2022.2.1 (Flamingo)
- Minimum SDK: 21
- Target SDK: 33
- JDK: 17
- Kotlin: 1.7.20
- Android Gradle Plugin: 8.1.0
- Gradle: 8.0

## Dependencies
The project utilizes several libraries and frameworks to enhance its functionality. Here are the key dependencies:

- [Material 3](https://m3.material.io/): Provides a modern and consistent design system for the app's UI, ensuring a visually appealing user experience.

- [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjwg-GjBhBnEiwAMUvNW3yzekVX4nip-iL9Zw-ANUPQ_4eFDIJ0NU5Do0dTMhZCX6caIh3J8BoCdgoQAvD_BwE&gclsrc=aw.ds&hl=id): Enables building the UI using a declarative and efficient approach, allowing for flexible and responsive user interfaces.

- [Firebase Auth](https://firebase.google.com/docs/auth/android/google-signin?hl=id): Provides authentication and Google Sign-In capabilities, ensuring secure user authentication and access to app features.

- [Google Play Services](https://developers.google.com/android/guides/setup): Offers additional services and APIs for integrating with various Google features, enhancing the app's functionality and user experience.

- [Coil Compose](https://coil-kt.github.io/coil/compose/): A Kotlin-first image loading library for handling image loading and caching within Compose UI, enabling efficient and seamless image loading.

- [Dagger Hilt](https://dagger.dev/hilt/): A dependency injection framework that simplifies the management of dependencies in the app, promoting modularity and testability.
Apologies for the cutoff. Here's the rest of the information:

- [Camera X](https://developer.android.com/jetpack/androidx/releases/camera?hl=id): Provides a modern API for accessing the device's camera functionalities, allowing the app to capture photos and videos seamlessly.

- [Tensorflow Lite (TFLite)](https://www.tensorflow.org/lite/android/quickstart): Enables the integration of machine learning models in the app using TensorFlow Lite, empowering the app with advanced ML capabilities for tasks such as image recognition or health analysis.

- [Room](https://developer.android.com/training/data-storage/room?hl=id): Offers a convenient and robust way to work with a SQLite database on Android, allowing efficient storage and retrieval of app data.

- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for making network requests and interacting with RESTful APIs, facilitating seamless communication with backend services.

- [Kotlin Flow](https://developer.android.com/kotlin/flow?hl=id): Provides a streamlined way to handle asynchronous data streams in a reactive programming style, enabling efficient and responsive data handling in the app.

## Getting Started
To build and run the project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/Hidup-Sehat/MobileDevelopment-HidupSehat.git`
2. Open the project in Android Studio.
3. Ensure that the required SDK versions and dependencies are installed.
4. Add your own google-services.json file to the project. This file is required for Firebase integration. You can obtain this file by creating a new Firebase project and enabling Firebase Authentication.
5. Build and run the app on an emulator or physical device.

Feel free to explore the codebase and make any contributions or improvements as needed. We appreciate your contributions to Hidup Sehat!

## Features
The Hidup Sehat Android application offers the following key features:

- User authentication using Firebase Auth, allowing users to create accounts and securely log in.
- Seamless image loading and caching with Coil Compose, ensuring fast and efficient image rendering within the app.
- Dependency injection with Dagger Hilt, promoting modularity and testability of the codebase.
- Camera functionality using Camera X, enabling users to capture and work with photos within the app.
- Integration of TensorFlow Lite for incorporating machine learning models, providing advanced health analysis or personalized recommendations.
- Data storage and retrieval with Room, offering a reliable and efficient database solution for managing app data.
- Network requests and API interactions facilitated by Retrofit, ensuring seamless communication with backend services.
- Asynchronous data handling with Kotlin Flow, allowing for responsive and reactive programming.
- Media playback using ExoPlayer, providing a powerful and customizable media player for playing audio content within the app.

Please refer to the codebase and documentation for more detailed information on each feature.
