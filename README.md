# Meditory App

This is a sample that demonstrates how to implement clean architecture. Developed with Kotlin and MVVM pattern is used.

## Libraries and tools used

* [Android Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Kotlinx.Serialization](https://github.com/Kotlin/kotlinx.serialization)

## Requirements
* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Min Android Version: API 23
* Target Android Version: API 30
* Latest Android SDK Tools and build tools.
* Android Studio: 4.1.3

## Features
* Login
* Home
* Detail

### Login
Enter point of the application. If username is not greater than 2 you will fail. If your password is less than 6 or does not contain at leat an uppercase letter or a number, you will fail.
  
### Home
Some dummy meditation and story items are listed. Used horizontal recyclerview for meditation items, vertical recyclerview for story items. If you click on one item detail page will open.

### Detail
It displays some information about the selected meditation or story. Background of this activity is changing according to background url of meditation or story item. And there is a media player. When you hit the play button a relaxing ocean sound will be playing.

## Running
* Open the project in Android Studio
* Hit the Run button 

## Testing
There are some tests in the project. Run ```./gradlew test``` command to run all unit tests.
