package ru.sem.teplovision.ui.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*
import ru.sem.teplovision.App
import ru.sem.teplovision.R
import ru.sem.teplovision.di.modules.ViewModelFactory
import ru.sem.teplovision.ui.base.BaseTakePhotoFragment
import ru.sem.teplovision.ui.photo.PhotoViewActivity
import java.io.File
import java.io.IOException
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseTakePhotoFragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainViewModel
    private val SEL_MAIN = 1
    private val SEL_GALLERY = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnScan.setOnClickListener{
            choosePhoto(SEL_MAIN)
        }
        btnRate.setOnClickListener{
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ru.ok.android&hl=ru"))
            startActivity(browserIntent)
        }
    }

    override fun onPhotoSelect(photoFileName: String?, selectId: Int) {
        Log.d(TAG, "onPhotoSelect: $photoFileName")
        if(photoFileName==null){
            showError("Ошибка дотупа к файлу $photoFileName", false)
            return
        }
        when(selectId){
            SEL_MAIN -> viewModel.setMainImage(photoFileName)
            //SEL_GALLERY -> viewModel.addToGallery(photoFileName)
        }
    }

    override fun cameraIntentFileName(): File? {
        val storageDir: File? = requireContext()
            .getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image: File? = null
        try {
            image = File.createTempFile(
                "cameraP",  /* prefix */
                ".jpg",  /* suffix */
                storageDir /* directory */
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    override fun getAppPictureDir(): File? {
        return requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            MainViewModel::class.java
        )
        viewModel.imgMainData.observe(viewLifecycleOwner, Observer {
            val intent = Intent(requireContext(), PhotoViewActivity::class.java)
            intent.putExtra(App.EXTRA_IMG, it.fileName)
            startActivityForResult(intent, App.REQ_PHOTO)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==RESULT_OK){
            when(requestCode){
                App.REQ_PHOTO ->choosePhoto(SEL_MAIN)
            }
        }
    }
}
