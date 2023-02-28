package dev.abdujabbor.translatemlkit

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.MALAY)
            .setTargetLanguage(TranslateLanguage.KOREAN)
            .build()

        val englishHindiTranslator = Translation.getClient(options)
        englishHindiTranslator.downloadModelIfNeeded()
            .addOnSuccessListener {
                Log.e(ContentValues.TAG, "Download Successful")
                itemViewBinding.progressbar.visibility = View.GONE
                itemViewBinding.tvTarjima.visibility = View.VISIBLE

                //Translates the given input from the source language into the target language.
                englishHindiTranslator.translate(list.id)
                    .addOnSuccessListener {
                        //Translation successful
                        itemViewBinding.tvTarjima.setText(it)
                    }
                    .addOnFailureListener {
                        //Error
                        Log.e(ContentValues.TAG, "Error: " + it.localizedMessage)
                    }
            }
            .addOnFailureListener {
                Log.e(ContentValues.TAG, "Download Error: " + it.localizedMessage)
                itemViewBinding.progressbar.visibility = View.GONE
                itemViewBinding.tvTarjima.visibility = View.VISIBLE
            }
    }
}