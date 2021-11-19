package godot

import java.net.URL
import java.net.URLClassLoader

// actual class loader that we can swap
class CustomUrlClassLoader(urls: Array<URL>, parentLoader: ClassLoader): URLClassLoader(urls, parentLoader.parent) {
    // make findClass public so we can proxy it
    public override fun findClass(name: String?): Class<*> {
        return super.findClass(name)
    }

    public override fun findClass(moduleName: String?, name: String?): Class<*> {
        return super.findClass(moduleName, name)
    }

    public override fun loadClass(name: String?): Class<*> {
        return super.loadClass(name)
    }

    public override fun loadClass(name: String?, resolve: Boolean): Class<*> {
        return super.loadClass(name, resolve)
    }
}
