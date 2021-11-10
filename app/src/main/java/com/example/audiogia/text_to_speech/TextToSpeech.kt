package com.example.audiogia.text_to_speech

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.audiogia.monument_info.MonumentInfo
import java.util.*

class TextToSpeech : TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null
    private var text: String? = null


    fun speakAux(text:String, context: Context){
        tts = TextToSpeech(context, this)
        this.text = text
        speakOut(text)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            // Carga idioma spañol
            val espLocale =  Locale ("spa","ESP")
            val result = tts!!.setLanguage(espLocale)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("=====> TTS", "The Language specified is not supported!")
            } else {
                text?.let { speakOut(it) }
            }
        } else {
            Log.e("=====> TTS", "Initialization Failed!")
        }
    }


    private fun speakOut(text: String) {
        Log.d("=====>", " SpeakOut")

       // var message = "hola amigos desde textoespeech speak out 28"//edittext_input.text.toString()
        //if (text.isNullOrBlank()) text = "Please enter a message"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("=====>", " SpeakOut -> IF")
            Log.d("====>", tts.toString() + " String tts")
        //    Log.d("====>", "$message -> String Mensaje")
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        } else {
            @Suppress("DEPRECATION")
            Log.d("=====>", " SpeakOut -> ELSE")
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    /*override fun onDestroy() {
        // Shut down TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }*/
}