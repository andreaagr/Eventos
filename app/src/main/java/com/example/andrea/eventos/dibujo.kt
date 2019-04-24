package com.example.andrea.eventos

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View

class dibujo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_dibujo)
        val vista = Vista(this)
        setContentView(vista)
    }

    //No es necesaria una vista dibujo porque generamos una a partir de código
    class Vista(context: Context?) : View(context){
        var coor_x : Float?= 50F
        var coor_y : Float? = 50F
        var accion= " "
        var path = Path()

        //Método que utilizaremos para dibujar
        override fun onDraw(canvas: Canvas){
            var paint = Paint()
            //-------------------Estilo predefinido del pincel
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5F
            paint.color = Color.MAGENTA
            //------------------------------------------------
            //Cuando se presione la pantalla, nos movemos a ese lugar
            if(accion == "presionar")
                coor_x?.let { coor_y?.let { it1 -> path.moveTo(it, it1) } }
            //Si movemos el dedo por la pantalla, se dibuja la ruta
            else if(accion == "mover")
                coor_x?.let { coor_y?.let { it1 -> path.lineTo(it, it1) } }
                canvas.drawPath(path, paint)
        }

        //Detectamos la acción que se esta realizando cuando se toque la pantalla
        override fun onTouchEvent(event: MotionEvent?): Boolean {
             coor_x = event?.x
             coor_y  = event?.y
             if(event?.action  == MotionEvent.ACTION_DOWN)
                 accion = "presionar"
             else if(event?.action == MotionEvent.ACTION_MOVE)
                 accion = "mover"
             invalidate()
             return true
        }
    }
}
