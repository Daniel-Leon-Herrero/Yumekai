package net.codefastly.yumekai.repository.online


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.models.comments.AnimeComment
import net.codefastly.yumekai.models.shop.CategoriesShop
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.VolumeDetailsShop
import net.codefastly.yumekai.models.shop.VolumeShop
import org.w3c.dom.Document
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

    fun getAllShopCategories(): LiveData<MutableList<CategoriesShop>>{
        val mutableData = MutableLiveData<MutableList<CategoriesShop>>()

        db
            .collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                if( documents != null){
                    var dataList = mutableListOf<CategoriesShop>()
                    for( document in documents ){
                        dataList.add( CategoriesShop(
                            document.data["title"] as String,
                            document.data["icon"] as String,
                            document.data["available"] as Boolean,
                            document.data["order"] as Long
                        )
                        )
                    }
                    dataList.sortBy { it.order }
                    mutableData.value = dataList
                }
            }

        return mutableData
    }

    suspend fun getAllCommentsByAnimeId(animeId: Int ): LiveData<MutableList<AnimeComment>>{
        val mutableData = MutableLiveData<MutableList<AnimeComment>>()

        withContext(Dispatchers.IO){
            db
                .collection("comments")
                .whereEqualTo("anime_id", animeId)
                .get()
                .addOnSuccessListener { documents ->
                    if( documents != null ){
                        var dataList = mutableListOf<AnimeComment>()
                        for( document in documents ){
                            dataList.add(
                                AnimeComment(
                                    document.data["anime_id"] as Long,
                                    document.data["commented_on"] as String,
                                    document.data["message"] as String,
                                    document.data["reactions"] as Long,
                                    document.data["supporter"] as Boolean,
                                    document.data["user_image"] as String,
                                    document.data["username"] as String,
                                )
                            )
                        }
                        mutableData.value = dataList
                    }
                }
        }

        return mutableData
    }

    suspend fun getRealTimeCommentsByAnimeId(animeId: Int ): LiveData<MutableList<AnimeComment>>{
        val mutableData = MutableLiveData<MutableList<AnimeComment>>()

        withContext(Dispatchers.IO){
            db
                .collection("comments")
                .whereEqualTo("anime_id", animeId)
                .addSnapshotListener { snapchots, e ->
                    if (e != null){
                        Log.w( TAG, "Listen:error", e)
                        return@addSnapshotListener
                    }
                    var dataList = mutableListOf<AnimeComment>()
                    for ( snap in snapchots!!.documentChanges ) {
                        when( snap.type ){
                            DocumentChange.Type.ADDED -> {
                                dataList.add(AnimeComment(
                                    snap.document.data["anime_id"] as Long,
                                    snap.document.data["commented_on"] as String,
                                    snap.document.data["message"] as String,
                                    snap.document.data["reactions"] as Long,
                                    snap.document.data["supporter"] as Boolean,
                                    snap.document.data["user_image"] as String,
                                    snap.document.data["username"] as String,
                                ))
                            }
                        }
                    }
                    mutableData.value = dataList
                }
        }

        return mutableData
    }


    /*
    * Only for test
    * */
    fun addComments( comment: HashMap<String, Any>) {
        db.collection("comments")
            .add(comment)
            .addOnSuccessListener { documentReference ->
                if (documentReference != null) {
                    Log.d(
                        "FIREBASE_SUCCESS",
                        "DocumentSnapshot written with ID: ${documentReference.id}"
                    )
                }
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE_WARNING", e)
            }
    }

}
