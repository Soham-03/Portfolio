package com.soham.portfolio

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class MyAdapter(private val context: Context, private val projectInfo:ArrayList<ProjectInfo>):
    PagerAdapter() {

    override fun getCount(): Int {
        return projectInfo.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.single_project,container,false)

        val project = projectInfo[position]
        val icon = project.projectIcon
        val description = project.txtProjectDescription
        val firstSs = project.imgFirstSsOfProject
        val secondSs = project.imgSecondSsOfProject
        val thirdSs = project.imgThirdSsOfProject

        var imgIcon = view.findViewById<ImageView>(R.id.imgProjectIcon)
        var txtDescription = view.findViewById<TextView>(R.id.txtProjectDescription)
        var firstSS = view.findViewById<ImageView>(R.id.imgFirstSsOfProject)
        var secondSS = view.findViewById<ImageView>(R.id.imgSecondSsOfProject)
        var thirdSS = view.findViewById<ImageView>(R.id.imgThirdSsOfProject)

        imgIcon.setImageResource(icon)
        txtDescription.text = description.toString()
        firstSS.setImageResource(firstSs)
        secondSS.setImageResource(secondSs)
        thirdSS.setImageResource(thirdSs)

        container.addView(view,position)

        imgIcon.setOnClickListener {
            when(position){
                0 ->{
                    val uri = Uri.parse("https://github.com/reaper1210/MusicApp/releases/download/v1.0/Euphony.apk")
                    val intent = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(context,intent,null)
                }
                1 ->{
                    val uri = Uri.parse("https://github.com/Soham-03/Food-Delivery-App")
                    val intent = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(context,intent,null)
                }
                else ->{
                    val uri = Uri.parse("https://github.com/Soham-03/BookHub")
                    val intent = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(context,intent,null)
                }
            }
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val pos = position + 1
        val title : String
        title = when(pos){
            1->"Euphony"
            2->"Fooder"
            else->

            {
                "BookHub"
            }
        }
        return title
    }

}