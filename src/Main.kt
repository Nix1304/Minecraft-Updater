import Checker.checkForUpdates
import java.net.URL

object Main {
    const val mcVersion = "1.10.2"
    const val version = "1.2.0.0"
    const val updateChannel = "latest" // "recommended" or "latest"

    @JvmStatic
    fun main(args: Array<String>) {
        val url = URL("https://gist.githubusercontent.com/Nix1304/18bf10dee1a4ae5ebc5b9fad0e8c9639/raw/1a11b54185e2864a5531819618b4d43baf4c4e2a/update.json")
        checkForUpdates(url)
    }
}