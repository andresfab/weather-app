# Weather app
## Libs and architecture implemented

- Jetpack Compose
- Clean Architecture
- Coroutines
- Flows
- MutableState
- MVVM
- Retrofit
- Coil
- Dagger Hilt

## Build
### Prerequisites

Those software configurations are recommended and tested.

- JDK 11. Gradle JVM should be set to Java 11.
    - For command line, make sure the environment variable `JAVA_HOME` is correctly point to JDK11, or set the build environment by [Gradle CLI option](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:environment_options) `-Dorg.gradle.java.home="YourJdkHomePath"` or by [Gradle Properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) `org.gradle.java.home=YourJdkHomePath`.
    - For both IntelliJ and Android Studio, see _Settings/Preferences | Build, Execution, Deployment | Build Tools | Gradle_.
- minSdk API 21 and targetSdk API 32.

## Notes

- The search engine was optimized to avoid making multiple requests in a short time
- The application retains its state if the phone is changed orientation
- Due to time issues, the dimensions and texts will be hardcoded
- Asynchronous processes were handled with coroutines
