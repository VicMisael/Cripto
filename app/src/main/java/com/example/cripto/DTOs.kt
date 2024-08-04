package com.example.cripto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class VoteItem(val name: String, val currency: String)

data class VoteResponse(
    val id: String,
    val name: String,
    val currency: String,
    val message: String = "Voto cadastrado com sucesso"
)

data class DeleteVoteRequest(val id: String)

data class Message(val message:String)

class VoteAdapter(private val context: Context, private val voteList: List<VoteItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return voteList.size
    }

    override fun getItem(position: Int): VoteItem {
        return voteList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val voteNameTextView: TextView = view.findViewById(R.id.textViewVoteName)
        val subvoteNameTextView: TextView = view.findViewById(R.id.textViewSubvoteName)

        val voteItem = getItem(position)
        voteNameTextView.text = voteItem.name
        subvoteNameTextView.text = voteItem.currency

        return view
    }
}
