package bot.boobbot.misc

import club.minnced.discord.webhook.send.WebhookEmbed
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.future.await
import net.dv8tion.jda.api.entities.MessageEmbed
import okhttp3.Response
import org.json.JSONObject
import java.net.URI
import java.util.concurrent.CompletableFuture

fun Dotenv.get(key: String, default: String): String = get(key) ?: default

fun <T> List<T>.separate(): Pair<T, List<T>> = Pair(first(), drop(1))

fun String.toUriOrNull(): URI? {
    return try {
        URI(this)
    } catch (e: Exception) {
        return null
    }
}

fun MessageEmbed.toWebhookEmbed(): WebhookEmbed {
    return WebhookEmbed(
        this.timestamp,
        this.colorRaw,
        this.description,
        this.thumbnail?.url,
        this.image?.url,
        if (this.footer != null) WebhookEmbed.EmbedFooter(this.footer!!.text ?: "", this.footer!!.iconUrl) else null,
        WebhookEmbed.EmbedTitle(this.title ?: "", this.url),
        if (this.author != null) WebhookEmbed.EmbedAuthor(
            this.author!!.name ?: "",
            this.author!!.iconUrl,
            this.author!!.url
        ) else null,
        this.fields.map { WebhookEmbed.EmbedField(it.isInline, it.name ?: "", it.value ?: "") }
    )
}

fun Response.json(): JSONObject? {
    val body = body()

    body().use {
        return if (isSuccessful && body != null) {
            JSONObject(body()!!.string())
        } else {
            null
        }
    }
}

suspend fun <T> CompletableFuture<T>.awaitSuppressed(): T? {
    return try {
        this.await()
    } catch (e: Exception) {
        null
    }
}

fun <T> CompletableFuture<T>.thenException(block: (Throwable) -> Unit) {
    this.exceptionally {
        block(it)
        return@exceptionally null
    }
}
