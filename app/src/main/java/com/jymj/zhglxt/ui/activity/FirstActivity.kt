package com.jymj.zhglxt.ui.activity

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.adapter.BottomAdapter
import com.jymj.zhglxt.ui.fragment.me.MeFragment
import com.jymj.zhglxt.ui.fragment.homepage.HomePageFragment
import com.jymj.zhglxt.ui.fragment.shopping.ShoppingFragment
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.commonutils.AppUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_first.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class FirstActivity : BaseActivity<BasePresenter<*,*>,BaseModel>() {

    private var isBackPressed: Boolean = false//是否退出软件

    override fun getLayoutId(): Int {
        return R.layout.activity_first
    }

    override fun initPresenter() {
    }

    override fun initViews() {
    }

    override fun initDatas() {
        init()
    }

    private fun init() {
//        CheckUpdateUtil.checkUpdate(this)//app更新
//        vp_first.setPadding(0, GetMyHeight.getStatusBarHeight(), 0, 0)//

        EventBus.getDefault().register(this)

        val appName = AppUtils.getAppName()
        vp_first.offscreenPageLimit = 3
        vp_first.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bnvw_first.menu.getItem(position).isChecked = true
            }
        })
        setupViewPager(vp_first)
        bnvw_first.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.item_tab1 -> {
                        vp_first.currentItem = 0
                    }
                    R.id.item_tab2 -> {
                        vp_first.currentItem = 1
//                        projectFragment!!.setOnClickXz()
                    }
                    R.id.item_tabthree->{
                        vp_first.currentItem = 2
                    }
                   /* R.id.item_tab3 -> {
                        vp_first.currentItem = 3
                    }*/
                }
                return false
            }
        })

        val jump = intent.getIntExtra("jump",0)
        vp_first.currentItem = jump
        /*if (jump == 1){
            vp_first.currentItem = 1
        }*/
        //底部导航小圆点
//        setBadge(2,5)

    }
    fun setupViewPager(viewpager: ViewPager) {
        val adapter = BottomAdapter(supportFragmentManager)
        val meFragment = MeFragment()
        adapter.addFragment(HomePageFragment())
        adapter.addFragment(ShoppingFragment())
        adapter.addFragment(meFragment)
        viewpager.adapter = adapter

    }

    // 接收地址
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun getRedPoint(redPoint: String) {
        setBadge(1,redPoint.toInt())
    }

    /**
     * 给BottomNavigationView 设置Badge 小红点
     *
     * BottomNavigationMenuView中的每一个Tab是一个FrameLayout，所以可以在上面随意添加View、这样就可以实现角标了
     */
    private fun setBadge(index: Int, count: Int) {
        //获取底部菜单view
        val menuView = bnvw_first.getChildAt(0) as BottomNavigationMenuView
        //获取第2个itemView
        val itemView = menuView.getChildAt(index) as BottomNavigationItemView
        //引入badgeView
        val badgeView = LayoutInflater.from(this).inflate(R.layout.layout_badge_view, menuView, false)
        //把badgeView添加到itemView中
        itemView.addView(badgeView)
        //获取子view并设置显示数目
        val countView = badgeView.findViewById<TextView>(R.id.tv_badge)
        countView.text = count.toString()

        countView.visibility = if (count > 0) View.VISIBLE else View.GONE
    }


    /**
     * 监听手机自带的返回按键  严以律己 宽以待人 厚德载物
     *//**//**//**/
    override fun onBackPressed() {
        if (isBackPressed) {
            isBackPressed = false
            super.onBackPressed()
        } else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()//Toast.LENGTH_SHORT
            isBackPressed = true
            object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        Thread.sleep(3000)
                        isBackPressed = false
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }

    override fun onStart() {
        super.onStart()
        clearActivity(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        LoadingDialog.cancelDialogForLoading()
        EventBus.getDefault().unregister(this)
    }
}
