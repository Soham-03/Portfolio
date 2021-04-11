package com.soham.portfolio

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.Lottie
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var projectInfo: ArrayList<ProjectInfo>
    lateinit var adapter: MyAdapter
    lateinit var viewPager: ViewPager
    lateinit var scrollView:ScrollView
    lateinit var tabLayout:TabLayout
    lateinit var clipBoard: ClipboardManager
    lateinit var profilePic: RelativeLayout
    lateinit var helloIAm: RelativeLayout
    lateinit var name: ImageView
    lateinit var socialMediaBox: RelativeLayout
    lateinit var secondPart: RelativeLayout
    lateinit var thirdPart: RelativeLayout
    lateinit var fourthPart: RelativeLayout
    lateinit var androidDeveloper: ImageView
    lateinit var androidDeveloperGif: RelativeLayout
    lateinit var aboutMeTxt: TextView
    lateinit var languagesTab: RelativeLayout
    lateinit var fifthPart: RelativeLayout
    lateinit var imgCertificate: ImageView
    lateinit var googleContact: ImageView
    lateinit var discordContact: ImageView
    lateinit var contactMe: ImageView
    lateinit var githubLogo: ImageView
    lateinit var linkedInLogo: ImageView
    lateinit var instagramLogo: ImageView
    //android and github both noob
    var flag = false
    var flag1 = false
    var flag2 = false
    var flag3 = false
    var flag4 = false
    var flag5 = false
    var flag6 = false
    var flag7 = false
    var flag8 = false
    var flag9 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flag = true
        flag1 = true
        flag2 = true
        flag3 = true
        flag4 = true
        flag5 = true
        flag6 = true
        flag7 = true
        flag8 = true
        flag9 = true

        scrollView = findViewById(R.id.scroll)
        profilePic = findViewById(R.id.profilePic)
        googleContact = findViewById(R.id.gmailContact)
        discordContact = findViewById(R.id.discordContact)
        contactMe = findViewById(R.id.contactMe)
        helloIAm = findViewById(R.id.helloAndName)
        name = findViewById(R.id.name)
        socialMediaBox = findViewById(R.id.socialMediaBox)
        secondPart = findViewById(R.id.secondPart)
        thirdPart = findViewById(R.id.thirdPart)
        fourthPart = findViewById(R.id.fourthpart)
        androidDeveloper = findViewById(R.id.androidDeveloper)
        androidDeveloperGif = findViewById(R.id.androidDeveloperGif)
        aboutMeTxt = findViewById(R.id.aboutMeDescription)
        languagesTab = findViewById(R.id.languagesTab)
        imgCertificate = findViewById(R.id.imgCertificate)
        fifthPart = findViewById(R.id.fifthPart)
        githubLogo = findViewById(R.id.githubLogo)
        linkedInLogo = findViewById(R.id.linkedInLogo)
        instagramLogo = findViewById(R.id.instaLogo)

        startingAnimations()

        loadProjects()

        contactMe.setOnClickListener {
            scrollView.isSmoothScrollingEnabled = true
            googleContact.parent.requestChildFocus(googleContact,googleContact)
            YoYo.with(Techniques.SlideInRight).duration(500).playOn(discordContact)
            YoYo.with(Techniques.SlideInLeft).duration(500).playOn(googleContact)
        }

        scrollChangeAnimations()

        googleContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("sohamparab1003@gmail.com"))
            intent.setType("text/plain")
            startActivity(Intent.createChooser(intent,"Send Email"))
        }

        discordContact.setOnClickListener {
            clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var clip:ClipData = ClipData.newPlainText("E-Mail","Ryuk#9045")
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this,"Username copied to clipboard!",Toast.LENGTH_LONG).show()
            val uri = Uri.parse("https://discord.com/channels/@me")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

        githubLogo.setOnClickListener {
            val uri = Uri.parse("https://github.com/Soham-03")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

        linkedInLogo.setOnClickListener {
            val uri = Uri.parse("https://www.linkedin.com/in/soham-parab-a51a791b7/")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

        instagramLogo.setOnClickListener {
            val uri = Uri.parse("https://www.instagram.com/cpu.exe/")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

    }

    private fun loadProjects() {

        projectInfo = ArrayList()

        projectInfo.add(ProjectInfo(
                R.drawable.euphony_logo,
                "Euphony - It is a Music App, which plays both Media from your Phone as well as Online through Firewall." +
                "If you want to know more about my project you can download it by clicking on the app icon. :)",
                R.drawable.euphony_1,
                R.drawable.euphony_2,
                R.drawable.euphony_3))

        projectInfo.add(ProjectInfo(R.drawable.fooder_icon,
                "Fooder - It is a Food Delivery Clone App. I made this app to get some knowledge on making API calls" +
                "If you want to know more about this app, Click on the app icon. :)",
                R.drawable.fooder_1,
                R.drawable.fooder_2,
                R.drawable.fooder_3))

        projectInfo.add(ProjectInfo(R.drawable.bookhub_icon,
                "BookHub - I made this app as a project for my android development course. If you want to know more about " +
                "this project click on the app icon. :)",
                R.drawable.bookhub_1,
                R.drawable.bookhub_2,
                R.drawable.bookhub_3))

        adapter = MyAdapter(this,projectInfo)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setSelectedTabIndicatorColor(1)

    }

    private fun startingAnimations(){

        profilePic.apply {
            profilePic.visibility = View.GONE
            helloIAm.visibility = View.INVISIBLE
            secondPart.visibility = View.GONE
            thirdPart.visibility = View.GONE
            fourthPart.visibility = View.GONE
            fifthPart.visibility = View.GONE

            YoYo.with(Techniques.SlideOutRight).duration(1).onEnd {
                YoYo.with(Techniques.SlideInRight).duration(500).onStart {
                    profilePic.visibility = View.VISIBLE
                }.onEnd {
                    YoYo.with(Techniques.BounceInLeft).duration(200).onStart {
                        helloIAm.visibility = View.VISIBLE
                    }.onEnd {
                        YoYo.with(Techniques.SlideInRight).duration(500).onStart {
                            secondPart.visibility = View.VISIBLE
                        }.onEnd {
                            YoYo.with(Techniques.SlideInLeft).duration(500).onStart {
                                thirdPart.visibility = View.VISIBLE
                            }.onEnd {
                                YoYo.with(Techniques.SlideInRight).duration(500).onStart {
                                    fourthPart.visibility = View.VISIBLE
                                }.onEnd {
                                    YoYo.with(Techniques.SlideInLeft).duration(500).onStart {
                                        fifthPart.visibility = View.VISIBLE
                                    }.playOn(fifthPart)
                                }.playOn(fourthPart)
                            }.playOn(thirdPart)
                        }.playOn(secondPart)
                    }.playOn(helloIAm)
                }.playOn(profilePic)
            }.playOn(profilePic)
        }

    }

    private fun scrollChangeAnimations() {

        scrollView.isSmoothScrollingEnabled = true

        scrollView.setOnScrollChangeListener(object :View.OnScrollChangeListener{
            override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {

                var scrollBounds:Rect = Rect()
                scrollView.getHitRect(scrollBounds)


                if(profilePic.getLocalVisibleRect(scrollBounds)){

                    if(flag) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInRight).duration(600).playOn(profilePic)
                        flag = true
                    }
                }
                else{
                    flag = false
                }


                if(socialMediaBox.getLocalVisibleRect(scrollBounds)){

                    if(flag1) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInLeft).duration(600).playOn(socialMediaBox)
                        flag1 = true
                    }
                }
                else{
                    flag1 = false
                }


                if(aboutMeTxt.getLocalVisibleRect(scrollBounds)){

                    if(flag2) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInRight).duration(600).playOn(aboutMeTxt)
                        flag2 = true
                    }
                }
                else{
                    flag2 = false
                }


                if(languagesTab.getLocalVisibleRect(scrollBounds)){

                    if(flag3) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInRight).duration(600).playOn(languagesTab)
                        flag3 = true
                    }
                }
                else{
                    flag3 = false
                }


                if(contactMe.getLocalVisibleRect(scrollBounds)){

                    if(flag4) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInRight).duration(600).playOn(contactMe)
                        flag4 = true
                    }
                }
                else{
                    flag4 = false
                }


                if(androidDeveloper.getLocalVisibleRect(scrollBounds)){

                    if(flag5) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInLeft).duration(600).playOn(androidDeveloper)
                        flag5 = true
                    }
                }
                else{
                    flag5 = false
                }


                if(androidDeveloperGif.getLocalVisibleRect(scrollBounds)){

                    if(flag6) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInRight).duration(600).playOn(androidDeveloperGif)
                        flag6 = true
                    }
                }
                else{
                    flag6 = false
                }


                if(imgCertificate.getLocalVisibleRect(scrollBounds)){

                    if(flag7) {
                    }
                    else{
                        YoYo.with(Techniques.Tada).duration(600).playOn(imgCertificate)
                        flag7 = true
                    }
                }
                else{
                    flag7 = false
                }


                if(googleContact.getLocalVisibleRect(scrollBounds)){

                    if(flag9) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInUp).duration(600).playOn(googleContact)
                        flag9 = true
                    }
                }
                else{
                    flag9 = false
                }


                if(discordContact.getLocalVisibleRect(scrollBounds)){

                    if(flag8) {
                    }
                    else{
                        YoYo.with(Techniques.SlideInDown).duration(600).playOn(discordContact)
                        flag8 = true
                    }
                }
                else{
                    flag8 = false
                }


            }

        })

    }

}