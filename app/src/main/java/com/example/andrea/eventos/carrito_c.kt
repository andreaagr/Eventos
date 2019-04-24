package com.example.andrea.eventos

import android.annotation.TargetApi
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Display
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_drag.*

class carrito_c : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag)
        //Al producto que será arrastrado se le asigna el escuchador del evento onTouch() que generamos
        producto.setOnTouchListener(MiTouchListener())
        //Al lugar en el que será soltado se le asigna el escuchador del eveneto onDrag() que generamos
        cesta.setOnDragListener(MiDragListener(this, numProductos, cesta))
    }

    //-----------------Añadimos la funcionalidad personalizada a nuestro propio escuchador onTouch()
    final class MiTouchListener : View.OnTouchListener{
        @TargetApi(Build.VERSION_CODES.N)
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            //Si tocamos el elemento...
            if(p1?.action == MotionEvent.ACTION_DOWN ){
                var data = ClipData.newPlainText("","")
                //Generamos una sombra para la vista que esta siendo arrastrada
                var shadow_builder = View.DragShadowBuilder(p0)
                p0?.startDragAndDrop(data,shadow_builder,p0, 0)
                return true
            }
            else
                return false
        }

    }
    //----------------------------------------------------------------------------------------------

    //------------------Añadimos la funcionalidad personalizada a nuestro propio escuchador onDrag()
    class MiDragListener(context: Context, display: TextView, imagen : ImageView) : View.OnDragListener{
        var ctx = context
        var display = display
        var imagen= imagen
        //Variable que guardará el número de elementos agregados
        var hamburguesas = 0

        override fun onDrag(p0: View?, p1: DragEvent?): Boolean {
            var accion = p1?.action
            //Cuando algo sea soltado...
            if(accion == DragEvent.ACTION_DROP )
                agregar(display,imagen)
            return true
        }

        fun agregar(mostrar:TextView, imagen: ImageView){
            //Se genera una animación sobre la canasta
            var animacion = AnimationUtils.loadAnimation(ctx, R.anim.animacion)
            imagen.startAnimation(animacion)
            //Se envia un mensaje al usuario
            Toast.makeText(ctx, "Se ha agregado a la cesta", Toast.LENGTH_SHORT).show()
            hamburguesas++
            //Se actualiza el número de productos
            mostrar.text = hamburguesas.toString() + " hamburguesa(s) en la cesta"

        }
    }
    //----------------------------------------------------------------------------------------------

}
