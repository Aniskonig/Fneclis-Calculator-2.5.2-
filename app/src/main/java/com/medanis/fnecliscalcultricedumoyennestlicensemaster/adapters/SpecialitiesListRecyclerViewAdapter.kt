package com.medanis.fnecliscalcultricedumoyennestlicensemaster.adapters


import android.content.Context
import android.content.Intent
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.pages.CalculatingPage
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.R
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.Spetialities


class SpecialitiesListRecyclerViewAdapter(val mContext: Context, private val mData: MutableList<Spetialities>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mFilteredList: MutableList<Spetialities> = mData


//    override fun getItemViewType(position: Int): Int {
//        return mData[position].getviewType()!!
//    }
    override fun getItemCount(): Int {
        return mData.size
    }

//    class ViewHolderAdMob(view: View) : RecyclerView.ViewHolder(view) {
//        var mAdView = view.findViewById(R.id.dialogAdView) as AdView
//        var RelativaL = view.findViewById(R.id.Ridl) as RelativeLayout
//        init {
//            val adRequest = AdRequest.Builder()
////                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .build()
//            mAdView.loadAd(adRequest)
//        }
//        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val  view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.speciality_card_view, parent, false)

//        val inflater = LayoutInflater.from(parent.context)
//        when (viewType) {
//            1 -> {
////                val v = inflater.inflate(R.layout.speciality_card_view, parent, false)
//
////                viewHolder = MyMainViewHolder(v)
//            }
//            2 -> {
////                val v = inflater.inflate(R.layout.list_item_admob, parent, false)
////                ViewHolderAdMob(v).mAdView.adListener = object: AdListener() {
////                    override fun onAdLoaded() {
////                        ViewHolderAdMob(v).RelativaL.visibility = View.VISIBLE
////
////                        }
////                }
////                viewHolder = ViewHolderAdMob(v)
//            }
//            3 -> {
////            val v = inflater.inflate(R.layout.list_item_admob_large_banner, parent, false)
////                ViewHolderAdMob(v).mAdView.adListener = object: AdListener() {
////                    override fun onAdLoaded() {
////                        ViewHolderAdMob(v).RelativaL.visibility = View.VISIBLE
////                    }
////                }
////                viewHolder = ViewHolderAdMob(v)
//            }
//        }
//        return viewHolder!!
        return MyMainViewHolder(view)
    }

    fun setfilter(listitem: MutableList<Spetialities>): MutableList<Spetialities>? {
        mFilteredList.clear()
        mFilteredList.addAll(listitem)
        notifyDataSetChanged()
        return mFilteredList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.setIsRecyclable(false)
//        when (holder.getItemViewType()) {
//            1 -> {
                val viewHolder = holder as MyMainViewHolder

                viewHolder.fullName.text = mData[position].getspecialityFullName()
                viewHolder.abriName.text = mData[position].getabriNAME()
//                viewHolder.imageView.setImageResource(mData[position].thumbnail)
                viewHolder.cardView.setOnClickListener {
                        val intent1 = Intent(mContext, CalculatingPage::class.java)
                        intent1.putExtra("SpecialityNAME",mData[position].getspecialityFullName())
                        intent1.putExtra("level Year semster", mData[position].getlevelYearSemester())


//                        val builder = AlertDialog.Builder(mContext)
//                        val inflater = LayoutInflater.from(mContext)
//                        val mydialog = inflater.inflate(R.layout.activity_ads, null)
//                        val dialog: AlertDialog = builder.create()
//                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//                        builder.setView(mydialog)
//                        dialog.show()
                        mContext.startActivity(intent1)

                }


//            }
//            2 -> {
//            }
//            3 -> {
//            }
//        }
    }

    class MyMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var fullName: TextView
        internal var abriName: TextView
//        internal var imageView: ImageView
        internal var cardView: CardView
        init {
            fullName = itemView.findViewById(R.id.fullName) as TextView
            abriName = itemView.findViewById(R.id.abriName) as TextView
//            imageView = itemView.findViewById(R.id.imageView) as ImageView
            cardView = itemView.findViewById(R.id.cardview_id) as CardView
        }
    }


}

