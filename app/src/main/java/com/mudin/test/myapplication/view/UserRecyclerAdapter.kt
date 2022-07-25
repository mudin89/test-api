package com.mudin.test.myapplication.view


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mudin.test.myapplication.databinding.ItemUserBinding
import com.mudin.test.myapplication.model.User


class UserRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"
    var onItemClick: ((User) -> Unit)? = null
    private var items: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(userList: List<User>) {
        items = userList
    }

    class UserHolder
    constructor(
        private val itemBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: User) {
            itemBinding.tvTitle.text = user.title
            itemBinding.tvBody.text = user.body

            itemBinding.container1.setOnClickListener(View.OnClickListener {
                val context = itemBinding.root.context
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("POST_ID", user.userId)
                context.startActivity(intent)
            })
        }

    }

}
