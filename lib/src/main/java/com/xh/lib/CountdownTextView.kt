package com.xh.lib

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import com.xh.lib.util.getColor
import com.xh.lib.util.getString
import com.xh.lib.util.isEmail
import com.xh.lib.util.isMobile

class CountdownTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    /**
     * 正常状态下的文字颜色
     */
    private var normalColor: Int = 0

    /**
     * 倒计时状态下的文字颜色
     */
    private var countdownColor: Int = 0

    /**
     * 总的时间
     */
    private var countTime: Int = 0

    /**
     * 倒计时回调时间间隔
     */
    private var interval: Int = 0

    /**
     * 首次正常状态下显示的文本
     */
    private var normalText: String = ""

    /**
     * 倒计时一次后正常状态下显示的文本
     */
    private var normalTextForAgain = ""

    /**
     * 倒计时显示文本的格式化模版
     */
    private var countdownTextFormat = ""

    /**
     * 关联的EditText
     */
    private var bindEt: EditText? = null

    /**
     * 是否正在倒计时
     */
    private var isTicking: Boolean = false

    /**
     * 计时器
     */
    private var countDownTimer: CountDownTimer? = null

    private fun init(context: Context, attrs: AttributeSet?) {

        // 默认属性
        val defaultNormalColor = getColor(context, R.color.countdownTextView_normal_color)
        val defaultCountdownColor = getColor(context, R.color.countdownTextView_countdown_color)
        val defaultNormalText = getString(context, R.string.countdownTextView_normal_text)
        val defaultNormalTextForAgain =
            getString(context, R.string.countdownTextView_normal_text_for_again)
        val defaultCountdownTextFormat =
            getString(context, R.string.countdownTextView_countdown_text_format)
        val defaultCountTime = 60
        val defaultInterval = 1

        context.obtainStyledAttributes(attrs, R.styleable.CountdownTextView).apply {
            normalColor = getColor(R.styleable.CountdownTextView_normalColor, defaultNormalColor)
            countdownColor =
                getColor(R.styleable.CountdownTextView_countdownColor, defaultCountdownColor)
            countTime = getInt(R.styleable.CountdownTextView_countTime, defaultCountTime)
            interval = getInt(R.styleable.CountdownTextView_interval, defaultInterval)
            normalText = getString(R.styleable.CountdownTextView_normalText) ?: defaultNormalText
            normalTextForAgain = getString(R.styleable.CountdownTextView_normalTextForAgain)
                ?: defaultNormalTextForAgain
            countdownTextFormat = getString(R.styleable.CountdownTextView_countdownTextFormat)
                ?: defaultCountdownTextFormat
        }.recycle()

        text = normalText
        isEnabled = true
    }

    /**
     * 绑定EditText
     */
    fun bindEt(et: EditText) {
        et.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            if (!isTicking) {
                isEnabled = text?.let { isMobile(it) || isEmail(it) } ?: false
            }
        })
        this.bindEt = et
        isEnabled = isMobile(et.text) || isEmail(et.text)
    }

    /**
     * 开始倒计时
     */
    fun startCountdown() {
        countDownTimer?.cancel()

        //总时间加上100毫秒是为了补偿时间，不然会直接从低一秒开始跳（例如定的60秒，则会从59秒开始倒计时）
        countDownTimer = object : CountDownTimer(countTime * 1000L + 100, interval * 1000L) {
            override fun onFinish() {
                post { toNormalState() }
            }

            override fun onTick(millisUntilFinished: Long) {
                post { text = String.format(countdownTextFormat, millisUntilFinished / 1000) }
            }
        }

        countDownTimer?.let {
            toCountdownState()
            it.start()
        }
    }

    /**
     * 切换到倒计时状态
     */
    private fun toCountdownState() {
        isTicking = true
        isEnabled = false
    }

    /**
     * 切换到正常状态
     */
    private fun toNormalState() {
        isTicking = false
        isEnabled = bindEt?.let { isMobile(it.text) || isEmail(it.text) } ?: true
        text = normalTextForAgain
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            setTextColor(normalColor)
        } else {
            setTextColor(countdownColor)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        countDownTimer?.cancel()
    }
}