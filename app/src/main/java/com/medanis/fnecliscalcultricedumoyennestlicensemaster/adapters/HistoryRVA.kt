package com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages.CalculatingPage
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.Spetialities
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.getHistoryArray
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.getModulesFromSharedPreferences
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.saveHistoryArray
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.saveModulesInSharedPreferences


class HistoryRVA(val mContext: Context, val mData: MutableList<Spetialities>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mFilteredList: MutableList<Spetialities> = mData

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.speciality_card_view, parent, false)

        return MyMainViewHolder(view)
    }

    fun setfilter(listitem: MutableList<Spetialities>): MutableList<Spetialities>? {
        mFilteredList.clear()
        mFilteredList.addAll(listitem)
        notifyDataSetChanged()
        return mFilteredList
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val viewHolder = holder as MyMainViewHolder


        viewHolder.fullName.text = mData[position].getspecialityFullName() // date
        viewHolder.abriName.text =
            CalculatingPage().getLevelYearSemesterInArabic(
                mData[position].getlevelYearSemester()
            ) + " " +CalculatingPage().getMajorsNameInArabic(mData[position].getabriNAME()) // Speciality Name

        viewHolder.cardView.setOnClickListener {
            val intent1 = Intent(mContext, CalculatingPage::class.java)
            intent1.putExtra(
                "SpecialityNAME",
                mData[position].getabriNAME() + "&&" + mData[position].getspecialityFullName()
            )
            intent1.putExtra("level Year semster", mData[position].getlevelYearSemester())
            intent1.putExtra("isHistory", true)

            mContext.startActivity(intent1)

        }
        viewHolder.cardView.setOnLongClickListener {
            // Get the current list of items from SharedPreferences
            var historyArray = getHistoryArray(mContext, "history_array")?.toMutableList() ?: mutableListOf()

// Check if the historyArray is not null before attempting to remove an item
            if (historyArray != null) {
                // Remove the desired items from the list (e.g., remove the item at 'position')
                if (position >= 0 && position < historyArray.size) {
                    historyArray.removeAt(position) // Change this according to your logic
                }
            }
            historyArray?.toTypedArray()
            // Save the updated list back to SharedPreferences
            saveHistoryArray(mContext, "history_array", historyArray.toTypedArray())
            Log.e("currentList", historyArray.toTypedArray().toString())
            Log.e("position", position.toString())
            // Notify the RecyclerView adapter that the dataset has changed
            notifyDataSetChanged()

            notifyItemRemoved(position)
            Toast.makeText(mContext,
                mContext.getString(R.string.les_r_sultats_ont_t_supprim_s), Toast.LENGTH_SHORT).show()
            true // Return true to indicate that long click is consumed
        }
    }

    class MyMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var fullName: TextView
        internal var abriName: TextView
        internal var cardView: CardView

        init {
            fullName = itemView.findViewById(R.id.fullName) as TextView
            abriName = itemView.findViewById(R.id.abriName) as TextView
            cardView = itemView.findViewById(R.id.cardview_id) as CardView
        }
    }


}

