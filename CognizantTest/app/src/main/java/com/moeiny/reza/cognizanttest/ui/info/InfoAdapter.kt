package com.moeiny.reza.cognizanttest.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.cognizanttest.data.model.uimodel.ShowInfoModel
import com.moeiny.reza.cognizanttest.databinding.InfoBinding

class InfoAdapter(private val onCardClicked: (title: String) -> Unit) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    var infoList: List<ShowInfoModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val infoBinding = InfoBinding.inflate(layoutInflater, parent, false)

        return InfoViewHolder(infoBinding)
    }

    override fun getItemCount(): Int {
        return infoList.count()
    }

    override fun onBindViewHolder(holderInfo: InfoViewHolder, position: Int) {
        var info = infoList.get(position)
        holderInfo.infoBinding.cardInfoParent.setOnClickListener {
            onCardClicked(info.title)
        }

        holderInfo.bind(info)
    }

    inner class InfoViewHolder(val infoBinding: InfoBinding) :
        RecyclerView.ViewHolder(infoBinding.root) {
        fun bind(modelViewShow: ShowInfoModel) {
            this.infoBinding.showInfo = modelViewShow
        }
    }

}