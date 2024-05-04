package com.example.final_project_term_six.AdapterClass


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_term_six.R
import com.example.final_project_term_six.SubFragment
import com.example.final_project_term_six.modelClass.HomeModel

class HomeAdapter(private val context: Context, private val list: ArrayList<HomeModel>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.title.text = model.title
        holder.des.text = model.des

        holder.itemView.setOnClickListener { v ->
            val activity = v.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.wrapper, SubFragment(model.title))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var des: TextView = itemView.findViewById(R.id.desc)
    }
}
