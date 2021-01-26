package wintercontingency.hq.tap_u5_e4_vectoresdinamicos_timers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.renderscript.ScriptGroup
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var vector = ArrayList<String>()
    var timer = object  : CountDownTimer(2000, 200){
        override fun onTick(millisUntilFinished: Long) {
            mostrarData()
        }

        override fun onFinish() {
            start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer.start()

        btnCapturar.setOnClickListener {
            var cadena = "Nombre:  ${txtNombre.text.toString()}\nEdad: ${txtEdad.text.toString()}" + "\nTelefono: ${txtTel.text.toString()}"

            vector.add(cadena)
            Toast.makeText(this, "Se capturo con exito", Toast.LENGTH_SHORT).show()
            txtNombre.setText(""); txtEdad.setText(""); txtTel.setText("")
        }

        btnEliminar.setOnClickListener {
            var campoPosicion = EditText(this)

            campoPosicion.inputType = InputType.TYPE_CLASS_NUMBER

            AlertDialog.Builder(this)
                .setTitle("Escriba la posición a eliminar")
                .setView(campoPosicion)
                .setPositiveButton("Borrar") { d, i ->
                    eliminar(campoPosicion.text.toString().toInt())
                }
                .setNegativeButton("Cancelar") { d, i ->

                }
                .show()
        }
    }

    fun eliminar(posicion:Int){
        try {
            vector.removeAt(posicion)
            Toast.makeText(this, "Se borro con exito", Toast.LENGTH_SHORT).show()
            txtNombre.setText(""); txtEdad.setText(""); txtTel.setText("")
        }catch (e:Exception){
            AlertDialog.Builder(this)
                .setTitle("Escriba la posición a eliminar")
                .setMessage(e.message)
                .show()
        }
    }

    fun mostrarData(){
        var cadena = "MOSTRAR: \n"

        if(vector.size == 0){
            txtLista.setText("MOSTRAR: \nNo existe datos aun")
        }

        var ultimoIndice = vector.size-1

        (0..ultimoIndice).forEach{
            cadena += "Posición: ${it}\n${vector.get(it)}\n\n"
        }

        txtLista.setText(cadena)
    }
}