package com.example.redstackapp.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.HomeFrgClickListener
import com.example.redstackapp.adapter.HomeAdapter
import com.example.redstackapp.databinding.FragmentCommerceBinding
import com.example.redstackapp.model.Article


class CommerceFragment : Fragment() ,HomeFrgClickListener{
    private lateinit var binding :FragmentCommerceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_commerce, container, false)
        val list = mutableListOf<Article>()
        fill_rv_list(list)
        setup_rv_vertical(binding.root, list)

        return binding.root
    }

    private fun setup_rv_vertical(veiw_con: View, list: MutableList<Article>) {
        binding.rvListCommerce.adapter = HomeAdapter(list, this)
        binding.rvListCommerce.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvListCommerce.setHasFixedSize(true)
    }

    private fun fill_rv_list(list: MutableList<Article>) {
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

    override fun OnBookMarkClick(pos: Int, article: Article) {
        article.IsBookmarked = article.IsBookmarked.not()
        binding.rvListCommerce.adapter?.notifyItemChanged(pos)
    }
    override fun OnRowClick(pos: Int, article: Article) {
        set_up_navigation(article)
    }
    private fun set_up_navigation(article: Article) {
        val bundle = Bundle()
        bundle.putParcelable(HomeFragment.ARG_DATA_CODE,article)
        findNavController().navigate(R.id.action_global_articleFragment, bundle)
    }
}