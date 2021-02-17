package com.example.anonymous_board.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.anonymous_board.R
import com.example.anonymous_board.datacs.list


class listAdapter(val context: Context?, val listData:ArrayList<list>): RecyclerView.Adapter<listAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.listadapter,parent,false)
        // 내가 쓸 custom_rev지정!
        return CustomViewHolder(
            view
        ).apply {
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition
                var data: list =listData.get(curPos)

                //val intent = Intent(view.getContext(), detail_coupon::class.java)
                Toast.makeText(context,"늉",Toast.LENGTH_SHORT).show()
                val pref=context!!.getSharedPreferences("listData",0)

                val edit=pref.edit()

                edit.clear()
                edit.putString("writer",data.writer)

                edit.putString("title",data.title)

                edit.putString("content",data.content)

                edit.apply()



                //context.startActivity(intent)

                //  Toast.makeText(parent.context,"이름 :${profile.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // holder.memo.setImageResource(1)



        holder.writer.text=listData.get(position).writer
        holder.title.text= listData.get(position).title
        holder.content.text=listData.get(position).content
//        if (context != null) {
//            Glide.with(context).load(listData.get(position).url).into(holder.img)
//        }



    }
    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layout=itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.listadapter)
        val writer=itemView.findViewById<TextView>(R.id.writer)
        val title=itemView.findViewById<TextView>(R.id.title)
        val content=itemView.findViewById<TextView>(R.id.content)
        //val img=itemView.findViewById<ImageView>(R.id.imageView)



    }





}