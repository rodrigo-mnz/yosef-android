package br.com.concrete.yosef.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator

class MainActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = MainActivity::class.java.getResource(
            "/assets/example_textField_button_text_elementgroup.json").readText()

        val dynamicView = DynamicViewCreator.Builder().build()

        setContentView(dynamicView.createViewFromJson(this, json, this))
    }

    override fun callAction(value: String) {
        findViewById<TextView>("textView".hashCode()).text = value
    }
}
