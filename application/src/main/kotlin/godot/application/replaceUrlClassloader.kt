package godot.application

import java.io.File
import java.net.URLClassLoader

fun getEntryFileThroughReplacedUrlClassloader(path: String): Any? {
    info("Creating new urlClassloader with jar path: $path")
    val classloader = URLClassLoader(
        arrayOf(
            File(path).toURL()
        ),
        ClassLoader.getSystemClassLoader()
    )

    return classloader.loadClass("godot.reloading.Entry").getDeclaredConstructor().newInstance()
}
