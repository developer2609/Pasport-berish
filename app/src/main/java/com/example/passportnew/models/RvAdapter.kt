package com.example.passportnew.models

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passportnew.databinding.ItemLayoutBinding


class RvAdapter (var list: List<User>) : RecyclerView.Adapter<RvAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemLayoutBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(user: User) {


            itemListBinding.textName.text=user.name
            itemListBinding.textSeriya.text=user.seriya
            itemListBinding.textData.text=user.data
            itemListBinding.textSurname.text=user.surname
            itemListBinding.imageGalery.setImageURI(Uri.parse(user.imagePath)  )




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}