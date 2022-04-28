package com.akash

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.akash.myassignment.DetailsActivity
import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import com.akash.myassignment.R
import com.akash.myassignment.databinding.UserlistBinding
import com.bumptech.glide.Glide

class UsersAdapter(val context: Context):RecyclerView.Adapter<UsersAdapter.UserViewHolder>(),View.OnClickListener {

     var userslist= mutableListOf<UsersItem>()


     fun setUser(users:List<UsersItem>){
        this.userslist=users.toMutableList()
         notifyDataSetChanged()
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val inflate=LayoutInflater.from(parent.context)
       val binding=UserlistBinding.inflate(inflate)
        return  UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users=userslist[position]
        val name:String=users.subjects.toString()
        val tvname:String=name.replace("[","")
        holder.binding.TVUserName.text=users.name
        holder.binding.TVSubject.text= tvname
        holder.binding.TVCollege.text=users.qualification.toString()
        Glide.with(holder.itemView.context).load(users.profileImage).into(holder.binding.imageview)

        val tName=users.name
        val subjectName=users.subjects
        val qualification=users.qualification
        val imageview=users.profileImage
        holder.binding.btnViewMore.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name",tName)
            bundle.putString("subject",subjectName.toString())
            bundle.putString("qualify",qualification.toString())
            bundle.putString("image",imageview)
            val intent=Intent(context,DetailsActivity::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return userslist.size
    }


    class UserViewHolder(val binding: UserlistBinding):RecyclerView.ViewHolder(binding.root){}

    override fun onClick(p0: View?) {

    }
}