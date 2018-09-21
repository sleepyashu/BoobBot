package bot.boobbot.misc

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame
import net.dv8tion.jda.core.audio.AudioSendHandler
import net.dv8tion.jda.core.entities.User

class GuildMusicManager(val guildId: Long, val player: AudioPlayer) : AudioEventAdapter(), AudioSendHandler {

    private var lastFrame: AudioFrame? = null
    private val queue = mutableListOf<AudioTrack>()
    private var lastTrack: AudioTrack? = null
    private var repeat = RepeatMode.NONE


    init {
        player.addListener(this)
    }

    // ----------------------------------------------
    // CUSTOM FUNCTIONS
    // ----------------------------------------------

    public fun addToQueue(track: AudioTrack) {
        if (!player.startTrack(track, true)) {
            queue.add(track)
        }
    }

    public fun playNext() {
        if (queue.size > 0) {
            player.startTrack(queue[0], false)
        } // Perhaps do something here if the queue is finished?
    }

    // ----------------------------------------------
    // PLAYER EVENT HANDLING
    // ----------------------------------------------

    override fun onTrackStart(player: AudioPlayer?, track: AudioTrack?) {
        playNext()
    }

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        lastTrack = track

        if (!endReason.mayStartNext) {
            return
        }

        val cloned = track.makeClone()
        cloned.userData = track.userData

        when (repeat) {
            RepeatMode.SINGLE -> player.startTrack(cloned, false)
            RepeatMode.ALL -> {
                queue.add(cloned)
                playNext()
            }
            else -> playNext()
        }
    }

    // ----------------------------------------------
    // JDA SEND HANDLER HOOKS
    // ----------------------------------------------

    override fun provide20MsAudio(): ByteArray {
        return lastFrame!!.data
        // We know this won't be null here as JDA will only call this if canProvide is true
        // And we already do null checks in canProvide
    }

    override fun canProvide(): Boolean {
        lastFrame = player.provide()
        return lastFrame != null
    }

    override fun isOpus(): Boolean = true


    // ----------------------------------------------
    // MISC
    // ----------------------------------------------

    public enum class RepeatMode {
        SINGLE, ALL, NONE
    }
}
