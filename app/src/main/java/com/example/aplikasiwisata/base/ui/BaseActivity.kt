package com.example.aplikasiwisata.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lintang.jetpackprolintang.base.utils.BaseHelper

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this

    }


    protected inline fun <reified Model> baseGetExtraData(extraKey: String): Model {
        val extraIntent = intent.getStringExtra(extraKey)
        val extraData = BaseHelper().baseFromJson<Model>(extraIntent)
        return extraData
    }


    protected fun checkExtra(extraKey: String): Boolean {
        return intent?.hasExtra(extraKey)!!
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected inline fun <reified ClassActivity> baseStartActivity(
        context: Context
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        startActivity(intent)

    }

    protected inline fun <reified ClassActivity, reified Model> baseStartActivity(
        context: Context,
        model: Model,
        key: String
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        intent.putExtra(key, BaseHelper().baseToJson(model))
        startActivity(intent)

    }

    protected inline fun <reified Model> getExtraData(key: String): Model {
        val extraIntent = mActivity.intent.getStringExtra(key)
        val extraData = BaseHelper().baseFromJson<Model>(extraIntent)
        return extraData
    }


    /*  protected inline fun <reified T : ViewModel> obtainViewModel(): T {
          val factory: ViewModelFactory = ViewModelFactory.getInstance(mActivity.application)
          return ViewModelProviders.of(this, factory).get(T::class.java)
      }*/
}