package com.nepplus.practiceproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nepplus.practiceproject.R
import com.nepplus.practiceproject.databinding.FragmentGithubBinding
import com.nepplus.practiceproject.databinding.ItemFragmentBinding
import com.nepplus.practiceproject.model.User
import com.nepplus.practiceproject.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubFragment  : Fragment(){

    lateinit var binding : FragmentGithubBinding

    val viewModel : GithubViewModel by viewModel()

    val githubadapter = GithubAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentGithubBinding.inflate(inflater, container, false).apply {
       binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
    }

    private fun initView(){
        binding.apply {
            editText.addTextChangedListener {
                viewModel.setUser(it.toString())
            }

        binding.recyclerview.apply {
            adapter = githubadapter.apply {
                clickListener = { user ->
                    val fragment = GithubDetailFragment.newInstance(user)
                    activity
                        ?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.fragmentContainerView, fragment)
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }

        }
        }
    }

    private fun observe(){
        //livedata -> observe ????????? ??? ????????? ??????.
        //livedata -> observe ????????? ??? ????????? ??????.
        //???????????? ???????????? ????????? ???????????? ????????? observe????????? ??? ????????? ??????.
        viewModel.user.observe(viewLifecycleOwner){
            githubadapter.submitList(it.items)
        }

    }
}

class GithubViewHolder(val binding : ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root)

class GithubAdapter : ListAdapter<User, GithubViewHolder>(User.DiffUtil){

    //?????? ????????? nullable??? ????????????.
    //?????? ?????? null ??? ???????????? ????????????.
    //????????? ????????? ??? null ??? ??????????????? it.root.setOnClickListener -> clicklister?.invoke get??? ????????????
    var clickListener : ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {

        val view = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GithubViewHolder(view)
    }

    //positon -> getItem
    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(this.root).load(getItem(position).avatar_url).into(image)
            title.text = getItem(position).login
            subTitle.text = getItem(position).repos_url
//            invoke -> ?????? ?????????
        }.also {
            it.root.setOnClickListener {
                clickListener?.invoke(getItem(position))
            }
        }
    }

}