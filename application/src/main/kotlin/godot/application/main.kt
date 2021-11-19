package godot.application

import godot.CustomUrlClassLoader
import godot.Entry
import godot.ProxyClassloader
import java.io.File

fun main(args: Array<String>) {
    var running = true

    while (running) {
        try {
            println("-----------------------------------------")
            info("Setting new classloader proxy with jar path: ${args[0]}")
            (ClassLoader.getSystemClassLoader() as ProxyClassloader).proxy = CustomUrlClassLoader(
                arrayOf(
                    File(args[0]).toURL()
                ),
                ClassLoader.getSystemClassLoader()
            )

            val entryFile = ClassLoader.getSystemClassLoader().loadClass("godot.reloading.Entry").getDeclaredConstructor().newInstance()
            info("Entry file instance: $entryFile still extends ${Entry::class.qualifiedName} from the classloader perspective: ${entryFile is Entry}")

            if (entryFile is Entry) {
                info("Content from entry: ${entryFile.change}")
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            warning("Entry file not found. Is it built?")
        }

        info("Press ENTER to reload or enter \"exit\" to exit the program")
        val input = readln()
        if (input == "exit") {
            running = false
        }
    }
}
