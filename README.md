# Konway

Implementation of Conway's Game of Life in Kotlin.
To start the simulation, press Space,
to fill the grid randomly, press Enter,
to reset the grid, press Backspace,
to place or remove a tile, click on the grid.

## Gradle tasks

 - `./gradlew run` runs `TemplateProgram.kt` (Use `gradlew.bat run` under Windows)
 - `./gradlew shadowJar` creates an executable platform specific jar file with all dependencies. Run the resulting program by typing `java -jar build/libs/openrndr-template-1.0.0-all.jar` in a terminal from the project root. If your project contains multiple `main` methods, specify which one to run with `java -cp build/libs/openrndr-template-1.0.0-all.jar MyProgramKt`, where `MyProgramKt` can also be `foo.bar.MyProgramKt` if it's in the package `foo.bar`.
 - `./gradlew jpackageZip` creates a zip with a stand-alone executable for the current platform (requires Java 17 or newer). Run it like this: `cd build/jpackage/openrndr-application/ && bin/openrndr-application`.

## Cross builds

To create a runnable jar for a platform different from your current platform, use `./gradlew jar -PtargetPlatform=<platform>`, where `<platform>` is either `windows`, `macos`, `linux-x64`, or `linux-arm64`. 
