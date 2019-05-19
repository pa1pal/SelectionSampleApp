package pawan.facility.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pawan.facility.R
import pawan.facility.data.model.Facility

/**
 * Created by Pawan Pal on 19/5/19.
 */
class HomeFragmentAdapter(
    private val list: List<Facility>?,
    context: Context?
) : RecyclerView.Adapter<HomeFragmentAdapter.ItemViewHolder>() {

    private var context: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false), context)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    class ItemViewHolder(itemView: View, context: Context?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var typeName: TextView
        private var radioGroup: RadioGroup
        private var context: Context? = context

        init {
            with(itemView) {
                typeName = findViewById(R.id.type)
                radioGroup = findViewById(R.id.options)
            }
        }

        override fun onClick(v: View?) {
        }

        fun bind(facility: Facility) {
            typeName.text = facility.name

            facility.options.forEach { opt ->
                var radioButton: RadioButton = RadioButton(context)
                radioButton.text = opt.name
                radioGroup.addView(radioButton)
            }
        }

    }
}