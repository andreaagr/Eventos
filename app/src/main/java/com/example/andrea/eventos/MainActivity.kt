package com.example.andrea.eventos

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //----------------------------------------------------------Cuando se presiona el botón CLICK DESDE KOTLIN

        boton2.setOnClickListener(){
            //Envia un mensaje
            Toast.makeText(this, "Has presionado el botón desde Kotlin (:", Toast.LENGTH_SHORT).show()
        }

        //--------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------Cuando se presiona el boton de CLICK LARGO
        longclick.setOnLongClickListener(){
            //Envia un mensaje
            Toast.makeText(this, "Click laargo...", Toast.LENGTH_SHORT).show()
            true
        }
        //--------------------------------------------------------------------------------------------------------

        //-----------------------------------------------------------Cuando se presiona el botón de FOCUS & ON_KEY
        focus.setOnClickListener(){
            //Vamos a la actividad evento
            val i = Intent(this, evento::class.java)
            startActivity(i)
        }
        //-------------------------------------------------------------------------------------------------------

        //-------------------------------------------------------Cuando se presiona el botón DIBUJAR CON ON_TOUCH
        dibujar.setOnClickListener(){
            //Vamos a la actividad dibujo
            val i = Intent(this, dibujo::class.java)
            startActivity(i)
        }
        //--------------------------------------------------------------------------------------------------------

        //-------Añadimos el escuchador de tipo swipe, para detectar eventos de este tipo cuando se toque el botón

        swip.setOnTouchListener(onSwipeListener(this))

        //--------------------------------------------------------------------------------------------------------
        //----------------------------------------------------------Cuando se presiona el botón DRAG CON ON_TOUCH
        drag.setOnClickListener {
            //Vamos a la actividad carrito_c
            val i = Intent(this, carrito_c::class.java)
            startActivity(i)
        }
        //--------------------------------------------------------------------------------------------------------
    }


    //-----------------------------------------------Función para detectar el click en el botón 1
    fun click(v : View){
        Toast.makeText(this, "Has presionado el botón (:", Toast.LENGTH_SHORT).show()
    }

    //-------------------------------------------------------------------------------------------

    //---------------------------------------------------------------Creando nuestro escuchador para eventos de tipo swipe

    class onSwipeListener(ctx : Context) : View.OnTouchListener{
        var gestureDetector = GestureDetector(ctx, GestureListener(ctx))
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            return  gestureDetector.onTouchEvent(p1)
        }

        class GestureListener(contexto : Context) : GestureDetector.SimpleOnGestureListener() {
            var ctx = contexto
            companion object {
                //---------------------Valores mínimos para los que se detectará el movimiento
                private val SWIPE_THRESHOLD = 100
                private val SWIPE_VELOCITY_THRESHOLD = 100
                //----------------------------------------------------------------------------
            }


            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                var result = true
                try {
                    val diffY = e2?.y!! - e1?.y!!
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {

                                onSwipeRight(ctx)

                            } else {
                                onSwipeLeft(ctx)
                            }
                        }
                        result = true
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom(ctx)
                        } else {
                            onSwipeTop(ctx)
                        }
                    }
                    result = true
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }

            //--------------------------Acciones para cada uno de los swipe dependiendo la direccion
            fun onSwipeLeft(con : Context){
                    Toast.makeText(con, "Swipe a la izquierda", Toast.LENGTH_SHORT).show()
            }


            fun onSwipeRight(con : Context){
                    Toast.makeText(con, "Swipe a la derecha", Toast.LENGTH_SHORT).show()
            }

            fun onSwipeBottom(con: Context){
                    Toast.makeText(con, "Swipe hacia abajo", Toast.LENGTH_SHORT).show()
            }

            fun onSwipeTop(con: Context){
                    Toast.makeText(con, "Swipe hacia arriba", Toast.LENGTH_SHORT).show()
            }
            //--------------------------------------------------------------------------------------
        }
    }

    //--------------------------------------------------------------------------------------------------------------------





}
