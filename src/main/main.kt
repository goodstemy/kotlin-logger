import logger.*
import java.io.File

fun main() {
    // Example execution
    val l = Logger(Types.FILE)

    l
        .setLogFile(File("log.txt"))
        .log("Hey")
        .log("Another line")
        .log("And another one")
}