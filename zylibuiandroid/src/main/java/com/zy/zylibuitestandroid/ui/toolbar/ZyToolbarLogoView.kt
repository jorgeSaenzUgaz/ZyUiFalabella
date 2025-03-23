package com.zy.zylibuitestandroid.ui.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R
import com.zy.zylibuiandroid.databinding.ViewZyToolbarBinding

class ZyToolbarLogoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, private val defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewZyToolbarBinding = ViewZyToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    private var pdLogo: Drawable? = ContextCompat.getDrawable(context, R.drawable.zy_logo_toolbar)
        set(value) {
            field = value
            setLogo()
        }

    init {
        setupAttrs(context, attrs)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.ZyToolbarLogoView, defStyleAttr, 0)
        try {
            pdLogo = ta.getDrawable(R.styleable.ZyToolbarLogoView_logo)
        } finally {
            ta.recycle()
        }
    }

    private fun setLogo() {
        binding.imvLogoToolbar.setImageDrawable(pdLogo)
    }
}