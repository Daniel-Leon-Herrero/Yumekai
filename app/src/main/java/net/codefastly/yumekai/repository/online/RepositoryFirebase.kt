package net.codefastly.yumekai.repository.online


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import net.codefastly.yumekai.models.shop.VolumeDetailsShop
import net.codefastly.yumekai.models.shop.VolumeShop
import java.lang.Exception

class RepositoryFirebase{

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }


    fun addTestVolume(){

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
                                VolumeDetailsShop(details["age_rating"] as String, details["dimensional_weight"] as Double, details["genre"] as List<String>, details["language"] as String, details["page_count"] as Long, details["publisher"] as String, details["release_date"] as String, details["themes"] as List<String>),
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

}