package com.example.andrea.eventos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_evento.*

class evento : AppCompatActivity() , View.OnKeyListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento)

        edittext.onFocusChangeListener= View.OnFocusChangeListener(){
            view, b ->
            var texto= edittext.text.toString()
            if(texto.isNotEmpty() and !b and !texto.equals("38")){
                Toast.makeText(this, "Sal de ahí Dewey, esa no es tu familia", Toast.LENGTH_SHORT).show()
            }
        }
        edittext.setOnKeyListener(this)
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        if(p1 == KeyEvent.KEYCODE_ENTER){
            Toast.makeText(this, "Presionaste enter", Toast.LENGTH_SHORT).show()
            edittext2.requestFocus()
        }
        return true
    }

}
