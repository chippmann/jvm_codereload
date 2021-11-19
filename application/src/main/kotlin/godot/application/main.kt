package godot.application

import godot.Entry

fun main(args: Array<String>) {
    var running = true

    while (running) {
        try {
            println("-----------------------------------------")

            // START: testing variants:
//            val entryFile = getEntryFileThroughCustomSystemClassloader(args[0]) // SystemClassloader
            val entryFile = getEntryFileThroughReplacedUrlClassloader(args[0]) //replaced urlClassloader
            // END: testing variants:

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
