package com.zy.zylibuitestandroid.ui.finger

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R
import com.zy.zylibuiandroid.databinding.ViewZyFingerCardBinding
import com.zy.zylibuiandroid.databinding.ViewZyFingerIndicatorBinding
import com.zy.zylibuitestandroid.ui.bars.ZyIndicatorBarView
import com.zy.zylibuitestandroid.ui.utils.Extensions.dpToPx

class ZyFingerIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewZyFingerIndicatorBinding

    //Card Elevación
    private var pfCardElevation: Float = 0f
        set(value) {
            field = context.dpToPx(value)
            setCardElevation()
        }

    //Esquinas redondeadas
    private var pfCardRadius: Float = 0f
        set(value) {
            field = context.dpToPx(value)
            setCardRadius()
        }

    //Color de texto
    private var piColorTextFingerIndicator: Int = 0
        set(value) {
            field = value
            setColorTextFingerIndicator()
        }

    private val llContent: LinearLayout

    private val inclFirstFingerCard: ViewZyFingerCardBinding
    private val imvFirstIndicatorFinger: ImageView
    private val imvFirstCaptureFinger: ImageView
    private val txtFirstFingerDescription: TextView
    private val txtFirstStatusCapture: TextView
    private val crdFirstFinger: CardView
    private val viewFirstIndicatorBar: ZyIndicatorBarView

    private val inclSecondFingerCard: ViewZyFingerCardBinding
    private val imvSecondIndicatorFinger: ImageView
    private val imvSecondCaptureFinger: ImageView
    private val txtSecondFingerDescription: TextView
    private val txtSecondStatusCapture: TextView
    private val crdSecondFinger: CardView
    private val viewSecondIndicatorBar: ZyIndicatorBarView

    init {
        binding = ViewZyFingerIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

        llContent = binding.llContent
        // Referencia a los elementos
        inclFirstFingerCard = binding.inclFirstFingerCard
        crdFirstFinger = inclFirstFingerCard.crdFinger
        txtFirstFingerDescription = inclFirstFingerCard.txtFingerDescription
        txtFirstStatusCapture = inclFirstFingerCard.txtStatusCapture
        imvFirstIndicatorFinger = inclFirstFingerCard.imvIndicatorFinger
        imvFirstCaptureFinger = inclFirstFingerCard.imvCaptureFinger
        viewFirstIndicatorBar = inclFirstFingerCard.viewIndicatorBar

        inclSecondFingerCard = binding.inclSecondFingerCard
        crdSecondFinger = inclSecondFingerCard.crdFinger
        txtSecondFingerDescription = inclSecondFingerCard.txtFingerDescription
        txtSecondStatusCapture = inclSecondFingerCard.txtStatusCapture
        imvSecondIndicatorFinger = inclSecondFingerCard.imvIndicatorFinger
        imvSecondCaptureFinger = inclSecondFingerCard.imvCaptureFinger
        viewSecondIndicatorBar = inclSecondFingerCard.viewIndicatorBar

        setupAttrs(context, attrs)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.FingerIndicatorView, defStyleAttr, 0)
        try {
            pfCardElevation = ta.getFloat(R.styleable.FingerIndicatorView_card_elevation, 5f)
            pfCardRadius = ta.getFloat(R.styleable.FingerIndicatorView_card_radius, 5f)
            piColorTextFingerIndicator = ta.getInt(
                R.styleable.FingerIndicatorView_color_text_finger_indicator,
                ContextCompat.getColor(context, R.color.zyTextColorFingerIndicator)
            )
        } finally {
            ta.recycle()
        }
    }

    private fun setCardElevation() {
        crdFirstFinger.cardElevation = pfCardElevation
        crdSecondFinger.cardElevation = pfCardElevation
    }

    private fun setCardRadius() {
        crdFirstFinger.radius = pfCardRadius
        crdSecondFinger.radius = pfCardRadius
    }

    private fun setColorTextFingerIndicator() {
        txtFirstFingerDescription.setTextColor(piColorTextFingerIndicator)
        txtFirstStatusCapture.setTextColor(piColorTextFingerIndicator)

        txtSecondFingerDescription.setTextColor(piColorTextFingerIndicator)
        txtSecondStatusCapture.setTextColor(piColorTextFingerIndicator)
    }

    private fun showImageFinger(dedo: String, imgMano: ImageView) {
        when (dedo.toIntOrNull()) {
            1 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            2 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            3 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            4 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            5 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            6 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            7 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            8 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            9 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
            10 -> imgMano.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.d1))
        }
    }

    private fun evaluateWidthCards() {
        val layoutParamsFirst = crdFirstFinger.layoutParams as LinearLayout.LayoutParams
        val layoutParamsSecond = crdSecondFinger.layoutParams as LinearLayout.LayoutParams

        if (crdFirstFinger.visibility == View.VISIBLE && crdSecondFinger.visibility == View.VISIBLE) {
            // Ambos visibles: cada uno ocupa la mitad del espacio
            layoutParamsFirst.width = 0
            layoutParamsFirst.weight = 1f

            layoutParamsSecond.width = 0
            layoutParamsSecond.weight = 1f

            binding.llContent.weightSum = 2f
            binding.llContent.gravity = android.view.Gravity.NO_GRAVITY // Sin centrado
        } else if (crdFirstFinger.visibility == View.VISIBLE) {
            // Solo el primero visible: Centrado y con ancho fijo de 160dp
            layoutParamsFirst.width = context.dpToPx(160f).toInt()
            layoutParamsFirst.weight = 0f

            crdSecondFinger.visibility = View.GONE

            binding.llContent.weightSum = 1f
            binding.llContent.gravity = android.view.Gravity.CENTER_HORIZONTAL // Centrado
        } else if (crdSecondFinger.visibility == View.VISIBLE) {
            // Solo el segundo visible: Centrado y con ancho fijo de 160dp
            layoutParamsSecond.width = context.dpToPx(160f).toInt()
            layoutParamsSecond.weight = 0f

            crdFirstFinger.visibility = View.GONE

            binding.llContent.weightSum = 1f
            binding.llContent.gravity = android.view.Gravity.CENTER_HORIZONTAL // Centrado
        }

        crdFirstFinger.layoutParams = layoutParamsFirst
        crdSecondFinger.layoutParams = layoutParamsSecond
    }

    private fun setQualityFinger(
        txtStatusCapture: TextView,
        piQualityFinger: Int,
        viewSecondIndicatorBar: ZyIndicatorBarView
    ) {
        when(piQualityFinger){
            in 1..50 -> {
                txtStatusCapture.text = ContextCompat.getString(context, R.string.very_bad)
                viewSecondIndicatorBar.setIndicator(1)
            }
            in 51..100 -> {
                txtStatusCapture.text = ContextCompat.getString(context, R.string.bad)
                viewSecondIndicatorBar.setIndicator(2)
            }
            in 101..120 -> {
                txtStatusCapture.text = ContextCompat.getString(context, R.string.regular)
                viewSecondIndicatorBar.setIndicator(3)
            }
            in 121..140 -> {
                txtStatusCapture.text = ContextCompat.getString(context, R.string.good)
                viewSecondIndicatorBar.setIndicator(4)
            }
            in 141..160 -> {
                txtStatusCapture.text = ContextCompat.getString(context, R.string.very_good)
                viewSecondIndicatorBar.setIndicator(5)
            }
        }
    }


    /**
     * Inputs
     */

    //First Finger
    /**
     * Permite cambiar el texto que indica cuál es el dedo que se utilizará
     * @param text: Texto que se mostrará en el indicador.
     */
    fun setFirstFingerText(text: String) {
        txtFirstFingerDescription.text = text
    }

    /**
     * Permite cambiar la imagen indicando el dedo que se capturará, para ello se deberá de indicar el código del dedo
     * @param codFinger: Código del dedo que va desde 1 al 10
     */
    fun setFirstFingerImage(codFinger: String) {
        if (codFinger.toInt() <= 10) {
            crdFirstFinger.visibility = View.VISIBLE
            imvFirstCaptureFinger.visibility = View.GONE
            txtFirstStatusCapture.visibility = View.GONE
            showImageFinger(codFinger, imvFirstIndicatorFinger)

            evaluateWidthCards()
        }
    }

    /**
     * Permite cambiar la imagen del dedo que ha sido capturada por el lector
     * @param bmHuellaCapturada: Imagen de la huella capturada
     */
    fun setFirstFingerCaptureImageBitmap(bmHuellaCapturada: Bitmap? = null, piQualityFinger: Int) {
        bmHuellaCapturada?.let {
            imvFirstIndicatorFinger.visibility = View.GONE

            imvFirstCaptureFinger.visibility = View.VISIBLE
            imvFirstCaptureFinger.setImageBitmap(it)

            txtFirstStatusCapture.visibility = View.VISIBLE
            viewFirstIndicatorBar.visibility = View.VISIBLE
            setQualityFinger(txtFirstStatusCapture, piQualityFinger, viewFirstIndicatorBar)
        }
    }

    //Second Finger
    /**
     * Permite cambiar el texto que indica cuál es el dedo que se utilizará
     * @param text: Texto que se mostrará en el indicador.
     */
    fun setSecondFingerText(text: String) {
        txtSecondFingerDescription.text = text
    }

    /**
     * Permite cambiar la imagen indicando el dedo que se capturará, para ello se deberá de indicar el código del dedo
     * @param codFinger: Código del dedo que va desde 1 al 10
     */
    fun setSecondFingerImage(codFinger: String) {
        if (codFinger.toInt() <= 10) {
            crdSecondFinger.visibility = View.VISIBLE
            imvSecondCaptureFinger.visibility = View.GONE
            txtSecondStatusCapture.visibility = View.GONE
            showImageFinger(codFinger, imvSecondIndicatorFinger)

            evaluateWidthCards()
        }
    }

    /**
     * Permite cambiar la imagen del dedo que ha sido capturada por el lector
     * @param bmHuellaCapturada: Imagen de la huella capturada
     */
    fun setSecondFingerCaptureImageBitmap(bmHuellaCapturada: Bitmap? = null, piQualityFinger: Int) {
        bmHuellaCapturada?.let {
            imvSecondIndicatorFinger.visibility = View.GONE

            imvSecondCaptureFinger.visibility = View.VISIBLE
            imvSecondCaptureFinger.setImageBitmap(it)

            txtSecondStatusCapture.visibility = View.VISIBLE
            viewSecondIndicatorBar.visibility = View.VISIBLE
            setQualityFinger(txtSecondStatusCapture, piQualityFinger, viewSecondIndicatorBar)
        }
    }
}