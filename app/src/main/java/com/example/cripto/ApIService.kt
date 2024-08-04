
import com.example.cripto.DeleteVoteRequest
import com.example.cripto.Message
import com.example.cripto.VoteItem
import com.example.cripto.VoteResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @GET("/moedas")  // Replace with your actual endpoint
    fun getData(): Call<DataOuterClass.Data>

    @GET("/vote")
    fun getVotes(@Query("type_out") typeOut: String = "xml"): Call<List<VoteItem>>

    @POST("/vote")
    fun postVote(@Body voteItem: VoteItem):Call<VoteResponse>;


    @HTTP(method = "DELETE", path = "vote", hasBody = true)
    fun deleteVote(@Body deleteRequest: DeleteVoteRequest):Call<Message>

}
