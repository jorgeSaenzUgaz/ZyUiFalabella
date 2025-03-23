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
import com.zy.zylibuiandroid.databinding.ViewFingerCardBinding
import com.zy.zylibuiandroid.databinding.ViewFingerIndicatorBinding

class FingerIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewFingerIndicatorBinding

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

    private val inclFirstFingerCard: ViewFingerCardBinding
    private val imvFirstIndicatorFinger: ImageView
    private val imvFirstCaptureFinger: ImageView
    private val txtFirstFingerDescription: TextView
    private val txtFirstStatusCapture: TextView
    private val crdFirstFinger: CardView

    private val inclSecondFingerCard: ViewFingerCardBinding
    private val imvSecondIndicatorFinger: ImageView
    private val imvSecondCaptureFinger: ImageView
    private val txtSecondFingerDescription: TextView
    private val txtSecondStatusCapture: TextView
    private val crdSecondFinger: CardView

    init {
        binding = ViewFingerIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

        llContent = binding.llContent
        // Referencia a los elementos
        inclFirstFingerCard = binding.inclFirstFingerCard
        crdFirstFinger = inclFirstFingerCard.crdFinger
        txtFirstFingerDescription = inclFirstFingerCard.txtFingerDescription
        txtFirstStatusCapture = inclFirstFingerCard.txtStatusCapture
        imvFirstIndicatorFinger = inclFirstFingerCard.imvIndicatorFinger
        imvFirstCaptureFinger = inclFirstFingerCard.imvCaptureFinger

        inclSecondFingerCard = binding.inclSecondFingerCard
        crdSecondFinger = inclSecondFingerCard.crdFinger
        txtSecondFingerDescription = inclSecondFingerCard.txtFingerDescription
        txtSecondStatusCapture = inclSecondFingerCard.txtStatusCapture
        imvSecondIndicatorFinger = inclSecondFingerCard.imvIndicatorFinger
        imvSecondCaptureFinger = inclSecondFingerCard.imvCaptureFinger

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

    private fun Context.dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
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

    private fun setQualityFinger(txtStatusCapture: TextView, piQualityFinger: Int) {
        when(piQualityFinger){
            in 1..50 -> txtStatusCapture.text = "Muy Mala"
            in 51..100 -> txtStatusCapture.text = "Mala"
            in 101..120 -> txtStatusCapture.text = "Regular"
            in 121..140 -> txtStatusCapture.text = "Buena"
            in 141..160 -> txtStatusCapture.text = "Muy Buena"
        }
    }


    /**
     * Inputs
     */

    //First Finger
    fun setFirstFingerText(text: String) {
        txtFirstFingerDescription.text = text
    }

    fun setFirstFingerImage(codFinger: String) {
        if (codFinger.toInt() <= 10) {
            crdFirstFinger.visibility = View.VISIBLE
            imvFirstCaptureFinger.visibility = View.GONE
            txtFirstStatusCapture.visibility = View.GONE
            showImageFinger(codFinger, imvFirstIndicatorFinger)

            evaluateWidthCards()
        }
    }

    fun setFirstFingerCaptureImageBitmap(bmHuellaCapturada: Bitmap? = null, piQualityFinger: Int) {
        bmHuellaCapturada?.let {
            imvFirstIndicatorFinger.visibility = View.GONE

            imvFirstCaptureFinger.visibility = View.VISIBLE
            imvFirstCaptureFinger.setImageBitmap(it)

            txtFirstStatusCapture.visibility = View.VISIBLE
            setQualityFinger(txtFirstStatusCapture, piQualityFinger)
        }
    }

    //Second Finger
    fun setSecondFingerText(text: String) {
        txtSecondFingerDescription.text = text
    }

    fun setSecondFingerImage(codFinger: String) {
        if (codFinger.toInt() <= 10) {
            crdSecondFinger.visibility = View.VISIBLE
            imvSecondCaptureFinger.visibility = View.GONE
            txtSecondStatusCapture.visibility = View.GONE
            showImageFinger(codFinger, imvSecondIndicatorFinger)

            evaluateWidthCards()
        }
    }

    fun setSecondFingerCaptureImageBitmap(bmHuellaCapturada: Bitmap? = null, piQualityFinger: Int) {
        bmHuellaCapturada?.let {
            imvSecondIndicatorFinger.visibility = View.GONE

            imvSecondCaptureFinger.visibility = View.VISIBLE
            imvSecondCaptureFinger.setImageBitmap(it)

            txtSecondStatusCapture.visibility = View.VISIBLE
            setQualityFinger(txtSecondStatusCapture, piQualityFinger)
        }
    }
}