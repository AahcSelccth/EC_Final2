/*package com.example.ec_final2.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ec_final2.databinding.ActivityAddCafeBinding
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddCafeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAddCafeBinding
    private lateinit var openCameraLouncher: ActivityResultLauncher<Intent>
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCafeBinding.inflate(layoutInflater)
        firestore = Firebase.firestore
        setContentView(binding.root)


        binding.btnRegisterCafe.setOnClickListener {
            val tipo = binding.tilTipoCafe.editText?.text.toString()
            val origen = binding.tilOrigenCafe.editText?.text.toString()
            val precioStr = binding.tilPrecioCafe.editText?.text.toString()
            val cantidadStr = binding.tilCantidadCafe.editText?.text.toString()
            val foto = binding.tilFotoCafe.editText?.text.toString()
            val isFavorite = binding.switchMaterial.isActivated

            if (tipo.isNotEmpty() && origen.isNotEmpty() && precioStr.isNotEmpty() && cantidadStr.isNotEmpty() && foto.isNotEmpty()) {
                val precio = precioStr.toDoubleOrNull() ?: 0.0 // Convierte el String a Double
                val cantidad = cantidadStr.toIntOrNull() ?: 0 // Convierte el String a Int

                // Ahora precio y cantidad son Double e Int respectivamente,
                // y puedes usarlos en addToFirestore
                addToFirestore(tipo, origen, precio, cantidad, foto, isFavorite)
            }
        }

    }

    private fun addToFirestore(tipo: String, origen: String,precio: Double,cantidad: Int,foto: String, favorite: Boolean) {

        val newCafe = hashMapOf<String, Any>(
            "tipo" to tipo,
            "origen" to origen,
            "precio" to precio,
            "cantidad" to cantidad,
            "foto" to foto,
            "isFavorite" to favorite,


        )
        firestore.collection("cafe").add(newCafe)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Café agregado con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agregar café: $e", Toast.LENGTH_SHORT).show()
            }

    }


    private fun getCafesFromFirestore(){
        firestore.collection("cafe").whereEqualTo("isFavorite",true)
            .get().addOnSuccessListener {
                for (document in it.documents){
                    Log.d("Cafes Firebase", document.id)
                }
            }
    }


    private fun takePicture() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        openCameraLouncher.launch(intent)
    }

    private fun permissionValidated(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA)
        }

        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 100)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(
        request: Int,
        permission: Array<out String>,
        grantResult: IntArray
    ) {
        super.onRequestPermissionsResult(request, permission, grantResult)
        when (request) {
            100 -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePicture()
                }
            }
        }
    }
}

*/

package com.example.ec_final2.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ec_final2.databinding.ActivityAddCafeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddCafeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCafeBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCafeBinding.inflate(layoutInflater)
        firestore = Firebase.firestore
        setContentView(binding.root)

        // Configurar el lanzador de la cámara
        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap?
                // Haz algo con la imagen si la necesitas
            }
        }

        binding.btnRegisterCafe.setOnClickListener {
            val tipo = binding.tilTipoCafe.editText?.text.toString()
            val origen = binding.tilOrigenCafe.editText?.text.toString()
            val precioStr = binding.tilPrecioCafe.editText?.text.toString()
            val cantidadStr = binding.tilCantidadCafe.editText?.text.toString()
            val foto = binding.tilFotoCafe.editText?.text.toString()
            val isFavorite = binding.switchMaterial.isChecked // Usar isChecked para obtener el estado

            if (tipo.isNotEmpty() && origen.isNotEmpty() && precioStr.isNotEmpty() && cantidadStr.isNotEmpty() && foto.isNotEmpty()) {
                val precio = precioStr.toDoubleOrNull() ?: 0.0 // Convierte el String a Double
                val cantidad = cantidadStr.toIntOrNull() ?: 0 // Convierte el String a Int

                // Ahora precio y cantidad son Double e Int respectivamente,
                // y puedes usarlos en addToFirestore
                addToFirestore(tipo, origen, precio, cantidad, foto, isFavorite)
            }
        }
    }

    private fun addToFirestore(tipo: String, origen: String, precio: Double, cantidad: Int, foto: String, favorite: Boolean) {

        val newCafe = hashMapOf(
            "tipo" to tipo,
            "origen" to origen,
            "precio" to precio,
            "cantidad" to cantidad,
            "foto" to foto,
            "isFavorite" to favorite,
        )

        firestore.collection("cafe").add(newCafe)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Café agregado con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agregar café: $e", Toast.LENGTH_SHORT).show()
            }
    }
}

