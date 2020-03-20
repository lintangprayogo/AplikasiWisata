package com.example.aplikasiwisata.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lintang.jetpackprolintang.base.utils.BaseHelper

abstract class BaseFragment : Fragment() {
    lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as BaseActivity)
    }


    protected inline fun <reified ClassActivity, Model> baseStartActivity(
        context: Context,
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        val extraData = BaseHelper().baseToJson(data)
        intent.putExtra(extraKey, extraData)
        context.startActivity(intent)
    }


    protected inline fun <reified Model> getExtraData(key: String): Model {
        val extraIntent = mActivity.intent.getStringExtra(key)
        val extraData = BaseHelper().baseFromJson<Model>(extraIntent)
        return extraData
    }

    protected inline fun <reified ClassActivity> baseStartActivity(
        context: Context

    ) {
        val intent = Intent(context, ClassActivity::class.java)
        context.startActivity(intent)
    }


}