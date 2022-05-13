package com.akash.myassignment.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akash.myassignment.R
import com.akash.myassignment.data.Article
import com.akash.myassignment.databinding.NewsItemBinding
import com.akash.myassignment.databinding.NewsLayoutItemBinding
import com.akash.ui.DetailsActivity
import com.bumptech.glide.Glide

class PaginationAdapter:RecyclerView.Adapter<PaginationAdapter.NewsViewHolder>() {


  private  val differcallback=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }


        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      return NewsViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.news_layout_item,parent,false)
      )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=differ.currentList[position]
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.imageView)
        holder.title.text=article.title
        holder.publishedAt.text=article.publishedAt
        holder.sourceAt.text=article.source.name

        holder.itemView.setOnClickListener {
            val title=article.title
            val description=article.description
            val publishedAt=article.publishedAt
            val source=article.source.name
            val bitmap=article.urlToImage
            val intent=Intent(holder.itemView.context,DetailsActivity::class.java)

            val bundle=Bundle()
            intent.putExtra("title",title)
            intent.putExtra("published",publishedAt)
            intent.putExtra("source",source)
            intent.putExtra("description",description)
            intent.putExtra("imageurl",bitmap)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
    class NewsViewHolder(itemview:View):RecyclerView.ViewHolder(itemview),View.OnClickListener{
        val imageView=itemView.findViewById<ImageView>(R.id.ivArticleImage)
        val title=itemView.findViewById<TextView>(R.id.tvTitle)
        val publishedAt=itemView.findViewById<TextView>(R.id.tvPublishedAt)
        val sourceAt=itemView.findViewById<TextView>(R.id.tvSource)
        override fun onClick(p0: View?) {
            val intent=Intent(itemView.context,DetailsActivity::class.java)
            itemView.context.startActivity(intent)
        }

    }




}