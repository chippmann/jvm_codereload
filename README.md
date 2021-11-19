# How to use:

## Initial build
- `./gradlew application:shadowJar`
- `./gradlew :reloading:build`

## Testing:
Assuming you are in the root project dir
- initial build
- `java -Djava.system.class.loader=godot.ProxyClassloader -jar application/build/libs/application-1.0-SNAPSHOT-all.jar reloading/build/libs/reloading-1.0-SNAPSHOT.jar`
  - terminal pops up -> keep open

Repeat the following as many times as you need to test the reloading:
- Change the property `change` inside [godot.reloading.Entry](reloading/src/main/kotlin/godot/reloading/Entry.kt)
- `./gradlew :reloading:build`
- hit ENTER in terminal


## project structure:
`application`: the dummy application which uses reloaded code
`classloader`: custom classloader impls which enable code reloading
`library`: library conaining a base class from which reloaded code extends
`reloading`: the code which gets reloaded by `application` through `classloader` and implements the base class from `library`
