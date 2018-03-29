# Pico y Placa Predictor
This is a demo project for predicting if a license plate number has pico y placa at the given date and time.

## Requirements
- Java 8 JDK
- Gradle

## Instructions
1. Open a console or terminal windows in the root folder of the project.
2. Execute: `./gradlew build` (Linux, OSX) or `gradle.bat build` (Windows) to compile and package the sources.
3. Next, execute the compiled package with the required arguments: 
```
java -jar build/libs/pico-placa-predictor.jar PSD-1231 26/03/2018 08:00
```

This will give you the output:
```
The license PSD-1231 has pico y placa on 26/03/2018 at 08:00.
```
Because the last digit of the license plate number is 1 and 26/03/2018 is Monday, means that license plate number is not allowed to circulate on the road between 07:00 and 09:30 and between 16:00 and 19:30.

The proper format for the license plate number  is 3 letters followed by `-` and 3 or 4 numbers, e.g. PCI-458, TGH-0184. The date format is dd/MM/yyyy and the time is a 24-hour format HH:mm. The program will detect any input which doesn't comply with these formats and will throw a corresponding message.

There is a summary report of the executed tests inside the directory `build/reports/test`. Just open the index.html in any browser and that's it.