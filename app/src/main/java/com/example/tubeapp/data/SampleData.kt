package com.example.tubeapp.data

object SampleData {
    private const val BASE = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"

    private val comments = listOf(
        Comment("أحمد", "فيديو رائع، شكراً لك!"),
        Comment("سارة", "استمتعت كثيراً بالمشاهدة"),
        Comment("علي", "ننتظر المزيد من هذا المحتوى")
    )

    val videos = listOf(
        Video(1, "Big Buck Bunny", "قناة الرسوم", "1.2 م مشاهدة", "منذ أسبوع", "9:56",
            BASE + "images/BigBuckBunny.jpg", BASE + "BigBuckBunny.mp4",
            "فيلم رسوم متحركة قصير مفتوح المصدر.", 1200, comments),
        Video(2, "Elephants Dream", "قناة الرسوم", "850 ألف مشاهدة", "منذ شهر", "10:53",
            BASE + "images/ElephantsDream.jpg", BASE + "ElephantsDream.mp4",
            "أول فيلم مفتوح المصدر من مشروع Blender.", 640, comments),
        Video(3, "For Bigger Blazes", "قناة التقنية", "320 ألف مشاهدة", "منذ 3 أيام", "0:15",
            BASE + "images/ForBiggerBlazes.jpg", BASE + "ForBiggerBlazes.mp4",
            "مقطع قصير تجريبي.", 210, comments),
        Video(4, "For Bigger Escape", "قناة التقنية", "98 ألف مشاهدة", "منذ يومين", "0:15",
            BASE + "images/ForBiggerEscapes.jpg", BASE + "ForBiggerEscapes.mp4",
            "مقطع قصير تجريبي.", 95, comments),
        Video(5, "Sintel", "قناة الرسوم", "2.4 م مشاهدة", "منذ سنة", "14:48",
            BASE + "images/Sintel.jpg", BASE + "Sintel.mp4",
            "فيلم رسوم متحركة من مشروع Blender.", 5100, comments),
        Video(6, "Tears of Steel", "قناة الخيال العلمي", "1.7 م مشاهدة", "منذ 6 أشهر", "12:14",
            BASE + "images/TearsOfSteel.jpg", BASE + "TearsOfSteel.mp4",
            "فيلم خيال علمي قصير.", 3300, comments)
    )

    fun byId(id: Int): Video? = videos.firstOrNull { it.id == id }
}
