package logger

import java.io.File
import java.lang.Exception
import java.time.LocalDateTime


enum class Types {
    STDOUT,
    FILE
}

class Logger(private val type: Types? = Types.STDOUT) {
    private var file: File? = null

    fun setLogFile(file: File): Logger {
        if (type !== Types.FILE) {
            throw Exception("You should change type to Type.STDOUT")
        }
        this.file = file
        return this
    }

    fun log(message: String = ""): Logger {
        val msg = this.updateMessage(message)

        when (type) {
            Types.FILE -> this.logToFile(msg)
            else -> this.logToStdout(msg)
        }
        return this
    }

    private fun updateMessage(message: String): String {
        val msg = "Time: ${LocalDateTime.now()} | Message: $message"

        return when (type) {
            Types.STDOUT -> msg
            else -> "$msg\n"
        }
    }

    private fun logToStdout(message: String) {
        println(message)
    }

    private fun logToFile(message: String): Logger {
        this.file?.appendText(message) ?: throw Exception("You need to specify file first")
        return this
    }
}