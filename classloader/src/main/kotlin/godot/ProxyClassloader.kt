package godot

//with null passed in as the parent loader, it will only delegate to `findBootstrapClassOrNull` and not to `loadClass`
class BaseLoader : ClassLoader(null) {
    public override fun findClass(name: String?): Class<*>? {
        return super.findClass(name)
    }

    public override fun findClass(moduleName: String?, name: String?): Class<*>? {
        return super.findClass(moduleName, name)
    }

    public override fun loadClass(name: String?): Class<*>? {
        return super.loadClass(name)
    }

    public override fun loadClass(name: String?, resolve: Boolean): Class<*>? {
        return super.loadClass(name, resolve)
    }
}

// system class loader
class ProxyClassloader @JvmOverloads constructor(
    parentClassLoader: ClassLoader,
    var proxy: CustomUrlClassLoader? = null
) : ClassLoader(parentClassLoader) {
    private val baseClassLoader = BaseLoader()
    public override fun findClass(name: String?): Class<*> {
        debug("findClass: $name | currentLoader: $this, proxiedLoader: $proxy")
        return try {
            proxy?.findClass(name) ?: throw ClassNotFoundException()
        } catch (e: Throwable) {
            debug("Not found in proxy, delegating to BaseLoader: $name")
            try {
                baseClassLoader.findClass(name) ?: throw ClassNotFoundException()
            } catch (e: Throwable) {
                debug("Not found in BaseLoader, delegating to parentLoader: $name")
                super.findClass(name)
            }
        }
    }

    public override fun findClass(moduleName: String?, name: String?): Class<*> {
        debug("findClass: $moduleName $name | currentLoader: $this, proxiedLoader: $proxy")
        return try {
            proxy?.findClass(moduleName, name) ?: throw ClassNotFoundException()
        } catch (e: Throwable) {
            debug("Not found in proxy, delegating to BaseLoader: $name")
            try {
                baseClassLoader.findClass(moduleName, name) ?: throw ClassNotFoundException()
            } catch (e: Throwable) {
                debug("Not found in BaseLoader, delegating to parentLoader: $name")
                super.findClass(moduleName, name)
            }
        }
    }

    public override fun loadClass(name: String?): Class<*> {
        debug("loadClass: $name | currentLoader: $this, proxiedLoader: $proxy")
        return try {
            proxy?.loadClass(name) ?: throw ClassNotFoundException()
        } catch (e: Throwable) {
            debug("Not found in proxy, delegating to BaseLoader: $name")
            try {
                baseClassLoader.loadClass(name) ?: throw ClassNotFoundException()
            } catch (e: Throwable) {
                debug("Not found in BaseLoader, delegating to parentLoader: $name")
                super.loadClass(name)
            }
        }
    }

    public override fun loadClass(name: String?, resolve: Boolean): Class<*> {
        debug("loadClass: $name $resolve | currentLoader: $this, proxiedLoader: $proxy")
        return try {
            proxy?.loadClass(name, resolve) ?: throw ClassNotFoundException()
        } catch (e: Throwable) {
            debug("Not found in proxy, delegating to BaseLoader: $name")
            try {
                baseClassLoader.loadClass(name, resolve) ?: throw ClassNotFoundException()
            } catch (e: Throwable) {
                debug("Not found in BaseLoader, delegating to parentLoader: $name")
                super.loadClass(name, resolve)
            }
        }
    }
}
