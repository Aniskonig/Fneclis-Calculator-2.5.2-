package com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters

import android.R.attr.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.MODULE
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.InputFilterMinMax
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.getHistoryArray
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.others.saveHistoryArray
import java.lang.reflect.Type


class MainRecylerViewAdapter(private val mContext: Context, var mData: MutableList<MODULE>) : RecyclerView.Adapter<MainRecylerViewAdapter.MyModulesAdapter>() {

    val  mFilteredList : MutableList<MODULE>? = null

    override fun getItemViewType(position: Int): Int {
        return mData[position].viewType
    }
    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyModulesAdapter {
        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.main_card_view, parent, false)
//        var viewHolder: RecyclerView.ViewHolder? = null
//        val inflater = LayoutInflater.from(parent.context)
//        when (viewType) {
//            1 -> {
//                val v = inflater.inflate(R.layout.main_card_view, parent, false)
//                viewHolder = MyModulesAdapter(v)
//            }
//            2 -> {
//                val v = inflater.inflate(R.layout.list_item_admob, parent, false)
//                viewHolder = ViewHolderAdMob(v)
//            }
//        }
//        return viewHolder!!
        return MyModulesAdapter(view)
    }

//    class ViewHolderAdMob(view: View) : RecyclerView.ViewHolder(view) {
//        var mAdView: AdView = view.findViewById(R.id.adView) as AdView
//
//        init {
//            val adRequest = AdRequest.Builder()
////                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build()
//            mAdView.loadAd(adRequest)
//        }
//    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyModulesAdapter, position: Int) {
        holder.setIsRecyclable(false)

//        when (holder.itemViewType) {
//            1 -> {
//                val viewHolder = holder as MyModulesAdapter
    holder.moduleNAME.text = mData[position].getmoduleName()
                val RATE = mData[position].getRATE()
                editFilters(holder.editCOURS)
                editFilters(holder.editTD)
                editFilters(holder.editTP)

                fun COURSinvisible(holder: MyModulesAdapter){
                    mData[position].setexistCOURS(false)
                    holder.editCOURS.visibility =View.GONE
                    holder.txtCOURS.visibility =View.GONE
                }
                fun TDinvisible(holder: MyModulesAdapter){
                    mData[position].setexistTD(false)
                    holder.editTD.visibility =View.GONE
                    holder.txtTD.visibility =View.GONE
                }
                fun TPinvisible(holder: MyModulesAdapter){
                    mData[position].setexistTP(false)
                    holder.editTP.visibility =View.GONE
                    holder.txtTP.visibility =View.GONE
                }


                if (RATE == 3.0 || (RATE == 2.0 ) || RATE == 1.3  || RATE == 3.4 || RATE == 5.0 || RATE==4.1 || RATE == 6.1){
                    TPinvisible(holder)
                }else if (RATE == 2.1 || RATE == 1.2 || RATE == 3.1 || RATE==4.2){
                    TDinvisible(holder)
                }else if (RATE == 2.2  || (RATE == 1.0)){
                    TDinvisible(holder)
                    TPinvisible(holder)
                }else if (RATE == 1.1 || RATE == 2.3 || RATE == 3.2 || RATE==5.1){
                    COURSinvisible(holder)
                    TDinvisible(holder)
                }
                val cour = mData[position].getischangedCourseMARK().toString()
                val td = mData[position].getischangedTdMARK().toString()
                val tp = mData[position].getischangedTpMARK().toString()
                holder.editCOURS.hint = "..."
                holder.editTD.hint = "..."
                holder.editTP.hint = "..."
                if (cour != "15.0" || tp!="25.0"){
                    holder.editCOURS.setText(cour)
                    holder.editTD.setText(td)
                    holder.editTP.setText(tp)
                }
//            }
//            2 -> {
//            }
//        }


//        val count = mData[position].getcount()
//        var color = R.color.transWhite

//        if (count==1){
//                color = R.color.blv1
//        }else if (count==2){
//                color = R.color.blv2
//        }else if (count==3){
//                color = R.color.blv3
//        }else if (count==4){
//                color = R.color.blv4
//        }else if (count==5){
//                color = R.color.blv5
//        }


//        holder.Ridl.setBackgroundColor(
//            ContextCompat.getColor(mContext,color)
//        )

    // Change CardView Bottom Margin
    if (position == mData.size-1 ) {
        val r: Resources = mContext.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10F,
            r.getDisplayMetrics()
        ).toInt()

        val px2 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            120F,
            r.getDisplayMetrics()
        ).toInt()

        val params = holder.cardView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(px, 0, px, px2)
        holder.cardView.layoutParams = params
    }

    }
    inner class MyModulesAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal  var txtCOURS = itemView.findViewById<TextView>(R.id.txtCOURS)
        internal  var txtTD = itemView.findViewById<TextView>(R.id.txtTD)
        internal  var txtTP = itemView.findViewById<TextView>(R.id.txtTP)
        internal  var cardView = itemView.findViewById<CardView>(R.id.cardview_id)


        internal var moduleNAME: TextView = itemView.findViewById(R.id.modNAME) as TextView

        internal var editCOURS: EditText
        internal var editTP: EditText
        internal var editTD: EditText

        init {
            editCOURS = itemView.findViewById(R.id.editCOURS)

            editCOURS.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                }
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.isNotEmpty()){
                        mData[adapterPosition].setCourseMARK(charSequence.toString().toDouble())
                    }else {
                            mData[adapterPosition].setCourseMARK(25.0)
                    }
                }
                override fun afterTextChanged(editable: Editable) {
                    if (editable.isEmpty()){
                        mData[adapterPosition].setCourseMARK(25.0)
                    }
                }
            })
            editTD = itemView.findViewById(R.id.editTD)

            editTD.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.isNotEmpty()){
                        mData[adapterPosition].setTdMARK(charSequence.toString().toDouble())

                    }else {
                        mData[adapterPosition].setTdMARK(25.0)
                    }
                }
                override fun afterTextChanged(editable: Editable) {
                    if (editable.isEmpty()){
                        mData[adapterPosition].setTdMARK(25.0)
                    }
                }
            })
            editTP = itemView.findViewById(R.id.editTP)

            editTP.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.isNotEmpty()){
                    mData[adapterPosition].setTpMARK(charSequence.toString().toDouble())
                    }else {
                        mData[adapterPosition].setTpMARK(25.0)
                    }
                }

                override fun afterTextChanged(editable: Editable) {
                    if (editable.isEmpty()){
                        mData[adapterPosition].setTpMARK(25.0)
                    }
                }
            })
    }
    }
    fun editFilters(editText: EditText){
        editText.filters = arrayOf<InputFilter>(InputFilterMinMax(0.toFloat(), 20.toFloat()))
    }

}

