package com.example.redstackapp.frg

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.HomeFrgClickListener
import com.example.redstackapp.adapter.CommentAdapter
import com.example.redstackapp.adapter.RelatedArticleAdapter
import com.example.redstackapp.databinding.FragmentArticleBinding
import com.example.redstackapp.model.Article
import com.example.redstackapp.model.Comment
import com.example.redstackapp.model.ShortArticle


class ArticleFragment : Fragment(), HomeFrgClickListener {
    lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the row_key_word for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        if (arguments?.get("data") != null) {
            val article = arguments?.get("data") as Article
        }
        if (arguments?.get("myData") != null) {
            val shortArticle = arguments?.get("myData") as ShortArticle
            binding.apply {
                userName.text = shortArticle.postWriter
                lastSeen.text = shortArticle.postDate
                title.text = shortArticle.postTitle
                descArticle.text = shortArticle.postDesc
            }

        }


        /* Related Articles */
        set_up_rv_article()

        /* Comments */
        set_up_rv_comment()

        /* show Dialog */
        binding.btnSendComment.setOnClickListener {
            showDialog()
        }

        binding.articleRightArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        set_up_navigation()
        return binding.root
    }

    private fun set_up_rv_article(

    ) {
        val relatedList = MutableList(10) {
            Article(
                R.drawable.profile2,
                "قیمت بیت کوین برای اولین\u200Cبار در یک سال گذشته به ۱۱٫۶ هزار دلار رسید ",
                "سارینا آجیلی",
                "12 روز پیش"
            )
        }
        // relatedList.add(article)
        binding.relatedArticlesRecyclerView.adapter =
            RelatedArticleAdapter(relatedList, this)
        binding.apply {
            relatedArticlesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            relatedArticlesRecyclerView.itemAnimator?.changeDuration = 0
            relatedArticlesRecyclerView.setHasFixedSize(true)
            relatedArticlesRecyclerView.isNestedScrollingEnabled = false
        }
    }

    private fun set_up_rv_comment() {
        val commentList = MutableList(10) {
            Comment(
                R.drawable.profile3,
                "محمد جواد رحمانی",
                "اینجا عرضه وحشتناک سهام های دولتی رشد های بی سر ته سهام های دولتی ذکر نشده سهام بدون پشتوانه عرضه کردن حبابه اگر همینطور بیشتر بشه قطعا از این نقاطی ک\u200Cگفتی عبور میکنه خدا نکنه سقوط کنه"
            )
        }
        binding.apply {
            commentsRecycler.adapter = CommentAdapter(commentList)
            commentsRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            commentsRecycler.setHasFixedSize(true)
        }
    }

    private fun set_up_navigation() {

        binding.profileImage.setOnClickListener {
            val action = ArticleFragmentDirections.actionArticleFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        binding.userName.setOnClickListener {
            val action = ArticleFragmentDirections.actionArticleFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.setCancelable(true)
        val commentEntered = dialog.findViewById<EditText>(R.id.editText_comment)
        val sendComment = dialog.findViewById<Button>(R.id.button_sendComment)
        sendComment.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        val window: Window? = dialog.getWindow()
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()?.setGravity(Gravity.TOP)
    }

    override fun OnBookMarkClick(pos: Int, article: Article) {
        article.IsBookmarked = article.IsBookmarked.not()
        binding.relatedArticlesRecyclerView.adapter?.notifyItemChanged(pos)
    }


    override fun OnRowClick(pos: Int, article: Article) {

        binding.apply {
            title.text = article.title
            lastSeen.text = article.date
            userName.text = article.writer
            profileImage.setImageResource(article.profilePic)
            scrollView.fullScroll(ScrollView.FOCUS_UP)
        }
    }

}
