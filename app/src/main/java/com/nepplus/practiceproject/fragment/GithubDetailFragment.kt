package com.nepplus.practiceproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.nepplus.practiceproject.databinding.FragmentGithubBinding
import com.nepplus.practiceproject.databinding.FragmentGithubDetailBinding
import com.nepplus.practiceproject.model.User
import com.nepplus.practiceproject.viewmodel.GithubViewModel

class GithubDetailFragment : Fragment(){

    lateinit var binding: FragmentGithubDetailBinding

    val viewModel : GithubViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentGithubDetailBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = this@GithubDetailFragment
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getParcelable<User>(GITHUB_USER)

        binding.apply {
            user?.let {
                Glide.with(this.root).load(user.avatar_url).into(detailImage)
                detailName.text = user.email
            }
        }
    }

    companion object{

        const val GITHUB_USER = "GITHUB_USER"

//       jvmstatic -  getter//setter 자동으로 생성
//       arguments -> fragment사이에서 데이터를 전달할 수 있도록, Fragment클래스에는 2개의 함수를 제공한다.
//        setargments, getarguments 2개를 제공한다.
//        setarguments - > Bundle을 저장한다.
//        bundle이란 map형태로 구현된 데이터의 묶음

        @JvmStatic
        fun newInstance(user : User) = GithubDetailFragment().apply {
            arguments = Bundle().apply { putParcelable(GITHUB_USER, user) }
        }
    }

}