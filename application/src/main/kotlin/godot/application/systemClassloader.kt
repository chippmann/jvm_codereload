package godot.application

import godot.CustomUrlClassLoader
import godot.ProxyClassloader
import java.io.File

fun getEntryFileThroughCustomSystemClassloader(path: String): Any? {
    info("Setting new classloader proxy with jar path: $path")
    (ClassLoader.getSystemClassLoader() as ProxyClassloader).proxy = CustomUrlClassLoader(
        arrayOf(
            File(path).toURL()
        ),
        ClassLoader.getSystemClassLoader()
    )

    return ClassLoader.getSystemClassLoader().loadClass("godot.reloading.Entry").getDeclaredConstructor().newInstance()
}
