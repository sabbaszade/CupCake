import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.ShortArticleClickHandler
import com.example.redstackapp.model.ShortArticle
import com.google.android.material.textview.MaterialTextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.row_recycler_posts.view.*

class ShortArticleAdapter(
    var shortArticleList: MutableList<ShortArticle>,
    val articleClickHandler: ShortArticleClickHandler
) : RecyclerView.Adapter<ShortArticleAdapter.ShortArticleViewHolder>() {

    inner class ShortArticleViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val userImage: CircleImageView = v.rvPost_userImage
        val postTitle: MaterialTextView = v.rvPost_title
        val postDesc: MaterialTextView = v.rvPost_description
        val postWriter: MaterialTextView = v.rvPost_userName
        val postDate: MaterialTextView = v.rvPost_dateWriten
        val likeNumber: MaterialTextView = v.rvPost_likesNum
        val commentNumber: MaterialTextView = v.rvPost_commentsNum
        val postBookmark: AppCompatImageView = v.rvPost_bookmark
        val postLike: AppCompatImageView = v.rvPost_iconFav


        init {

            postBookmark.setOnClickListener {
                articleClickHandler.onBookmarkClick(layoutPosition)
            }
            v.rvPost_iconMore.setOnClickListener {
                articleClickHandler.onMoreClick(layoutPosition)
            }
            postLike.setOnClickListener {
                articleClickHandler.onLikeClick(layoutPosition)
            }

            itemView.setOnClickListener {
                articleClickHandler.OnRowClick(layoutPosition, shortArticleList[layoutPosition])
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_recycler_posts, parent, false)

        return ShortArticleViewHolder(itemView)
    }

    override fun getItemCount() = shortArticleList.size

    override fun onBindViewHolder(holder: ShortArticleViewHolder, position: Int) {

        val currentItem = shortArticleList[position]
        holder.userImage.setImageResource(currentItem.userImage)
        holder.postTitle.text = currentItem.postTitle
        holder.postDesc.text = currentItem.postDesc
        holder.postWriter.text = currentItem.postWriter
        holder.postDate.text = currentItem.postDate
        holder.likeNumber.text = currentItem.likeNumber
        holder.commentNumber.text = currentItem.commentNumber
        if (currentItem.isBookmark)
            holder.postBookmark.setImageResource(R.drawable.ic_bookmark_fill)
        else {
            holder.postBookmark.setImageResource(R.drawable.ic_bookmark_2)
        }

        if (currentItem.isLiked)
            holder.postLike.setImageResource(R.drawable.ic_heart_fill)
        else {
            holder.postLike.setImageResource(R.drawable.ic_heart_1)
        }

    }
}