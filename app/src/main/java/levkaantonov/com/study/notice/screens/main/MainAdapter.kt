package levkaantonov.com.study.notice.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.models.AppNotice

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private var mListNotice = emptyList<AppNotice>()


    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNotice: TextView = view.findViewById(R.id.item_notice_name)
        val textNotice: TextView = view.findViewById(R.id.item_notice_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notice_item, parent, false)
        return MainHolder(v)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textNotice.text = mListNotice[position].text
        holder.nameNotice.text = mListNotice[position].name
    }

    override fun getItemCount(): Int = mListNotice.size

    fun setList(list: List<AppNotice>) {
        mListNotice = list
        notifyDataSetChanged()
    }
}