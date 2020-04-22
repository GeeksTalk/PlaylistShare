package ke.co.appslab.playslistshare.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Song
import kotlinx.android.synthetic.main.item_songs.view.*

typealias songsListener = (Song) -> Unit

class SongsAdapter(private val songs: List<Song>, private val songsListener: songsListener) :
    RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    class SongsViewHolder(itemView: View, private val songsListener: songsListener) :
        RecyclerView.ViewHolder(itemView) {
        private val tvSongName: TextView = itemView.tvSongName

        fun bindSong(song: Song) {
            with(song) {
                tvSongName.text = songName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_songs, parent, false)
        return SongsViewHolder(itemView, songsListener)
    }

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.bindSong(songs[position])
    }
}