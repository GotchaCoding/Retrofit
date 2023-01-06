package org.techtown.retrofit2

import android.view.View

interface MovieClickListener {
    fun onClick(v: View, movie : Movie)
    // 온아이템클릭 메서드가 호출될때 파라미터로  뷰객체 그리고 뷰의 포지션 정보가 전달.
}