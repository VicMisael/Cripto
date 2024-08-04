import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cripto.DeleteVoteRequest
import com.example.cripto.Message
import com.example.cripto.RetrofitClient
import com.example.cripto.VoteItem
import com.example.cripto.VoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {
    private val _data = MutableLiveData<DataOuterClass.Data>()
    val data: LiveData<DataOuterClass.Data> get() = _data

    private val _voteList = MutableLiveData<List<VoteItem>>()
    val voteList: LiveData<List<VoteItem>> get() = _voteList

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    fun fetchData() {
        apiService.getData().enqueue(object : Callback<DataOuterClass.Data> {
            override fun onResponse(call: Call<DataOuterClass.Data>, response: Response<DataOuterClass.Data>) {
                Log.e("NETWORK", "onResponse: RESPONSED", )
                if (response.isSuccessful) {

                    _data.value = response.body()
                }
            }

            override fun onFailure(call: Call<DataOuterClass.Data>, t: Throwable) {
                Log.e("ERROR", "onFailure: ", )
            }
        })
    }

    // Fetch the list of votes from the server
    fun fetchVotes() {
        apiService.getVotes().enqueue(object : Callback<List<VoteItem>> {
            override fun onResponse(call: Call<List<VoteItem>>, response: Response<List<VoteItem>>) {
                Log.e("NETWORK", "onResponse: RESPONSED", )
                if (response.isSuccessful) {

                    _voteList.value = response.body()
                }
            }

            override fun onFailure(call:  Call<List<VoteItem>>, t: Throwable) {
                Log.e("ERROR", "onFailure: 2", )
            }
        }
        )

    }


    fun postVote(voteItem: VoteItem) {


        apiService.postVote(voteItem).enqueue(object : Callback<VoteResponse> {
            override fun onResponse(call: Call<VoteResponse>, response: Response<VoteResponse>) {
                Log.e("NETWORK", "onResponse: RESPONSED SAVED", )
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call:  Call<VoteResponse>, t: Throwable) {
                Log.e("ERROR", "onFailure: 2", )
            }

            })

    }

    fun deleteVote(name:String) {


        apiService.deleteVote(DeleteVoteRequest(name)).enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                Log.e("NETWORK", "onResponse: RESPONSED SAVED", )
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call:  Call<Message>, t: Throwable) {
                Log.e("ERROR", "onFailure: 2", )
            }

        })

    }

}
