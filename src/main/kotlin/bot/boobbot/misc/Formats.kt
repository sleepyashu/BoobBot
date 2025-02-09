package bot.boobbot.misc

import bot.boobbot.BoobBot
import net.dv8tion.jda.api.JDAInfo
import java.text.MessageFormat


/**
 * Created by Tom on 10/5/2017.
 */

object Formats {
    const val BOOT_BANNER = "(.Y.)"
    const val LEWD_EMOTE = "<:Ass:520247323343978509>"
    const val DISCORD_EMOTE = "<:discord:486939267365470210>"
    const val BOT_EMOTE = "<:bot:486939322151338005>"
    const val LINK_EMOTE = "\uD83D\uDD17"
    const val MAGIC_EMOTE = "✨"
    const val INFO_EMOTE = "<:info:486945488080338944>"
    const val PATREON_EMOTE = "<:p_:475801484282429450>"
    //const val PAYPAL_EMOTE = "<:paypal:486945242369490945>"
    val LING_MSG = String.format(
        "\n%s **Join the Server**: [https://invite.boob.bot](https://invite.boob.bot)"
                + "\n%s **Add the Bot**: [https://bot.boob.bot](https://bot.boob.bot)"
                + "\n%s **Visit the Website**: [https:/boob.bot](https://boob.bot)"
                //+ "\n%s **Paypal**: [https://paypal.boob.bot](https://paypal.boob.bot)"
                + "\n%s **Unlock cool shit**: [https://www.patreon.com/OfficialBoobBot](https://www.patreon.com/OfficialBoobBot)",
        DISCORD_EMOTE, BOT_EMOTE, LINK_EMOTE, PATREON_EMOTE
    )

    val tag = arrayOf(
        "Amateur",
        "Anal Masturbation",
        "Anal Sex",
        "Animated",
        "Asian",
        "Bareback",
        "Bathroom",
        "BBW",
        "Behind the Scenes",
        "Big Ass",
        "Big Cock",
        "Big Tits",
        "Bikini",
        "Bisexual",
        "Black-haired",
        "Blonde",
        "Blowjob",
        "Bondage",
        "Boots",
        "Brunette",
        "Bukkake",
        "Car",
        "Cartoon",
        "Caucasian",
        "Celebrity",
        "CFNM",
        "Chubby",
        "Compilation",
        "Couple",
        "Cream Pie",
        "Cum Shot",
        "Cum Swap",
        "Deepthroat",
        "Domination",
        "Double Penetration",
        "Ebony",
        "Facial",
        "Fat",
        "Female-Friendly",
        "Femdom",
        "Fetish",
        "Fisting",
        "Footjob",
        "Funny",
        "Gagging",
        "Gangbang",
        "Gay",
        "Gay Couple",
        "Gay Group Sex",
        "German",
        "Glamour",
        "Glasses",
        "Glory Hole",
        "Granny",
        "Group Sex",
        "Gym",
        "Hairy",
        "Handjob",
        "Hentai",
        "High Heels",
        "Hospital",
        "Indian",
        "Interracial",
        "Japanese",
        "Kissing",
        "Latex",
        "Latin",
        "Lesbian",
        "Licking Vagina",
        "Lingerie",
        "Maid",
        "Massage",
        "Masturbation",
        "Mature",
        "Midget",
        "MILF",
        "Muscular",
        "Nurse",
        "Office",
        "Oral Sex",
        "Oriental",
        "Outdoor",
        "Pantyhose",
        "Party",
        "Peeing",
        "Piercings",
        "Police",
        "Pool",
        "Pornstar",
        "Position 69",
        "POV",
        "Pregnant",
        "Public",
        "Redhead",
        "Rimming",
        "Romantic",
        "Russian",
        "School",
        "Secretary",
        "Shaved",
        "Shemale",
        "Skinny",
        "Small Tits",
        "Solo Gay",
        "Solo Girl",
        "Solo Male",
        "Spanking",
        "Spectacular",
        "Spycam",
        "Squirting",
        "Stockings",
        "Strap-on",
        "Striptease",
        "Swallow",
        "Tattoos",
        "Teen",
        "Threesome",
        "Titfuck",
        "Toilet",
        "Toys",
        "Tribbing",
        "Uniform",
        "Vaginal Masturbation",
        "Vaginal Sex",
        "Vintage",
        "Wanking",
        "Webcam",
        "Young & Old"
    )

    val readyFormat by lazy {
        val shardManager = BoobBot.shardManager
        val home = shardManager.home
        val jda = shardManager.shards[0]

        String.format(
            "Logging in %s\n"
                    + "Oauth link: %s\n"
                    + "JDA Version: %s\n"
                    + "Docs halp: http://home.dv8tion.net:8080/job/JDA/javadoc/\n"
                    + "Logged in as: %s (%s)\n"
                    + "Guilds: %d\n"
                    + "Shards: %d\n"
                    + "Users: %d\n"
                    + "Bots: %d\n"
                    + "Total Users: %d\n"
                    + "Home Guild: %s\n"
                    + "Users: %d\n"
                    + "Bots: %d\n"
                    + "Total Users: %d",
            BOOT_BANNER,
            BoobBot.inviteUrl,
            JDAInfo.VERSION,
            jda.selfUser.name,
            jda.selfUser.id,
            shardManager.guildCache.size(),
            shardManager.shardsTotal,
            shardManager.users.filter { !it.isBot }.size,
            shardManager.users.filter { it.isBot }.size,
            shardManager.userCache.size(),
            home?.name,
            home?.members?.filter { !it.user.isBot }?.size,
            home?.members?.filter { it.user.isBot }?.size,
            home?.memberCache?.size()
        )
    }

    fun error(text: String): String {
        return MessageFormat.format("\uD83D\uDEAB {0}", text)
    }

    fun info(text: String): String {
        return MessageFormat.format("{1} {0}", text, INFO_EMOTE)
    }

    fun monospaced(items: List<String>): String {
        return items.joinToString("`, `", prefix = "`", postfix = "`")
    }
}
