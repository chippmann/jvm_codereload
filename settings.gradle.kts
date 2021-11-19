rootProject.name = "codereloading"

includeBuild("classloader") {
    dependencySubstitution {
        substitute(module("com.utopia-rise:classloader")).using(project(":"))
    }
}
includeBuild("library") {
    dependencySubstitution {
        substitute(module("com.utopia-rise:library")).using(project(":"))
    }
}
includeBuild("reloading") {
    dependencySubstitution {
        substitute(module("com.utopia-rise:reloading")).using(project(":"))
    }
}

include(":application")
