package ke.co.appslab.playslistshare.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Playlist
import kotlinx.android.synthetic.main.item_playlist.view.*

typealias  clicklistener = (Playlist) -> Unit

class PlaylistAdapter(val playlists: List<Playlist>, private val clickListener: clicklistener) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    class PlaylistViewHolder(itemView: View, val clickListener: clicklistener) :
        RecyclerView.ViewHolder(itemView) {
        val tvPlaylistName = itemView.tvPlaylistName
        val tvPlaylistLink = itemView.tvPlaylistLink
        val tvPlaylistType = itemView.tvPlaylistType

        fun bindPlaylist(playlist: Playlist) {
            with(playlist) {
                tvPlaylistName.text = playlistName
                tvPlaylistLink.text = playlistLink
                tvPlaylistType.text = playlistType

                itemView.setOnClickListener {
                    clickListener(this)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users, parent, false)
        return PlaylistViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bindPlaylist(playlists[position])
    }
}