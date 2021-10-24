import android.app.Activity
import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStream
import org.json.JSONObject
import org.json.JSONArray







class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    var imagesList = arrayListOf<JsonImage>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ImagesAdapter(imagesList, this)

        val jsonArray = JSONArray(loadJSONFromAsset())
        for(index in 0 until jsonArray.length()){
            val imgObj = jsonArray.getJSONObject(index)
            val title = imgObj.getString("title")
            val url = imgObj.getString("url")
            val explanation = imgObj.getString("explanation")
            imagesList.add(JsonImage(title, url, explanation))
            recyclerView.adapter!!.notifyItemInserted(index)
        }

    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = applicationContext.assets.open("data.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}