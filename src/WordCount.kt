import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("You forgot something. Please name of the file")
    } else {

        val inputStream: InputStream = File(args[0]).inputStream()

        val allTheWords = inputStream.bufferedReader().use { it.readText() }

        val words = allTheWords.replace("]", "").replace("[", "").replace("?", "").replace(".", "").replace(",", "")
            .replace("(", "").replace(")", "").split(" ")

        val wordMap = mutableMapOf<String, Int>()

        for (word in words) {
            if (word != "") {
                if (wordMap[word.lowercase()] == null) {
                    wordMap[word.lowercase()] = 1
                } else {
                    val wordCount = wordMap[word.lowercase()]!!
                    wordMap[word.lowercase()] = wordCount + 1
                }
            }
        }

        val wordList = wordMap.toList()

        val sortedWords = wordList.sortedBy { it.second }

        for (word in sortedWords) {
            println("${word.first} - ${word.second}")
        }
    }
}