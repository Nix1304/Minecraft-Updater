import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.net.URL

class Checker {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val url = URL("https://gist.githubusercontent.com/Nix1304/18bf10dee1a4ae5ebc5b9fad0e8c9639/raw/1a11b54185e2864a5531819618b4d43baf4c4e2a/update.json")
            val mcVersion = "1.10.2"
            val version = "1.2.0.0"
            val updateChannel = "latest" // "recommended" or "latest"

            val reader = InputStreamReader(url.openStream())
            val jsonTree = JsonParser().parse(reader)
            if(jsonTree.isJsonObject) {
                val homepage = jsonTree.asJsonObject.get("homepage").asString
                val promos = jsonTree.asJsonObject.get("promos")
                if(promos.isJsonObject) {
                    try {
                        val modVersionRecommended = promos.asJsonObject.get("$mcVersion-recommended").asString
                        val modVersionLatest = promos.asJsonObject.get("$mcVersion-latest").asString
                        val changelogRecommended = jsonTree.asJsonObject.get(mcVersion).asJsonObject.get(modVersionRecommended).asString
                        val changelogLatest = jsonTree.asJsonObject.get(mcVersion).asJsonObject.get(modVersionLatest).asString

                        println("Your Minecraft version: $mcVersion")
                        if(updateChannel == "recommended" && modVersionRecommended != version) {
                            println("A new version $modVersionRecommended available!")
                            println("Changelog: $changelogRecommended")
                            println("You can download it from $homepage")
                        } else if(updateChannel == "latest" && modVersionLatest != version) {
                            println("A new version $modVersionLatest available!")
                            println("Changelog: $changelogLatest")
                            println("You can download it from $homepage")
                        } else {
                            println("No new version available!")
                        }

                    } catch(e: Exception) {
                        error(e.localizedMessage)
                    }
                }
            }
        }
    }
}