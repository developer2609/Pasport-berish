package com.example.passportnew.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.passportnew.R
import com.example.passportnew.databinding.FragmentHomeBinding
import com.example.passportnew.models.AppDatabase
import com.example.passportnew.models.RvAdapter
import com.example.passportnew.models.User

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var lists: ArrayList<User>
    private lateinit var rvAdapter: RvAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)

       val animation =AnimationUtils.loadAnimation(binding.root.context,R.anim.alpha_anim)
        binding.rv.startAnimation(animation)
        appDatabase=AppDatabase.getInstance(binding.root.context)

        binding.chiqish.setOnClickListener {

            findNavController().navigate(R.id.passportAddFragment)
        }

       lists= ArrayList()
          lists.addAll(appDatabase.myuserDao().getAllContact())

        rvAdapter= RvAdapter(lists)
        binding.rv.adapter=rvAdapter
        rvAdapter.notifyDataSetChanged()


        return binding.root
    }


}