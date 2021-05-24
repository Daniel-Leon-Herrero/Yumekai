package net.codefastly.yumekai.repository.online


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.VolumeDetailsShop
import net.codefastly.yumekai.models.shop.VolumeShop
import java.lang.Exception

class RepositoryFirebase{

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }


    fun getVolumesBySerie( serie: String ): LiveData<MutableList<VolumeShop>> {

        val mutableData = MutableLiveData<MutableList<VolumeShop>>()

        db
            .collection("volumes")
            .whereEqualTo("serie", serie)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    if( documents != null ){
                        var dataList = mutableListOf<VolumeShop>()
                        for (document in documents){
                            val details = document.data["details"] as Map<String, Any>
                            val volume = VolumeShop(
                                document.data["description"] as String,
                                VolumeDetailsShop(details["age_rating"] as String, details["dimensional_weight"] as Double, details["genre"] as List<String>, details["language"] as String, details["page_count"] as Long, details["publisher"] as String, details["release_date"] as String, details["themes"] as List<String>, details["media"] as String),
                                document.data["image_url"] as String,
                                document.data["price"] as Double,
                                document.data["price_rtl"] as Double,
                                document.data["serie"] as String,
                                document.data["start_description"] as String,
                                document.data["title"] as String,
                                document.data["volume"] as Long,
                            )
                            dataList.add(volume)
                        }
                        mutableData.value = dataList
                    }
                }catch ( ex: Exception ){
                    Log.e(TAG, ex.message.toString() )
                }
            }
            .addOnFailureListener {  e -> Log.e(TAG, "Error writing document", e) }

        return mutableData
    }

    fun getAvailableSeries(): LiveData<MutableList<SerieShop>> {
        val mutableData = MutableLiveData<MutableList<SerieShop>>()

        db
            .collection("series")
            .whereEqualTo("available", true)
            .get()
            .addOnSuccessListener { documents ->
                try{
                    if( documents != null ){
                        var dataList = mutableListOf<SerieShop>()
                        for( document in documents ){
                            dataList.add( SerieShop(
                                document.data["order"] as Long,
                                document.data["title"] as String,
                                document.data["available"] as Boolean,
                                document.data["image_url"] as String
                            ))
                        }
                        dataList.sortBy { it.order }
                        mutableData.value = dataList
                    }
                }catch ( ex: Exception ){
                    Log.e(TAG, ex.message.toString() )
                }
            }
            .addOnFailureListener {  e -> Log.e(TAG, "Error writing document", e) }

        return mutableData
    }


    /*
    * Only for test
    * */
    private fun addMagiVolumes(){

        val volume1 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 1,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol1.png?alt=media&token=5396496d-02a5-411f-b841-fcdab9e39aa9",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 1 presenta historia y arte de Shinobu Otaka.",
            "description" to "Deep within the desert lie the mysterious Dungeons, vast stores of riches there for the taking by anyone lucky enough to find them and brave enough to venture into the depths from where few have ever returned. Plucky young adventurer Aladdin means to find the Dungeons and their riches, but Aladdin is just as mysterious as the treasures he seeks. Together with his friend, Alibaba, and the genie, Ugo, Aladdin sets out to find his fortune in the depths of the endless dunes...",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "13-08-2013",
                "page_count" to 200,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume2 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 2,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol2.png?alt=media&token=e917f187-4d50-43d1-ab41-c2a12b44939d",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 2 features story and art by Shinobu Ohtaka.",
            "description" to "Aladdin and Alibaba have entered the Dungeon of Qishan hoping to find hidden treasure - " +
                    "but danger's found them! A horde of slimes closes in on them, while Lord Jamil and his slaves head " +
                    "into the dungeon looking to intercept Aladdin and grab any riches he may have found! But these " +
                    "rivals have more to worry about than each other, and new friends, new enemies and amazing " +
                    "riches are yet to be discovered!",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "08-10-2013",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume3 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 3,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol3.png?alt=media&token=f8554efe-82cd-4cb0-ae5e-f73c9edfb1ef",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 3 features story and art by Shinobu Ohtaka.",
            "description" to "Aladdin finds himself among the Kouga tribe, who live deep in the desert far from Qishan. An emissary from the Kou Empire arrives offering peace, but when it turns out to be an offer they can't refuse, things take a turn for the worse. Aladdin learns more about the legend of the Magi and the Rukh, bird-like beings of light, with whom he appears to share a deep connection...",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "10-12-2013",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume4 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 4,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol4.png?alt=media&token=0096c678-3783-4be9-8a26-da86ab1d19d3",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 4 features story and art by Shinobu Ohtaka.",
            "description" to "After many adventures, Aladdin finally reaches the great city of Balbadd. There he reunites with his friend Alibaba hoping to once again capture a dungeon together. However, a more cruel destiny lies ahead...",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "11-02-2014",
                "page_count" to 208,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume5 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 5,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol5.png?alt=media&token=a7c2ba59-baa8-4e2f-af0e-9d78c61649a7",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 5 features story and art by Shinobu Ohtaka.",
            "description" to "Aladdin sets his plan to rescue Alibaba from Balbadd into motion. Things take an unexpected turn when another Magi, Judar, stands in Aladdin's way.",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "08-04-2014",
                "page_count" to 208,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume6 = hashMapOf(
            "serie" to "Magi",
            "title" to "Magi Manga",
            "volume" to 6,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol6.png?alt=media&token=335d534a-186e-4ec1-97de-858aa9bdd275",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Magi 6 features story and art by Shinoby Ohtaka.",
            "description" to "Alibaba finally takes a stand for his homeland. What power does he need to acquire? The true ability of the Djinn's Metal Vessel becomes clear!",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action", "Fantasy"),
                "themes" to listOf<String>("Adventure","Battles","Historical","Powers"),
                "age_rating" to "16+",
                "release_date" to "10-06-2013",
                "page_count" to 200,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        db.collection("volumes")
            .add(volume1)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume2)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume3)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume4)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume5)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume6)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

    }
    private fun addKimetsuVolumes(){

        val volume1 = hashMapOf(
            "serie" to "Demon Slayer",
            "title" to "Demon Slayer Kimetsu no Yaiba Manga",
            "volume" to 1,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2FDemon%20Slayer%2Fdemon_slayer_manga_vol1.png?alt=media&token=0b44cbf5-5b99-4ce0-bd04-0aa72742ccd1",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Demon Slayer: Kimetsu no Yaiba manga volume 1 features story and art by Koyoharu Gotouge.",
            "description" to "In Taisho-era Japan, Tanjiro Kamado is a kindhearted boy who makes a living selling charcoal. But his peaceful life is shattered when a demon slaughters his entire family. His little sister Nezuko is the only survivor, but she has been transformed into a demon herself! Tanjiro sets out on a dangerous journey to find a way to return his sister to normal and destroy the demon who ruined his life.\n" +
                    "\n" +
                    "Learning to slay demons won’t be easy, and Tanjiro barely knows where to start. The surprise appearance of another boy named Giyu, who seems to know what’s going on, might provide some answers…but only if Tanjiro can stop Giyu from killing his sister first!",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action"),
                "themes" to listOf<String>("Battles","Historical","Supernatural"),
                "age_rating" to "13+",
                "release_date" to "03-07-2018",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume2 = hashMapOf(
            "serie" to "Demon Slayer",
            "title" to "Demon Slayer Kimetsu no Yaiba Manga",
            "volume" to 2,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2FDemon%20Slayer%2Fdemon_slayer_manga_vol2.png?alt=media&token=f61a5ad4-b374-4902-9bf6-3225cac074e3",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Demon Slayer: Kimetsu no Yaiba manga volume 2 features story and art by Koyoharu Gotouge.",
            "description" to "During final selection for the Demon Slayer Corps, Tanjiro faces a disfigured demon and uses the techniques taught by his master, Urokodaki! As Tanjiro begins to walk the path of the Demon Slayer, his search for the demon who murdered his family leads him to investigate the disappearances of young girls in a nearby town.",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action"),
                "themes" to listOf<String>("Battles","Historical","Supernatural"),
                "age_rating" to "13+",
                "release_date" to "04-09-2018",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume3 = hashMapOf(
            "serie" to "Demon Slayer",
            "title" to "Demon Slayer Kimetsu no Yaiba Manga",
            "volume" to 3,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2FDemon%20Slayer%2Fdemon_slayer_manga_vol3.png?alt=media&token=42826fbf-91a3-47e5-b0a7-e4acf0c10fa1",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Demon Slayer: Kimetsu no Yaiba manga volume 3 features story and art by Koyoharu Gotouge.",
            "description" to "Tanjiro and Nezuko cross paths with two powerful demons who fight with magical weapons. Even help from Tamayo and Yushiro may not be enough to defeat these demons who claim to belong to the Twelve Kizuki that directly serve Kibutsuji, the demon responsible for all of Tanjiro’s woes! But if these demons can be defeated, what secrets can they reveal about Kibutsuji?",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action"),
                "themes" to listOf<String>("Battles","Historical","Supernatural"),
                "age_rating" to "13+",
                "release_date" to "06-11-2018",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )

        val volume4 = hashMapOf(
            "serie" to "Demon Slayer",
            "title" to "Demon Slayer Kimetsu no Yaiba Manga",
            "volume" to 4,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2FDemon%20Slayer%2Fdemon_slayer_manga_vol4.png?alt=media&token=769a4aa3-fd4a-48be-940d-504898c05ee5",
            "price_rtl" to 11.99,
            "price" to 9.99,
            "start_description" to "Demon Slayer: Kimetsu no Yaiba manga volume 4 features story and art by Koyoharu Gotouge.",
            "description" to "After a fierce battle with a demon inside a maddening house of ever-changing rooms, Tanjiro has a chance to find out about the fighter in the boar-head mask. Who is this passionate swordsman and what does he want? Later, a new mission has Tanjiro and his compatriots heading for Mt. Natagumo and a confrontation with a mysterious and horrifying threat…",
            "details" to hashMapOf(
                "publisher" to "VIZ BOOKS",
                "media" to "Manga",
                "genre" to listOf<String>("Action"),
                "themes" to listOf<String>("Battles","Historical","Supernatural"),
                "age_rating" to "13+",
                "release_date" to "01-01-2019",
                "page_count" to 192,
                "dimensional_weight" to 1.00,
                "language" to "English"
            )
        )



        db.collection("volumes")
            .add(volume1)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume2)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume3)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db.collection("volumes")
            .add(volume4)
            .addOnSuccessListener { documentReference ->
                if(documentReference != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }


    }
    fun addSeries(){
        val serie1 = hashMapOf(
            "order" to 1,
            "title" to "Magi",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fmagi%2Fserie_magi.png?alt=media&token=070222d2-c831-467c-b08d-2963b6f8ea98"
        )

        val serie2 = hashMapOf(
            "order" to 2,
            "title" to "Demon Slayer",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fdemon_slayer%2Fserie_demon_slayer.png?alt=media&token=715a71e1-7de4-4081-bd03-87c671047838"
        )

        val serie3 = hashMapOf(
            "order" to 3,
            "title" to "Re:Zero",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fre_zero%2Fserie_re_zero.png?alt=media&token=33d6f27b-800c-4461-bcf4-7d7bc15ad7b5"
        )

        val serie4 = hashMapOf(
            "order" to 4,
            "title" to "Black Clover",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fblack_clover%2Fserie_black_clover.png?alt=media&token=e284f251-f455-470a-a1f2-1b9724c34ee0"
        )

        val serie5 = hashMapOf(
            "order" to 5,
            "title" to "Darling in the Franxx",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol2.png?alt=media&token=e917f187-4d50-43d1-ab41-c2a12b44939d"
        )

        val serie6 = hashMapOf(
            "order" to 6,
            "title" to "My Hero Academia",
            "available" to true,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fdarling_in_the_franxx%2Fserie_darling_in_the_franxx.png?alt=media&token=93256fec-db72-4277-b79b-995463c81795"
        )

        val serie7 = hashMapOf(
            "order" to 7,
            "title" to "Attack on Titan",
            "available" to false,
            "image_url" to "https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/series%2Fattack_on_titan%2Fserie_attack_on_titan.png?alt=media&token=43288a14-a1fc-4ac7-ab5b-f08ef857e010"
        )

        db
            .collection("series")
            .add(serie1)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db
            .collection("series")
            .add(serie2)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db
            .collection("series")
            .add(serie3)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db
            .collection("series")
            .add(serie4)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db
            .collection("series")
            .add(serie5)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }

        db
            .collection("series")
            .add(serie6)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }


        db
            .collection("series")
            .add(serie7)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.d("FIREBASE_SUCCESS", "DocumentSnapshot written with id: ${document.toString()}")
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }
    }

    fun addCategories(){
        val cat1 = hashMapOf(
            "title" to "Manga",
            "icon" to "book",
            "available" to true,
            "order" to 1
        )

        db
            .collection("categories")
            .add(cat1)
            .addOnSuccessListener { document ->
                if( document != null ){
                    Log.e(TAG, "Success ${document.id}")
                }
            }
            .addOnFailureListener { e -> Log.e(TAG, e.toString() ) }
    }




}