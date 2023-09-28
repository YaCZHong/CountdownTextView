# 可倒计时的 TextView

**_应用场景：类似于获取短信/邮箱验证码_**

### 演示视频

https://github.com/lrCzh/CountdownTextView/assets/41668961/e74a40fb-65fc-4d04-95f9-69317eccb529

## 使用

### 1、引入

```
allprojects {
	repositories {
	    ...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.lrCzh:CountdownTextView:1.0.1'
}
```

### 2、简单的布局，一个输入手机号/邮箱的 EditText 和我们用于显示倒计时的 CountdownTextView

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/et"
        android:layout_width="0dp"
        android:layout_height="48dp"
        android:hint="请输入手机号码或者邮箱"
        app:layout_constraintEnd_toStartOf="@id/cdtv"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.xh.lib.CountdownTextView
        android:id="@+id/cdtv"
        android:layout_width="108dp"
        android:layout_height="48dp"
        android:gravity="center"
        app:countTime="10"
        app:countdownColor="#B6B6B6"
        app:countdownTextFormat="倒计时%ss"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/et"
        app:layout_constraintTop_toTopOf="parent"
        app:normalColor="#009688"
        app:normalText="获取验证码"
        app:normalTextForAgain="重新获取验证码" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

### 3、在 Activity 当中将两者关联起来

```
class BindEtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBindEtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindEtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            // 关联EditText
            cdtv.bindEt(et)

            cdtv.setOnClickListener {
                // 在这里请求接口，接口请求成功后调用startCountdown()开始倒计时，这里为了演示，就直接调用了
                cdtv.startCountdown()
            }
        }
    }
}
```

## 完。
