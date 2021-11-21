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
        //livedata -> observe 처리를 해 주어야 한다.
        //livedata -> observe 처리를 해 주어야 한다.
        //데이터의 업데이트 정보를 위해서는 반드시 observe처리를 해 주어야 한다.
        viewModel.user.observe(viewLifecycleOwner){
            githubadapter.submitList(it.items)
        }

    }
}

class GithubViewHolder(val binding : ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root)

class GithubAdapter : ListAdapter<User, GithubViewHolder>(User.DiffUtil){

    //람다 자체가 nullable인 타입이다.
    //함수 대신 null 로 초기화가 되어있다.
    //클릭을 하였을 때 null 이 아닌경우에 it.root.setOnClickListener -> clicklister?.invoke get을 가져온다
    var clickListener : ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {

        val view = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GithubViewHolder(view)
    }

    //positon -> getItem
    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(this.root).load(getItem(position).avatar_url).into(image)
            title.text = getItem(position).email
            subTitle.text = getItem(position).blog
//            invoke -> 람다 호출식
        }.also {
            it.root.setOnClickListener {
                clickListener?.invoke(getItem(position))
            }
        }
    }

}