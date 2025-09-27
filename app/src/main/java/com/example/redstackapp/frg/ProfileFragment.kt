package com.example.redstackapp.frg

import ShortArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.ShortArticleClickHandler
import com.example.redstackapp.databinding.FragmentProfileBinding
import com.example.redstackapp.model.ShortArticle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ShortArticleClickHandler {
    lateinit var binding: FragmentProfileBinding

    companion object {
        const val ARG_DATA_CODE = "myData"
    }

    val postList = MutableList(10) {
        ShortArticle(
            R.drawable.profile,
            "کاهش درآمدهای گوگل در سه\u200Cماهه دوم سال ۲۰۲۰ طی ۱۶ سال اخیر بی\u200Cسابقه بوده است ",
            "اپل در طول اعلام نتایج درآمد سه\u200C ماهه سوم خود در روز پنج شنبه گفت که هیات\u200C مدیره شرکت یک تجزیه سهام چهار به یک را تصویب کرده\u200C است",
            "خرامان",
            "\u200F3 روز پیش",
            true,
            true,
            "2.5k",
            "\u200F20 دیدگاه"
        )
    }
    val postAdapter = ShortArticleAdapter(postList, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args: ProfileFragmentArgs by navArgs()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        set_up_rv_post()
        onChipClick()

        binding.profileRightArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        /* hide follow btn */

        val follow: MaterialButton = binding.btnFollow
        if (args.visibility == false) {
            follow.setVisibility(View.GONE)
        }


        return binding.root
    }

    private fun set_up_navigation(shortArticle: ShortArticle) {

        val bundle = Bundle()
        bundle.putParcelable(ARG_DATA_CODE, shortArticle)
        findNavController().navigate(R.id.action_profileFragment_to_articleFragment, bundle)
    }

    private fun onChipClick() {
        binding.profleFrgChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipPosts -> {
                    chipPosts.setChipBackgroundColorResource(R.color.white)
                    chipPosts.setTextColor(resources.getColor(R.color.purple_813))
                    chipFav.setChipBackgroundColorResource(R.color.bg_f3)
                    chipFav.setTextColor(resources.getColor(R.color.black36))
                    postAdapter.shortArticleList = postList
                    postAdapter.notifyDataSetChanged()
                }
                R.id.chipFav -> {
                    chipFav.setChipBackgroundColorResource(R.color.white)
                    chipFav.setTextColor(resources.getColor(R.color.purple_813))
                    chipPosts.setChipBackgroundColorResource(R.color.bg_f3)
                    chipPosts.setTextColor(resources.getColor(R.color.black36))
                    postAdapter.shortArticleList = postList.filter { it.isBookmark }.toMutableList()
                    postAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun set_up_rv_post() {

        binding.apply {
            postsRecyclerView.adapter = postAdapter
            postsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            postsRecyclerView.itemAnimator?.changeDuration = 0
            postsRecyclerView.setHasFixedSize(true)
            postsRecyclerView.isNestedScrollingEnabled = false
        }
    }


    override fun onBookmarkClick(position: Int) {
        postAdapter.shortArticleList[position].isBookmark =
            postAdapter.shortArticleList[position].isBookmark.not()
        postAdapter.notifyItemChanged(position)
    }

    override fun onLikeClick(position: Int) {
        postAdapter.shortArticleList[position].isLiked =
            postAdapter.shortArticleList[position].isLiked.not()
        postAdapter.notifyItemChanged(position)
    }

    override fun onMoreClick(position: Int) {

        showBottomSheet(position)
    }

    override fun OnRowClick(pos: Int, shortArticle: ShortArticle) {
        set_up_navigation(shortArticle)
    }

    private fun showBottomSheet(position: Int) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.bottom_sheet)
        val deleteArticle = bottomSheet.findViewById<Button>(R.id.deleteArticle_btn)
        val editArticle = bottomSheet.findViewById<Button>(R.id.editArticle_btn)
        editArticle?.setOnClickListener {
            val title_tv: MaterialTextView? = view?.findViewById(R.id.rvPost_title)
            val desc_tv: MaterialTextView? = view?.findViewById(R.id.rvPost_description)
            val title = title_tv?.text.toString()
            val desc = desc_tv?.text.toString()
            set_navigation(title, desc)
            bottomSheet.dismiss()
        }
        deleteArticle?.setOnClickListener {
            postAdapter.shortArticleList.removeAt(position)
            postAdapter.notifyItemRemoved(position)
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }

    private fun set_navigation(title: String, desc: String) {
        val action =
            ProfileFragmentDirections.actionProfileFragmentToWriteArticleFragment(title, desc)
        findNavController().navigate(action)
    }
}

