package net.codefastly.yumekai.repository.online


import android.content.ContentValues.TAG
import android.util.Log
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

    }

    fun getVolumesBySerie( serie: String ){

        val mutableData : MutableLiveData<List<VolumeShop>>

        db
            .collection("volumes")
            .whereEqualTo("serie", serie)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    if( documents != null ){
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
                                document.data["volumne"] as Long,
                            )



                        }
                    }
                }catch ( ex: Exception ){
                    Log.e(TAG, ex.message.toString() )
                }
            }
            .addOnFailureListener {  e -> Log.e(TAG, "Error writing document", e) }
    }

}