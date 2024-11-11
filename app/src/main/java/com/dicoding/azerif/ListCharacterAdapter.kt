package com.dicoding.azerif

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.azerif.databinding.ItemCharacterBinding

class ListCharacterAdapter(private val listCharacter: ArrayList<HonkaiCharacter>) : RecyclerView.Adapter<ListCharacterAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var lastClickTime: Long = 0
    private val clickDelay = 1000L

    interface OnItemClickCallback {
        fun onItemClicked(data: HonkaiCharacter)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, path, type, desc, charImg, charImgBG, rarityImg, pathImg, typeImg) = listCharacter[position]
        holder.binding.tvCharacterName.text = name
        holder.binding.tvCharacterType.text = type
        holder.binding.characterImage.setImageResource(charImg)
        holder.binding.characterImage.setBackgroundResource(charImgBG)
        holder.binding.rarityImage.setImageResource(rarityImg)
        holder.binding.typeImage.setImageResource(typeImg)

        holder.itemView.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > clickDelay) { // Check if enough time has passed
                lastClickTime = currentTime // Update last click time
                val context = holder.itemView.context
                val passItem = Intent(context, HonkaiDetail::class.java).apply {
                    putExtra("EXTRA_NAME", name)
                    putExtra("EXTRA_PATH", path)
                    putExtra("EXTRA_TYPE", type)
                    putExtra("EXTRA_DESC", desc)
                    putExtra("EXTRA_IMG", charImg)
                    putExtra("EXTRA_IMG_BG", charImgBG)
                    putExtra("EXTRA_RARITY_IMG", rarityImg)
                    putExtra("EXTRA_PATH_IMG", pathImg)
                    putExtra("EXTRA_TYPE_IMG", typeImg)
                }
                context.startActivity(passItem)
            }
        }
    }

    override fun getItemCount(): Int = listCharacter.size
}
