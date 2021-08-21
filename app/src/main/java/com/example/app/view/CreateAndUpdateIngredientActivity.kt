package com.example.app.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.R
import com.example.app.databinding.ActivityCreateAndUpdateIngredientBinding
import com.example.app.service.constants.IngredientConstants
import com.example.app.service.constants.RequestCodeConstants
import com.example.app.viewmodel.CreateAndUpdateIngredientViewModel
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.*
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.buttonSave
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.editTextBrand
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.editTextName
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.editTextPrice
import kotlinx.android.synthetic.main.activity_create_and_update_ingredient.editTextQuantity
import java.io.ByteArrayOutputStream


/** classe - GuestFormActivity - no curso
 * Aulas: 99-149-163-164-214 */
class CreateAndUpdateIngredientActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCreateAndUpdateIngredientBinding
    private lateinit var mViewModel: CreateAndUpdateIngredientViewModel
    private var mIngredientId: Int = 0

    private var photo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        binding = ActivityCreateAndUpdateIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(CreateAndUpdateIngredientViewModel::class.java)

        setListeners()
        observe()
        loadData()
    }

    override fun onClick(v: View) {
        val id = v.id
        // when - switch/case
        when (id) {
            R.id.buttonBack -> {
                finish()
            }

            R.id.buttonCamera -> {
                //selectPhoto.capturePhotoCamera(this)
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityForResult(takePictureIntent, RequestCodeConstants.requestCodeCamera)
                    }
                }
            }

            R.id.buttonGallery -> {
                //selectPhoto.selectPhotoGallery(this)
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityForResult(takePictureIntent, RequestCodeConstants.requestCodeGallery)
                    }
                }
            }

            R.id.buttonSave -> {
                if(validation()) {
                    val name = binding.editTextName.text.toString().trimStart().trimEnd()
                    val brand = binding.editTextBrand.text.toString().trimStart().trimEnd()
                    val price = binding.editTextPrice.text.toString().trimStart().trimEnd()
                    val quantity = binding.editTextQuantity.text.toString().trimStart().trimEnd()
                    val unitMeasure = binding.spinnerUnitMeasure.selectedItem.toString().trimStart().trimEnd()
                    mViewModel.save(mIngredientId, name, brand, price, quantity, unitMeasure, photo)
                }else{
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validation(): Boolean {
        return (!editTextName.text.toString().trim().isEmpty()
                && !editTextBrand.text.toString().trim().isEmpty()
                && !editTextPrice.text.toString().trim().isEmpty()
                && !editTextQuantity.text.toString().trim().isEmpty())
    }

    private fun setListeners(){
        buttonBack.setOnClickListener(this)
        buttonCamera.setOnClickListener(this)
        buttonGallery.setOnClickListener(this)
        buttonSave.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveIngredient.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.ingredient.observe(this, Observer {
            textViewingredient.text = it.name
            editTextName.setText(it.name)
            editTextBrand.setText(it.brand)
            editTextPrice.setText(it.price)
            editTextQuantity.setText(it.quantity)

            if(!it.photo.equals("")) {
                var photoInBytes: ByteArray
                photoInBytes = Base64.decode(it.photo, Base64.DEFAULT)
                var photoDecodedInBitmap: Bitmap = BitmapFactory.decodeByteArray(photoInBytes, 0, photoInBytes.size)
                circleImageViewIngredient.setImageBitmap(photoDecodedInBitmap)
                photo = it.photo
            }

            val arrayUnitMeasure = mutableListOf<String>()
            arrayUnitMeasure.addAll(resources.getStringArray(R.array.unitOfMeasurement))
            if (arrayUnitMeasure.contains(it.unitMeasure)){
                arrayUnitMeasure.remove(it.unitMeasure)
                arrayUnitMeasure.add(0, it.unitMeasure)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayUnitMeasure)
            spinnerUnitMeasure.adapter = adapter
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mIngredientId = bundle.getInt(IngredientConstants.INGREDIENTID)
            mViewModel.load(mIngredientId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK) {
            lateinit var photoSelect: Bitmap
            when(requestCode) {
                RequestCodeConstants.requestCodeCamera -> {
                    photoSelect = data?.extras?.get("data") as Bitmap
                }

                RequestCodeConstants.requestCodeGallery -> {
                    var photoSelectUri = data?.data
                    photoSelect = MediaStore.Images.Media.getBitmap(contentResolver, photoSelectUri)
                }
            }

            if (photoSelect != null) {
                circleImageViewIngredient.setImageBitmap(photoSelect)
                val photoInBytes: ByteArray
                val streamPhotoInBytes = ByteArrayOutputStream()
                photoSelect.compress(Bitmap.CompressFormat.PNG, 70, streamPhotoInBytes)
                photoInBytes = streamPhotoInBytes.toByteArray()
                photo = Base64.encodeToString(photoInBytes, Base64.DEFAULT)
            }
        }
    }


}
