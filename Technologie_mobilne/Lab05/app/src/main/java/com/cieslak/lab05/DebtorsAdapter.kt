package com.cieslak.lab05

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class DebtorsAdapter(context: Context, debtors: List<Debtor>): BaseAdapter() {

    private var context = context
    var debtors = debtors

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)

        val row: View = inflater.inflate(R.layout.debtors_list_item, null)

        row.tag = getItem(p0).id

        val debtorName = row.findViewById<TextView>(R.id.nameTextView)
        debtorName.text = getItem(p0).name

        val debtorPhone = row.findViewById<TextView>(R.id.phoneTextView)
        debtorPhone.text = getItem(p0).phone

        val debtorPhoto = row.findViewById<ImageView>(R.id.debtorImageView)
        setPhoto(p0, debtorPhoto)

        return row

    }
    fun setPhoto(p0 :Int, debtorPhoto: ImageView) {
        var photo: Bitmap? = null
        try {
            if (getItem(p0).photoUri != null) {
                val imageUri: Uri = Uri.parse(getItem(p0).photoUri)
                photo = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            }
        } catch (e: Exception) {
            photo = null
        }
        if(photo != null) {
            debtorPhoto.setImageBitmap(photo)
        } else {
            debtorPhoto.setImageResource(R.drawable.doge)
        }
    }
    override fun getItem(p0: Int): Debtor {
        return debtors.get(p0)
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getCount(): Int {
        return debtors.count()
    }

}