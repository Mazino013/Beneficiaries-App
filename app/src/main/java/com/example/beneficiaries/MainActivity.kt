package com.example.beneficiaries

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.beneficiaries.ui.theme.beneficiarylist.BeneficiaryListFragment
import com.example.beneficiaries.util.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*FrameLayout serves as the container for fragments.
        & container is given a unique ID to manage fragment transactions*/
        val container = FrameLayout(this).apply {
            id = R.id.fragment_container // Use a fixed ID from resources
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(container.id, BeneficiaryListFragment(), Constants.TAG_BENEFICIARY_LIST)
                .commit()
        }
    }
}