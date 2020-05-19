import Main.mcVersion
import Main.updateChannel
import Main.version
import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.net.URL

object Checker {
    fun checkForUpdates(url: URL) {
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
                    println()
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