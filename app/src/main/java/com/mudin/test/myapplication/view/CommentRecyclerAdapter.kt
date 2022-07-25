package com.mudin.test.myapplication.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mudin.test.myapplication.R
import com.mudin.test.myapplication.databinding.ItemCommentBinding
import com.mudin.test.myapplication.model.Comment
import kotlin.collections.ArrayList


class CommentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"
    private var items: List<Comment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommentHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(CommentList: List<Comment>) {
        items = CommentList
    }

    class CommentHolder
    constructor(
        private val itemBinding: ItemCommentBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(Comment: Comment) {
            itemBinding.tvName.text = Comment.name
            itemBinding.tvEmail.text = Comment.email
            itemBinding.tvBody.text = Comment.body
        }

    }

}
