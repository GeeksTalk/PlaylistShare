package ke.co.appslab.playslistshare.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.User
import kotlinx.android.synthetic.main.item_users.view.*

typealias clickListener = (User) -> Unit

class UserAdapter(val users: List<User>, val clickListener: clickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View, val clickListener: clickListener) :
        RecyclerView.ViewHolder(itemView) {
        val tvFullName = itemView.tvFullName
        val tvUserEmail = itemView.tvUserEmail

        fun bindUser(user: User) {
            with(user) {
                tvFullName.text = name
                tvUserEmail.text = email
                itemView.setOnClickListener {
                    clickListener(this)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users, parent, false)
        return UserViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(users[position])
    }
}