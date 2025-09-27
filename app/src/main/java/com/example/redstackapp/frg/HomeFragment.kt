package com.example.redstackapp.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.HomeFrgClickListener
import com.example.redstackapp.adapter.HomeAdapter
import com.example.redstackapp.adapter.RelatedArticleAdapter
import com.example.redstackapp.model.Article

class HomeFragment : Fragment(), HomeFrgClickListener {

    lateinit var recyclerVer: RecyclerView
    lateinit var recyclerHoriz: RecyclerView
    companion object
    {
        const val ARG_DATA_CODE = "data"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()

        val list = mutableListOf<Article>()
        fill_rv_list(list)
        setup_rv_vertical(view, list)
        setup_tv_horiz(view, list)
        return view
    }

    private fun set_up_navigation(article: Article) {

        val bundle = Bundle()
        bundle.putParcelable(ARG_DATA_CODE,article)
        findNavController().navigate(R.id.action_global_articleFragment, bundle)
    }

    fun fill_rv_list(list: MutableList<Article>) {
        repeat(5) {
            list.add(
                Article(
                    R.drawable.profile,
                    "کاهش درآمدهای گوگل در سه\u200Cماهه دوم سال ۲۰۲۰ طی ۱۶ سال اخیر بی\u200Cسابقه بوده است ",
                    "آمنه داودی",
                    "2 روز پیش"

                )
            )
        }
    }

    fun setup_rv_vertical(
        view: View,
        list: MutableList<Article>
    ) {
        this.recyclerVer = view.findViewById<RecyclerView>(R.id.rv_list)
        this.recyclerVer.adapter = HomeAdapter(list, this)
        this.recyclerVer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        this.recyclerVer.setHasFixedSize(true)

    }

    fun setup_tv_horiz(view: View, list: MutableList<Article>) {
        this.recyclerHoriz = view.findViewById<RecyclerView>(R.id.rv_best_horiz)
        this.recyclerHoriz.adapter =
            RelatedArticleAdapter(list, this)
        this.recyclerHoriz.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        this.recyclerHoriz.setHasFixedSize(true)
    }

    override fun OnBookMarkClick(pos: Int, article: Article) {
        article.IsBookmarked = article.IsBookmarked.not()
        this.recyclerVer.adapter?.notifyItemChanged(pos)
    }

    override fun OnRowClick(pos: Int, article: Article) {
        set_up_navigation(article)
    }

}