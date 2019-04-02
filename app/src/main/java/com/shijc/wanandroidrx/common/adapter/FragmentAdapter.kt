package com.shijc.wanandroidrx.common.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @Package com.shijc.wanandroidkotlin.common.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/13 下午 2:22
 * @version V1.0
 */
class FragmentAdapter(fm:FragmentManager,private val fragments:List<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}