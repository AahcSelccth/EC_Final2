/*package com.example.ec_final2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ec_final2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabAddCafe.setOnClickListener{
            val intent = Intent(this, AddCafeActivity::class.java)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fcvCafe.id) as NavHostFragment
        val navControler = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navControler)
    }

}
*/

package com.example.ec_final2.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ec_final2.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el NavController
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fcvCafe.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

        // Configurar el botón "Agregar Café"
        binding.fabAddCafe.setOnClickListener {
            val intent = Intent(this, AddCafeActivity::class.java)
            startActivity(intent)
        }

        // Configurar el botón "Salir"
        binding.fabSalir.setOnClickListener {
            // Cerrar sesión en Firebase
            FirebaseAuth.getInstance().signOut()

            // Redirigir al usuario a la pantalla de inicio de sesión (reemplaza LoginActivity::class.java)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual para evitar que el usuario vuelva atrás

            // Mostrar mensaje de cierre de sesión exitoso
            Toast.makeText(this, "Cerró sesión correctamente", Toast.LENGTH_SHORT).show()

        }
    }
}
