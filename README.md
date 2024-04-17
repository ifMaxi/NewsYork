# New's York

Small news application that consumes three Apis from The New York Times.

## Description

A news app that obtains different articles thanks to [The New York Times](https://developer.nytimes.com/) APIs.

Due to API requirements, this app has no commercial purpose.

The UI is simple and basic as it does not present or highlight many essential functionalities. These articles, when pressed, are sent through an Intent to open it directly in the browser. The app has Cache support thanks to the OkHttp library to provide a bit of Offline support, the reason why it did not use an integrated database was that due to the type of app, which is small, it was not necessary.
At the same time, it has search and pull to refresh functionality.

**It has 3 screens**:
- The main screen that will show all the articles in real time as they are published.
- The search screen with which you can search for articles of interest.
- The top stories screen that will show a series of categories with related articles.

> [!NOTE]
> The app has light and dark mode support, and also supports dynamic colors for devices with Android 12+.

> [!IMPORTANT]
> In order to start the app from Android Studio you will need an **API** key that you can obtain by registering from [the page provided](https://developer.nytimes.com/).
>
> Once you have obtained the key you will need to place it in your ***"local.properties"*** file in the root folder of the project and name the variable as ***"API_KEY"***, the rest of the configurations in the build gradle
> file have already been done. Then rebuild the project and you can start the app from the emulator.

## Apis

The application uses three APIs to obtain the data:
- [The Time Wire](https://developer.nytimes.com/docs/timeswire-product/1/overview) which will show all articles in real time as they are published.
- [Article Search](https://developer.nytimes.com/docs/articlesearch-product/1/overview) that will search and display the articles according to the query provided.
- [The Top Stories](https://developer.nytimes.com/docs/top-stories-product/1/overview) which will display articles from different categories to satisfy the user's interests.

## Architecture

The type of architecture used for this project was MVVM(Model-View-ViewModel).

This is divided into the:

- Model: Which represents the data and business logic
- View: Which represents the UI
- ViewModel: Which represents the bridge between the View and the Model

## Language and libraries

- Kotlin
  - Serialization
  - Coroutines
  - Kps
- Jetpack Libraries
  - Compose
  - Hilt
  - ViewModel
  - Navigation
  - Paging 3
- Other libraries
  - Retrofit
  - OkHttp
  - Coil
  - Lottie
